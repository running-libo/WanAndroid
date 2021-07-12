package com.libo.modulesystem.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.libo.basemvvm.fragment.BaseMvvmFragment
import com.libo.modulesystem.databinding.FragmentSystemBinding
import com.libo.modulesystem.viewmodel.SystemViewModel

@Route(path = "/system/systemPage")
class SystemFragment : BaseMvvmFragment<FragmentSystemBinding, SystemViewModel>() {

    override fun initView() {
        binding.smartRefreshLayout.setEnableRefresh(false)
        binding.smartRefreshLayout.setEnableLoadMore(false)
    }

}