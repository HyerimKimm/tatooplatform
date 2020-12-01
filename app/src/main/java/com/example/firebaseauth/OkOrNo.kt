package com.example.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.firebaseauth.databinding.ActivityOkOrNoBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class OkOrNo : AppCompatActivity() {
    lateinit var binding : ActivityOkOrNoBinding
    val db : FirebaseFirestore = Firebase.firestore
    private  val tatstCollectionRef = db.collection("reservation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOkOrNoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val dataId = intent.getStringExtra("id").toString()

        binding.ok.setOnClickListener {
            tatstCollectionRef.document(dataId).get()
                .addOnSuccessListener {
                    if(it["state"] == "wait"){
                        tatstCollectionRef.document(dataId).update("state", "ok")
                            .addOnSuccessListener {
                                AlertDialog.Builder(this).setMessage("승인 성공").show()
                            }
                    }
                }
        }

        binding.no.setOnClickListener {
            tatstCollectionRef.document(dataId).get()
                .addOnSuccessListener {
                    if(it["state"] == "wait"){
                        tatstCollectionRef.document(dataId).update("state", "no")
                            .addOnSuccessListener {
                                AlertDialog.Builder(this).setMessage("거절 성공").show()
                            }
                    }
                }
        }
    }
}