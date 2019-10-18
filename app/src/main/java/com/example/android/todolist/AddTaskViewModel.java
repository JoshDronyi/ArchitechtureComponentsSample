package com.example.android.todolist;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.android.todolist.database.TaskDatabase;
import com.example.android.todolist.database.TaskEntry;

import java.util.Date;

public class AddTaskViewModel extends AndroidViewModel {
    private LiveData<TaskEntry> taskEntryLiveData;
    private AddNewTaskObservables observables = new AddNewTaskObservables();
    private TaskDatabase mDB;
    private final MutableLiveData<Boolean> dataSaved = new MutableLiveData<>();
    private ObservableField<String> buttonText = new ObservableField<>();
    private Context context;

    public AddTaskViewModel(@NonNull Application application) {
        super(application);
        mDB = TaskDatabase.getInstance(application.getApplicationContext());
        context = application.getApplicationContext();
        buttonText.set(context.getString(R.string.add_button));
    }


    void getTaskById(LifecycleOwner owner, int entryId) {

        taskEntryLiveData = mDB.taskDAO().getEntry(entryId);
        taskEntryLiveData.observe(owner, new Observer<TaskEntry>() {
            @Override
            public void onChanged(TaskEntry entry) {
                taskEntryLiveData.removeObserver(this);
                observables.setValues(entry);
            }
        });
        buttonText.set(context.getString(R.string.update_button));

    }

    MutableLiveData<Boolean> getDataSaved() {
        return dataSaved;
    }

    public void SaveData() {
        dataSaved.setValue(true);
        String description = observables.getTaskName().get();
        Date date = new Date();

        final TaskEntry entry = new TaskEntry(description, observables.getPriority(), date);

        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {

                if (taskEntryLiveData == null || taskEntryLiveData.getValue() == null) {
                    mDB.taskDAO().insert(entry);
                } else {
                    entry.setId(taskEntryLiveData.getValue().getId());
                    mDB.taskDAO().update(entry);
                }
                dataSaved.postValue(false);
            }
        });
    }

    public AddNewTaskObservables getObservables() {
        return observables;
    }

    public LiveData<TaskEntry> getTaskEntryLiveData() {
        return taskEntryLiveData;
    }
}
