package com.ubaya.ubayakuliner160419035.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.FragmentAddReservationBinding
import com.ubaya.ubayakuliner160419035.util.loadImage
import kotlinx.android.synthetic.main.fragment_add_reservation.*
class AddReservationFragment : Fragment(),ButtonAddReservationClickListener {
    private lateinit var dataBinding:FragmentAddReservationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate<FragmentAddReservationBinding>(inflater,R.layout.fragment_add_reservation, container, false)
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var photoUrl= "https://iccadubai.ae/stockpot/wp-content/uploads/sites/2/2020/06/Culinary-Insights-The-UAE-Food.jpg"
        imageViewImageReservation.loadImage(photoUrl, progressLoadImageAddReservation)
    }

    override fun onButtonAddReservationClick(v: View) {
        Log.d("Update", "Run Add Here")
    }

}