package com.demo.letstrack.view

import com.demo.letstrack.api.ApiHelper
import com.demo.letstrack.api.response.QuestionResponse
import com.demo.letstrack.database.AppDataBase
import com.demo.letstrack.database.entity.Question
import com.demo.letstrack.database.entity.QuestionOwner
import com.demo.letstrack.database.entity.Tags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dataBase: AppDataBase
) {

    suspend fun getQuestions() = apiHelper.getQuestions()

    internal fun getAllQuestions() = dataBase.questionDao.getAllQuestions()

    internal fun prepareQuestionData(questionResponse: QuestionResponse) {
        CoroutineScope(Dispatchers.Default).launch {
            val questionDBData = dataBase.questionDao.getQuestionDetail(questionResponse.question_id)
            if (questionDBData == null) {
                val question = Question(
                    question_id = questionResponse.question_id,
                    view_count = questionResponse.view_count,
                    answer_count = questionResponse.answer_count,
                    score = questionResponse.score,
                    last_activity_date = questionResponse.last_activity_date,
                    creation_date = questionResponse.creation_date,
                    content_license = questionResponse.content_license,
                    link = questionResponse.link,
                    title = questionResponse.title,
                    is_answered = questionResponse.is_answered
                )
                dataBase.questionDao.insert(question)

                val questionOwner = QuestionOwner(
                    question_id = questionResponse.question_id,
                    reputation = questionResponse.owner.reputation,
                    user_id = questionResponse.owner.user_id,
                    user_type = questionResponse.owner.user_type,
                    profile_image = questionResponse.owner.profile_image,
                    display_name = questionResponse.owner.display_name,
                    profile_link = questionResponse.owner.profile_link
                )
                dataBase.questionOwnerDao.insert(questionOwner)

                val tagList = ArrayList<Tags>()
                questionResponse.tags.forEach {
                    tagList.add(Tags(tags = it, question_id = questionResponse.question_id))
                }
                dataBase.tagsDao.insert(tagList)
            }
        }
    }
}