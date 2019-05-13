package com.csp.sample.room.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertOrReplace(Word word);

    @Insert
    void insert(Word word);

    @Delete()
    void delete(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    // the ViewModel only exposes immutable LiveData objects to the observers
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * from word_table ORDER BY word ASC")
    List<Word> getAll();

    @Query("SELECT * from word_table WHERE word like :contain")
    List<Word> getWords(String contain);

//    // the developer want to update the stored data, you must use MutableLiveData instead of LiveData
//    @Query("SELECT * from word_table ORDER BY word ASC")
//    MutableLiveData<List<Word>> getAllWords();
}