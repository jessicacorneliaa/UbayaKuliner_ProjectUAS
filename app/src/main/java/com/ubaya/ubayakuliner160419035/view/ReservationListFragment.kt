package com.ubaya.ubayakuliner160419035.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.model.Reservation
import com.ubaya.ubayakuliner160419035.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_promo_list.*
import kotlinx.android.synthetic.main.fragment_reservation_list.*

class ReservationListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val reservationListAdapter= ReservationListAdapter(arrayListOf())

    companion object{
        val SHARED_ACCOUNT_ID="SHARED_ACCOUNT_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedId = context?.packageName
        var shared = context?.getSharedPreferences(sharedId, Context.MODE_PRIVATE)
        var id = shared?.getString(AccountFragment.SHARED_ACCOUNT_ID, null)

        viewModel= ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refreshReservation(id.toString())


        recViewReservation.layoutManager= LinearLayoutManager(context)
        recViewReservation.adapter=reservationListAdapter

        observeViewModel()

        refreshLayoutReservation.setOnRefreshListener {
            recViewReservation.visibility= View.GONE
            textErrorReservation.visibility= View.GONE
            progressLoadReservationList.visibility= View.VISIBLE
            viewModel.refreshReservation(id.toString())
            refreshLayoutReservation.isRefreshing= false
        }

        floatingButtonAddReservation.setOnClickListener {
            val action= ReservationListFragmentDirections.actionAddreservationFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.reservationsLD.observe(viewLifecycleOwner){
            reservationListAdapter.updateReservationList(it as ArrayList<Reservation>)
        }
        viewModel.reservationsLoadError.observe(viewLifecycleOwner){
            textErrorReservation.visibility= if(it)View.VISIBLE else View.GONE
        }
        viewModel.reservationsloadingLD.observe(viewLifecycleOwner){
            if(it){
                recViewReservation.visibility= View.GONE
                progressLoadReservationList.visibility= View.VISIBLE
            }else{
                recViewReservation.visibility= View.VISIBLE
                progressLoadReservationList.visibility= View.GONE
            }
        }
    }
}