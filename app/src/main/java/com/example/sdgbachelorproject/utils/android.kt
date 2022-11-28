package com.example.sdgbachelorproject.utils

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.sdgbachelorproject.R

fun Fragment.switchFragment(fragmentId: Int) {
    Navigation.findNavController(requireActivity(), R.id.activity_main_nav_host_fragment).navigate(
        fragmentId
    )
}