package com.codesample.davek.library.translator

import android.content.Context
import android.support.annotation.VisibleForTesting
import com.codesample.davek.library.R
import java.lang.StringBuilder

internal class Translator(private val context: Context): TranslatePlacesInterface {

    companion object {
        const val MAX_SUPPORTED_VALUE = 999999999999999
        const val TRILLION = 1000000000000
        const val BILLION = 1000000000
        const val MILLION = 1000000
        const val THOUSAND = 1000
        const val HUNDRED = 100
        const val TEN = 10
    }

    /**
     * Parse input from type Int (converted to Double)
     *
     * @param input: Int value to parse readable format from
     * @return String: human readable format
     */
    fun parse(input: Int): String {
        return parse(input.toDouble())
    }

    /**
     * Parse input from type Double
     *
     * @param input: Double value to parse readable format from
     * @return String: human readable format
     */
    fun parse(input: Double): String {
        val builder = StringBuilder()
        val absValue = Math.abs(input)

        if (absValue > MAX_SUPPORTED_VALUE) {
            return context.getString(R.string.max_value_reached)
        }

        builder.append(getIsNegativeString(input))
        builder.append(getTrillionsPlace(absValue))
        builder.append(getBillionsPlace(absValue))
        builder.append(getMillionsPlace(absValue))
        builder.append(getThousandsPlace(absValue))

        if (absValue.toInt() == 0) {
            builder.append(context.getString(R.string.zero))
        } else {
            builder.append(getHundredthsPlace(absValue))
        }

        return builder.toString()
    }

    // TranslatePlacesInterface region
    /**
     * Get negative number identifier
     *
     * @param input: value of the number as a Double
     * @return String: human readable format
     */
    override fun getIsNegativeString(input: Double): String {
        return if (input < 0) {
            fixSpaces(context.getString(R.string.negative))
        } else {
            ""
        }
    }

    /**
     * Get trillions place string value
     *
     * @param: input: value of trillions place as a Double
     * @return String: human readable format
     */
    override fun getTrillionsPlace(input: Double): String {
        val billionsPlace = getMaxForPlace(Math.floor(input / TRILLION))
        return formatPlace(billionsPlace, fixSpaces(context.getString(R.string.trillion)))
    }

    /**
     * Get billions place string value
     *
     * @param: input: value of billions place as a Double
     * @return String: human readable format
     */
    override fun getBillionsPlace(input: Double): String {
        val billionsPlace = getMaxForPlace(Math.floor(input / BILLION))
        return formatPlace(billionsPlace, fixSpaces(context.getString(R.string.billion)))
    }

    /**
     * Get millions place string value
     *
     * @param: input: value of millions place as a Double
     * @return String: human readable format
     */
    override fun getMillionsPlace(input: Double): String {
        val millionsPlace = getMaxForPlace(Math.floor(input / MILLION))
        return formatPlace(millionsPlace, fixSpaces(context.getString(R.string.million)))
    }

    /**
     * Get thousands place string value
     *
     * @param: input: value of thousands place as a Double
     * @return String: human readable format
     */
    override fun getThousandsPlace(input: Double): String {
        val thousandsPlace = getMaxForPlace(Math.floor(input / THOUSAND))
        return formatPlace(thousandsPlace, fixSpaces(context.getString(R.string.thousand)))
    }

    /**
     * Get hundredths place string value
     *
     * @param: input: value of hundredths place as a Double
     * @return String: human readable format
     */
    override fun getHundredthsPlace(input: Double): String {
        val hundredthsPlace = getMaxForPlace(input)
        return formatPlace(hundredthsPlace, "")
    }
    // End TranslatePlacesInterface region

    private fun getMaxForPlace(input: Double): Double {
        val upperBound = Math.floor(input / THOUSAND)
        return input - (upperBound * THOUSAND)
    }

    private fun formatPlace(input: Double, postFix: String): String {
        val output = getHundreds(input) + getFirstTwo(input)
        return if (output.isNotEmpty()) {
            output + (if (postFix.isNotEmpty()) postFix else "")
        } else {
            ""
        }
    }

    @VisibleForTesting // visible to test appended spaces on strings which effects assertions
    fun fixSpaces(string: String): String {
        return "$string "
    }

    private fun getHundreds(input: Double): String {
        val hundreds = Math.floor(input / HUNDRED).toInt()
        return if (hundreds > 0) {
            getSingleDigitTranslation(hundreds) + fixSpaces(context.getString(R.string.hundred))
        } else {
            ""
        }
    }

    private fun getFirstTwo(input: Double): String {
        val place = getMaxForPlace(input).toInt()
        val hundreds = Math.floor(input / HUNDRED)
        val valueOutput = (place - (hundreds * HUNDRED))
        return if (valueOutput > 0) {
            if (valueOutput in 11..19) {
                getTeensTranslation(valueOutput.toInt())
            } else {
                val tens = Math.floor(valueOutput / TEN).toInt()
                val single = (valueOutput - (tens * TEN)).toInt()
                getTenthsTranslation(tens) + getSingleDigitTranslation(single)
            }
        } else {
            ""
        }
    }

    private fun getSingleDigitTranslation(input: Int): String {
        if (input == 0) return ""
        return fixSpaces(when (input) {
            1 -> context.getString(R.string.one)
            2 -> context.getString(R.string.two)
            3 -> context.getString(R.string.three)
            4 -> context.getString(R.string.four)
            5 -> context.getString(R.string.five)
            6 -> context.getString(R.string.six)
            7 -> context.getString(R.string.seven)
            8 -> context.getString(R.string.eight)
            9 -> context.getString(R.string.nine)
            else -> throw IllegalStateException("how did you do this?")
        })
    }

    private fun getTeensTranslation(input: Int): String {
        if (input == 0) return ""
        return fixSpaces(when (input) {
            11 -> context.getString(R.string.eleven)
            12 -> context.getString(R.string.twelve)
            13 -> context.getString(R.string.thirteen)
            14 -> context.getString(R.string.fourteen)
            15 -> context.getString(R.string.fifteen)
            16 -> context.getString(R.string.sixteen)
            17 -> context.getString(R.string.seventeen)
            18 -> context.getString(R.string.eighteen)
            19 -> context.getString(R.string.nineteen)
            else -> throw IllegalStateException("how did you do this?")
        })
    }

    private fun getTenthsTranslation(input: Int): String {
        if (input == 0) return ""
        return fixSpaces(when (input) {
            1 -> context.getString(R.string.ten)
            2 -> context.getString(R.string.twenty)
            3 -> context.getString(R.string.thirty)
            4 -> context.getString(R.string.forty)
            5 -> context.getString(R.string.fifty)
            6 -> context.getString(R.string.sixty)
            7 -> context.getString(R.string.seventy)
            8 -> context.getString(R.string.eighty)
            9 -> context.getString(R.string.ninety)
            else -> throw IllegalStateException("how did you do this?")
        })
    }

}
