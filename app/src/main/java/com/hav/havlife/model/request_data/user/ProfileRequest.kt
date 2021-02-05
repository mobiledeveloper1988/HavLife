/*
 * Created by Jithin kozhiyodan on 28/1/21 11:20 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 28/1/21 11:20 AM
 *
 */

package com.hav.havlife.model.request_data.user

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ProfileRequest(
    @SerializedName("email")
    @Expose
    var email: String? = "",

    @SerializedName("name")
    @Expose
    var name: String? = "",
    @SerializedName("lastname")
    @Expose
    var lastName: String? = "",
    @SerializedName("role")
    @Expose
    val role: String? = "",

    @SerializedName("contactNumber")
    @Expose
    var contactNumber: String? = "",

    @SerializedName("referralcode")
    @Expose
    val referralCode: String? = "",

    @SerializedName("birthDate")
    @Expose
    var birthDate: String? = "",

    @SerializedName("gender")
    @Expose
    var gender: String? = "",

    @SerializedName("profession")
    @Expose
    var profession: String? = "",

    @SerializedName("location")
    @Expose
    var location: String? = "",
    @SerializedName("interests")
    @Expose
    var interests: List<String>? = null,
    @SerializedName("medicalCondition")
    @Expose
    var medicalCondition: String? = "",

    @SerializedName("physicalState")
    @Expose
    var physicalState: String? = "",

    @SerializedName("mentalState")
    @Expose
    var mentalState: String? = "",
    @SerializedName("height")
    @Expose
    var height: Height? = null,

    @SerializedName("weight")
    @Expose
    var weight: Weight? = null,

    @SerializedName("foodPreference")
    @Expose
    var foodPreference: FoodPreference? = null

) : Serializable {}