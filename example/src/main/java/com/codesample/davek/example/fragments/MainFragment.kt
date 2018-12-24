package com.codesample.davek.example.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.codesample.davek.library.NumberToString
import com.codesample.davek.example.R
import kotlinx.android.synthetic.main.main_fragment_layout.*
import com.codesample.davek.example.utils.KeyboardUtils.hideKeyboard
import java.lang.NumberFormatException


class MainFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberEditText.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage()
                handled = true
            }
            handled
        }

        getOutputButton.setOnClickListener {
            sendMessage()
        }
        deleteFieldButton.setOnClickListener {
            numberEditText.setText("")
        }
    }

    private fun sendMessage() {
        hideKeyboard(activity)
        try {

            val message = NumberToString.convertToString(numberEditText.text.toString().toDouble(), numberEditText.context)
            activity?.let {
                val dialog = AlertDialog.Builder(it)
                    .setTitle(R.string.string_ouput)
                    .setMessage(message)
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->  }.create()
                dialog.show()
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}
