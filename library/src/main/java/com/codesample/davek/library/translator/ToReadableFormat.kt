package com.codesample.davek.library.translator

import android.content.Context
import com.codesample.davek.library.exceptions.CustomNumberFormatException

class ToReadableFormat(private val input: Number, context: Context) {

    private val translator by lazy {
        Translator(context)
    }

    /**
     * Parse the human readable string output from an Int
     */
    fun parseFromInt(): String {
        if (isCorrectType<Int>()) {
            return translator.parse(input.toInt())
        } else {
            throw CustomNumberFormatException("Number value is not of type Int")
        }
    }

    /**
     * Parse the human readable string output from a Double
     */
    fun parseFromDouble(): String {
        if (isCorrectType<Double>()) {
            return translator.parse(input.toDouble())
        } else {
            throw CustomNumberFormatException("Number value is not of type Double")
        }
    }

    // TODO: DMK 12/23: add support for other types

    /**
     * Sanity check to make sure we are parsing the correct Number type
     */
    private inline fun <reified T : Number> isCorrectType(): Boolean {
        return when (input) {
            is T -> true
            else -> false
        }
    }

}