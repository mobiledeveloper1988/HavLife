/*
 * Created by Jithin kozhiyodan on 27/1/21 3:24 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 27/1/21 3:24 PM
 *
 */

package com.hav.havlife.model.request_data.login

data class RegisterRequest(var name:String="", var email:String="",var phonenumber:String="", var password:String="",var confirmpassword:String="") {
}