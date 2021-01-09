package com.gitter.polaczenie

import com.gitter.data.OdpowiedzRepozytorium
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface GithubApi {
    @GET("/search/repositories")
    fun szukaj(@Query("q") zapytanie: String): Call<OdpowiedzRepozytorium>
}