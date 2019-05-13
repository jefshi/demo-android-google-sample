package com.csp.sample.room.mvvm;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.csp.sample.room.database.Word;
import com.csp.sample.room.database.WordDao;
import com.csp.sample.room.database.WordRoomDatabase;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    public void delete(Word word) {
        new deleteAsyncTask(mWordDao).execute(word);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mWordDao).execute();
    }

    public void getAll() {
        new getAllAsyncTask(mWordDao).execute();
    }

    public void getWords(String contained) {
        new getWordsAsyncTask(mWordDao).execute(contained);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        deleteAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        deleteAllAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class getAllAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        getAllAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            List<Word> mAll = mAsyncTaskDao.getAll();
            for (Word word : mAll) {
                Log.e("TAG", word.toString());
            }
            return null;
        }
    }

    private static class getWordsAsyncTask extends AsyncTask<String, Void, Void> {

        private WordDao mAsyncTaskDao;

        getWordsAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            List<Word> mAll = mAsyncTaskDao.getWords(params[0]);
            for (Word word : mAll) {
                Log.e("TAG", word.toString());
            }
            return null;
        }
    }
}
