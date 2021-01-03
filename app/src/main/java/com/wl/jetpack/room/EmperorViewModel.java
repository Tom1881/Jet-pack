package com.wl.jetpack.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EmperorViewModel extends AndroidViewModel {

    private MyDatabase myDatabase;
    private LiveData<List<Emperor>> listEmperor;


    public EmperorViewModel(@NonNull Application application) {
        super(application);

        //初始化数据
        myDatabase = MyDatabase.getDatabaseInstance(application);
        listEmperor = myDatabase.getEmperorDao().queryEmperorsByLiveData();
    }

    public LiveData<List<Emperor>> getListEmperor() {
        return listEmperor;
    }
}
