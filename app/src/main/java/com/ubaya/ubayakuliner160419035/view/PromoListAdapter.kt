package com.ubaya.ubayakuliner160419035.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.PromoListItemBinding
import com.ubaya.ubayakuliner160419035.databinding.TenatListItemBinding
import com.ubaya.ubayakuliner160419035.model.Promo
import com.ubaya.ubayakuliner160419035.util.loadImage
import kotlinx.android.synthetic.main.promo_list_item.view.*

class PromoListAdapter (val promoList: ArrayList<Promo>) :RecyclerView.Adapter<PromoListAdapter.PromoViewHolder>(){
    class PromoViewHolder(var view: PromoListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<PromoListItemBinding>(inflater,R.layout.promo_list_item, parent, false)
        return PromoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.view.promo = promoList[position]
//        val promo= promoList[position]
//        Log.d("promo name", promo.name.toString())
//        with(holder.view){
//            textNamaVoucher.text= promo.name
//            textValue.text= promo.value
//            textValidUntil.text= promo.validUntil
//            imageViewPromo.loadImage(promo.photoUrl.toString(), progressLoadPromoList)
//
//            val promoID= promo.idPromo
//            buttonRedeem.setOnClickListener {
//                val action= PromoListFragmentDirections.actionPromoDetailFragment(promoID.toString())
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
    }

    override fun getItemCount()= promoList.size

    fun updatePromoList(newPromoList: List<Promo>){
        promoList.clear()
        promoList.addAll(newPromoList)
        notifyDataSetChanged()
    }


}