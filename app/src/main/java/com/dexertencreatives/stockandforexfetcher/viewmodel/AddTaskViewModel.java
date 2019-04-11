package com.dexertencreatives.stockandforexfetcher.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.dexertencreatives.stockandforexfetcher.FXDatabase.AppDatabase;
import com.dexertencreatives.stockandforexfetcher.FXDatabase.JournalEntry;

/**
 * Created by shola on 3/18/2019.
 */

public class AddTaskViewModel extends ViewModel {

    private LiveData<JournalEntry> task;

    public AddTaskViewModel(AppDatabase database, int taskId) {
        task = database.taskDao().loadTaskById(taskId);
    }

    public LiveData<JournalEntry> getTask() {
        return task;
    }
}
