package com.connection.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {

    lateinit var progressBar:ProgressBar
    lateinit var tvId:TextView
    lateinit var tvUserId:TextView
    lateinit var tvBody:TextView
    lateinit var tvTitle:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        tvId = findViewById(R.id.tvId)
        tvUserId = findViewById(R.id.tvUserId)
        tvBody = findViewById(R.id.tvBody)
        tvTitle = findViewById(R.id.tvTitle)

            progressBar.visibility = View.VISIBLE
            tvId.visibility = View.GONE
            tvUserId.visibility = View.GONE
            tvBody.visibility = View.GONE
            tvTitle.visibility = View.GONE

        getRequest()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getUser()

            }catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}", Toast.LENGTH_LONG).show()
                return@launch
            }catch (e: IOException){
                Toast.makeText(applicationContext,"app error ${e.message}", Toast.LENGTH_LONG).show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){

                        progressBar.visibility = View.GONE
                        tvId.visibility = View.VISIBLE
                        tvUserId.visibility = View.VISIBLE
                        tvBody.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE

                        tvId.text = "id: ${ response.body()!!.id}"
                        tvUserId.text = "user id: ${ response.body()!!.userId}"
                        tvTitle.text = response.body()!!.title
                        tvBody.text = response.body()!!.body

                }
            }
        }
    }
}