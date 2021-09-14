package com.hackathon.traventials;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    //Insert Query
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    //DELETE QUERY
    @Delete
    void delete(MainData mainData);

    //Delete All Query
    @Delete
    void reset(List<MainData> mainData);

    //GET ALL
    @Query("SELECT * FROM table_name")
    List<MainData> getAll();
}
