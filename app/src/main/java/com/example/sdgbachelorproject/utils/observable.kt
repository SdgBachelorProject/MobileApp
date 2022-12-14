package com.example.sdgbachelorproject.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

fun <T> fromPublisher(observable: Observable<T>) =
    LiveDataReactiveStreams.fromPublisher(
        observable.toFlowable(BackpressureStrategy.LATEST)
    )

fun <T> Observable<T>.toLiveData() = fromPublisher(this)

fun <T> LiveData<T>.observeValue(owner: LifecycleOwner, block: (T) -> Unit) =
    observe(owner, {
        block(it)
    })

fun <T> Observable<T>.observeAsLiveData(owner: LifecycleOwner, block: (T) -> Unit) =
    toLiveData().observeValue(owner) {
        block(it)
    }

fun <T> behavior() = BehaviorSubject.create<T>()