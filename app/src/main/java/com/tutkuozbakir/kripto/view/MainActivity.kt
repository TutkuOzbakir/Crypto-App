package com.tutkuozbakir.kripto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutkuozbakir.kripto.adapter.RecyclerViewAdapter
import com.tutkuozbakir.kripto.databinding.ActivityMainBinding
import com.tutkuozbakir.kripto.model.CryptoModel
import com.tutkuozbakir.kripto.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var cryptoList: ArrayList<CryptoModel>
    private lateinit var binding: ActivityMainBinding
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        loadData()
    }

    private fun handleResponse(list: List<CryptoModel>){
        cryptoList = ArrayList(list)
        binding.recyclerview.adapter = RecyclerViewAdapter(cryptoList)
    }
    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).
            addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))

        /*

        --------------------------CALL---------------------------

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(CryptoAPI::class.java)
       val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>>{
            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>) {
                if(response.isSuccessful){
                    response.body()?.let{
                        cryptoList = ArrayList(it)
                        binding.recyclerview.adapter = RecyclerViewAdapter(cryptoList)
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
        */
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}