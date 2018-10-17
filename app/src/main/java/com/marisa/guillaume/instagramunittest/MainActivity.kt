package com.marisa.guillaume.instagramunittest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() , InstaImagesListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getImages(v : View){
        getInstaImages(this, this)
    }

    override fun onImagesReceived(src: java.util.ArrayList<String>?) {
        loadPicture(ArrayList(src))
    }

    fun loadPicture(src : ArrayList<String>){
        runOnUiThread {

            Picasso.with(this@MainActivity)
                    .load(src[0])//optional                       //optional
                    .into(findViewById<ImageView>(R.id.imageView))

            Picasso.with(this@MainActivity)
                    .load(src[1])//optional                       //optional
                    .into(findViewById<ImageView>(R.id.imageView1))

            Picasso.with(this@MainActivity)
                    .load(src[2])//optional                       //optional
                    .into(findViewById<ImageView>(R.id.imageView2))

            /*Picasso.with(this@MainActivity)
                    .load(src[3])//optional                       //optional
                    .into(findViewById<ImageView>(R.id.imageView3))

            Picasso.with(this@MainActivity)
                    .load(src[4])//optional                       //optional
                    .into(findViewById<ImageView>(R.id.imageView4))

            Picasso.with(this@MainActivity)
                    .load(src[5])//optional                       //optional
                    .into(findViewById<ImageView>(R.id.imageView5))*/
        }
    }

}
