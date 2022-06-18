package com.ubaya.ubayakuliner160419035.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.TenatListItemBinding
import com.ubaya.ubayakuliner160419035.model.Tenant
import com.ubaya.ubayakuliner160419035.util.loadImage
import kotlinx.android.synthetic.main.tenat_list_item.view.*

class TenantListAdapter (val tenantList: ArrayList<Tenant>) :RecyclerView.Adapter<TenantListAdapter.TenantViewHolder>(), ButtonDetailTenantClickListener{
    class TenantViewHolder(var view:TenatListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TenatListItemBinding>(inflater,R.layout.tenat_list_item, parent, false)
        return TenantViewHolder(view)
    }

    override fun onBindViewHolder(holder: TenantViewHolder, position: Int) {
        holder.view.tenant = tenantList[position]
        holder.view.listener = this
//        val tenant= tenantList[position]
//        Log.d("tenantname", tenant.name.toString())
//
//        with(holder.view){
//            textNamaTenant.text= tenant.name.toString()
//            textKategori.text= tenant.kategori.toString()
//            imageViewTenant.loadImage(tenant.photoUrl.toString(), progressLoadTenantImage)
//
//            val tenantID= tenant.id.toString()
//            Log.d("tenantid", tenantID)
//            buttonDetail.setOnClickListener {
//                val action= TenantListFragmentDirections.actionTenantDetail(tenantID)
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
    }

    override fun getItemCount()= tenantList.size

    fun updateTenantList(newTenantList: List<Tenant>){
        tenantList.clear()
        tenantList.addAll(newTenantList)

        notifyDataSetChanged()
    }

    override fun onButtonDetailTenantClick(v: View) {
        val action= TenantListFragmentDirections.actionTenantDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}