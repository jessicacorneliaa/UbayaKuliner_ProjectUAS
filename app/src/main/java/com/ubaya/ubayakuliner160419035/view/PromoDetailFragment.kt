package com.ubaya.ubayakuliner160419035.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.util.loadImage
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_promo_detail.*
import kotlinx.android.synthetic.main.fragment_tenant_detail.*

class PromoDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
            var promo= it
            textNamaVoucherDetail.setText(promo.name)
            textValueDetail.setText(promo.value)
            textTncDetail.setText(promo.tnc)
            textValidUntilDetail.setText(promo.validUntil)
            imageViewPromoDetail.loadImage(promo.photoUrl.toString(), progressLoadImagePromoDetail)
        }
    }

}