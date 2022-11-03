package com.ozkan.bookshelf.util.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


/**
 * Activity uzerinden klavyeyi kapatmamizi saglar.
 */
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}


/**
 * Klavyeyi kapatmamizi saglar.
 */
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


/**
 * Context uzerinden toast mesaj gondermemizi saglar.
 */
fun Context.toast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, message, length).show()
    }
}

/**
 * Context uzerinden toast mesaj gondermemizi saglar, mesaji stringden int olarak alir.
 */
fun Context.toast(message: Int?, length: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, this.getText(message), length).show()
    }
}