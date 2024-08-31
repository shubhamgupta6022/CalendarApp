package com.sgupta.calendarapp.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status") val status: String
)