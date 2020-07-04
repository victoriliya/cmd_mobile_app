package com.example.cmd.Utilities

const val BASE_URL = "https://friendly-worms-58884.herokuapp.com/api/"

/*const val BASE_URL = "http://10.0.2.2:3000/api/"*/
const val REGISTRATION_URL = "${BASE_URL}register"
const val LOGIN_URL = "${BASE_URL}login"
const val FIND_USER_URL = "${BASE_URL}user"
const val FIND_DINING_USER_URL = "${BASE_URL}dining"
const val FIND_DINING_DATA_URL = "${BASE_URL}diningData"
const val UPDATE_DINING_USER_URL = "${BASE_URL}userDiningStatus"
const val REGISTER_PROFILE_URL = "${BASE_URL}addUser"

// Broadcast constants

const val BROADCAST_USER_DATA_CHANGED = "BROADCAST_USER_DATA_CHANGED"