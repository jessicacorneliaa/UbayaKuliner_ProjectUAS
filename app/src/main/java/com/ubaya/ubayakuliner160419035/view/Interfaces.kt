package com.ubaya.ubayakuliner160419035.view

import android.view.View
import com.ubaya.ubayakuliner160419035.model.Account
import com.ubaya.ubayakuliner160419035.model.Review

interface ButtonAddReservationClickListener{
    fun onButtonAddReservationClick(v:View)
}

interface ButtonToAddReservationClickListener{
    fun onButtonToAddReservationClick(v:View)
}

interface ButtonDetailTenantClickListener{
    fun onButtonDetailTenantClick(v: View)
}

interface ButtonDetailPromoClickListener{
    fun onButtonDetailPromoClick(v: View)
}

interface ButtonWriteReviewClickListener{
    fun onButtonWriteReviewClick(v: View)
}

interface ButtonAddReviewClickListener{
    fun onButtonAddReviewClick(v: View)
}

interface DateTimeClickListener{
    fun onDateClick(v: View)
    fun onTimeClick(v: View)
}

interface ButtonEditAccountClickListener{
    fun onButtonEditAccountClick(v: View,obj:Account)
}