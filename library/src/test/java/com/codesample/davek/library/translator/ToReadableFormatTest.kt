package com.codesample.davek.library.translator

import android.content.Context
import com.codesample.davek.library.exceptions.CustomNumberFormatException
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class ToReadableFormatTest {

    private val mockContext by lazy {
        Mockito.mock(Context::class.java)
    }

    @Before
    fun before() {
        `when`(mockContext.getString(Matchers.anyInt())).thenReturn("test_string")
    }

    @Test
    fun parseFromInt_valid() {
        val testInt = 5
        val toReadableFormat = ToReadableFormat(testInt, mockContext)

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", toReadableFormat.parseFromInt())
    }

    @Test
    fun parseFromInt_invalidType() {

        val testInt = 5
        val toReadableFormat = ToReadableFormat(testInt, mockContext)

        try {
            assertEquals("test_string ", toReadableFormat.parseFromDouble())
        } catch (e: CustomNumberFormatException) {
            // exception thrown, test passed
            assertTrue(true)
        }
    }

    @Test
    fun parseFromDouble_valid() {

        val testDouble = 5.0
        val toReadableFormat = ToReadableFormat(testDouble, mockContext)

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", toReadableFormat.parseFromDouble())
    }

    @Test
    fun parseFromDouble_invalidType() {

        val testDouble = 5.0
        val toReadableFormat = ToReadableFormat(testDouble, mockContext)

        try {
            assertEquals("test_string ", toReadableFormat.parseFromInt())
        } catch (e: CustomNumberFormatException) {
            // exception thrown, test passed
            assertTrue(true)
        }
    }

    @Test
    fun parseTwoDigitsFromInt_valid() {
        val testInt = 50
        val toReadableFormat = ToReadableFormat(testInt, mockContext)

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", toReadableFormat.parseFromInt())
    }

    @Test
    fun parseTwoDigitsFromInt_invalidType() {

        val testInt = 50
        val toReadableFormat = ToReadableFormat(testInt, mockContext)

        try {
            assertEquals("test_string ", toReadableFormat.parseFromDouble())
        } catch (e: CustomNumberFormatException) {
            // exception thrown, test passed
            assertTrue(true)
        }
    }

    @Test
    fun parseTwoDigitsFromDouble_valid() {

        val testDouble = 55.0
        val toReadableFormat = ToReadableFormat(testDouble, mockContext)

        // we have to append a space at the end to ensure the text spacing is correct
        // we're expecting 'test_string test_string' for 'fifty five'
        assertEquals("test_string test_string ", toReadableFormat.parseFromDouble())
    }

    @Test
    fun parseTwoDigitsFromDouble_invalidType() {

        val testDouble = 55.0
        val toReadableFormat = ToReadableFormat(testDouble, mockContext)

        try {
            assertEquals("test_string test_string ", toReadableFormat.parseFromInt())
        } catch (e: CustomNumberFormatException) {
            // exception thrown, test passed
            assertTrue(true)
        }
    }

    @Test
    fun parseThreeDigitsFromInt_valid() {
        val testInt = 555
        val toReadableFormat = ToReadableFormat(testInt, mockContext)

        // we have to append a space at the end to ensure the text spacing is correct
        // we're expecting 'test_string test_string test_string test_string' for 'five hundred fifty five'
        assertEquals("test_string test_string test_string test_string ", toReadableFormat.parseFromInt())
    }

    @Test
    fun parseThreeDigitsFromInt_invalidType() {

        val testInt = 555
        val toReadableFormat = ToReadableFormat(testInt, mockContext)

        try {
            assertEquals("test_string test_string test_string test_string ", toReadableFormat.parseFromDouble())
        } catch (e: CustomNumberFormatException) {
            // exception thrown, test passed
            assertTrue(true)
        }
    }

    @Test
    fun parseThreeDigitsFromDouble_valid() {

        val testDouble = 555.0
        val toReadableFormat = ToReadableFormat(testDouble, mockContext)

        // we have to append a space at the end to ensure the text spacing is correct
        // we're expecting 'test_string test_string test_string test_string' for 'five hundred fifty five'
        assertEquals("test_string test_string test_string test_string ", toReadableFormat.parseFromDouble())
    }

    @Test
    fun parseThreeDigitsFromDouble_invalidType() {

        val testDouble = 555.0
        val toReadableFormat = ToReadableFormat(testDouble, mockContext)

        try {
            assertEquals("test_string test_string test_string test_string ", toReadableFormat.parseFromInt())
        } catch (e: CustomNumberFormatException) {
            // exception thrown, test passed
            assertTrue(true)
        }
    }

}
