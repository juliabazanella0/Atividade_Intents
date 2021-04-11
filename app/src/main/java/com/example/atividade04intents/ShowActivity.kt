package com.example.atividade04intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_show.*

class ShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        if (intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            val sharedMessage = intent.getStringExtra(Intent.EXTRA_TEXT)
            txtMessage.text = sharedMessage
        }
    }
}