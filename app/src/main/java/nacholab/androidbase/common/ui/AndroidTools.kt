package nacholab.androidbase.common.ui

import android.content.Context
import android.content.res.Resources
import android.os.IBinder
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Int.toPx(r: Resources?) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    r?.displayMetrics
).toInt()

fun Float.toPx(r: Resources?) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    r?.displayMetrics
).toInt()

fun View.openKeyboard() = openKeyboard(context, this)
fun View.closeKeyboard() = closeKeyboard(context, windowToken)

fun openKeyboard(context: Context, view: View){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun closeKeyboard(context: Context, windowToken: IBinder?){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    windowToken?.let { imm.hideSoftInputFromWindow(it, 0) }
}
