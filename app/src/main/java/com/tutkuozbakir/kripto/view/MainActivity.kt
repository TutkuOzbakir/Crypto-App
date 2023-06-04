package com.tutkuozbakir.kripto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutkuozbakir.kripto.databinding.ActivityMainBinding
import com.tutkuozbakir.kripto.model.CryptoModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var cryptoList: ArrayList<CryptoModel>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        loadData()
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()


        /*
        val service = retrofit.create(KriptoAPI::class.java)
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
}