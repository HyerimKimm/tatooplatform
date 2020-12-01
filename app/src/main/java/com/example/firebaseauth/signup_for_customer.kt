package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.firebaseauth.databinding.ActivitySignupForCustomerBinding
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class signup_for_customer : AppCompatActivity() {
    lateinit var binding: ActivitySignupForCustomerBinding
    val db : FirebaseFirestore = Firebase.firestore
    val userCollectionRef = db.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupForCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = Firebase.auth

        binding.customerSignup.setOnClickListener {
            //firebase에 사용자 추가
            val id = binding.customerId.text.toString()
            val pw = binding.customerPw.text.toString()
            val name = binding.customerName.text.toString()
            val userMap = hashMapOf(
                "pw" to pw, "name" to name, "pagename" to null, "pageint" to null, "address" to null
            )

            auth.createUserWithEmailAndPassword(id, pw)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        //create new document
                        userCollectionRef.document(id).set(userMap)
                            .addOnSuccessListener{
                                val nextIntent = Intent(this, MainActivity::class.java)
                                startActivity(nextIntent)
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