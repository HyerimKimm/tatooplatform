package com.example.firebaseauth

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val db: FirebaseFirestore = Firebase.firestore
    val userCollectionRef = db.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = Firebase.auth

        binding.signin.setOnClickListener {
            val id = binding.ID.text.toString()
            val pw = binding.PW.text.toString()
            auth.signInWithEmailAndPassword(id, pw)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        userCollectionRef.document(id).get()
                            .addOnSuccessListener {
                                if (it["pagename"] == null) {
                                    val nextIntent = Intent(this, SuccessPageCustomer::class.java)
                                    nextIntent.putExtra("id",it.id)
                                    nextIntent.putExtra("name", it["name"].toString())
                                    startActivity(nextIntent)
                                } else {
                                    val nextIntent = Intent(this, SuccessPage::class.java)
                                    nextIntent.putExtra("id", it.id)
                                    nextIntent.putExtra("pagename", it["pagename"].toString())
                                    startActivity(nextIntent)
                                }
                            }
                    } else {
                        AlertDialog.Builder(this)
                            .setMessage("Sign-in Failed.").show()
                    }
                }
        }

        binding.signup.setOnClickListener {
            val nextIntent = Intent(this, signup_choose::class.java)
            startActivity(nextIntent)
        }
    }
}