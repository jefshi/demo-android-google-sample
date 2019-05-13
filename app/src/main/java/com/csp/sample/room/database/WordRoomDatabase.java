package com.csp.sample.room.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Arrays;
import java.util.List;

@Database(entities = {Word.class, User.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static volatile WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .addMigrations(MIGRATION_1_2)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WordDao wordDao();

    public abstract UserDao userDao();

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word_table "
                    + " ADD COLUMN name TEXT");

            database.execSQL("CREATE TABLE user_table ("
                    + "name TEXT NOT NULL, PRIMARY KEY(`name`))");

            Log.e("TAG", "1 -> 2");
        }
    };

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        private final UserDao mUserDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
            mUserDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Word word = new Word("Hello", "01");
            mDao.insert(word);
            word = new Word("World", "02");
            mDao.insert(word);
            word = new Word("AAA", "03");
            mDao.insert(word);
            word = new Word("BBB", "04");
            mDao.insert(word);

            mUserDao.insert(new User("01"));
            mUserDao.insert(new User("02"));

            List<User> all = mUserDao.getAll();
            Log.e("TAG", all.isEmpty() + "");

            List<String> words = mUserDao.getWord();
            Log.e("TAG", Arrays.toString(words.toArray()));

            return null;
        }
    }
}