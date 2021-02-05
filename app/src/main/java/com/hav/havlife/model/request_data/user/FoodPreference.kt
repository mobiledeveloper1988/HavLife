/*
 * Created by Jithin kozhiyodan on 2/2/21 4:16 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/2/21 4:16 PM
 *
 */

package com.hav.havlife.model.request_data.user

data class FoodPreference(var diet:String="", var favoriteFood:ArrayList<String>?=null) {
}