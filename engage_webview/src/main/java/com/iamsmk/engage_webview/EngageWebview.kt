package com.iamsmk.engage_webview

import android.content.Context
import android.content.Intent

class EngageWebview {
    fun launchEngageWebview(context: Context?, webURL: String?) {
        val intent = Intent(context, EngageWebviewActivity::class.java)
        intent.putExtra("webURL", webURL)
        context?.startActivity(intent)
    }
}