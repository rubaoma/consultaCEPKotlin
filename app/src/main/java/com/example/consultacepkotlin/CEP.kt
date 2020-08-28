package com.example.consultacepkotlin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

abstract class CEP(

    @SerializedName("cep")
    @Expose
    var cep: String,
    @SerializedName("logradouro")
    @Expose
    var logradouro: String,
    @SerializedName("complemento")
    @Expose
    var complemento: String,
    @SerializedName("bairro")
    @Expose
    var bairro: String,
    @SerializedName("localidade")
    @Expose
    var localidade: String,
    @SerializedName("uf")
    @Expose
    var uf: String,
    @SerializedName("ibge")
    @Expose
    var ibge: String,
    @SerializedName("gia")
    @Expose
    var gia: String,
    @SerializedName("ddd")
    @Expose
    var ddd: String
)

fun getCep(cep: String?): String? {
    return cep
}

