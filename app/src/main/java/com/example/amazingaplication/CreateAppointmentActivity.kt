package com.example.amazingaplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_appointment.*
import kotlinx.android.synthetic.main.activity_create_appointment_step_one.*
import kotlinx.android.synthetic.main.activity_create_appointment_step_two.*
import kotlinx.android.synthetic.main.activity_create_appointment_step_three.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.item_appointment.*
import java.util.*

class CreateAppointmentActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private var selectedHour :  RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)

        nextStepAppointment.setOnClickListener {

            if(description.text.toString().length < 3){
                description.error = "La descripcion es demasiado corta"
            }else{
                stepAppointment1.visibility = View.GONE
                stepAppointment2.visibility = View.VISIBLE
            }


        }

        finishAppointment.setOnClickListener {
            inputDateSchedule.text.toString()
            confirmTime.text             = selectedHour?.text.toString()
            if(inputDateSchedule.text.toString().isEmpty()){
                inputDateSchedule.error = "Debe Seleccionar una fecha"
            }else if(selectedHour == null){
                Snackbar.make(layoutCreateAppointment, "Debe Seleccionar una hora", Snackbar.LENGTH_SHORT).show()
            }else{
                pasteAppointmentSummary()
                stepAppointment2.visibility = View.GONE
                stepAppointment3.visibility = View.VISIBLE
            }




        }

        confirmAppointment.setOnClickListener{
            Toast.makeText(this, "Cita Registrada", Toast.LENGTH_SHORT).show()

            finish()
        }

        val specialtyOptions     = arrayOf("Specialidad A", "Specialidad B", "Specialidad C")
        spinnerSpecialty.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, specialtyOptions)

        val doctorOptions     = arrayOf("Doctor House", "Doctor Shpedard", "Doctora Cameron")
        spinnerDoctor.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,doctorOptions)

    }

    override fun onBackPressed() {
        when {
            stepAppointment3.visibility == View.VISIBLE -> {
                stepAppointment3.visibility = View.GONE
                stepAppointment2.visibility = View.VISIBLE
            }
            stepAppointment2.visibility == View.VISIBLE -> {
                stepAppointment2.visibility = View.GONE
                stepAppointment1.visibility = View.VISIBLE
            }
            else -> {
                var builder  = AlertDialog.Builder(this)
                builder.setTitle("Estas Seguro que deseas salir?")
                builder.setMessage("Perderas el avance de la cita")

                builder.setPositiveButton("Si, Salir"){ _, _ ->
                    finish()
                }

                builder.setNegativeButton("Continuar Registro"){
                        dialog , _ ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }
        }

    }

    fun openDatePicker(v : View?){


        var year     = calendar.get(Calendar.YEAR)
        var month     = calendar.get(Calendar.MONTH)
        var dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH) + 1



        val listener = DatePickerDialog.OnDateSetListener{ _, y, m, d ->

            Toast.makeText(this, "$y-$m-$d", Toast.LENGTH_SHORT).show()
            calendar.set(y, m, d)
            inputDateSchedule.setText("$y-${ (m +1).twoDigits() }-${ d.twoDigits() }")

            displayRadioButton()
        }

        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(this, listener, year, month, dayOfMonth)
        val datePicker = datePickerDialog.datePicker

        calendar.add(Calendar.DAY_OF_MONTH, 1)
        datePicker.minDate = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_MONTH, 31)
        datePicker.maxDate= calendar.timeInMillis

        datePickerDialog.show()
    }

    fun displayRadioButton(){
        //radio_group.clearCheck()
        //radio_group.removeAllViews()
        radio_groupl.removeAllViews()
        radio_groupr.removeAllViews()
        selectedHour = null


        var hours = arrayOf("12:00 P.M.", "12:30 P.M.", "1:00 P.M.", "1:30 P.M.")
        var goToLeft = true


        hours.forEach {
            val radioButton = RadioButton(this)
            radioButton.id = View.generateViewId()
            radioButton.text = it

            radioButton.setOnClickListener { view ->
                selectedHour?.isChecked = false
                selectedHour = view as RadioButton?
                selectedHour?.isChecked = true
            }

            if(goToLeft)
                radio_groupl.addView(radioButton)
            else
                radio_groupr.addView(radioButton)

            goToLeft = !goToLeft
        }
    }

    fun Int.twoDigits(): String{
        return if(this >= 9) this.toString() else "0$this"
    }

    private fun pasteAppointmentSummary(){

        confirmDescription.text = description.text.toString()
        confirmSpeciality.text  = spinnerSpecialty.selectedItem.toString()

        val selectRadioButtonId = tipo_cita.checkedRadioButtonId
        val selectedRadioType   = tipo_cita.findViewById<RadioButton>(selectRadioButtonId)

        confirmType.text             = selectedRadioType.text.toString()
        confirmDoctor.text           = spinnerDoctor.selectedItem.toString()
        confirmDate.text             = inputDateSchedule.text.toString()
        confirmTime.text             = selectedHour?.text.toString()
    }
}
