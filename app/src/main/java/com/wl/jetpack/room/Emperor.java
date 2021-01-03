package com.wl.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "emperor_table")
public class Emperor {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    public int id;


    @ColumnInfo(name = "emperor_name", typeAffinity = ColumnInfo.TEXT)
    public String name;


    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    public Integer age;

    //增加一个性别的字段
    @ColumnInfo(name = "gender", typeAffinity = ColumnInfo.TEXT)
    public String gender;

    public Emperor(int id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Ignore
    public Emperor(int id, String name, Integer age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Ignore
    public Emperor(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Emperor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
