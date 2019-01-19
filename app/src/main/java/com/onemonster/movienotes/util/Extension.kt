package com.onemonster.movienotes.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LifecycleOwner.observeLiveData(liveData: LiveData<T>, crossinline onChanged: (T) -> Unit) {
    liveData.observe(this, Observer { it?.let { value -> onChanged(value) } })
}