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
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_review, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.listener = this

        dataBinding.review = Review("", 0F,"","","")

        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        var tenantId=""
        if(arguments != null){
            tenantId= AddReviewFragmentArgs.fromBundle(requireArguments()).tenantId
        }
        viewModel.fetch(tenantId)

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
        var id = shared?.getString(AccountFragment.SHARED_ACCOUNT_ID, null)

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)

        dataBinding.review?.let {
            Log.d("check",v.tag.toString())
            val tag = v.tag.toString().split(",")
            val tenantId = tag[0]
            val accountid = id

            it.accountId = accountid
            it.tenantId = tenantId
            it.date = formatted
            var list = listOf(it)

//            Log.d("test",review.toString())
            viewModel.addDataReview(list)
            Toast.makeText(v.context, "Your review has been successfully added!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
    }

}