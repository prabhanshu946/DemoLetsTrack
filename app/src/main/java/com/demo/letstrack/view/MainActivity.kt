package com.demo.letstrack.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.demo.letstrack.R
import com.demo.letstrack.database.QuestionRelation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private var mSearchView: SearchView? = null
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainListAdapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvQuestion.apply {
            mainListAdapter = MainListAdapter(onClick)
            adapter = mainListAdapter
        }

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.getQuestions()
        }

        mainViewModel.getAllQuestions().observe(this, {
            mainViewModel.questionList.clear()
            mainViewModel.questionList.addAll(it)
            submitListToAdapter(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchItem?.expandActionView()
        mSearchView = searchItem?.actionView as SearchView
        updateItemView()
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                onBackPressed()
                return false
            }
        })
        return true
    }

    private fun updateItemView() {
        val searchCloseButton =
            mSearchView?.findViewById<ImageView>(R.id.search_close_btn)
        mSearchView?.queryHint = getString(R.string.search_question)
        //change clear button color
        searchCloseButton?.setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        // remove background bar
        val searchEditFrame = mSearchView?.findViewById<LinearLayout>(R.id.search_edit_frame)
        searchEditFrame?.background = null
        mSearchView?.setOnQueryTextListener(this)
        mSearchView?.isFocusable = true
        mSearchView?.isIconified = false
        mSearchView?.isFocusableInTouchMode = true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            if (it.trim().isEmpty()) {
                submitListToAdapter(mainViewModel.questionList)
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val filterTitle = async {
                        mainViewModel.questionList.filter { questionData ->
                            questionData.question.title!!.lowercase().contains(it.lowercase())
                        }
                    }

                    val filter = async {
                        mainViewModel.questionList.filter { questionData ->
                            questionData.owner?.display_name!!.lowercase().contains(it.lowercase())
                        }
                    }
                    (filterTitle.await() as ArrayList<QuestionRelation>).addAll(filter.await().distinct())
                    withContext(Dispatchers.Main){
                        submitListToAdapter(filterTitle.await())
                    }
                }

            }
        }
        return false
    }


    private fun submitListToAdapter(list: List<QuestionRelation>) {
        mainListAdapter.submitList(list.distinct())

        val answerCount = list.distinct().map { it.question.answer_count!! }.average().toString()
        val viewCount = list.distinct().map { it.question.view_count!! }.average().toString()

        tvAverageAnswerCount.text =
            if (answerCount == "NaN") getString(R.string.zero) else answerCount
        tvAverageViewCount.text = if (viewCount == "NaN") getString(R.string.zero) else viewCount

    }

    private val onClick: (link: String?) -> Unit = { url ->
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}