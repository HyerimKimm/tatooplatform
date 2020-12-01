package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.firebaseauth.databinding.ActivitySignupChooseBinding
import com.example.firebaseauth.databinding.ActivitySignupForTatooistBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class signup_for_tatooist : AppCompatActivity() {
    lateinit var binding: ActivitySignupForTatooistBinding
    val db : FirebaseFirestore = Firebase.firestore
    val userCollectionRef = db.collection("users")
    val pageCollectionRef = db.collection("pages")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupForTatooistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = Firebase.auth

        binding.tatooistSignup.setOnClickListener {
            //firebase에 사용자 추가
            val id = binding.tatooistId.text.toString()
            val pw = binding.tatooistPw.text.toString()
            val name = binding.tatooistName.text.toString()
            val pagename = binding.tatooistPagename.text.toString()
            val pageintro = binding.tatooistIntroduce.text.toString()
            val address = binding.tatooistAddress.text.toString()
            val userMap = hashMapOf(
                "pw" to pw, "name" to name, "pagename" to pagename, "pageint" to pageintro, "address" to address
            )
            val pageMap = hashMapOf(
                "introduce" to pageintro, "address" to address
            )

            auth.createUserWithEmailAndPassword(id, pw)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        userCollectionRef.document(id).set(userMap)
                            .addOnSuccessListener{
                                pageCollectionRef.document(pagename).set(pageMap)
                                    .addOnSuccessListener {
                                        val nextIntent = Intent(this, MainActivity::class.java)
                                        startActivity(nextIntent)
                                    }
                            }.addOnFailureListener{ AlertDialog.Builder(this)
                                .setMessage("firestore Failed.").show() }
                    } else {
                        AlertDialog.Builder(this)
                            .setMessage("Sign-up Failed.").show()
                    }
                }
        }
    }
}