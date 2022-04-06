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
import com.ubaya.ubayakuliner160419035.model.Account
import com.ubaya.ubayakuliner160419035.model.Promo
import com.ubaya.ubayakuliner160419035.model.Review
import com.ubaya.ubayakuliner160419035.model.Tenant

class DetailViewModel(application:Application):AndroidViewModel(application) {
    val tenantsLD= MutableLiveData<Tenant>()
    val tenantsLoadError= MutableLiveData<Boolean>()
    val loadingLD= MutableLiveData<Boolean>()
    val TAG= "volleyTag"
    private var queue: RequestQueue?= null

    val promosLD= MutableLiveData<Promo>()
    val promosLoadError= MutableLiveData<Boolean>()
    val promosloadingLD= MutableLiveData<Boolean>()
    val PROMOTAG= "volleyTag"
    private var promoQueue: RequestQueue?= null

    val accountsLD= MutableLiveData<Account>()
    val accountsLoadError= MutableLiveData<Boolean>()
    val accountsloadingLD= MutableLiveData<Boolean>()
    val ACCOUNTTAG= "volleyTag"
    private var accountQueue: RequestQueue?= null

    val reviewsLD= MutableLiveData<ArrayList<Review>>()
    val reviewsLoadError= MutableLiveData<Boolean>()
    val reviewsloadingLD= MutableLiveData<Boolean>()
    val REVIEWTAG= "volleyTag"
    private var reviewsQueue: RequestQueue?= null

    fun fetch(tenantId:String){
        tenantsLoadError.value= false
        loadingLD.value= true

        queue= Volley.newRequestQueue(getApplication())

        Log.d("Tenant ID", tenantId)

        val url= "http://192.168.0.14/anmp/ubayaKuliner160419035.php?data=tenants&id=$tenantId"
        val stringRequest= StringRequest(
            Request.Method.GET, url,
            {
                val result= Gson().fromJson<Tenant?>(it, Tenant::class.java)
                Log.d("result", result.toString())
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

    fun fetchPromo(promoId:String){
        promosLoadError.value= false
        promosloadingLD.value= true

        promoQueue= Volley.newRequestQueue(getApplication())

        Log.d("Promo ID det", promoId)

        val url= "http://192.168.0.14/anmp/ubayaKuliner160419035.php?data=promos&id=$promoId"
        val stringRequest= StringRequest(
            Request.Method.GET, url,
            {
                val result= Gson().fromJson<Promo>(it, Promo::class.java)
                Log.d("resultpromo", result.toString())
                promosLD.value= result
                promosloadingLD.value= false
                Log.d("showvolley", it)
            },
            {
                promosloadingLD.value= false
                promosLoadError.value= true
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag= "PROMOTAG"
        }
        promoQueue?.add(stringRequest)
    }

    fun fetchAccount(accountId:String){
        accountsLoadError.value= false
        accountsloadingLD.value= true

        accountQueue= Volley.newRequestQueue(getApplication())

        Log.d("Account ID det", accountId)

        val url= "http://192.168.0.14/anmp/ubayaKuliner160419035.php?data=accounts&id=$accountId"
        val stringRequest= StringRequest(
            Request.Method.GET, url,
            {
                val result= Gson().fromJson<Account>(it, Account::class.java)
                Log.d("resultaccount", result.toString())
                accountsLD.value= result
                accountsloadingLD.value= false
                Log.d("showvolley", it)
            },
            {
                accountsloadingLD.value= false
                accountsLoadError.value= true
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag= "ACCOUNTTAG"
        }
        accountQueue?.add(stringRequest)
    }

    fun fetchReviews(tenantId:String){
        reviewsLoadError.value= false
        reviewsloadingLD.value= true

        reviewsQueue= Volley.newRequestQueue(getApplication())


        val url= "http://192.168.0.14/anmp/ubayaKuliner160419035.php?data=reviews&id=$tenantId"
        val stringRequest= StringRequest(
            Request.Method.GET, url,
            {
                val sType= object : TypeToken<ArrayList<Review>>(){}.type
                val result= Gson().fromJson<ArrayList<Review>>(it, sType)
                Log.d("result", result.toString())
                reviewsLD.value= result
                reviewsloadingLD.value= false
                Log.d("showvolley", it)
            },
            {
                reviewsloadingLD.value= false
                reviewsLoadError.value= true
                Log.d("errorvolley", it.toString())
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
        accountQueue?.cancelAll(ACCOUNTTAG)
        reviewsQueue?.cancelAll(REVIEWTAG)
    }
}