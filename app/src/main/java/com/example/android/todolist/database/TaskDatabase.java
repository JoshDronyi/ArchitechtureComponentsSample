package com.example.android.todolist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = TaskEntry.class,version = 1)
@TypeConverters(DateConverter.class)
public abstract class TaskDatabase extends RoomDatabase {
    private static final String LOG_TAG = TaskDatabase.class.getSimpleName();
    private static final  Object LOCK = new Object();
    private static final String DATABASE_NAME = "todolist";
    private static TaskDatabase sINSTANCE;

    public static TaskDatabase getInstance(Context context){
        if(sINSTANCE == null){
            synchronized (LOCK){
                sINSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(),
                                TaskDatabase.class, TaskDatabase.DATABASE_NAME)
                                .allowMainThreadQueries()
                                .build();
            }
        }
        return sINSTANCE;
    }

    public abstract TaskDAO taskDAO();

}
