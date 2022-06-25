package com.ubaya.ubayakuliner160419035.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.FragmentPromoDetailBinding
import com.ubaya.ubayakuliner160419035.util.loadImage
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_promo_detail.*
import kotlinx.android.synthetic.main.fragment_tenant_detail.*

class PromoDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentPromoDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentPromoDetailBinding>(inflater, R.layout.fragment_promo_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        var promoId=""
        if(arguments != null){
            promoId= PromoDetailFragmentArgs.fromBundle(requireArguments()).promoId
        }
        viewModel.fetchPromo(promoId)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.promosLD.observe(viewLifecycleOwner){
//            var promo= it
//            textNamaVoucherDetail.setText(promo.name)
//            textValueDetail.setText(promo.value)
//            textTncDetail.setText(promo.tnc)
//            textValidUntilDetail.setText(promo.validUntil)
//            imageViewPromoDetail.loadImage(promo.photoUrl.toString(), progressLoadImagePromoDetail)
            Log.d("itpromo", it.toString())
            dataBinding.promo = it

        }
    }

}