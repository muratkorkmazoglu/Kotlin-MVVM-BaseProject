package com.murat.mvvm_baseproject.service

import com.murat.mvvm_baseproject.service.Response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbAPI {
    @GET("/")
    fun getSearchMovies(@Query("t") title: String, @Query("y") year: String, @Query("apikey") apikey: String): Observable<MovieResponse>
}
