package com.gitter.ekran

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.telecom.Call
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.gitter.R
import kotlinx.android.synthetic.main.szczegoly.*

class SzczegolyRepo: AppCompatActivity() {

    companion object {

        val URL_ = "url"
        val TYTUL_ = "tytul"

       fun getIntent(aktywnosc: Activity, tytul: String?, url: String?): Intent {
            val intent = Intent(aktywnosc, SzczegolyRepo::class.java)
           intent.putExtra(URL_, url)
           intent.putExtra(TYTUL_, tytul)
           return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.szczegoly)
        setSupportActionBar(toolbar1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val url = intent.extras?.getString(URL_)
//        val tytul = intent.extras?.getString(TYTUL_)
        if (url != null) {
            webview.loadUrl(url)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item?.let {
            if(item.itemId == android.R.id.home) {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}