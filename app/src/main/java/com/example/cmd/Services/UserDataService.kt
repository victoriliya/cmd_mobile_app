package com.example.cmd.Services

object UserDataService {
    var id = ""
    var name = ""
    var surname = ""
    var othername = ""
    var email = ""
    var course = ""
    var date = ""
    var authToken = ""


    fun logout(){
        id = ""
        email = ""
        name = ""
    }

}