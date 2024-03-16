package com.mvi.data.mappers

import com.mvi.data.mockdata.loadEmptyInput
import com.mvi.data.mockdata.loadEmptySubBreed
import com.mvi.data.mockdata.loadEmptySubBreedExpected
import com.mvi.data.mockdata.loadMultipleSubBreeds
import com.mvi.data.mockdata.loadMultipleSubBreedsExpected
import com.mvi.data.mockdata.loadNoSubBreeds
import com.mvi.data.mockdata.loadNoSubBreedsExpected
import com.mvi.data.mockdata.loadSingleSubBreed
import com.mvi.data.mockdata.loadSingleSubBreedExpected
import com.mvi.domain.model.DogBreed
import org.junit.Assert.assertEquals
import org.junit.Test


class DogListMappersTest {

    @Test
    fun `test toDogBreed with multiple sub-breeds`() {
        val dto = loadMultipleSubBreeds( )
        val expected = loadMultipleSubBreedsExpected()

        val mapper = DogListMappers()
        val result = mapper.toDogBreed(dto)

        assertEquals(expected.dogs.size, result.dogs.size)
        assertEquals(expected, result)
    }

    @Test
    fun `test toDogBreed with single sub-breed`() {
        val dto = loadSingleSubBreed()
        val expected = loadSingleSubBreedExpected()

        val mapper = DogListMappers()
        val result = mapper.toDogBreed(dto)

        assertEquals(expected.dogs.size, result.dogs.size)
        assertEquals(expected, result)
    }

    @Test
    fun `test toDogBreed with no sub-breeds`() {
        val dto = loadNoSubBreeds()
        val expected = loadNoSubBreedsExpected()

        val mapper = DogListMappers()
        val result = mapper.toDogBreed(dto)

        assertEquals(expected.dogs.size, result.dogs.size)
        assertEquals(expected, result)
    }

    @Test
    fun `test toDogBreed with empty input`() {
        val dto = loadEmptyInput()
        val expected = DogBreed(emptyList())

        val mapper = DogListMappers()
        val result = mapper.toDogBreed(dto)

        assertEquals(expected.dogs.size, result.dogs.size)
        assertEquals(expected, result)
    }

    @Test
    fun `test toDogBreed with empty sub-breed`() {
        val dto = loadEmptySubBreed()
        val expected = loadEmptySubBreedExpected()

        val mapper = DogListMappers()
        val result = mapper.toDogBreed(dto)

        assertEquals(expected.dogs.size, result.dogs.size)
        assertEquals(expected, result)
    }

}