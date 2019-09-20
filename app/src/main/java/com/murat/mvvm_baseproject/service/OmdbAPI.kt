package com.murat.mvvm_baseproject.service

import com.murat.mvvm_baseproject.service.Response.TMDBResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OmdbAPI {
    @GET("movie/{type}")
    fun getMovies(@Path("type") TYPE: String, @Query("api_key") API_KEY: String, @Query("language") LANGUAGE: String, @Query("page") PAGE: Int): Observable<TMDBResponse>
}
