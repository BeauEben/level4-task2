package com.example.level4_task2

import android.graphics.drawable.Drawable
import android.icu.text.DateFormat
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*
@Parcelize
@Entity(tableName = "historyTable")
data class HistoryItem (

    @ColumnInfo(name = "outcome")
    var historyOutcome: String,

    @ColumnInfo(name = "moveComputer")
    var historyMoveComputer: Int,

    @ColumnInfo(name = "movePlayer")
    var historyMovePlayer: Int,

    @ColumnInfo(name = "date")
    var historyDate: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable