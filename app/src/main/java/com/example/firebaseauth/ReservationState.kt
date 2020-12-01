package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseauth.databinding.ActivityReservationStateBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReservationState : AppCompatActivity() {
    lateinit var binding: ActivityReservationStateBinding
    private var adapter: ReserveAdapter? = null
    val db : FirebaseFirestore = Firebase.firestore
    private val reserveCollectionRef = db.collection("reservation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationStateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = ReserveAdapter(this, emptyList())
        adapter?.setOnItemClickListener{val pageName = reserveCollectionRef.document(it).get()
            .addOnSuccessListener {

            }
        }
        binding.recyclerview.adapter = adapter

        updateList()
    }

    private fun updateList() {
        reserveCollectionRef.get().addOnSuccessListener {
            val items = mutableListOf<ReserveItem>()
            for (doc in it) {
                items.add(ReserveItem(doc))
            }
            adapter?.updateList(items)
        }
    }
}