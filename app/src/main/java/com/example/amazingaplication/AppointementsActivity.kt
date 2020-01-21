package com.example.amazingaplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazingaplication.model.Appointment
import kotlinx.android.synthetic.main.activity_appointements.*

class AppointementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointements)


        val appointments = ArrayList<Appointment>();

        appointments.add(
            Appointment(1, "Dr Gonzalo", "23-10-2020", "14:00 P.M.")
        )

        appointments.add(
            Appointment(2, "Dr House", "23-10-2020", "16:00 P.M.")
        )

        appointments.add(
            Appointment(3, "Dr Shepard", "23-10-2020", "18:00 P.M.")
        )

        rvAppointments.layoutManager = LinearLayoutManager(this)
        rvAppointments.adapter       = AppointmentAdapter(appointments)
    }
}
