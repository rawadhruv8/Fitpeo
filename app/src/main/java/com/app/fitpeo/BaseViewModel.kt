package com.app.fitpeo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import org.json.JSONObject
import retrofit2.HttpException

open class BaseViewModel : ViewModel() {

    val observeError = MutableLiveData<Any>()
    private val compositeDisposable = ArrayList<Disposable>()

    fun addDisposable(subscribe: Disposable) {
        compositeDisposable.add(subscribe)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    fun getRetrofitError(it: Throwable) {
        try {
            if (it is HttpException) {
                val errorBody = JSONObject(it.response()?.errorBody()?.string() ?: "")
                val message: String = errorBody.getString("message")
                observeError.value = message
            } else
                observeError.value = R.string.some_error_occured
        } catch (exp: Exception) {
            observeError.value = R.string.some_error_occured
        }
    }
}