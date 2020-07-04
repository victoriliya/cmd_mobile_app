package com.example.cmd.Controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cmd.R
import android.view.View
import android.widget.Toast
import com.example.cmd.Services.AuthServices
import com.example.cmd.Services.ErrorMessages
import com.wajahatkarim3.easyvalidation.core.collection_ktx.minLengthList
import com.wajahatkarim3.easyvalidation.core.view_ktx.textEqualTo
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.activity_registration.*
import javax.xml.validation.Validator

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    fun signupBtnClicked(view: View){

        var username = regEmailText.text.toString()
        var password = regPasswordText.text.toString()
        val cpassword = regConfirmPasswordText.text.toString()



       /* password.validator().minLength(6).addErrorCallback {
            Toast.makeText(this, "it", Toast.LENGTH_SHORT).show()
        }

*/
        username.validEmail() {
            // This method will be called when  is not a valid email.
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            username = ""
        }


        if ( password.textEqualTo(cpassword)){
            minLengthList(5, password, cpassword) { view, msg ->

                Toast.makeText(this, "Password length should be greater than 5", Toast.LENGTH_SHORT).show()
                password = ""
            }
        } else{
            Toast.makeText(this, "Password don't match", Toast.LENGTH_SHORT).show()
            password = ""
        }

        if( username.isNotEmpty() && password.isNotEmpty()){
            println(username + "  ========  " + password)
                   AuthServices.newRegistration(this, username, password){complete->

                if (complete){

                    val registrationIntent = Intent(this, RegistrationForm::class.java)
                    startActivity(registrationIntent)
                }else{
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }



}







