package com.example.android.todolist;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.android.todolist.database.TaskEntry;

public class AddNewTaskObservables extends BaseObservable {
    private ObservableField<String> taskName = new ObservableField<>();

    private ObservableInt radioButton = new ObservableInt(R.id.high);

    public AddNewTaskObservables() {

    }

    public ObservableInt getRadioButton() {
        return radioButton;
    }

    public ObservableField<String> getTaskName() {
        return taskName;
    }

    public void setValues(TaskEntry entry) {
        taskName.set(entry.getDescription());

        switch (entry.getPriority()) {
            case 1:
                radioButton.set(R.id.high);
                break;
            case 2:
                radioButton.set(R.id.med);
                break;
            case 3:
                radioButton.set(R.id.low);
                break;
        }
    }

    public int getPriority() {
        if (radioButton.get() == R.id.high) return 1;
        else if (radioButton.get() == R.id.med) return 2;
        else return 3;
    }
}
