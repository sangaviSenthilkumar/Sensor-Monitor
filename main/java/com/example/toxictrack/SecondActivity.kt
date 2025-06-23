package com.example.toxictrack

//import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
//import android.widget.Button
//import android.widget.ImageButton
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging

class SecondActivity : AppCompatActivity() {

    private lateinit var mDatabase: DatabaseReference
    private lateinit var sv1: TextView
    private lateinit var sv2: TextView
    private lateinit var sv3: TextView
    private lateinit var sv4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val userName=intent.getStringExtra("USER")
        val dispUser=findViewById<TextView>(R.id.loggedUser)
        dispUser.text=userName

        // Initialize Firebase Database
        mDatabase = FirebaseDatabase.getInstance().reference

        // Initialize TextViews
        sv1 = findViewById(R.id.sv1)
        sv2 = findViewById(R.id.sv2)
        sv3 = findViewById(R.id.sv3)
        sv4 = findViewById(R.id.sv4)

        // Fetch and display data
        fetchSensorData()
    }

    private fun fetchSensorData() {
        // Fetch data for sensor1
        mDatabase.child("Sensor1").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Double::class.java)
                sv1.text = value?.toString() ?: "No data available"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                handleDatabaseError(databaseError)
            }
        })

        // Fetch data for sensor2
        mDatabase.child("Sensor2").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Double::class.java)
                sv2.text = value?.toString() ?: "No data available"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                handleDatabaseError(databaseError)
            }
        })

        // Fetch data for sensor3
        mDatabase.child("Sensor3").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Double::class.java)
                sv3.text = value?.toString() ?: "No data available"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                handleDatabaseError(databaseError)
            }
        })

        // Fetch data for sensor4
        mDatabase.child("Sensor4").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Double::class.java)
                sv4.text = value?.toString() ?: "No data available"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                handleDatabaseError(databaseError)
            }
        })
    }



    private fun handleDatabaseError(databaseError: DatabaseError) {
        // Log the error (optional)
        Log.e("FirebaseError", "Error code: ${databaseError.code}, message: ${databaseError.message}")

        // Display a toast message to the user
        when (databaseError.code) {
            DatabaseError.DISCONNECTED ->
                Toast.makeText(this, "Disconnected from Firebase. Please check your internet connection.", Toast.LENGTH_LONG).show()
            DatabaseError.PERMISSION_DENIED ->
                Toast.makeText(this, "You don't have permission to access this data.", Toast.LENGTH_LONG).show()
            DatabaseError.NETWORK_ERROR ->
                Toast.makeText(this, "Network error. Please try again later.", Toast.LENGTH_LONG).show()
            DatabaseError.OPERATION_FAILED ->
                Toast.makeText(this, "Operation failed. Please try again.", Toast.LENGTH_LONG).show()
            else ->
                Toast.makeText(this, "Error fetching data: ${databaseError.message}", Toast.LENGTH_LONG).show()
        }
    }
}