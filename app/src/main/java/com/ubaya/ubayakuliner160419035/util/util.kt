package com.ubaya.ubayakuliner160419035.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.model.UbayaKulinerDatabase
import java.lang.Exception

// fun load Image

fun ImageView.loadImage(url:String, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility= View.GONE
            }

            override fun onError(e: Exception?) {
            }

        })
}

val DB_NAME = "newtododb"

//build db
fun buildDb(context: Context): UbayaKulinerDatabase =
    Room.databaseBuilder(context, UbayaKulinerDatabase::class.java, DB_NAME)
        .addMigrations()
        .build()

@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoURL(view:ImageView, url: String?, pb:ProgressBar){
    if (url != null) {
        view.loadImage(url, pb)
    }
}