package com.example.android.todolist;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.todolist.database.TaskDatabase;
import com.example.android.todolist.database.TaskEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<TaskEntry>> liveEntries;
    private String TAG = "mainViewModel";
    public MainViewModel(@NonNull Application application) {
        super(application);
        Log.e(TAG,"Retrieving from database");
        liveEntries = TaskDatabase.getInstance(application.getApplicationContext()).taskDAO().getAllEntries();
    }

    public LiveData<List<TaskEntry>> getLiveEntries(){
        return liveEntries;
    }

}
