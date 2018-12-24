package com.codesample.davek.example.utils

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {

    /**
     * Utility method to hide the keyboard with the currently focused view
     *
     * @param activity: FragmentActivity? that gets the currently focused view's window token to make the request
     */
    fun hideKeyboard(activity: FragmentActivity?) {
        activity?.let {fragmentActivity ->
            val inputMethodManager = fragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            val currentFocusView = fragmentActivity.currentFocus

            ifNotNull(inputMethodManager, currentFocusView) { imm, view ->
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    private fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit)) {
        if (value1 != null && value2 != null) {
            bothNotNull(value1, value2)
        }
    }
}
