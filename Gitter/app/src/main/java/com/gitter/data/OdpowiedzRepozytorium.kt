package com.gitter.data

import com.google.gson.annotations.SerializedName

data class OdpowiedzRepozytorium(@SerializedName("items") val listaRepozytoriow: Collection<Repozytorium>)