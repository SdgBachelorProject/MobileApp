package com.example.sdgbachelorproject.utils

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.sdgbachelorproject.R

fun Fragment.switchFragment(fragmentId: Int) {
    Navigation.findNavController(requireActivity(), R.id.activity_main_nav_host_fragment).navigate(
        fragmentId
    )
}

fun createAlertDialog(message: String, context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(R.string.consumption_alert_dialog_title)
    //TODO fill in the message
    builder.setMessage(message)
    builder.setPositiveButton(R.string.consumption_alert_dialog_button) { dialog, which ->
    }
    builder.show()
}