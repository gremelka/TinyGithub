package com.limprove.tinygithub.utilities;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class DoubleTrigger extends MediatorLiveData<Pair<String, Boolean>> {

    public DoubleTrigger(LiveData<String> query, LiveData<Boolean> value) {
        addSource(query, first -> setValue(Pair.create(first, value.getValue())));
        addSource(value, second -> setValue(Pair.create(query.getValue(), second)));
    }
}