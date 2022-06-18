package com.ubaya.ubayakuliner160419035.viewmodel

import android.app.Application
import android.icu.number.IntegerWidth
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayakuliner160419035.model.*
import com.ubaya.ubayakuliner160419035.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application:Application):AndroidViewModel(application), CoroutineScope {
    private val job= Job()

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

    val reviewsLD= MutableLiveData<List<Review>>()
    val reviewsLoadError= MutableLiveData<Boolean>()
    val reviewsloadingLD= MutableLiveData<Boolean>()
    val REVIEWTAG= "volleyTag"
    private var reviewsQueue: RequestQueue?= null

    fun addProfile(list:List<Account>){
        launch {
            val db = buildDb(getApplication())
            db.ubayaKulinerDao().insertAllAccount()
        }
    }
    fun addReservation(list: List<Reservation>){
        launch {
            val db = buildDb(getApplication())
            db.ubayaKulinerDao().insertAllReserve()
        }
    }

    fun fetch(tenantId:String){
        tenantsLoadError.value= false
        loadingLD.value= true

        launch {
            val db= Room.databaseBuilder(
                getApplication(),
                UbayaKulinerDatabase::class.java, "ubayakulinerdb"
            ).build()
            tenantsLD.value= db.ubayaKulinerDao().selectTenant(tenantId)
        }
        loadingLD.value= false
    }

    fun fetchPromo(promoId:String){
        launch {
            val db = buildDb(getApplication())
            promosLD.value =  db.ubayaKulinerDao().selectPromo(promoId)
        }
    }

    fun fetchAccount(accountId:String){
        accountsLoadError.value= false
        launch {
            val db = buildDb(getApplication())
            accountsLD.value =  db.ubayaKulinerDao().selectAccount(accountId)
            Log.d("Account:" , accountsLD.value.toString())
        }
    }

    fun addDataReview(list: List<Review>){
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                UbayaKulinerDatabase::class.java, "ubayakulinerdb"
            ).build()
            db.ubayaKulinerDao().insertReviews(*list.toTypedArray())
        }
    }

    fun fetchReviews(tenantId:String){
//        reviewsLoadError.value= false
//        reviewsloadingLD.value= true

        launch {
            val db= Room.databaseBuilder(
                getApplication(),
                UbayaKulinerDatabase::class.java, "ubayakulinerdb"
            ).build()
            reviewsLD.value= db.ubayaKulinerDao().selectReviewsTenant(tenantId)
            Log.d("fetchreview", reviewsLD.value.toString())
        }
//        loadingLD.value= false
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
        promoQueue?.cancelAll(PROMOTAG)
        accountQueue?.cancelAll(ACCOUNTTAG)
        reviewsQueue?.cancelAll(REVIEWTAG)
    }

    override val coroutineContext: CoroutineContext
        get()= job  + Dispatchers.Main
}