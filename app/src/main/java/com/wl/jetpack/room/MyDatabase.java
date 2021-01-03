package com.wl.jetpack.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//在这指定版本号
//是否导出schema文件，默认是导出的
@Database(entities = {Emperor.class}, version = 3, exportSchema = true)
public abstract class MyDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "emperor_db";

    private static MyDatabase databaseInstance;

    public static synchronized MyDatabase getDatabaseInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    //为了防止数据库升级失败导致崩溃，加入该方法可以在出现异常时创建数据表而不崩溃，但表中数据会丢失
                    .fallbackToDestructiveMigration()
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

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //创建临时表
            database.execSQL("CREATE TABLE tem_emperor (" + "id INTEGER PRIMARY KEY NOT NULL," +
                    "emperor_name TEXT," +
                    "age INTEGER," +
                    "gender TEXT)");
            //将数据导入临时表
            database.execSQL("INSERT INTO tem_emperor (id,emperor_name,age,gender) SELECT id,emperor_name,age,gender FROM emperor_table");
            //删除原表
            database.execSQL("DROP TABLE emperor_table");
            //临时表改成原表的名字
            database.execSQL("ALTER TABLE tem_emperor RENAME TO emperor_table");
        }
    };

}
