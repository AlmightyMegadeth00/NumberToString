package com.codesample.davek.library.translator

import android.content.Context
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mockito

class TranslatorTest {

    private val mockContext by lazy {
        Mockito.mock(Context::class.java)
    }

    private lateinit var translator: Translator

    @Before
    fun before() {
        Mockito.`when`(mockContext.getString(Matchers.anyInt())).thenReturn("test_string")
        translator = Translator(mockContext)
    }

    @Test // method annotated @VisibleForTesting since it affects assertions
    fun testFixSpaces() {
        val testString = "test_string"
        val expctedOutput = "test_string "
        val actualOutput = translator.fixSpaces(testString)

        assertEquals(expctedOutput, actualOutput)
    }

    @Test
    fun parseNegativeInt_valid() {
        val testInt = -5

        // we are expecting "test_string test_string " for "negative five "
        assertEquals("test_string test_string ", translator.parse(testInt))
    }

    @Test
    fun parseTeensInt_valid() {
        val testInt = 13

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", translator.parse(testInt))
    }

    @Test
    fun parseTensIntNoSinglesPlace_valid() {
        val testInt = 20

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", translator.parse(testInt))
    }

    @Test
    fun parseNegativeDouble_valid() {
        val testInt = -5.0

        // we are expecting "test_string test_string " for "negative five "
        assertEquals("test_string test_string ", translator.parse(testInt))
    }

    @Test
    fun parseTeensDouble_valid() {
        val testInt = 13.0

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", translator.parse(testInt))
    }

    @Test
    fun parseTensDoubleNoSinglesPlace_valid() {
        val testInt = 20.0

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", translator.parse(testInt))
    }

    @Test
    fun parseFromInt_valid() {
        val testInt = 5

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", translator.parse(testInt))
    }

    @Test
    fun parseFromInt_invalid() {
        val testInt = 5

        // we have to append a space at the end to ensure the text spacing is correct, so "test_string" should not equal the actual results
        assertNotEquals("test_string", translator.parse(testInt))
    }

    @Test
    fun parseFromDouble_valid() {

        val testDouble = 5.0

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", translator.parse(testDouble))
    }

    @Test
    fun parseTwoDigitsFromInt_valid() {
        val testInt = 50

        // we have to append a space at the end to ensure the text spacing is correct
        assertEquals("test_string ", translator.parse(testInt))
    }

    @Test
    fun parseTwoDigitsFromDouble_valid() {

        val testDouble = 55.0

        // we have to append a space at the end to ensure the text spacing is correct
        // we're expecting 'test_string test_string' for 'fifty five'
        assertEquals("test_string test_string ", translator.parse(testDouble))
    }

    @Test
    fun parseThreeDigitsFromInt_valid() {
        val testInt = 555

        // we have to append a space at the end to ensure the text spacing is correct
        // we're expecting 'test_string test_string test_string test_string' for 'five hundred fifty five'
        assertEquals("test_string test_string test_string test_string ", translator.parse(testInt))
    }


    @Test
    fun parseThreeDigitsFromDouble_valid() {

        val testDouble = 555.0

        // we have to append a space at the end to ensure the text spacing is correct
        // we're expecting 'test_string test_string test_string test_string' for 'five hundred fifty five'
        assertEquals("test_string test_string test_string test_string ", translator.parse(testDouble))
    }

    @Test
    fun parseLargerThanMaxSupported() {
        val testDouble = 555000000000000000000.0

        // we have to append a space at the end to ensure the text spacing is correct
        // we're expecting 'test_string test_string test_string test_string' for 'five hundred fifty five'
        assertEquals("test_string", translator.parse(testDouble))
    }

}
