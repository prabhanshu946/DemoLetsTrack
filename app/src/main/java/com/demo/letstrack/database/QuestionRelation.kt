package com.demo.letstrack.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.demo.letstrack.database.entity.Question
import com.demo.letstrack.database.entity.QuestionOwner
import com.demo.letstrack.database.entity.Tags

data class QuestionRelation(
    @Embedded val question: Question,
    @Relation(
        parentColumn = Question.QUESTION_ID,
        entityColumn = QuestionOwner.QUESTION_ID,
        associateBy = Junction(QuestionOwner::class)
    )
    val owner: QuestionOwner?,
    @Relation(
        parentColumn = Question.QUESTION_ID,
        entityColumn = QuestionOwner.QUESTION_ID,
        associateBy = Junction(Tags::class)
    )
    val tags: List<Tags>?

)