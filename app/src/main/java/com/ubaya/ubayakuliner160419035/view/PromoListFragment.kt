package com.ubaya.ubayakuliner160419035.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_promo_list.*
import kotlinx.android.synthetic.main.fragment_tenant_list.*
import kotlinx.android.synthetic.main.fragment_tenant_list.refreshLayoutPromo

class PromoListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val promoListAdapter= PromoListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refreshPromo()

        recViewPromo.layoutManager= LinearLayoutManager(context)
        recViewPromo.adapter=promoListAdapter

        observeViewModel()

        refreshLayoutPromo.setOnRefreshListener {
            recViewPromo.visibility= View.GONE
            textErrorPromo.visibility= View.GONE
            progressLoadPromo.visibility= View.VISIBLE
            viewModel.refreshPromo()
            refreshLayoutPromo.isRefreshing= false
        }
    }
    private fun observeViewModel() {
        viewModel.promosLD.observe(viewLifecycleOwner){
            promoListAdapter.updatePromoList(it)
        }
        viewModel.promosLoadError.observe(viewLifecycleOwner){
            textErrorPromo.visibility= if(it)View.VISIBLE else View.GONE
        }
        viewModel.promosloadingLD.observe(viewLifecycleOwner){
            if(it){
                recViewPromo.visibility= View.GONE
                progressLoadPromo.visibility= View.VISIBLE
            }else{
                recViewPromo.visibility= View.VISIBLE
                progressLoadPromo.visibility= View.GONE
            }
        }
    }
}