package com.example.toxictrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
//import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val welcomeMsg=findViewById<TextView>(R.id.welcMsg)
        val enterName=findViewById<EditText>(R.id.user)
        val subButton=findViewById<Button>(R.id.submit)
        subButton.setOnClickListener{
            val inputGot=enterName.text.toString()
            if(inputGot==""){
                Toast.makeText(this@MainActivity, "Enter UserName", Toast.LENGTH_SHORT).show()
            } else{
                val intent=Intent(this,SecondActivity::class.java)
                intent.putExtra("USER",inputGot)
                startActivity(intent)
            }

        }
    }
}
