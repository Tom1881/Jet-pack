package com.wl.jetpack.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmperorDao {

    //增
    @Insert
    public void insertEmperor(Emperor... emperor);


    //删
    @Delete
    public void deleteEmperor(Emperor... emperor);


    //改
    @Update
    public void updateEmperor(Emperor... emperor);


    //查符合指定条件的某一个
    @Query("SELECt * FROM emperor_table WHERE id=:id")
    public Emperor queryEmperorById(int id);

    //查所有符合条件的
    @Query("SELECt * FROM emperor_table WHERE age=:age")
    public List<Emperor> queryEmperorsByAge(String age);


    //查所有
    @Query("SELECt * FROM emperor_table")
    public List<Emperor> queryEmperors();


    //查所有，使用LiveData将结果List<Emperor>包装起来
    @Query("SELECt * FROM emperor_table")
    public LiveData<List<Emperor>> queryEmperorsByLiveData();
}
