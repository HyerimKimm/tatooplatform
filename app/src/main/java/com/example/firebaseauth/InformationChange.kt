package com.example.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.firebaseauth.databinding.ActivityInformationChangeBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InformationChange : AppCompatActivity() {
    lateinit var binding: ActivityInformationChangeBinding
    val db : FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val dataId = intent.getStringExtra("id").toString()

        binding.change.setOnClickListener {
            val name = binding.name.text.toString()
            db.collection("users").document(dataId).update("name", name)
                .addOnSuccessListener {
                    AlertDialog.Builder(this)
                        .setMessage("변경 성공").show()
                }
        }
    }
}