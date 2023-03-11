package com.example.spacex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex.api.Api
import com.example.spacex.repository.RocketListRepository
import kotlinx.coroutines.launch
import java.io.IOException

class RocketListViewModel(private val repository: RocketListRepository) : ViewModel() {

    //  private var rocketListData = MutableLiveData<List<RocketsResponse>>()

    /**
     * A list of rockets to be displayed on the screen.
     */
    val rocketList = repository.rockets

    /**
     * Event triggered for network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromRepository()
    }

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.fetchRocketList()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if (rocketList.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

   /*  fun getRocketList() {

         Api.getClient().getRocketList().enqueue(object : Callback<List<RocketsResponse>> {
             override fun onResponse(
                 call: Call<List<RocketsResponse>>,
                 response: Response<List<RocketsResponse>>
             ) {
                 if (response.body() != null) {
                     rocketListData.value = response.body()!!
                 } else {
                     return
                 }
                 Log.e("ListData", "" + response.body()!!.size);
             }

             override fun onFailure(call: Call<List<RocketsResponse>>, t: Throwable) {
                 Log.d("TAG", t.message.toString())
             }
         })

     }

     fun observeRocketListData(): LiveData<List<RocketsResponse>> {
         return rocketListData
     }
*/

}