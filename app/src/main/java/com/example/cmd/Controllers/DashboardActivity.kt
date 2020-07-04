package com.example.cmd.Controllers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.cmd.R
import com.example.cmd.Services.AuthServices
import com.example.cmd.Services.DiningUserDataService
import com.example.cmd.Services.UserDataService
import com.example.cmd.Utilities.BROADCAST_USER_DATA_CHANGED
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.dinning_dialogue.*
import kotlinx.android.synthetic.main.dinning_dialogue.usernameText

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

       /* LocalBroadcastManager.getInstance(this).registerReceiver(userDataChangeReceiver, IntentFilter(
            BROADCAST_USER_DATA_CHANGED))*/

        println(UserDataService.name)
        println(UserDataService.email)
        println("Dining Act" + DiningUserDataService.email)
        println("Dining Act" + DiningUserDataService.status)
        usernameText.text = UserDataService.name
        userIdText.text = UserDataService.email

    }

    private val userDataChangeReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(AuthServices.isLoggedIn){
                println("UserDataService.username")
                println("UserDataService.id")
                usernameText.text = UserDataService.name
                userIdText.text = UserDataService.email
            }
        }
    }


    fun dinningBtnClicked(view: View){

        if(DiningUserDataService.status == true){
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dinning_dialogue, null)

            builder.setView(dialogView)
                .setPositiveButton("Scan Ticket"){_, _ ->
                    println("Great Job")
                    val diningBarcodeActivity = Intent(getBaseContext(), DiningBarcodeActivity::class.java)
                    startActivity(diningBarcodeActivity)

                }
                .setNegativeButton("Cancel"){_, _ ->

                }.show()
        }else{

        }
    }



    fun timetableBtnClicked(view: View){

    }

    fun attendanceBtnClicked(view: View){

    }

    fun newsBtnClicked(view: View){

    }

    fun chatBtnClicked(view: View){

    }


    fun contactBtnClicked(view: View){

    }




}
