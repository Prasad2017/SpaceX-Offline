package com.example.spacex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spacex.api.ApiInterface
import com.example.spacex.database.RocketsDao
import com.example.spacex.model.Rockets
import com.example.spacex.model.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RocketListRepository @Inject constructor(

    private val client: ApiInterface,
    private val rocketsDao: RocketsDao,

    ) {

    val rockets: LiveData<List<Rockets>> = Transformations.map(
        when {

            else -> {
                rocketsDao.getRockets()
            }
        }
    ) {
        it.asDomainModel()
    }

    /**
     * Refresh the data stored in the offline cache.
     */
    suspend fun fetchRocketList() {
        withContext(Dispatchers.IO) {
            val rockets = client.getRocketList()
            //  rocketsDao.insertAll(rockets.asDatabaseModel())
        }
    }

}
