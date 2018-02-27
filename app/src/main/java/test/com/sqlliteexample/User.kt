package com.smartway.android.fucrea.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by ignaciodeandreisdenis on 18/2/18.
 */
data class User(
        @SerializedName("id") var id: Int?,
        @SerializedName("username") var username: String?,
        @SerializedName("email") var email: String?,
        @SerializedName("name") var name: String?,
        @SerializedName("address") var address: String?,
        @SerializedName("phone_number") var phone_number: String?,
        @SerializedName("departament") var departament: Int?,
        @SerializedName("uninitialized_establishments") var uninitialized_establishments: Boolean?,
        @SerializedName("role") var role: String?,
        @SerializedName("end_date") var end_date: String?,
        @SerializedName("temporary") var temporary: Boolean?
) : Serializable