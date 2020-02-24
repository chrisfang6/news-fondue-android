package net.chris.news.fondue.android.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    internal val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}
