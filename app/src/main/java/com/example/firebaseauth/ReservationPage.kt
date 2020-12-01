package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseauth.databinding.ActivityReservationPageBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReservationPage : AppCompatActivity() {
    lateinit var binding: ActivityReservationPageBinding
    private var adapter: PageAdapter? = null
    val db : FirebaseFirestore = Firebase.firestore
    private  val pageCollectionRef = db.collection("pages")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val dataId = intent.getStringExtra("id")
        val dataName = intent.getStringExtra("name")

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = PageAdapter(this, emptyList())
        adapter?.setOnItemClickListener{val pageName = pageCollectionRef.document(it).get()
            .addOnSuccessListener {
                val nextIntent = Intent(this, ReserveForm::class.java)
                nextIntent.putExtra("id", dataId)
                nextIntent.putExtra("name", dataName)
                nextIntent.putExtra("pagename", it.id)
                startActivity(nextIntent)
            }
        }
        binding.recyclerview.adapter = adapter

        updateList()
    }

    private fun updateList() {
        pageCollectionRef.get().addOnSuccessListener {
            val items = mutableListOf<PageItem>()
            for (doc in it) {
                items.add(PageItem(doc))
            }
            adapter?.updateList(items)
        }
    }
}