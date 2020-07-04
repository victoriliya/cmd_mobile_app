package com.example.cmd.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cmd.R
import android.view.View
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.cmd.Services.AuthServices
import com.example.cmd.Services.UserDataService
import com.example.cmd.Utilities.BROADCAST_USER_DATA_CHANGED
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import kotlinx.android.synthetic.main.activity_login.*
import java.net.Authenticator
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginLoginBtnClicked(view : View){
        var email = loginEmailText.text.toString()
        val password = loginPasswordText.text.toString()

        email.validEmail() {
            // This method will be called when myEmailStr is not a valid email.
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            email = ""
        }

        if(password.isEmpty()){
            Toast.makeText(this, "Please provide password", Toast.LENGTH_SHORT).show()

        }



        if(email.isNotEmpty() && password.isNotEmpty()){
            AuthServices.loginUser(this, email, password){loginSuccess ->
                if (loginSuccess){
                    AuthServices.findDiningData(this)
                    AuthServices.findDiningUser(this)
                    AuthServices.findUser(this, UserDataService.authToken){ findSuccess ->
                        if (findSuccess){
                            val dashboardIntent = Intent(this, DashboardActivity::class.java)
                            startActivity(dashboardIntent)
                        }
                    }

                }else{
                    Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
                }

            }
        }


    }

    fun loginSignUpBtnClicked(view: View){
        val signUpIntent = Intent(this, RegistrationActivity::class.java)
        startActivity(signUpIntent)
    }

}
