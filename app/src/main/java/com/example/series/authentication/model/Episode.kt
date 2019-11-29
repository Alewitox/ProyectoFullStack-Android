package com.example.series.authentication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Episode.TABLE_NAME)
data class Episode(
    @ColumnInfo(name = "season") @NotNull val season: String,
    @ColumnInfo(name = "episode_number") @NotNull val episodeNumber: String,
    @ColumnInfo(name = "title") @NotNull val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "release_date") @NotNull val releaseDate: String
) {
    companion object {
        const val TABLE_NAME = "episode"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}