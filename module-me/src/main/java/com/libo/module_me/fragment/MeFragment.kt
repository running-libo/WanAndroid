package com.libo.module_me.fragment

import android.content.Context.MODE_PRIVATE
import com.alibaba.android.arouter.facade.annotation.Route
import com.libo.base.util.LanguageHelper
import com.libo.basemvvm.fragment.BaseMvvmFragment
import com.libo.module_me.databinding.FragmentMeBinding

@Route(path = "/me/mePage")
class MeFragment : BaseMvvmFragment<FragmentMeBinding, MeViewModel>() {

    override fun initView() {
        var isGrayMode = context?.getSharedPreferences("mode", MODE_PRIVATE)?.getBoolean("mode", false)
        binding.viewSwitch.isChecked = isGrayMode!!

        binding.viewSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            context?.getSharedPreferences("mode", MODE_PRIVATE)?.edit()?.putBoolean("mode", isChecked)?.commit()
        }

        binding.tvCurLanguage.text = LanguageHelper.getSystemLanguage()
    }
}