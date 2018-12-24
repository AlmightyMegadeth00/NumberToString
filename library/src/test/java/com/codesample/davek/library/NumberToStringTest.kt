package com.codesample.davek.library

import android.content.Context
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Matchers
import org.mockito.Mockito
import java.lang.IllegalStateException
import java.math.BigDecimal
import java.math.BigInteger

class NumberToStringTest {

    private val mockContext by lazy {
        Mockito.mock(Context::class.java)
    }

    @Before
    fun before() {
        Mockito.`when`(mockContext.getString(Matchers.anyInt())).thenReturn("test_string")
    }

    @Test
    fun convertToString_fromDouble() {
        val testDouble = 5.0
        val output = NumberToString.convertToString(testDouble, mockContext)

        assertEquals("test_string ", output)
    }

    @Test
    fun convertToString_fromFloat() {
        val testFloat = 5F
        try {
            val output = NumberToString.convertToString(testFloat, mockContext)
            assertTrue(false)
        } catch (e: NotImplementedError) {
            assertTrue(true) // not supported yet, so we throw an exception
        }
    }

    @Test
    fun convertToString_fromLong() {
        val testLong = 5L
        try {
            val output = NumberToString.convertToString(testLong, mockContext)
            assertTrue(false)
        } catch (e: NotImplementedError) {
            assertTrue(true) // not supported yet, so we throw an exception
        }
    }

    @Test
    fun convertToString_fromInt() {
        val testInt = 5
        val output = NumberToString.convertToString(testInt, mockContext)

        assertEquals("test_string ", output)
    }

    @Test
    fun convertToString_fromShort() {
        val testShort: Short = 5
        try {
            val output = NumberToString.convertToString(testShort, mockContext)
            assertTrue(false)
        } catch (e: NotImplementedError) {
            assertTrue(true) // not supported yet, so we throw an exception
        }
    }

    @Test
    fun convertToString_fromByte() {
        val testByte: Byte = 5
        try {
            val output = NumberToString.convertToString(testByte, mockContext)
            assertTrue(false)
        } catch (e: NotImplementedError) {
            assertTrue(true) // not supported yet, so we throw an exception
        }
    }

    @Test
    fun convertToString_fromBigDecimal() {
        val testBigDecimal = BigDecimal(5)
        try {
            val output = NumberToString.convertToString(testBigDecimal, mockContext)
            assertTrue(false)
        } catch (e: NotImplementedError) {
            assertTrue(true) // not supported yet, so we throw an exception
        }
    }

    @Test
    fun convertToString_fromBigInteger() {
        val testBigInteger = BigInteger("5")
        try {
            val output = NumberToString.convertToString(testBigInteger, mockContext)
            assertTrue(false)
        } catch (e: NotImplementedError) {
            assertTrue(true) // not supported yet, so we throw an exception
        }
    }

    @Test
    fun convertToString_unknownType() {
        val testUnkownFormat = UnkownFormat()
        try {
            val output = NumberToString.convertToString(testUnkownFormat, mockContext)
            assertTrue(false)
        } catch (e: IllegalStateException) {
            assertTrue(true) // not supported yet, so we throw an exception
        }
    }

    private class UnkownFormat(): Number() {
        override fun toByte(): Byte {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun toChar(): Char {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun toDouble(): Double {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun toFloat(): Float {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun toInt(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun toLong(): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
        override fun toShort(): Short {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

}
