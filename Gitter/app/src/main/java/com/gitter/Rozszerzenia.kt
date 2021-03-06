package com.gitter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChanged(odpowiedz: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher{
        override fun afterTextChanged(s: Editable?) {
//            s?.let {
//                odpowiedz.invoke(s.toString())
//            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            s?.let {
//                odpowiedz.invoke(s.toString())
//            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                odpowiedz.invoke(s.toString())
            }
        }

    })
}