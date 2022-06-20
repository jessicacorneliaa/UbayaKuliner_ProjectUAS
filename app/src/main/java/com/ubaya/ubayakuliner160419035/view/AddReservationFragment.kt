package com.ubaya.ubayakuliner160419035.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.FragmentAddReservationBinding
import com.ubaya.ubayakuliner160419035.model.Account
import com.ubaya.ubayakuliner160419035.model.Reservation
import com.ubaya.ubayakuliner160419035.util.UbayaKulinerWorker
import com.ubaya.ubayakuliner160419035.util.loadImage
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import com.ubaya.ubayakuliner160419035.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_add_reservation.*
import kotlinx.android.synthetic.main.fragment_add_reservation.view.*
import java.util.*
import java.util.concurrent.TimeUnit

class AddReservationFragment : Fragment(),ButtonAddReservationClickListener, DateTimeClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var dataBinding:FragmentAddReservationBinding
    private lateinit var viewModel: DetailViewModel
    var year= 0
    var month= 0
    var day= 0
    var hour= 0
    var minute= 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate<FragmentAddReservationBinding>(inflater,R.layout.fragment_add_reservation, container, false)
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var photoUrl= "https://iccadubai.ae/stockpot/wp-content/uploads/sites/2/2020/06/Culinary-Insights-The-UAE-Food.jpg"
        imageViewImageReservation.loadImage(photoUrl, progressLoadImageAddReservation)

        dataBinding.datetimeListener= this
    }

    override fun onButtonAddReservationClick(v: View) {
        Log.d("Update", "Run Add Here")

        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
//        var reservation = Reservation(editTextTenantName.text.toString(), editTextDate.text.toString(), editTextTime.text.toString(), Integer.parseInt(editTextPeople.text.toString()),
//            , editTextPhoneNumber.text.toString(), "Booking" )
//        viewModel.addReservation(listOf(reservation))

        Toast.makeText(v.context, "Your reservation has been successfully added!", Toast.LENGTH_SHORT).show()

        // selisih waktu skrng dgn waktu reservasi
        val calendar= Calendar.getInstance()
        calendar.set(year, month, day, hour, minute, 0)
        val today= Calendar.getInstance()
        val diff= calendar.timeInMillis/ 1000L - today.timeInMillis/ 1000L

        val myWorkReq= OneTimeWorkRequestBuilder<UbayaKulinerWorker>()
            .setInitialDelay(diff, TimeUnit.SECONDS)
            .setInputData(workDataOf(
                "title" to "Reminder",
                "message" to "Here is your reminder of your reservation tomorrow."))
            .build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkReq)
        Navigation.findNavController(v).popBackStack()
    }

    override fun onDateClick(v: View) {
        val calendar= Calendar.getInstance()
        val year= calendar.get(Calendar.YEAR)
        val month= calendar.get(Calendar.MONTH)
        val day= calendar.get(Calendar.DAY_OF_MONTH)
        activity?.let {
            DatePickerDialog(it, this, year, month, day).show()
        }
    }

    override fun onTimeClick(v: View) {
        val calendar= Calendar.getInstance()
        val hour= calendar.get(Calendar.HOUR_OF_DAY)
        val minute= calendar.get(Calendar.MINUTE)
        TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity)).show()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        Calendar.getInstance().let {
            it.set(year, month, day)
            val date= day.toString().padStart(2, '0')+"-"+
                    month.toString().padStart(2, '0')+"-"+
                    year.toString()
            dataBinding.root.editTextDate.setText(date)
            this.year= year
            this.month= month
            this.day= day
        }
    }

    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        val time= hourOfDay.toString().padStart(2, '0')+':'+
                minute.toString().padStart(2, '0')
        dataBinding.root.editTextTime
        this.hour= hourOfDay
        this.minute=minute
    }

}