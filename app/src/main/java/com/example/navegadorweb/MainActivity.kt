package com.example.navegadorweb

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTextUrl = findViewById<EditText>(R.id.txt_url)
        val buttonNavegar = findViewById<Button>(R.id.btn_navegar)
        val webView = findViewById<WebView>(R.id.webview1)
        val btnIngresar: Button=findViewById(R.id.btn_ejercicio2)

        btnIngresar.setOnClickListener {
            val intent = Intent(this, Ejercicio2::class.java)
            startActivity(intent)
        }

        webView.webViewClient = WebViewClient()

        buttonNavegar.setOnClickListener {
            var url = editTextUrl.text.toString()
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://www.$url.com"
            }
            webView.loadUrl(url)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}