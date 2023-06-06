package com.example.tugaskelompokapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import com.example.tugaskelompokapi.retrofit.ApiService
import com.example.tugaskelompokapi.retrofit.MainModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private  val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        getDataFromApi()


    }

    private fun getDataFromApi(){

        ApiService.endpoint.getData()
            .enqueue(object : Callback<MainModel>{
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    if (response.isSuccessful){
                        showData(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    prinLog(t.toString())
                }

            })

    }

    private  fun prinLog(message: String){
        Log.d(TAG,message)
    }

    private  fun showData(data: MainModel){
        val results = data.result
        for (result in results){
            prinLog("title: ${result.title}")
        }
    }
}