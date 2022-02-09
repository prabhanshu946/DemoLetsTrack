package com.demo.letstrack.database.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "Tags",
    foreignKeys = [ForeignKey(
        entity = Question::class,
        parentColumns = arrayOf(Question.QUESTION_ID),
        childColumns = arrayOf(QuestionOwner.QUESTION_ID),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Tags(
    @ColumnInfo(name = "tagID")
    @PrimaryKey(autoGenerate = true) val tagId: Int = 0,

    @ColumnInfo(name = QuestionOwner.QUESTION_ID)
    val question_id: Long,

    @ColumnInfo(name = "tags")
    val tags: String?
)