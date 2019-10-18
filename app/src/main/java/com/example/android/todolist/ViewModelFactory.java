package com.example.android.todolist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.todolist.database.TaskDatabase;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private TaskDatabase mDB;
    private int entryID;

    public ViewModelFactory(int entryID,TaskDatabase mDB) {
        this.mDB = mDB;
        this.entryID = entryID;
    }

  /*  @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskViewModel(entryID, mDB);
    }*/
}
