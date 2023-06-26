package com.app.fitpeo

import androidx.lifecycle.MutableLiveData
import com.app.fitpeo.retrofit.APIService
import com.app.fitpeo.retrofit.ResponseImages
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ImagesViewModel(private val apiService: APIService) :
    BaseViewModel() {

    val observeResponse = MutableLiveData<ArrayList<ResponseImages>>()

    fun callImagesApi() {
        addDisposable(
            apiService.getPhotos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    observeResponse.value = it
                }, {
                    getRetrofitError(it)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        clearDisposable()
    }
}