package com.libo.module_article.activity

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.libo.basemvvm.activity.BaseActivity
import com.libo.module_article.databinding.ActivityArticleBinding
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 * create by libo
 * create on 2021/6/29
 * description webview加载文章页面
 */
@Route(path = "/article/webpage")
class ArticleActivity : BaseActivity() {
    @JvmField
    @Autowired
    var url: String = ""

    private lateinit var viewBinding: ActivityArticleBinding

    override fun setLayoutId() = viewBinding.root

    override fun initViewBinding(layoutInflater: LayoutInflater) {
        viewBinding = ActivityArticleBinding.inflate(layoutInflater)
    }

    override fun initView() {
        ARouter.getInstance().inject(this)
        setSetting()
        initWebView()
    }

    private fun setSetting() {
        viewBinding.webView!!.settings.apply {
            //支持javascript
            javaScriptEnabled = true
            // 设置可以支持缩放
            setSupportZoom(true)
            useWideViewPort = true
            //自适应屏幕
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            loadWithOverviewMode = true
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }

    }

    private fun initWebView() {
        viewBinding.webView.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(webview: WebView, url: String): Boolean {
                if (url == null) {
                    return false
                }

                if (url.startsWith("weixin://") || url.startsWith("jianshu://")) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    return true
                }
                webview.loadUrl(url)
                return true
            }
        }
        viewBinding.webView.webChromeClient = object: WebChromeClient() {
            override fun onProgressChanged(p0: WebView?, p1: Int) {
                super.onProgressChanged(p0, p1)


            }
        }
        viewBinding.webView.loadUrl(url)
    }

    override fun onDestroy() {
        viewBinding.webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        viewBinding.webView.clearHistory()
        (viewBinding.webView.parent as ViewGroup).removeView(viewBinding.webView)
        viewBinding.webView.destroy()
        super.onDestroy()
    }
}