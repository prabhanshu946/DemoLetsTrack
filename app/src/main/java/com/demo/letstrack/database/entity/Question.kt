package com.demo.letstrack.database.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.demo.letstrack.database.entity.Question.Companion.TBL_QUESTION
import java.util.*

@Keep
@Entity(tableName = TBL_QUESTION, indices = [Index(Question.QUESTION_ID)])
data class Question(
    @ColumnInfo(name = QUESTION_ID)
    @PrimaryKey
    val question_id: Long,

    @ColumnInfo(name = VIEW_COUNT)
    val view_count : Int?,

    @ColumnInfo(name = ANSWER_COUNT)
    val answer_count : Int?,

    @ColumnInfo(name = SCORE)
    val score: Int?,

    @ColumnInfo(name = LAST_ACTIVITY_DATE)
    val last_activity_date: Long?,

    @ColumnInfo(name = CREATION_DATE)
    val creation_date: Long?,

    @ColumnInfo(name = CONTENT_LICENSE)
    val content_license: String?,

    @ColumnInfo(name = LINK)
    val link: String?,

    @ColumnInfo(name = TITLE)
    val title: String?,

    @ColumnInfo(name = IS_ANSWERED)
    val is_answered: Boolean
) {

    companion object {
        const val TBL_QUESTION = "tbl_question"
        const val IS_ANSWERED = "is_answered"
        const val VIEW_COUNT = "view_count"
        const val ANSWER_COUNT = "answer_count"
        const val SCORE = "score"
        const val LAST_ACTIVITY_DATE = "last_activity_date"
        const val CREATION_DATE = "creation_date"
        const val QUESTION_ID = "question_id"
        const val CONTENT_LICENSE = "content_license"
        const val LINK = "link"
        const val TITLE = "title"
    }
}