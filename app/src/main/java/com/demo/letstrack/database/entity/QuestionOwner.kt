package com.demo.letstrack.database.entity

import androidx.annotation.Keep
import androidx.room.*
import com.demo.letstrack.database.entity.QuestionOwner.Companion.TBL_QUESTION_OWNER

@Keep
@Entity(tableName = TBL_QUESTION_OWNER, indices = [Index(QuestionOwner.QUESTION_OWNER_ID)],
    foreignKeys = [ForeignKey(
        entity = Question::class,
        parentColumns = arrayOf(Question.QUESTION_ID),
        childColumns = arrayOf(QuestionOwner.QUESTION_ID),
        onDelete = ForeignKey.CASCADE
    )]
)
data class QuestionOwner(

    @ColumnInfo(name = QUESTION_OWNER_ID)
    @PrimaryKey(autoGenerate = true) val question_owner_id: Int = 0,

    @ColumnInfo(name = QUESTION_ID)
    val question_id: Long,

    @ColumnInfo(name = REPUTATION)
    val reputation: Int?,

    @ColumnInfo(name = USER_ID)
    val user_id: Long,

    @ColumnInfo(name = USER_TYPE)
    val user_type: String?,

    @ColumnInfo(name = PROFILE_IMAGE)
    val profile_image : String?,

    @ColumnInfo(name = DISPLAY_NAME)
    val display_name: String?,

    @ColumnInfo(name = PROFILE_LINK)
    val profile_link: String?

){
    companion object{
        const val TBL_QUESTION_OWNER = "tbl_question_owner"
        const val QUESTION_OWNER_ID = "question_owner_id"
        const val QUESTION_ID = "question_id"
        const val REPUTATION = "reputation"
        const val USER_ID = "user_id"
        const val USER_TYPE = "user_type"
        const val PROFILE_IMAGE = "profile_image"
        const val DISPLAY_NAME = "display_name"
        const val PROFILE_LINK = "link"
    }
}
