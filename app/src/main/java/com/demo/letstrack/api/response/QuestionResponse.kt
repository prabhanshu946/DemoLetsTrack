package com.demo.letstrack.api.response

data class StackQuestionsResponse(val items: ArrayList<QuestionResponse>)

data class QuestionResponse(
    val question_id: Long,
    val view_count: Int,
    val answer_count: Int,
    val score: Int,
    val last_activity_date: Long,
    val creation_date: Long,
    val content_license: String,
    val link: String,
    val title: String,
    val is_answered: Boolean,
    val owner: QuestionOwnerResponse,
    val tags: ArrayList<String>
)

data class QuestionOwnerResponse(
    val reputation: Int,
    val user_id: Long,
    val user_type: String,
    val profile_image: String,
    val display_name: String,
    val profile_link: String
)