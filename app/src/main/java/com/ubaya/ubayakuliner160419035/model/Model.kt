package com.ubaya.ubayakuliner160419035.model

data class Tenant(
    val id:String?,
    var name: String?,
    var kategori: String?,
    var deskripsi: String?,
    var lokasi: String?,
    var jamBuka: String?,
    var pembayaran: String?,
    var photoUrl: String?
)

data class Review(
    val id:String?,
    var date: String?,
    var star: Int,
    var comment: String?,
    var accountId: String?,
    var tenantId: String?
)

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