package com.example.mywebview

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebChromeClient
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_main.*
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import org.jetbrains.anko.alert
import org.jetbrains.anko.textColor


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wvLayout.settings.javaScriptEnabled = true
        wvLayout.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                animationLoading.visibility = VISIBLE
            }


            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                animationLoading.visibility = GONE
            }
        }
        wvLayout.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                animationLoading.visibility = VISIBLE
                if (newProgress == 100) {
                    animationLoading.visibility = GONE
                }

            }
        }
        wvLayout.loadUrl("https://lindungihutan.com")

    }

    override fun onBackPressed() {
        if (wvLayout.canGoBack()){
            wvLayout.goBack()
        } else {
            showAlert()
        }
    }

    // TODO: 26/10/19 set ondoublebackpress

    private fun showAlert() {
        alert("Apakah Kamu yakin akan Keluar dari Aplikasi?") {
            positiveButton("Ya") {
                finish()
            }
            negativeButton("Nggak jadi") {
            }
        }.show().apply {
            getButton(AlertDialog.BUTTON_POSITIVE)?.let { it.textColor = Color.GREEN }
            getButton(AlertDialog.BUTTON_NEGATIVE)?.let { it.textColor = Color.RED }
        }
    }
}
