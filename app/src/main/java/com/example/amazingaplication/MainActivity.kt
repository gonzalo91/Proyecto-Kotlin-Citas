package com.example.amazingaplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.example.amazingaplication.PreferenceHelper.get
import com.example.amazingaplication.PreferenceHelper.set

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val preferences = getSharedPreferences("general", Context.MODE_PRIVATE)
        val session     = preferences.getBoolean("session", false)
        */
        val preferences = PreferenceHelper.defaultPrefs(this)


        if(preferences["session"])
            goToMenuActivit()

        tvGoToRegister.setOnClickListener{
            Toast.makeText(this,"Por Favor, completa tus datos", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, RegisterActivity::class.java);

            startActivity(intent);
        }

        goToLogin.setOnClickListener {
            createSessionPreferences()
            goToMenuActivit()
        }
    }

    private fun createSessionPreferences(){
        /*
        val preferences = getSharedPreferences("general", Context.MODE_PRIVATE)
        val editor      = preferences.edit()

        editor.putBoolean("session", true)
        editor.apply()
        */
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true
    }

    private fun goToMenuActivit(){
        Toast.makeText(this,"Bienvenido", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MenuActivity::class.java);

        startActivity(intent);
        finish()
    }
}

