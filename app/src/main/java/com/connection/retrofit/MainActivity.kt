package com.connection.retrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.connection.retrofit.models.User
import com.google.android.material.snackbar.Snackbar
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

        //getRequest()

        postRequest()
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

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    private fun postRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                //val user = User("new body",null,"new title",23)
                RetrofitInstance.api.createUrlPost(23,"url title","url body")

            }catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }catch (e: IOException){
                Toast.makeText(applicationContext,"app error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){

                        progressBar.visibility = View.GONE
                        tvId.visibility = View.VISIBLE
                        tvUserId.visibility = View.VISIBLE
                        tvBody.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE

                        tvId.text = "id: ${ response.body()!!.id.toString() }"
                        tvUserId.text = "user id: ${ response.body()!!.userId}"
                        tvTitle.text = "title: ${ response.body()!!.title}"
                        tvBody.text = "body: ${ response.body()!!.body}"

                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun putRequest(){
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                val user = User("put body",null,null,23)
                RetrofitInstance.api.putPost(23,user)

            }catch (e: HttpException){
                Toast.makeText(applicationContext,"http error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }catch (e: IOException){
                Toast.makeText(applicationContext,"app error ${e.message}",Toast.LENGTH_LONG).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){

                        progressBar.visibility = View.GONE
                        tvId.visibility = View.VISIBLE
                        tvUserId.visibility = View.VISIBLE
                        tvBody.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE

                        tvId.text = "id: ${ response.body()!!.id.toString() }"
                        tvUserId.text = "user id: ${ response.body()!!.userId}"
                        tvTitle.text = "title: ${ response.body()!!.title}"
                        tvBody.text = "body: ${ response.body()!!.body}"

                }
            }
        }
    }
}