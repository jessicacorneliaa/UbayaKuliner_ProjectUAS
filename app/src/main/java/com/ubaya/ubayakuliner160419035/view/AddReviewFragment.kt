package com.ubaya.ubayakuliner160419035.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.model.Review
import com.ubaya.ubayakuliner160419035.util.loadImage
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import com.ubaya.ubayakuliner160419035.viewmodel.ListViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_add_review.*
import kotlinx.android.synthetic.main.fragment_tenant_detail.*
import java.text.SimpleDateFormat
import java.util.*

class AddReviewFragment : Fragment() {
    private lateinit var viewModel:DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        var tenantId=""
        if(arguments != null){
            tenantId= AddReviewFragmentArgs.fromBundle(requireArguments()).tenantId
        }
        viewModel.fetch(tenantId)

        val format= SimpleDateFormat("dd/M/yyyy")
        var date = format.format(Date())
        var rating= ratingBar.rating
        var review= Review(date, rating, editComment.toString(), "A001", tenantId)
        var listReview= listOf(review)

        buttonSubmitReview.setOnClickListener{
            viewModel.addDataReview(listReview)
        }


        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.tenantsLD.observe(viewLifecycleOwner){

            var tenant= it
            imageViewImageReview.loadImage(tenant.photoUrl.toString(), progressLoadImageAddReview)
        }
    }

}