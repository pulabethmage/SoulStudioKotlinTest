package com.pulasthi.soulstudioskilltestkotlin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.ArrayList

class LoadBookActivity : AppCompatActivity() {

    var Imgv_bookthum: ImageView? = null
    var Tv_bookTitle: TextView? = null
    var Tv_bookDes:TextView? = null

    var progressDialog: ProgressDialog? = null

     val arraybooktitles = ArrayList<String>()
     val arraybookdescriptions = ArrayList<String>()
     val arraybookthumbnails = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_book)


        progressDialog = ProgressDialog(this)

        Imgv_bookthum = findViewById(R.id.book_tumbnail)
        Tv_bookTitle = findViewById(R.id.book_title)
        Tv_bookDes = findViewById(R.id.book_description)


    }

    override fun onResume() {
        super.onResume()

        loadBooks()
    }


    fun loadBooks() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://www.googleapis.com/books/v1/volumes?q=flowers&startIndex=0&maxResults=10"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    progressDialog!!.dismiss()
                    setresponse(response)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error ->
            progressDialog!!.dismiss()
            error.printStackTrace()
        }
        progressDialog!!.setMessage("\tLoading Books...")
        progressDialog!!.show()
        queue.add(request)
        queue.addRequestFinishedListener<Any> { queue.cache.clear() }
    }


    @Throws(JSONException::class)
    fun setresponse(response: JSONObject) {
        val jsonArray = response.getJSONArray("items")
        for (i in 0 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i)
                val jsonVolumeInfo = jsonObject.getJSONObject("volumeInfo")
                val jsonImageLinksObject = jsonVolumeInfo.getJSONObject("imageLinks")

                ///jsonVolumeInfo
                arraybooktitles.add(jsonVolumeInfo.getString("title"))
                arraybookdescriptions.add(jsonVolumeInfo.getString("description"))

                ///jsonImageLinksObject
                arraybookthumbnails.add(jsonImageLinksObject.getString("thumbnail"))

                // Toast.makeText(this,jsonImageLinksObject.getString("thumbnail"), Toast.LENGTH_SHORT).show();
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        initRecyclerSingleAdapter()
    }

    fun initRecyclerSingleAdapter() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.book_list_recyclerview)
        recyclerView.layoutManager = layoutManager
        val adapter = BookListRecyclerViewAdapter(
            this,
            Imgv_bookthum,
            Tv_bookTitle,
            Tv_bookDes,
            arraybookthumbnails,
            arraybooktitles,
            arraybookdescriptions
        )
        recyclerView.adapter = adapter
    }
}