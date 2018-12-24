package com.codesample.davek.library

import android.content.Context
import com.codesample.davek.library.translator.ToReadableFormat
import java.lang.IllegalStateException
import java.math.BigDecimal
import java.math.BigInteger

object NumberToString {

    /**
     * Convert an Integer to a human readable string value
     * ex: input: convertToString(5) -> output: "five"
     *
     * @param input: Double
     *
     */
    @JvmStatic fun convertToString(input: Double, context: Context): String {
        return ToReadableFormat(input,context).parseFromDouble()
    }

    @Throws(NotImplementedError::class)
    @JvmStatic fun convertToString(input: Float, context: Context): String {
        TODO("Float number values are not currently supported")
    }

    @Throws(NotImplementedError::class)
    @JvmStatic fun convertToString(input: Long, context: Context): String {
        TODO("Long number valuess are not currently supported")
    }

    /**
     * Convert an Integer to a human readable string value
     * ex: input: convertToString(5) -> output: "five"
     *
     * @param input: Int
     *
     */
    @JvmStatic fun convertToString(input: Int, context: Context): String {
        return ToReadableFormat(input, context).parseFromInt()
    }

    @Throws(NotImplementedError::class)
    @JvmStatic fun convertToString(input: Short, context: Context): String {
        TODO("Short number values are not currently supported")
    }

    @Throws(NotImplementedError::class)
    @JvmStatic fun convertToString(input: Byte, context: Context): String {
        TODO("Byte number values are not currently supported")
    }

    // TODO: support other number APIs
    @Throws(NotImplementedError::class)
    @JvmStatic fun convertToString(input: BigDecimal, context: Context): String {
        TODO("BigDecimal number values are not currently supported")
    }

    // TODO: support other number APIs
    @Throws(NotImplementedError::class)
    @JvmStatic fun convertToString(input: BigInteger, context: Context): String {
        TODO("BigInteger number values are not currently supported")
    }

    /**
     * Safeguard against extensions of the abstract Number class other than standard types Double, Float, Long, etc
     */
    @Throws(IllegalStateException::class)
    @JvmStatic fun convertToString(input: Number, context: Context): String {
        return when (input) {
            is Double -> convertToString(input, context)
            is Float -> convertToString(input, context)
            is Long -> convertToString(input, context)
            is Int -> convertToString(input, context)
            is Short -> convertToString(input, context)
            is Byte -> convertToString(input, context)
            else -> throw IllegalStateException("Number type is not a supported format")
        }
    }

}

