package com.example.spacex.model

import com.example.spacex.model.database.RocketsEntity
import com.example.spacex.model.network.RocketsResponse

/**
 * Convert Network results to database objects
 */
fun List<RocketsResponse>.asDatabaseModel(): List<RocketsEntity> {
    return this.map {
        RocketsEntity(
            id = it.id,
            active = it.active,
            rocketName = it.name,
            firstFlight = it.first_flight,
            country = it.country,
            image = it.flickr_images?.get(0) ?: "",
            wikipedia = it.wikipedia,
            description = it.description
        )
    }
}