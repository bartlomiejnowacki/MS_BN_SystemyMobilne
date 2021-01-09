package com.gitter.data

import com.google.gson.annotations.SerializedName

//String? ponieważ wartość może być nullem
data class Repozytorium(
                      @SerializedName("name") val nazwa: String?,
                      @SerializedName("description") val opis: String?,
                      @SerializedName("html_url") val url: String?,
                      @SerializedName("stargazers_count") val gwiazdki: Int)