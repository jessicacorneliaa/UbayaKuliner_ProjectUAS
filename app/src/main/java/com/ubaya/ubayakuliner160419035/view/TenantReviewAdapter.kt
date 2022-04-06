package com.ubaya.ubayakuliner160419035.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.model.Review
import com.ubaya.ubayakuliner160419035.model.Tenant
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.tenant_review_item.view.*

class TenantReviewAdapter (val tenantReviewList: ArrayList<Review>) : RecyclerView.Adapter<TenantReviewAdapter.TenantReviewViewHolder>(){
    class TenantReviewViewHolder(var view: View): RecyclerView.ViewHolder(view)

    private lateinit var viewModel: DetailViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantReviewViewHolder {
        val infalter= LayoutInflater.from(parent.context)
        val view= infalter.inflate(R.layout.tenant_review_item, parent, false)
        return TenantReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: TenantReviewViewHolder, position: Int) {
        val review= tenantReviewList[position]
        with(holder.view){
            textTanggalReview.text= review.date
            textCommentReview.text= review.comment
            textAccountReview.text= review.accountId
            ratingBarReview.numStars= 5
            ratingBarReview.rating=review.star.toFloat()
        }
    }

    override fun getItemCount()= tenantReviewList.size

    fun updateTenantReviewList(newTenantReviewList: ArrayList<Review>){
        tenantReviewList.clear()
        tenantReviewList.addAll(newTenantReviewList)
        notifyDataSetChanged()
    }
}