package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseauth.databinding.ActivityReservationManageBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ReservationManage : AppCompatActivity() {
    lateinit var binding: ActivityReservationManageBinding

    private var adapter: ForTatstAdapter? = null
    val db : FirebaseFirestore = Firebase.firestore
    private  val tatstCollectionRef = db.collection("reservation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val dataPagename = intent.getStringExtra("pagename").toString()

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = ForTatstAdapter(this, emptyList())
        adapter?.setOnItemClickListener{val pageName = tatstCollectionRef.document(it).get()
            .addOnSuccessListener {
                val intent = Intent(this, OkOrNo::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)

            }
        }
        binding.recyclerview.adapter = adapter

        updateList(dataPagename)
    }

    private fun updateList(dataPagename : String) {
        tatstCollectionRef.get().addOnSuccessListener {
            val items = mutableListOf<ReserveForTatooistItem>()
            for (doc in it) {
                if((doc["pagename"].toString())?.equals(dataPagename))
                items.add(ReserveForTatooistItem(doc))
            }
            adapter?.updateList(items)
        }
    }
}