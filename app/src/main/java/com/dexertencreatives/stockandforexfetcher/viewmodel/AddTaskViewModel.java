package com.dexertencreatives.stockandforexfetcher.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.dexertencreatives.stockandforexfetcher.data.database.AppDatabase;
import com.dexertencreatives.stockandforexfetcher.model.JournalEntry;

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
