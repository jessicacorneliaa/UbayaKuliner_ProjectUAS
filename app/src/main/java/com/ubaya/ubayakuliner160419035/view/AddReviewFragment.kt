package com.ubaya.ubayakuliner160419035.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.FragmentAddReviewBinding
import com.ubaya.ubayakuliner160419035.model.Review
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_add_review.*
import kotlinx.android.synthetic.main.fragment_tenant_detail.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.log

class AddReviewFragment : Fragment(), ButtonAddReviewClickListener {
    private lateinit var viewModel:DetailViewModel
    private lateinit var dataBinding: FragmentAddReviewBinding

    companion object{
        val SHARED_ACCOUNT_ID="SHARED_ACCOUNT_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentAddReviewBinding>(inflater, R.layout.fragment_add_review, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.listener = this

        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        var tenantId=""
        if(arguments != null){
            tenantId= AddReviewFragmentArgs.fromBundle(requireArguments()).tenantId
        }
        viewModel.fetch(tenantId)

//        val format= SimpleDateFormat("dd/M/yyyy")
//        var date = format.format(Date())
//        var rating= ratingBar.rating
//        var review= Review(date, rating, editComment.toString(), "A001", tenantId)
//        var listReview= listOf(review)
//
//        buttonSubmitReview.setOnClickListener{
//            viewModel.addDataReview(listReview)
//        }


        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.tenantsLD.observe(viewLifecycleOwner){

            var tenant= it
            dataBinding.tenant = tenant
//            imageViewImageReview.loadImage(tenant.photoUrl.toString(), progressLoadImageAddReview)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onButtonAddReviewClick(v: View) {
        //Retrieve the saved account id
        var sharedId = context?.packageName
        var shared = context?.getSharedPreferences(sharedId, Context.MODE_PRIVATE)
        var playerId = shared?.getString(AccountFragment.SHARED_ACCOUNT_ID, null)

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)

        Log.d("check",v.tag.toString())
        val tag = v.tag.toString().split(",")
        val tenantId = tag[0]
        val accountid = playerId

        var review =Review(formatted, ratingBar.rating, editComment.text.toString(), accountid, tenantId)
        Log.d("test",review.toString())
        viewModel.addDataReview(listOf(review))
        Toast.makeText(v.context, "Your review has been successfully added!", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }

}