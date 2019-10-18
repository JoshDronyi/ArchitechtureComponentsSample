package com.example.android.todolist.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDAO {

    @Query("SELECT * FROM task ORDER BY priority")
    LiveData<List<TaskEntry>> getAllEntries();

    @Insert
    void insert(TaskEntry entry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(TaskEntry entry);

    @Delete
    void delete(TaskEntry entry);

    @Query("Select * from task where id=:entryID")
    LiveData<TaskEntry> getEntry(int entryID);
}
