package com.example.amazingaplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazingaplication.model.Appointment
import kotlinx.android.synthetic.main.item_appointment.view.*

class AppointmentAdapter(private val appointments : ArrayList<Appointment>) : RecyclerView.Adapter<AppointmentAdapter.ViewHolder>(){



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(appointment : Appointment){

            with(itemView){
                appointmentId.text = "Cita Medica # ${appointment.id}"
                doctorName.text    = appointment.doctorName
                scheduledDate.text    = "El día: ${appointment.scheduledDate}"
                scheduledTime.text    = "A la hora: ${appointment.scheduleTime}"
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_appointment, parent, false))
    }

    override fun getItemCount()
        = appointments.size//To change body of created functions use File | Settings | File Templates.


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val appointment = appointments[position]

        holder.bind(appointment);

    }
}