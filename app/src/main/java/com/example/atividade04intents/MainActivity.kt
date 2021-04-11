package com.example.atividade04intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btShareText.setOnClickListener { shareText(txtText.text.toString()) }
    }

    private fun shareText(text: String){
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

//    private fun shareImage(image: String){
//        val intent = Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(Intent.EXTRA_TEXT, text)
//            type = "image/jpeg"
//        }
//        val shareIntent = Intent.createChooser(intent, null)
//        startActivity(shareIntent)
//    }

//    private fun sendText(to: String, message: String) {
//        val uri = Uri.parse("sms:$to")
//        val intent = Intent(Intent.ACTION_SENDTO, uri).apply {
//            putExtra("sms_body", message)
//        }
//        startActivity(intent)
//    }
}