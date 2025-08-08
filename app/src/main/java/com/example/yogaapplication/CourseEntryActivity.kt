package com.example.yogaapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.util.LocalePreferences.FirstDayOfWeek.Days
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.yogaapplication.databinding.ActivityCourseEntryBinding
import com.example.yogaapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration.Companion.days

class CourseEntryActivity : AppCompatActivity() {
    lateinit var  binding: ActivityCourseEntryBinding
    private val days = listOf("Select Day of the Week...","Monday","Tuesday","Wednesday","Thursday",
        "Friday")
    private var selectedDay:String = ""
    private var description:String = ""
    private var selectedTime:String = ""
    private var typeOfClass:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.SpnDayOfWeek.onItemSelectedListener =  object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int,
                                        id: Long)
            {
                selectedDay = days[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?)
            {
            }

        }
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,days)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SpnDayOfWeek.adapter= arrayAdapter

//        val calendar = Calendar.getInstance()
//        binding.txttime.setOnClickListener{
//            var timepickdialog = TimePickerDialog(this,
//                {
//                    _,hour,minute ->
//                    var cal = Calendar.getInstance()
//                    cal.set(Calendar.HOUR_OF_DAY,hour)
//                    cal.set(Calendar.MINUTE,minute)
//                    val timeformat = SimpleDateFormat("hh:mm a", Locale.getDefault())
//                    selectedTime = timeformat.format(cal.time)
//                    binding.txttime.text= "Time of course : $selectedTime"
//                },
//                calendar.get(Calendar.HOUR_OF_DAY),
//                calendar.get(Calendar.MINUTE),
//                false
//            )
//            timepickdialog.show()
//        }
         //DatePicker
        val calendar = Calendar.getInstance()
        binding.txttime.setOnClickListener{
            var datepickdialog = DatePickerDialog(this,
                {
                        _,year,month,day ->
                    var cal = Calendar.getInstance()
                    cal.set(year,month,day)
                    val dateformat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val selecteddate = dateformat.format(cal.time)
                    binding.txttime.text= "Time of course : $selecteddate"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datepickdialog.show()
        }
        //Radio Type of class

        ////
        binding.btnCourseSave.setOnClickListener{
            val checkedId = binding.radioGpType.checkedRadioButtonId
            if(checkedId != -1){
            val rdoBtn:RadioButton = findViewById(checkedId)
            typeOfClass = rdoBtn.text.toString()
                }
            description = binding.editDesc.text.toString()
            Toast.makeText(this,"$selectedDay,$description,$typeOfClass",Toast.LENGTH_LONG).show()
        }
    }
}