package com.ubaya.ubayakuliner160419035.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.FragmentAccountBinding
import com.ubaya.ubayakuliner160419035.databinding.FragmentAddReviewBinding
import com.ubaya.ubayakuliner160419035.model.Account
import com.ubaya.ubayakuliner160419035.util.loadImage
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import com.ubaya.ubayakuliner160419035.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_promo_detail.*
import kotlinx.android.synthetic.main.fragment_tenant_detail.*
import kotlinx.android.synthetic.main.fragment_tenant_list.*

class AccountFragment : Fragment(), ButtonEditAccountClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentAccountBinding

    companion object{
        val SHARED_ACCOUNT_ID="SHARED_ACCOUNT_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentAccountBinding>(inflater, R.layout.fragment_account, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.listener = this

        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
//        var account1= Account("anna_","Anna", "081005102223", "ann_10@gmail.com", "10 Desember 1990", "https://images.unsplash.com/photo-1474978528675-4a50a4508dc3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fHByb2ZpbGV8ZW58MHx8MHx8&w=1000&q=80")
//        var account2 = Account("johnK28", "John","082661382109", "john28@gmail.com", "28 Maret 2001", "https://images.unsplash.com/photo-1529665253569-6d01c0eaf7b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZmlsZXxlbnwwfHwwfHw%3D&w=1000&q=80")
//        val listProfile = listOf(account1, account2)
//        viewModel.addProfile(listProfile)

        //Retrieve the saved account id
        var sharedId = context?.packageName
        var shared = context?.getSharedPreferences(sharedId, Context.MODE_PRIVATE)
        var playerId = shared?.getString(SHARED_ACCOUNT_ID, null)

//        var username=account1.idAccount
        viewModel.fetchAccount(playerId.toString())

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.accountsLD.observe(viewLifecycleOwner){
            dataBinding.account = it
        }
    }

    override fun onButtonEditAccountClick(v: View, obj: Account) {
//        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}