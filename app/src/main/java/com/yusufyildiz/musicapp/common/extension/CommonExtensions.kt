package com.yusufyildiz.musicapp.common.extension

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun View.showSnackBar(message: String) = Snackbar.make(this,message,Snackbar.LENGTH_SHORT).show()
fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.gone(){
    this.visibility = View.GONE
}

fun String.toDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val date: Date = inputFormat.parse(this) as Date
    return outputFormat.format(date)
}

