package com.csp.sample.room.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertOrReplace(Word word);

    @Insert
    void insert(User word);

    @Query("SELECT * from user_table")
    List<User> getAll();

    @Query("SELECT word from user_table LEFT JOIN word_table ON user_table.name = word_table.name")
    List<String> getWord();
}