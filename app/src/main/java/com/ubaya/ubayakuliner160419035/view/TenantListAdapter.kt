package com.ubaya.ubayakuliner160419035.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.model.Tenant
import com.ubaya.ubayakuliner160419035.util.loadImage
import kotlinx.android.synthetic.main.tenat_list_item.view.*

class TenantListAdapter (val tenantList: ArrayList<Tenant>) :RecyclerView.Adapter<TenantListAdapter.TenantViewHolder>(){
    class TenantViewHolder(var view:View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantViewHolder {
        val infalter= LayoutInflater.from(parent.context)
        val view= infalter.inflate(R.layout.tenat_list_item, parent, false)
        return TenantViewHolder(view)
    }

    override fun onBindViewHolder(holder: TenantViewHolder, position: Int) {
        val tenant= tenantList[position]
        Log.d("tenant name", tenant.name.toString())
        with(holder.view){
            textNamaTenant.text= tenant.name
            textKategori.text= tenant.kategori
            imageViewTenant.loadImage(tenant.photoUrl.toString(), progressLoadTenantImage)

            val tenantID= tenant.id
            buttonDetail.setOnClickListener {
                val action= TenantListFragmentDirections.actionTenantDetail(tenantID.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount()= tenantList.size

    fun updateTenantList(newTenantList: ArrayList<Tenant>){
        tenantList.clear()
        tenantList.addAll(newTenantList)
        notifyDataSetChanged()
    }
}