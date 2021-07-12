package com.libo.question.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.libo.basemvvm.fragment.BaseMvvmFragment
import com.libo.question.databinding.FragmentQuestionBinding
import com.libo.question.viewmodel.QuestionViewModel

/**
 * create by libo
 * create on 2021/6/28
 * description 问答fragment
 */
@Route(path = "/qa/question")
class QuestionFragment : BaseMvvmFragment<FragmentQuestionBinding, QuestionViewModel>() {

    override fun initView() {
        setRefreshLoadMore()
    }

    private fun setRefreshLoadMore() {
        binding.smartRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        binding.smartRefreshLayout.setOnLoadMoreListener {
            viewModel.loadMore()
        }

        viewModel.baseLiveData.refresh.observe(this, {
            binding.smartRefreshLayout.finishRefresh()
        })

        viewModel.baseLiveData.loadMore.observe(this, {
            binding.smartRefreshLayout.finishLoadMore()
        })

        viewModel.baseLiveData.loadFail.observe(this, {
            binding.smartRefreshLayout.finishRefresh()
            binding.smartRefreshLayout.finishLoadMore()
        })
    }

}