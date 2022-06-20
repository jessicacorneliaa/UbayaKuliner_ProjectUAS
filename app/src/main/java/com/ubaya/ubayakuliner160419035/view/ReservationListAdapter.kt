package com.ubaya.ubayakuliner160419035.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.PromoListItemBinding
import com.ubaya.ubayakuliner160419035.databinding.ReservationListItemBinding
import com.ubaya.ubayakuliner160419035.model.Reservation
import com.ubaya.ubayakuliner160419035.util.loadImage
import kotlinx.android.synthetic.main.reservation_list_item.view.*

class ReservationListAdapter (val reservationList: ArrayList<Reservation>) :RecyclerView.Adapter<ReservationListAdapter.ReservationViewHolder>(){
    class ReservationViewHolder(var view: ReservationListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ReservationListItemBinding>(inflater,R.layout.reservation_list_item, parent, false)
        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {

        holder.view.reservation = reservationList[position]

//        val reservation= reservationList[position]
//        with(holder.view){
//            textReservationTenant.text= reservation.tenantName
//            textReservationID.text= reservation.idReservation.toString()
//            textReservationDate.text= "Date ${reservation.date}"
//            textReservationTime.text= "Time ${reservation.time}"
//            textReservationTable.text= "Table ${reservation.table}"
//            textReservationPeople.text= "Number of person ${reservation.people.toString()}"
//            textReservationName.text= reservation.reservationName
//            textReservationContact.text= reservation.contact
//            textReservationStatus.text= reservation.status
//            imageViewReservation.loadImage(reservation.photoUrl.toString(), progressLoadReservationImage)
//        }
    }

    override fun getItemCount()=reservationList.size

    fun updateReservationList(newReservationList: ArrayList<Reservation>){
        reservationList.clear()
        reservationList.addAll(newReservationList)
        notifyDataSetChanged()
    }
}