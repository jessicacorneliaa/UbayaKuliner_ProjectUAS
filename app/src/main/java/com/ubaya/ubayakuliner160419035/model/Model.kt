package com.ubaya.ubayakuliner160419035.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tenants")
data class Tenant(
    @PrimaryKey(autoGenerate = false)
    var id:String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "kategori")
    var kategori: String?,
    @ColumnInfo(name = "deskripsi")
    var deskripsi: String?,
    @ColumnInfo(name = "lokasi")
    var lokasi: String?,
    @ColumnInfo(name = "jamBuka")
    var jamBuka: String?,
    @ColumnInfo(name = "pembayaran")
    var pembayaran: String?,
    @ColumnInfo(name = "photoUrl")
    var photoUrl: String?
)

@Entity(tableName = "reviews")
data class Review(
    @ColumnInfo(name = "date")
    var date: String?,
    @ColumnInfo(name = "star")
    var star: Float,
    @ColumnInfo(name = "comment")
    var comment: String?,
    @ColumnInfo(name = "accountId")
    var accountId: String?,
    @ColumnInfo(name = "tenantId")
    var tenantId: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}

data class Promo(
    val id:String?,
    var name: String?,
    var value: String?,
    var validUntil: String?,
    var tnc: String?,
    var photoUrl: String?,
    var tenantid:String?
)

data class Account(
    val id:String?,
    var name: String?,
    var noTelp: String?,
    var email: String?,
    var tglLahir: String?,
    var photoUrl: String?,
)

data class Reservation(
    val id:String?,
    var tenantName: String?,
    var date: String?,
    var time: String?,
    var table: String?,
    var people: Int?,
    var reservationName: String?,
    var contact: String?,
    var status: String?,
    var photoUrl: String?,
)