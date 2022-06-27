package com.ubaya.ubayakuliner160419035.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.ubaya.ubayakuliner160419035.R
import com.ubaya.ubayakuliner160419035.databinding.FragmentAddReservationBinding
import com.ubaya.ubayakuliner160419035.model.Reservation
import com.ubaya.ubayakuliner160419035.util.UbayaKulinerWorker
import com.ubaya.ubayakuliner160419035.util.loadImage
import com.ubaya.ubayakuliner160419035.viewmodel.DetailViewModel
import com.ubaya.ubayakuliner160419035.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_add_reservation.*
import kotlinx.android.synthetic.main.fragment_add_reservation.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class AddReservationFragment : Fragment(),ButtonAddNewReservationClickListener, DateTimeClickListener, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {
    private lateinit var dataBinding:FragmentAddReservationBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelList: ListViewModel
    var tenants : Array<String> = arrayOf()

    var selectedItemString = ""
    var year= 0
    var month= 0
    var day= 0
    var hour= 0
    var minute= 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate<FragmentAddReservationBinding>(inflater,R.layout.fragment_add_reservation, container, false)
//        val v: View = inflater.inflate(R.layout.fragment_add_reservation, container, false)

        val values = arrayOf(
            "Time at Residence",
            "Under 6 months",
            "6-12 months",
            "1-2 years",
            "2-4 years",
            "4-8 years",
            "8-15 years",
            "Over 15 years"
        )
        val spinner = dataBinding.spinnerTenant
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, values)
        adapter.setDropDownViewResource(R.layout.myspinner_item_layout)
        spinner.adapter = adapter

//        return v
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var photoUrl= "https://iccadubai.ae/stockpot/wp-content/uploads/sites/2/2020/06/Culinary-Insights-The-UAE-Food.jpg"
        imageViewImageReservation.loadImage(photoUrl, progressLoadImageAddReservation)
        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetchAccount("anna_")

        dataBinding.datetimeListener = this
        dataBinding.listener = this

        // Instantiate
        dataBinding.reservation = Reservation("","","","","","","Waiting")

//        editTextDate.setOnClickListener {
//            val today = Calendar.getInstance()
//            val year = today.get(Calendar.YEAR)
//            val month = today.get(Calendar.MONTH)
//            val day = today.get(Calendar.DAY_OF_MONTH)
//
//            //Create Date Picker
//            var picker = DatePickerDialog(requireContext(),
//                DatePickerDialog.OnDateSetListener { datePicker, selYear, selMonth, selDay ->
//                    val calendar = Calendar.getInstance()
//                    calendar.set(selYear, selMonth, selDay)
//
//                    var dateFormat = SimpleDateFormat("dd,MMMM,yyyy")
//                    var date = dateFormat.format(calendar.time)
//                    editTextDate.setText(date)
//                },
//                year, month, day)
//            picker.show()
//        }
//
//        editTextTime.setOnClickListener {
//            val calendar= Calendar.getInstance()
//            val hour= calendar.get(Calendar.HOUR_OF_DAY)
//            val minute= calendar.get(Calendar.MINUTE)
//            TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity)).show()
//        }
    }

    override fun onButtonAddNewReservationClick(v: View) {
//        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)

        val calendar= Calendar.getInstance()
        calendar.set(year, month, day-1, hour, minute, 0)

        val today= Calendar.getInstance()
        val diff= calendar.timeInMillis/ 1000L - today.timeInMillis/ 1000L

        // Do Add Reservation here
        dataBinding.reservation?.let {
            val list = listOf(it)
            viewModel.addReservation(list)

            Toast.makeText(v.context, "Your reservation has been successfully added!", Toast.LENGTH_SHORT).show()

            val myWorkReq = OneTimeWorkRequestBuilder<UbayaKulinerWorker>()
                .setInitialDelay(diff, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                        "title" to "Reminder Notification",
                        "message" to "It is now the day before your reservation date",
                        "description" to "Dear, ${it.reservationName}\nWe would like to remind you of your reservation.\nThese are your reservation details:\n" +
                                "Tenant name: ${it.tenantName}\nDate & Time: ${it.date} ${it.time}\n" +
                                "Number of person: ${it.people}\n\nSee you soon!")
                )
                .build()
            WorkManager.getInstance(requireContext()).enqueue(myWorkReq)
            Navigation.findNavController(v).popBackStack()
        }

//        var reservation = Reservation("MCD", editTextDate.text.toString() , editTextTime.text.toString(),
//            editTextPeople.text.toString(), editNamePerson.text.toString(), editTextPhoneNumber.text.toString(), "Booking" )
//        viewModel.addReservation(listOf(reservation))



    }

    override fun onDateClick(v: View) {
        val calendar= Calendar.getInstance()
        calendar.add(Calendar.DATE, +1)
        val year= calendar.get(Calendar.YEAR)
        val month= calendar.get(Calendar.MONTH)
        val day= calendar.get(Calendar.DAY_OF_MONTH)
        activity?.let {
            //DatePickerDialog(it, this, year, month, day+1).show()
            val datePickerDialog = DatePickerDialog(it, this, year, month, day)
            datePickerDialog.datePicker.minDate = calendar.timeInMillis
            datePickerDialog.show()
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
        dataBinding.root.editTextTime.setText(time)
        this.hour= hourOfDay
        this.minute=minute
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var selected= tenants[p2]
        selectedItemString =selected.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}