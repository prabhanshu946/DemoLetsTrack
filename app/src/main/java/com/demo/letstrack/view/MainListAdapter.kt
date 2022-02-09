package com.demo.letstrack.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.letstrack.database.QuestionRelation
import com.demo.letstrack.databinding.AdapterMainQuestionItemBinding
import com.demo.letstrack.shared.DateTimeUtils

class MainListAdapter(private val onClick: (link: String?) -> Unit) : ListAdapter<QuestionRelation, MainListAdapter.QuestionViewHolder>(SearchQuestionDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = AdapterMainQuestionItemBinding.inflate(  LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        with(holder) {
            binding.question = getItem(absoluteAdapterPosition)
            binding.date = DateTimeUtils.getQuestionFormatDateTime(getItem(absoluteAdapterPosition).question.last_activity_date!!)
            binding.cvQuestionItem.setOnClickListener {
                onClick.invoke(getItem(absoluteAdapterPosition).question.link)
            }
        }
    }

    class QuestionViewHolder(val binding: AdapterMainQuestionItemBinding): RecyclerView.ViewHolder(binding.root)
}