package com.mvi.data.mappers

import com.mvi.data.dto.DogDetailsDto
import com.mvi.domain.model.DogDetails
import javax.inject.Inject

class DogDetailsMappers @Inject constructor(){
    fun toDogDetails(dto: DogDetailsDto): DogDetails {
        return DogDetails(dto.message)
    }
}
