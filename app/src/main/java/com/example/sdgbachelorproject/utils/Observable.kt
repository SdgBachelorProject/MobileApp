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

fun <T> connect(owner: LifecycleOwner, observable: Observable<T>, fn: (T) -> Unit) {
    observable.observeAsLiveData(owner, fn)
}

fun <T> behavior() = BehaviorSubject.create<T>()
fun <T> publishSubject() = PublishSubject.create<T>()
fun <T> defaultBehavior(default: T) = BehaviorSubject.createDefault<T>(default).apply {
    DEFAULTED_BEHAVIOR_SUBJECTS.add(this.hashCode())
}

fun <T> BehaviorSubject<T>.valueOrDefault() =
    if (DEFAULTED_BEHAVIOR_SUBJECTS.contains(this.hashCode())) {
        value!!
    } else {
        throw Exception("Used valueOrDefault on non-defaulted behavior subject")
    }

val DEFAULTED_BEHAVIOR_SUBJECTS = HashSet<Int>()

// Transformation ----------------------------------------------------------------------------------

fun <E, T : Collection<E>> Subject<T>.isNotEmpty() = this.map { it.isNotEmpty() }
fun <T> Observable<Collection<T>>.filterNotEmpty() = filter { it.isNotEmpty() }
fun <T> Observable<Collection<T>>.filterEmpty() = filter { it.isEmpty() }

fun Observable<Boolean>.filterTrue() = filter { it }
fun Observable<Boolean>.filterNotTrue() = filter { !it }

fun <T> Observable<T>.unit() = map {}