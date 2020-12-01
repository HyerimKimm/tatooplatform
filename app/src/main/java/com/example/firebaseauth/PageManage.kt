package com.example.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.firebaseauth.databinding.ActivityPageManageBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PageManage : AppCompatActivity() {
    lateinit var binding : ActivityPageManageBinding
    val db : FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPageManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val dataPagename = intent.getStringExtra("pagename").toString()
        val dataId = intent.getStringExtra("id").toString()

        binding.change.setOnClickListener {
            db.collection("users").document(dataId).update("pagename", binding.pagename.text.toString())
            db.collection("users").document(dataId).update("pageint", binding.pageintro.text.toString())
            db.collection("users").document(dataId).update("address", binding.address.text.toString())

            val pageMap = hashMapOf(
                "introduce" to binding.pageintro.text.toString(), "address" to binding.address.text.toString()
            )

            db.collection("pages").document(dataPagename).delete()
            db.collection("pages").document(binding.pagename.text.toString()).set(pageMap)
                .addOnSuccessListener {
                    AlertDialog.Builder(this)
                        .setMessage("변경 성공").show()
                }
        }
    }
}