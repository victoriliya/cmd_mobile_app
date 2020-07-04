package com.example.cmd.Controllers

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.cmd.Models.QRDiningCodeModel
import com.example.cmd.Models.QRGeoModel
import com.example.cmd.Models.QRVCardModel
import com.example.cmd.R
import com.example.cmd.Services.AuthServices
import com.example.cmd.Services.DiningDataServices
import com.example.cmd.Services.DiningUserDataService
import com.example.cmd.Services.UserDataService
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_dining_barcode.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask


class DiningBarcodeActivity : AppCompatActivity(), ZXingScannerView.ResultHandler  {



    override fun handleResult(rawResult: Result?) {


        processRawResult(rawResult!!.text)
        println("code " + DiningDataServices.diningCode)
        println("status " + DiningUserDataService.status)

        if((rawResult!!.text.isNotEmpty()) && (rawResult!!.text == DiningDataServices.diningCode) && (DiningUserDataService.status == true) ){
            DiningDataServices.count += 1
            val builder =  AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.confirm_success_dialogue, null)
            builder.setView(dialogView).show()


            Timer().schedule(2000){
                DiningUserDataService.status = false
                DiningUserDataService.verified = true
                updateDinnerData()
            }


        }else{
            val builder =  AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.confirm_failure_dialogue, null)
            builder.setView(dialogView).show()

            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (vibrator.hasVibrator()) { // Vibrator availability checking
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
                } else {
                    vibrator.vibrate(500) // Vibrate method for below API Level 26
                }
            }

            Timer().schedule(2000){

                if(DiningDataServices.count  <= 3){
                    finish();
                    startActivity(getIntent())
                }else{
                    finish();
                }

            }

        }


    }

    private fun processRawResult(text: String?) {
        if (text!!.startsWith("BEGIN:")){
            val tokens = text!!.split("\n".toRegex())!!.dropLastWhile({ it.isEmpty() }).toTypedArray()
            val qrvCardModel = QRVCardModel()
            for(i in tokens.indices){
                if (tokens[i].startsWith("BEGIN:"))
                    qrvCardModel.type = tokens[i].substring("BEGIN:".length)
                else if(tokens[i].startsWith("N:"))
                    qrvCardModel.name = tokens[i].substring("N:".length)
                else if(tokens[i].startsWith("ORG:"))
                    qrvCardModel.org = tokens[i].substring("ORG:".length)
                else if(tokens[i].startsWith("TEL:"))
                    qrvCardModel.tel = tokens[i].substring("TEL:".length)
                else if(tokens[i].startsWith("URL:"))
                    qrvCardModel.url = tokens[i].substring("URL:".length)
                else if(tokens[i].startsWith("EMAIL:"))
                    qrvCardModel.email = tokens[i].substring("EMAIL:".length)
                else if(tokens[i].startsWith("ADR:"))
                    qrvCardModel.address = tokens[i].substring("ADR:".length)
                //VEVENT
                else if(tokens[i].startsWith("SUMMARY:"))
                    qrvCardModel.summary = tokens[i].substring("SUMARY:".length)
                else if(tokens[i].startsWith("DTSTART:"))
                    qrvCardModel.dtstart = tokens[i].substring("DTSTART:".length)
                else if(tokens[i].startsWith("DTEND:"))
                    qrvCardModel.dtend = tokens[i].substring("DTEND:".length)

                text_result!!.text = qrvCardModel.type

            }
        }
        else if (text!!.startsWith("http://") || text!!.startsWith("https://") || text!!.startsWith("www.") ) {

            val qrurlModel = QRDiningCodeModel()
            qrurlModel.url = text!!
            text_result!!.text = qrurlModel.url
        }
        else if (text!!.startsWith("geo:")){
            val qrGeoModel = QRGeoModel()
            val delims = "[ , ?q= ]+"
            val tokens = text.split(delims.toRegex()).dropLastWhile({ it.isEmpty()}).toTypedArray()

            for (i in tokens.indices){
                if (tokens[i].startsWith(" geo:")){
                    qrGeoModel.lat = tokens[i].substring("geo:".length)
                }
            }
            qrGeoModel.lat = tokens[0].substring("geo: ".length)
            qrGeoModel.lng = tokens[1]
            qrGeoModel.geo_place = tokens[2]

            text_result.text = qrGeoModel.lat + " / " + qrGeoModel.lng
        }else{
            text_result.text = text!!
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dining_barcode)

        println("Scanner" + DiningDataServices.course)
        println("Scanner" + DiningDataServices.diningCode)

        Dexter.withActivity(this)
            .withPermission(android.Manifest.permission.CAMERA)
            .withListener(object: PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    zxscan.setResultHandler(this@DiningBarcodeActivity)
                    zxscan.startCamera()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toast.makeText(this@DiningBarcodeActivity, "You should enable the permission", Toast.LENGTH_SHORT).show()
                }

            }).check()







    }


    fun updateDinnerData(){
        AuthServices.updateDinnerUser(this, UserDataService.email){complete->
            if (complete){
                println("Usersddd")
               /* val dashboardIntent = Intent(this, DashboardActivity::class.java)
                startActivity(dashboardIntent)*/
                finish()

            }else{
                println("Error updateDinnerUser")
            }

        }
    }



}

