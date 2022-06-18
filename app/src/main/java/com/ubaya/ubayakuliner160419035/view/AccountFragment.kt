package com.ubaya.ubayakuliner160419035.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.model.Account
import com.ubaya.ubayakuliner160419035.util.loadImage
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import com.ubaya.ubayakuliner160419035.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_promo_detail.*
import kotlinx.android.synthetic.main.fragment_tenant_detail.*
import kotlinx.android.synthetic.main.fragment_tenant_list.*

class AccountFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        var account1= Account("anna_","Anna", "081005102223", "ann_10@gmail.com", "10 Desember 1990", "photoUrl\":\"https://images.unsplash.com/photo-1474978528675-4a50a4508dc3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fHByb2ZpbGV8ZW58MHx8MHx8&w=1000&q=80")
        var account2 = Account("johnK28", "John","082661382109", "john28@gmail.com", "28 Maret 2001", "https://images.unsplash.com/photo-1529665253569-6d01c0eaf7b6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cHJvZmlsZXxlbnwwfHwwfHw%3D&w=1000&q=80")
        val listProfile = listOf(account1, account2)
        viewModel.addProfile(listProfile)

        var username="johnK28"
        viewModel.fetchAccount(username)

        observeViewModel()

        buttonEditAccount.setOnClickListener {

        }
    }

    private fun observeViewModel() {
        viewModel.accountsLD.observe(viewLifecycleOwner){
            textAccountName.setText(it.name)
            textAccountEmail.setText(it.email)
            textAccountPhone.setText(it.noTelp)
            textAccountTgl.setText(it.tglLahir)
            imageViewAccount.loadImage(it.photoUrl.toString(), progressLoadImageAccount)
        }
    }
}