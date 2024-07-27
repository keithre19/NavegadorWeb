package com.example.navegadorweb

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject
import java.io.InputStream

class Ejercicio2 : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var sitiosWeb: List<Pair<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio2)

            val spinner = findViewById<Spinner>(R.id.spinner_sitios_web)
            val buttonNavegar = findViewById<Button>(R.id.btn_navegar)
            webView = findViewById(R.id.webview2)

            sitiosWeb = leerJsonDesdeRaw(R.raw.websites)
            val nombresSitios = sitiosWeb.map { it.first }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresSitios)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            webView.webViewClient = WebViewClient()

            buttonNavegar.setOnClickListener {
                val posicion = spinner.selectedItemPosition
                val url = sitiosWeb[posicion].second
                webView.loadUrl(url)
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

        }

    private fun leerJsonDesdeRaw(resourceId: Int): List<Pair<String, String>> {
        val inputStream: InputStream = resources.openRawResource(resourceId)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("websites")
        val list = mutableListOf<Pair<String, String>>()
        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            val name = item.getString("name")
            val url = item.getString("url")
            list.add(Pair(name, url))
        }
        return list
    }
}