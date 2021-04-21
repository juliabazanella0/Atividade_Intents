package com.example.atividade04intents

import android.Manifest
import android.app.SearchManager
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val PICK_IMAGE = 111
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btShareText.setOnClickListener { shareText(txtText.text.toString()) }
        btSelect.setOnClickListener { image() }
        btShare.setOnClickListener { shareImage(image().toString()) }

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if (intent.type?.startsWith("image/") == true) {
                    handleSendImage(intent) // Lidar com uma única imagem enviada
                }
            }
        }
    }

    //Compartilhar texto

    private fun shareText(text: String){
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    //Compartinhar imagem

    private fun image(){
        Dexter.withContext(this)
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        handleSendImage(intent)
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {}
                    override fun onPermissionRationaleShouldBeShown(
                            p0: PermissionRequest?,
                            p1: PermissionToken?
                    ) {
                    }
                })
                .check()
    }

//    private fun selectImage() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(intent, PICK_IMAGE)
//    }

    private fun handleSendImage(intent: Intent) {
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            // Update UI to reflect image being shared
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == PICK_IMAGE) {
//            val imageBitmap = data?.extras?.get("data") as Bitmap
//            imImage.setImageBitmap(imageBitmap)
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }

    private fun shareImage(image: String){
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, image)
            type = "image/jpeg"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

//    private fun searchWeb(query: String) {
//        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
//            putExtra(SearchManager.QUERY, query)
//        }
//        startActivity(intent)
//    }

}