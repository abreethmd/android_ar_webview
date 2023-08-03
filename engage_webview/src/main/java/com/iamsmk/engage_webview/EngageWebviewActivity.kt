package com.iamsmk.engage_webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.iamsmk.engage_webview.databinding.ActivityEngageWebviewBinding

class EngageWebviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEngageWebviewBinding
    private var webURL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEngageWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webURL = intent.extras?.getString("webURL").toString()

        setupWebView()
        WebView.setWebContentsDebuggingEnabled(true)

        binding.wvView.settings.apply {
            allowContentAccess = true
            javaScriptEnabled = true
            mediaPlaybackRequiresUserGesture = false
        }

        binding.wvView.webViewClient = WebViewClient()
        binding.wvView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                runOnUiThread {
                    if (request.resources.first() == PermissionRequest.RESOURCE_VIDEO_CAPTURE) {
                        request.grant(request.resources)
                    } else {
                        request.deny()
                    }
                }
            }
        }
    }

    private fun setupWebView() {
        binding.wvView.loadUrl(webURL)
    }
}