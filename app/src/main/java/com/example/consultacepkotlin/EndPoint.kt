package com.example.consultacepkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoint {

    val BASE_URL: String
        get() = "https://viacep.com.br/ws/"

    /* Retorna uma lista de objetos CEP */
    @GET("{estado}/{cidade}/{endereco}/json/")
    fun getCEPByCidadeEstadoEndereco(
        @Path("estado") estado: String?,
        @Path("cidade") cidade: String?,
        @Path("endereco") endereco: String?
    ): Call<List<CEP?>?>?


    @GET("{CEP}/json/")
    open fun getCEP(@Path("CEP") CEP: String?): Call<CEP?>?
}