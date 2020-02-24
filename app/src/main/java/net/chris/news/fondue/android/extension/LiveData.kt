package net.chris.news.fondue.android.extension

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.addAll(collection: Collection<T>) {
    this.value?.addAll(collection).let { this.value = this.value }
}

fun <T> MutableLiveData<List<T>>.appendAll(collection: Collection<T>) {
    (this.value?.toMutableList() ?: mutableListOf())
        .also {
            it.addAll(collection)
            this.value = it
        }
}
