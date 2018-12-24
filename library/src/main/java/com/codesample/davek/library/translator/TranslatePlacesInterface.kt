package com.codesample.davek.library.translator

/**
 * Interface for translating each number place to allow for different interpretations based on language
 */
internal interface TranslatePlacesInterface {

    fun getIsNegativeString(input: Double): String
    fun getTrillionsPlace(input: Double): String
    fun getBillionsPlace(input: Double): String
    fun getMillionsPlace(input: Double): String
    fun getThousandsPlace(input: Double): String
    fun getHundredthsPlace(input: Double): String

}
