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
//import kotlinx.coroutines.android.Main
//import kotlinx.coroutines.android.UI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Wyszukiwanie : AppCompatActivity() {

    private val adapter = Adapter({
        repozytorium ->  val intent = SzczegolyRepo.getIntent(this, repozytorium.nazwa, repozytorium.url)
        startActivity(intent)
    })
    private val api: GithubApi
    private var job: Job = Job()

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

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun przeszukajGithub(text: String) {
        progressBar.visibility = View.VISIBLE

        job.cancel()
        job = GlobalScope.launch(Dispatchers.Default) {
         try {
             val odpowiedz = api.szukaj(text).execute().body()

             kotlinx.coroutines.runBlocking(Dispatchers.Main) {
                 progressBar.visibility = View.INVISIBLE
                 val listaRepozytoriow = odpowiedz?.listaRepozytoriow
                 listaRepozytoriow?.let {
                     println("weszlo")
                     adapter.setRepozytoria(listaRepozytoriow)
                 }
             }
         } catch (e: Throwable) {
             e.printStackTrace()
         }
        }
//        job.cancel()
//        job = launch(CommonPool) {
//            try {
//                val odpowiedz = api.szukaj(text).execute().body()
//
//                run(UI) {
//                    progressBar.visibility = View.INVISIBLE
//                    val listaRepozytoriow = odpowiedz?.listaRepozytoriow
//                    listaRepozytoriow?.let {
//                        adapter.setRepozytoria(listaRepozytoriow)
//                    }
//                }
//            } catch (e: Throwable) {
//                e.printStackTrace()
//            }
//        }
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