package com.ubaya.ubayakuliner160419035.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.TenantReviewItemBinding
import com.ubaya.ubayakuliner160419035.databinding.TenatListItemBinding
import com.ubaya.ubayakuliner160419035.model.Review
import com.ubaya.ubayakuliner160419035.model.Tenant
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.tenant_review_item.view.*

class TenantReviewAdapter (val tenantReviewList: ArrayList<Review>) : RecyclerView.Adapter<TenantReviewAdapter.TenantReviewViewHolder>(){
    class TenantReviewViewHolder(var view: TenantReviewItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantReviewViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TenantReviewItemBinding>(inflater,R.layout.tenant_review_item, parent, false)
        return TenantReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: TenantReviewViewHolder, position: Int) {
        holder.view.review = tenantReviewList[position]
//        val review= tenantReviewList[position]
//        Log.d("itemrev",review.date.toString())
//
//        with(holder.view){
//            textTanggalReview.text= review.date
//            textCommentReview.text= review.comment
//            textAccountReview.text= review.accountId
//            ratingBarReview.numStars= 5
//            ratingBarReview.rating= review.star
//        }
    }

    override fun getItemCount()= tenantReviewList.size

    fun updateTenantReviewList(newTenantReviewList: List<Review>){
        tenantReviewList.clear()
        tenantReviewList.addAll(newTenantReviewList)
        notifyDataSetChanged()
    }
}