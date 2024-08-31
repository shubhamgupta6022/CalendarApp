package com.sgupta.calendarapp.data.model

import com.google.gson.annotations.SerializedName

data class UserIdRequest(
    @SerializedName("user_id") val userId: Int
)