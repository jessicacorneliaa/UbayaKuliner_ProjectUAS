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
import com.ubaya.ubayakuliner160419035.model.Account
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
    }

    override fun getItemCount()= tenantList.size

    fun updateTenantList(newTenantList: List<Tenant>){
        tenantList.clear()
        tenantList.addAll(newTenantList)

        notifyDataSetChanged()
    }

    override fun onButtonDetailTenantClick(v: View) {
        var string = v.tag.toString().split("+")
        var id = string[0]
        var name = string[1]

        val action= TenantListFragmentDirections.actionTenantDetail(id, name)
        Navigation.findNavController(v).navigate(action)
    }
}