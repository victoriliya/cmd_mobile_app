package com.example.cmd.Controllers

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cmd.R

class MainActivity : AppCompatActivity() {
    private var loadingDialog : Dialog? = null

    var email = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun departmentBtnClicked(view: View){
        val intent = Intent(this, DepartmentActivity::class.java)
        startActivity(intent)
    }

    fun servicesBtnClicked(view: View){

    }
    fun leadershipBTnClicked(view: View){
        val intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
    }

    fun galleryBtnClicked(view: View){

    }

    fun loginBtnClicked(view: View){

        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)

    }

    fun aboutBtnClicked(view: View){

        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.about_dialogue, null)

        builder.setView(dialogView)
            .setPositiveButton(""){_, _ ->


            }
            .setNegativeButton(""){_, _ ->

            }.show()


    }

    fun emailBtnClicked(view: View){

        val emailIntent = Intent(Intent.ACTION_SEND)

        val aEmailList =
            arrayOf("info@cmd.gov.ng", "info@cmd.gov.ng")

        emailIntent.putExtra(Intent.EXTRA_EMAIL, aEmailList)

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My subject")

        emailIntent.type = "plain/text"
        emailIntent.putExtra(Intent.EXTRA_TEXT, "My message body.")

        startActivity(emailIntent)



    }


    fun signupBtnClicked(view: View){
        val regIntent = Intent(this, RegistrationActivity::class.java);
        startActivity(regIntent)
        println("welldone")

    }






}
