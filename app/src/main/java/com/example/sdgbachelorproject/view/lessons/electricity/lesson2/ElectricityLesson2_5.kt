package com.example.sdgbachelorproject.view.lessons.electricity.lesson2

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.FragmentElectricityLesson25Binding
import com.example.sdgbachelorproject.utils.switchFragment
import kotlinx.android.synthetic.main.fragment_electricity_lesson2_5.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class ElectricityLesson2_5 : Fragment() {
    var webView: WebView? = null

    private var _binding: FragmentElectricityLesson25Binding? = null
    private val binding get() = _binding!!
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElectricityLesson25Binding.inflate(inflater, container, false)
        val view = binding.root

        // initialise the layout
        webView = view.webvidew
        // enable the javascript to load the url
        webView!!.settings.javaScriptEnabled = true
        webView!!.webViewClient = WebViewClient()

        // add the url of gif
        webView!!.loadUrl("https://i.gifer.com/7A9Y.gif")

        val nextButton = view.findViewById<Button>(com.example.sdgbachelorproject.R.id.btn_next_lesson2_5)
        val prevButton = view.findViewById<Button>(com.example.sdgbachelorproject.R.id.btn_previous_lesson2_5)

        nextButton.setOnClickListener {
            switchFragment(com.example.sdgbachelorproject.R.id.tutorialFragmentBottom)
        }
        prevButton.setOnClickListener {
            switchFragment(com.example.sdgbachelorproject.R.id.electricityLesson2_4)
        }
        return view
    }
}