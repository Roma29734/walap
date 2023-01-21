package com.example.walap.data.model.entity

import android.net.Uri
import android.os.Parcelable
import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "categories_table")
data class CategoriesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "image")val imageView: String,
): Parcelable

//@Parcelize
//@Entity(tableName = "workout_table")
//data class WorkoutEntity (
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id") val id: Int,
//    @ColumnInfo(name = "day") val day: String,
//    @ColumnInfo(name = "workout")
//    @TypeConverters(WorkoutConverter::class)
//    val workout: List<WorkoutDayModel>,
//): Parcelable