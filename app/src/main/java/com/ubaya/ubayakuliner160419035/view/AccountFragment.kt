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
        var username="johnK28"
        viewModel.fetchAccount(username)

        observeViewModel()

        buttonEditAccount.setOnClickListener {
            val action= AccountFragmentDirections.actionEditAccount()
            Navigation.findNavController(it).navigate(action)
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