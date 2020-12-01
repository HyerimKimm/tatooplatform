package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauth.databinding.ActivityMypageTatooistBinding

class MypageTatooist : AppCompatActivity() {
    lateinit var binding : ActivityMypageTatooistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageTatooistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val dataPagename = intent.getStringExtra("pagename")
        val dataId = intent.getStringExtra("id")

        binding.infoChange.setOnClickListener {
            val nextIntent = Intent(this, InformationChange::class.java)
            nextIntent.putExtra("id", dataId)
            startActivity(nextIntent)
        }

        binding.pagemanage.setOnClickListener {
            val nextIntent = Intent(this, PageManage::class.java)
            nextIntent.putExtra("id", dataId)
            nextIntent.putExtra("pagename", dataPagename)
            startActivity(nextIntent)
        }
    }
}