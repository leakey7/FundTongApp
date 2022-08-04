package com.gzyslczx.ncfundscreenapp;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gzyslczx.ncfundscreenapp.databinding.ActivityWebBinding;

public class WebActivity extends BaseActivity<ActivityWebBinding> {

    private final String TAG = "WebActivity";
    public static final String WebPath = "WebUrl";

    @Override
    public void InitLayout() {
        mViewBinding = ActivityWebBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());
    }

    @Override
    public void InitViews() {
        SetWeb();
        /*禁止Web跳转其他浏览器*/
        mViewBinding.MyWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "Web加载中");
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, "Web加载完成");
                super.onPageFinished(view, url);
            }
        });

        /*监听Web加载状态*/
        mViewBinding.MyWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress<100){
                    mViewBinding.MyWebProgress.setVisibility(View.VISIBLE);
                    mViewBinding.MyWebProgress.setProgress(newProgress);
                }else {
                    mViewBinding.MyWebProgress.setVisibility(View.GONE);
                }
            }

        });

        /*加载目的Url*/
        String PathUrl = getIntent().getStringExtra(WebPath);
        mViewBinding.MyWebView.loadUrl(PathUrl);

    }


    private void SetWeb(){
        WebSettings settings = mViewBinding.MyWebView.getSettings();
        // 设置WebView支持JavaScript
        settings.setJavaScriptEnabled(true);
        //支持自动适配
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持放大缩小
        settings.setSupportZoom(true);
        //显示缩放按钮
        settings.setBuiltInZoomControls(true);
        settings.setAllowFileAccess(false);
        settings.setSaveFormData(false);
        settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        settings.setSupportMultipleWindows(true);
        //不适用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //显示图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 把图片加载不放在最后来加载渲染
        settings.setBlockNetworkImage(false);
    }

}