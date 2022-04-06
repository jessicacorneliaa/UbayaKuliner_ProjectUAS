package com.ubaya.ubayakuliner160419035.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayakuliner160419035.model.Promo
import com.ubaya.ubayakuliner160419035.model.Reservation
import com.ubaya.ubayakuliner160419035.model.Review
import com.ubaya.ubayakuliner160419035.model.Tenant

class ListViewModel(application: Application):AndroidViewModel(application) {
    val tenantsLD= MutableLiveData<ArrayList<Tenant>>()
    val tenantsLoadError= MutableLiveData<Boolean>()
    val loadingLD= MutableLiveData<Boolean>()
    val TAG= "volleyTag"
    private var queue: RequestQueue?= null

    val promosLD= MutableLiveData<ArrayList<Promo>>()
    val promosLoadError= MutableLiveData<Boolean>()
    val promosloadingLD= MutableLiveData<Boolean>()
    val PROMOTAG= "volleyTag"
    private var promoQueue: RequestQueue?= null

    val reservationsLD= MutableLiveData<ArrayList<Reservation>>()
    val reservationsLoadError= MutableLiveData<Boolean>()
    val reservationsloadingLD= MutableLiveData<Boolean>()
    val RESERVATIONTAG= "volleyTag"
    private var reservationQueue: RequestQueue?= null

    val reviewsLD= MutableLiveData<ArrayList<Review>>()
    val reviewsLoadError= MutableLiveData<Boolean>()
    val reviewsloadingLD= MutableLiveData<Boolean>()
    val REVIEWTAG= "volleyTag"
    private var reviewsQueue: RequestQueue?= null

    fun refresh(){
        tenantsLoadError.value= false
        loadingLD.value= true

        queue= Volley.newRequestQueue(getApplication())
        val url= "http://192.168.0.14/anmp/ubayaKuliner160419035.php?data=tenants"

        val stringRequest= StringRequest(
            Request.Method.GET, url,
            {
                val sType= object : TypeToken<ArrayList<Tenant>>(){}.type
                val result= Gson().fromJson<ArrayList<Tenant>>(it, sType)
                tenantsLD.value= result
                loadingLD.value= false
                Log.d("showvolley", it)
            },
            {
                loadingLD.value= false
                tenantsLoadError.value= true
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag= "TAG"
        }
        queue?.add(stringRequest)
    }

    fun refreshPromo(){
        promosLoadError.value= false
        promosloadingLD.value= true

        promoQueue= Volley.newRequestQueue(getApplication())
        val url= "http://192.168.0.14/anmp/ubayaKuliner160419035.php?data=promos"

        val stringRequest= StringRequest(
            Request.Method.GET, url,
            {
                val sType= object : TypeToken<ArrayList<Promo>>(){}.type

                val result= Gson().fromJson<ArrayList<Promo>>(it, sType)
                promosLD.value= result
                promosloadingLD.value= false
                Log.d("showvolleypromo", it)
            },
            {
                promosloadingLD.value= false
                promosLoadError.value= true
                Log.d("errorvolleypromo", it.toString())
            }
        ).apply {
            tag= "PROMOTAG"
        }
        promoQueue?.add(stringRequest)
    }

    fun refreshReservation(){
        reservationsLoadError.value= false
        reservationsloadingLD.value= true

        reservationQueue= Volley.newRequestQueue(getApplication())
        val url= "http://192.168.0.14/anmp/ubayaKuliner160419035.php?data=reservations"

        val stringRequest= StringRequest(
            Request.Method.GET, url,
            {
                val sType= object : TypeToken<ArrayList<Reservation>>(){}.type

                val result= Gson().fromJson<ArrayList<Reservation>>(it, sType)
                reservationsLD.value= result
                reservationsloadingLD.value= false
                Log.d("showvolleyreservation", it)
            },
            {
                reservationsloadingLD.value= false
                reservationsLoadError.value= true
                Log.d("errorvolleyreservation", it.toString())
            }
        ).apply {
            tag= "RESERVATIONTAG"
        }
        reservationQueue?.add(stringRequest)
    }

    fun refreshReview(tenantId:String){
        reviewsLoadError.value= false
        reservationsloadingLD.value= true

        reservationQueue= Volley.newRequestQueue(getApplication())
        val url= "http://192.168.0.14/anmp/ubayaKuliner160419035.php?data=reviews&id=$tenantId"
        val stringRequest= StringRequest(
            Request.Method.GET, url,
            {
                val sType= object : TypeToken<ArrayList<Review>>(){}.type

                val result= Gson().fromJson<ArrayList<Review>>(it, sType)
                reviewsLD.value= result
                reviewsloadingLD.value= false
                Log.d("showvolleyreview", it)
            },
            {
                reservationsloadingLD.value= false
                reservationsLoadError.value= true
                Log.d("errorvolleyreview", it.toString())
            }
        ).apply {
            tag= "REVIEWTAG"
        }
        reviewsQueue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
        promoQueue?.cancelAll(PROMOTAG)
        reservationQueue?.cancelAll(RESERVATIONTAG)
        reviewsQueue?.cancelAll(REVIEWTAG)
    }
}
