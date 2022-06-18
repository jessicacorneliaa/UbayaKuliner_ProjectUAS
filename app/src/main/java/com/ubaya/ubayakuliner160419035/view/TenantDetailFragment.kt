package com.ubaya.ubayakuliner160419035.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.model.Review
import com.ubaya.ubayakuliner160419035.util.loadImage
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_tenant_detail.*

class TenantDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private val tenantReviewAdapter= TenantReviewAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tenant_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        var tenantId=""
        if(arguments != null){
            tenantId= TenantDetailFragmentArgs.fromBundle(requireArguments()).tenantId
        }
        viewModel.fetch(tenantId)
        viewModel.fetchReviews(tenantId)

        recViewReviewDetail.layoutManager= LinearLayoutManager(context)
        recViewReviewDetail.adapter= tenantReviewAdapter

        observeViewModel()

        buttonAddReview.setOnClickListener {
            val action= TenantDetailFragmentDirections.actionAddReviewFragment(tenantId)
            Navigation.findNavController(it).navigate(action)
        }

    }
    private fun observeViewModel() {
        viewModel.tenantsLD.observe(viewLifecycleOwner){

            var tenant= it
            textNamaTenantDetail.setText(tenant.name)
            textKategoriDetail.setText(tenant.kategori)
            textLokasiDetail.setText(tenant.lokasi)
            textJamBukaDetail.setText(tenant.jamBuka)
            textPembayaranDetail.setText(tenant.pembayaran)
            textDeskripsiDetail.setText(tenant.deskripsi)
            imageViewTenantDetail.loadImage(tenant.photoUrl.toString(), progressLoadImageTenantDetail)
        }

        viewModel.reviewsLD.observe(viewLifecycleOwner){
            tenantReviewAdapter.updateTenantReviewList(it)
        }
        viewModel.reviewsLoadError.observe(viewLifecycleOwner){
//            textError.visibility= if(it)View.VISIBLE else View.GONE
        }
        viewModel.reviewsloadingLD.observe(viewLifecycleOwner){
            if(it){
                recViewReviewDetail.visibility= View.GONE
//                progressLoad.visibility= View.VISIBLE
            }else{
                recViewReviewDetail.visibility= View.VISIBLE
//                progressLoad.visibility= View.GONE
            }
        }
    }
}