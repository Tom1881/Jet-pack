package com.wl.jetpack.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Emperor.class}, version = 2)
public abstract class MyDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "emperor_db";

    private static MyDatabase databaseInstance;

    public static synchronized MyDatabase getDatabaseInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return databaseInstance;
    }


    public abstract EmperorDao getEmperorDao();


    //升级相关
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //在这里做升级操作，比如增加或者减少表中的字段
            database.execSQL("ALTER TABLE emperor_table ADD COLUMN gender TEXT");
        }
    };

}
