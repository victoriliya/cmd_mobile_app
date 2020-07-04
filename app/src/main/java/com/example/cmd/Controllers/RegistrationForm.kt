package com.example.cmd.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cmd.R
import android.view.View
import android.widget.Toast
import com.example.cmd.Services.AuthServices
import com.example.cmd.Services.DiningUserDataService
import com.example.cmd.Services.ErrorMessages
import com.example.cmd.Services.UserDataService
import kotlinx.android.synthetic.main.activity_registration_form.*

class RegistrationForm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_form)

        lottieAnimation.setAnimation("user-profile.json")
        lottieAnimation.playAnimation()
        lottieAnimation.loop(true)

    }

    fun createUserButton(view: View){
        val name = profileNameText.text.toString()
        val surname = profilesurnameText.text.toString()
        val othername = profileOthernameText.text.toString()

        if (name.isNotEmpty() && surname.isNotEmpty()){
            AuthServices.registerProfile(this, name, surname, othername, UserDataService.authToken){ complete->

                if (complete){

                    AuthServices.findDiningData(this)
                    AuthServices.findDiningUser(this)

                    println(UserDataService.authToken)

                    AuthServices.findUser(this, UserDataService.authToken){ findSuccess ->
                        if (findSuccess){
                            val dashboardIntent = Intent(this, DashboardActivity::class.java)
                            startActivity(dashboardIntent)
                        }
                    }

                }
            }

        }else{
            Toast.makeText(this, "Please fill in provide name and surname", Toast.LENGTH_LONG).show()
        }

    }
}
