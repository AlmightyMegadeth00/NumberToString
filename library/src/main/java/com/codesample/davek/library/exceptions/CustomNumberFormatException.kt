package com.codesample.davek.library.exceptions

import java.lang.NumberFormatException

class CustomNumberFormatException(override val message: String): NumberFormatException()
