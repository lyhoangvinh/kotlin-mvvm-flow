package com.vinh.domain.model.entities.comic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Volume(
    @PrimaryKey
    @SerializedName("id")
    var id: Long? = 0,

    @SerializedName("name")
    var name: String? = ""
)