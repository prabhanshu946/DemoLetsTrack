package com.demo.letstrack.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.demo.letstrack.database.QuestionRelation
import com.demo.letstrack.database.dao.BaseDao
import com.demo.letstrack.database.entity.Question

@Dao
abstract class QuestionDao : BaseDao<Question>() {

    @Query("Select * from tbl_question where question_id=:questionId")
    abstract fun getQuestionDetail(questionId: Long): Question?

    @Query("select * from tbl_question")
    abstract fun getAllQuestions(): LiveData<List<QuestionRelation>>
}