package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.firebaseauth.databinding.ActivityReserveFormBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReserveForm : AppCompatActivity() {
    lateinit var binding: ActivityReserveFormBinding
    val db: FirebaseFirestore = Firebase.firestore
    val reserveCollectionRef = db.collection("reservation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReserveFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataId = intent.getStringExtra("id")
        val dataName = intent.getStringExtra("name")
        val pageName = intent.getStringExtra("pagename")
        val key = dataName+pageName


        binding.btnreserve.setOnClickListener {
            val date : String? = binding.date.text.toString()
            val design : String? = binding.design.text.toString()

            //reservation document 생성
            val reserveMap = hashMapOf(
                "cid" to dataId,
                "cname" to dataName,
                "pagename" to pageName,
                "date" to date,
                "design" to design,
                "state" to "wait"
            )

            reserveCollectionRef.document(key).set(reserveMap)
                .addOnSuccessListener {
                    AlertDialog.Builder(this)
                        .setMessage("예약 신청 성공").show()
                }.addOnFailureListener {
                    AlertDialog.Builder(this)
                        .setMessage("예약 신청 실패").show()
                }
            }
    }
}