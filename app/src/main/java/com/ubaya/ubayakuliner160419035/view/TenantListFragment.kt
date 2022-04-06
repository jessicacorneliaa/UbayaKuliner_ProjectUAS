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
import kotlinx.android.synthetic.main.fragment_tenant_list.*

class TenantListFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private val tenantListAdapter= TenantListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tenant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recView.layoutManager= LinearLayoutManager(context)
        recView.adapter=tenantListAdapter

        observeViewModel()

        refreshLayoutPromo.setOnRefreshListener {
            recView.visibility= View.GONE
            textError.visibility= View.GONE
            progressLoad.visibility= View.VISIBLE
            viewModel.refresh()
            refreshLayoutPromo.isRefreshing= false
        }
    }

    private fun observeViewModel() {
        viewModel.tenantsLD.observe(viewLifecycleOwner){
            tenantListAdapter.updateTenantList(it)
        }
        viewModel.tenantsLoadError.observe(viewLifecycleOwner){
            textError.visibility= if(it)View.VISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner){
            if(it){
                recView.visibility= View.GONE
                progressLoad.visibility= View.VISIBLE
            }else{
                recView.visibility= View.VISIBLE
                progressLoad.visibility= View.GONE
            }
        }
    }
}