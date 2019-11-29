package com.example.series.authentication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Actor.TABLE_NAME)
data class Actor(
    @ColumnInfo(name = "character_name") @NotNull val characterName: String,
    @ColumnInfo(name = "actor_name") @NotNull val actorName: String
) {
    companion object {
        const val TABLE_NAME = "actor"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}