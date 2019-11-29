package com.example.series.authentication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = Serie.TABLE_NAME)

data class Serie(
    @ColumnInfo(name = "title") @NotNull val title: String,
    @ColumnInfo(name = "network") val network: String,
    @ColumnInfo(name = "description") @NotNull val description: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "creator") @NotNull val creator: String,
    @ColumnInfo(name = "first_air_date") @NotNull val firstAirDate: String,
    @ColumnInfo(name = "last_air_date") @NotNull val lastAirDate: String
) {
    companion object {
        const val TABLE_NAME = "serie"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}