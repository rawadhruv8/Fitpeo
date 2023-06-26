package com.app.fitpeo.retrofit

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

import retrofit2.Response

interface APIService {

    @GET("photos")
    fun getPhotos(): Observable<ArrayList<ResponseImages>>

}