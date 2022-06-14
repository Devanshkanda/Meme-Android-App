package com.example.meme_app

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity() : AppCompatActivity(), android.os.Parcelable {

    private var currentImageUrl: String? = null

    constructor(parcel: android.os.Parcel) : this() {
        currentImageUrl = parcel.readString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
    }

    private fun loadMeme(){

        val progressbar = findViewById<ProgressBar>(R.id.progressBar)
        progressbar.visibility = View.VISIBLE

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        val image = findViewById<ImageView>(R.id.memeImage)

    // Request a string response from the provided URL.
        val jSONOBJrequest = JsonObjectRequest(Request.Method.GET,url,null,
            { response ->
               currentImageUrl = response.getString("url")

                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                            progressbar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        progressbar.visibility = View.GONE
                        return false
                    }
                }).into(image)
            },
            { Log.d("error",it.localizedMessage)}
            )

        queue.add(jSONOBJrequest)
        //Mysingleton.getinstance(this).addtoRequestqueue()
    }
    fun shareMeme(view: View) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"

        intent.putExtra(Intent.EXTRA_TEXT,"Hey , Ya come look at this $currentImageUrl")

        val chooser = Intent.createChooser(intent,"Hey , Share this Meme ...")

        startActivity(chooser)

    }
    fun nextMeme(view: View) {

        loadMeme()

    }

    override fun writeToParcel(parcel: android.os.Parcel, flags: Int) {
        parcel.writeString(currentImageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : android.os.Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: android.os.Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}