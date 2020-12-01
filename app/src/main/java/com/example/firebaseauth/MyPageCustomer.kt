package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauth.databinding.ActivityMyPageCustomerBinding

class MyPageCustomer : AppCompatActivity() {
    lateinit var binding:ActivityMyPageCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val dataId = intent.getStringExtra("id")
        val dataName = intent.getStringExtra("name")

        binding.infoChange.setOnClickListener {
            val intent = Intent(this, InformationChange::class.java)
            intent.putExtra("name", dataName)
            intent.putExtra("id", dataId)
            startActivity(intent)
        }

        binding.reserveState.setOnClickListener {
            val intent = Intent(this, ReservationState::class.java)
            startActivity(intent)
        }
    }
}