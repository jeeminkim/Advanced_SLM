package com.example.kang.limine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by kang on 2016-05-19.
 */
public class authnate extends AppCompatActivity {
    private WebView mWv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mWv = (WebView) findViewById(R.id.webView);
        mWv.getSettings().setJavaScriptEnabled(true);  // 웹뷰에서 자바스크립트실행가능
        mWv.loadUrl("http://www.nate.com");
        mWv.setWebViewClient(new HelloWebViewClient());
    }
    private class HelloWebViewClient extends WebViewClient { //주소창 없앰
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public void goback5(View v){
        Intent intent = new Intent (getApplicationContext(),Join.class);
        startActivity(intent);
    }
}