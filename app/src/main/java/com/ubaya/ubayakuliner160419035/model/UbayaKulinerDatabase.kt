package com.ubaya.ubayakuliner160419035.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Tenant::class, Review::class), version = 1)
abstract class UbayaKulinerDatabase:RoomDatabase() {
    abstract fun ubayaKulinerDao(): UbayaKulinerDao

    companion object{
        @Volatile private var instance:UbayaKulinerDatabase?=null
        // volatile -> data instance dapat otomatis diakses/dibaca oleh thread lain ketika dibutuhkan
        private val LOCK= Any()

        //Membuat database
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
                UbayaKulinerDatabase::class.java, "ubayakulinerdb").build()

        // Memastikan bahwa object tododb adalah sigleton
        operator fun invoke(context: Context){
            if(instance!=null){
                synchronized(LOCK){
                    instance?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}