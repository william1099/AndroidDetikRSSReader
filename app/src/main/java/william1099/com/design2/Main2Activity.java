package william1099.com.design2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String url = getIntent().getExtras().getString("link");
        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(url);
        webView.setWebViewClient(new myBrowser());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {
                WebView web = (WebView) view;
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    switch(keycode) {
                        case KeyEvent.KEYCODE_BACK: {
                            if (web.canGoBack()) {
                                web.goBack();
                                return true;
                            } else {
                                closeActivity();
                            }
                            break;
                        }
                    }
                }

                return false;
            }
        });
        //webView.setW
    }

    public void closeActivity() {
        Intent i = new Intent();
        i.putExtra("value", "Browser telah ditutup");
        setResult(RESULT_OK, i);
//        finish();
    }

    private class myBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

}
