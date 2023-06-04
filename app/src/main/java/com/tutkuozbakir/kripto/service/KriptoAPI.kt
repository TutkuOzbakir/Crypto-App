package com.tutkuozbakir.kripto.service

import com.tutkuozbakir.kripto.model.CryptoModel
import io.reactivex.Observable
//import retrofit2.Call
import retrofit2.http.GET

interface KriptoAPI {


    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData() : Observable<List<CryptoModel>>


}