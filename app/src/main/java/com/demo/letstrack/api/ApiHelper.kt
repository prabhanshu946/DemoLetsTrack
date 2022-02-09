package com.demo.letstrack.api

import com.demo.letstrack.api.response.StackQuestionsResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getQuestions(): Response<StackQuestionsResponse>
}