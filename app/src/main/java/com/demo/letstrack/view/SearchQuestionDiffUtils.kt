package com.demo.letstrack.view

import androidx.recyclerview.widget.DiffUtil
import com.demo.letstrack.database.QuestionRelation

class SearchQuestionDiffUtils : DiffUtil.ItemCallback<QuestionRelation>() {
    override fun areItemsTheSame(oldItem: QuestionRelation, newItem: QuestionRelation): Boolean {
        return oldItem.question.question_id == newItem.question.question_id
    }

    override fun areContentsTheSame(oldItem: QuestionRelation, newItem: QuestionRelation): Boolean {
        return oldItem.question == newItem.question && oldItem.owner == newItem.owner && oldItem.tags == newItem.tags
    }
}