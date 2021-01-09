package com.gitter.ekran

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gitter.R
import com.gitter.onTextChanged
import com.gitter.polaczenie.GithubApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.android.UI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Wyszukiwanie : AppCompatActivity() {

    private val adapter = Adapter()
    private val api: GithubApi

    init {
        val retrofit =
                Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(GithubApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view.adapter = adapter
        edit_text.onTextChanged {
            text: String -> przeszukajGithub(text)
        }
    }

    fun przeszukajGithub(text: String) {
        progressBar.visibility = View.VISIBLE

//        GlobalScope.launch() {
//         try {
//             val odpowiedz = api.szukaj(text).execute().body()
//
//             runBlocking (Dispatchers.Main) {
//                 progressBar.visibility = View.INVISIBLE
//                 val listaRepozytoriow = odpowiedz?.listaRepozytoriow
//                 listaRepozytoriow?.let {
//                     adapter.setRepozytoria(listaRepozytoriow)
//                 }
//             }
//         } catch (e: Throwable) {
//             e.printStackTrace()
//         }
//        }
        launch(CommonPool) {
            try {
                val odpowiedz = api.szukaj(text).execute().body()

                run(UI) {
                    progressBar.visibility = View.INVISIBLE
                    val listaRepozytoriow = odpowiedz?.listaRepozytoriow
                    listaRepozytoriow?.let {
                        adapter.setRepozytoria(listaRepozytoriow)
                    }
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    //dodaje menu do głównego okna aplikacji
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    //obsługujemy przyciski z menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item1) {
            Toast.makeText(this, "Autorzy aplikacji:\nBartłomiej Nowacki\nMarcin Sarnecki", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item)
        }
    }
}