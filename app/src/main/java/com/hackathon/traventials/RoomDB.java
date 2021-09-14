package com.hackathon.traventials;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add database entities
@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    //Create database instance
    private static RoomDB database;
    //Define database name
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context){
        //CHECK CONDITION
        if(database == null){
            //WHEN DATABASE IS NULL
            //INITIALIZE DB
            database = Room.databaseBuilder(context.getApplicationContext()
            ,RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //RETURN DATABASE
        return database;
    }
    //Create DAO

    public abstract MainDao mainDao();
}
