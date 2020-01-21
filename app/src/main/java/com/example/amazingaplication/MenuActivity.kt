package com.example.amazingaplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.amazingaplication.PreferenceHelper.set
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    val snackBar by lazy {
        Snackbar.make(mainLayoutId,"Presiona otra vez para salir", Snackbar.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //val intent = Intent(this, CreateAppointmentActivity::class.java)

        //startActivity(intent)
    }

    fun registerSchedule(v: View?){
        val intent = Intent(this, CreateAppointmentActivity::class.java);

        startActivity(intent)

    }

    fun logOut(v: View?){
        clearSession()

        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
        finish()
    }

    private fun clearSession(){
        /*
        val preferences = getSharedPreferences("general", Context.MODE_PRIVATE)
        val editor      = preferences.edit()

        editor.putBoolean("session", false)
        editor.apply()
        */
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true
    }

    fun mySchedule(v: View?){
        val intent = Intent(this, AppointementsActivity::class.java)

        startActivity(intent)
    }


    override fun onBackPressed() {
        if(snackBar.isShown)
            super.onBackPressed()
        else
            snackBar.show()
    }
}
