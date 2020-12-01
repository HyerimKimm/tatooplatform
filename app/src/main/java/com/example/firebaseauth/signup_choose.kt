package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauth.databinding.ActivitySignupChooseBinding

class signup_choose : AppCompatActivity() {
    lateinit var binding: ActivitySignupChooseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupTatooist.setOnClickListener {
            val intent = Intent(this, signup_for_tatooist::class.java)
            startActivity(intent)
        }

        binding.signupCustomer.setOnClickListener {
            val intent = Intent(this, signup_for_customer::class.java)
            startActivity(intent)
        }
    }
}