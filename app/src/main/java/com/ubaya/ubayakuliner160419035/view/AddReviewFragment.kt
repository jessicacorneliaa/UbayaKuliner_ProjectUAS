package com.ubaya.ubayakuliner160419035.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.util.loadImage
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import com.ubaya.ubayakuliner160419035.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_add_review.*
import kotlinx.android.synthetic.main.fragment_tenant_detail.*

class AddReviewFragment : Fragment() {
    private lateinit var viewModel:DetailViewModel
    private val tenantListAdapter= TenantListAdapter(arrayListOf())

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

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.tenantsLD.observe(viewLifecycleOwner){

            var tenant= it
            imageViewImageReview.loadImage(tenant.photoUrl.toString(), progressLoadImageAddReview)
        }
    }

}