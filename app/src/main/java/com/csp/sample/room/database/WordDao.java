package com.csp.sample.room.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertOrReplace(Word word);

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    // the ViewModel only exposes immutable LiveData objects to the observers
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

//    // the developer want to update the stored data, you must use MutableLiveData instead of LiveData
//    @Query("SELECT * from word_table ORDER BY word ASC")
//    MutableLiveData<List<Word>> getAllWords();
}