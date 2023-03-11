package com.example.spacex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacex.api.Api
import com.example.spacex.model.network.RocketsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RocketDetailsViewModel : ViewModel() {

    private var rocketDetailsData = MutableLiveData<RocketsResponse>()

    fun getRocketDetails(rocketId: String) {

        Api.getClient().getRocketDetails(rocketId)
            .enqueue(object : Callback<RocketsResponse> {
                override fun onResponse(
                    call: Call<RocketsResponse>,
                    response: Response<RocketsResponse>
                ) {
                    if (response.body() != null) {
                        rocketDetailsData.value = response.body()!!
                    } else {
                        return
                    }
                    Log.e("ListData", "" + response.body()!!);
                }

                override fun onFailure(call: Call<RocketsResponse>, t: Throwable) {
                    Log.d("TAG-Details", t.message.toString())
                }
            })

    }

    fun observeRocketDetailsData(): LiveData<RocketsResponse> {
        return rocketDetailsData
    }

}