package com.wl.jetpack.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Emperor.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "emperor_db";

    private static MyDatabase databaseInstance;

    public static synchronized MyDatabase getDatabaseInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME).build();
        }
        return databaseInstance;
    }


    public abstract EmperorDao getEmperorDao();

}
