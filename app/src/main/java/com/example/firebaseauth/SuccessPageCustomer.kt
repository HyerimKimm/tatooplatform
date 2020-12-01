package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauth.databinding.ActivitySuccessPageBinding
import com.example.firebaseauth.databinding.ActivitySuccessPageCustomerBinding

class SuccessPageCustomer : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessPageCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessPageCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val dataId = intent.getStringExtra("id")
        val dataName = intent.getStringExtra("name")

        binding.reserve.setOnClickListener {
            val intent = Intent(this, ReservationPage::class.java)
            intent.putExtra("id", dataId)
            intent.putExtra("name", dataName)
            startActivity(intent)
        }

        binding.design.setOnClickListener {
            val intent = Intent(this, DesignPage::class.java)
            startActivity(intent)
        }

        binding.mypage.setOnClickListener {
            val intent = Intent(this, MyPageCustomer::class.java)
            intent.putExtra("name", dataName)
            intent.putExtra("id", dataId)
            startActivity(intent)
        }
    }
}