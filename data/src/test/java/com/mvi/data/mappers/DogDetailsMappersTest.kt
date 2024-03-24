package com.mvi.data.mappers

import com.mvi.data.mockdata.loadDogDetailsDto
import com.mvi.data.mockdata.loadDogDetailsExpected
import org.junit.Assert.assertEquals
import org.junit.Test

class DogDetailsMappersTest {
    @Test
    fun `test toDogDetails`() {
        val dto = loadDogDetailsDto()
        val expected = loadDogDetailsExpected()

        val mapper = DogDetailsMappers()
        val result = mapper.toDogDetails(dto)

        assertEquals(expected.dogImageUrl, result.dogImageUrl)
        assertEquals(expected.dogImageUrl1, result.dogImageUrl1)
        assertEquals(expected.dogImageUrl3, result.dogImageUrl3)
    }
}
