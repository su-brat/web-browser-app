package appstore.com.myapplication9;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv1=(WebView) findViewById(R.id.wv1);
        wv1.getSettings().setBuiltInZoomControls(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(MainActivity.this,"Page is loading",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(MainActivity.this,"Page loaded",Toast.LENGTH_SHORT).show();

            }
        });
        wv1.addJavascriptInterface(this,"myinterface");
    }

    @JavascriptInterface
    public void display(String uname,String pwd) {
        Toast.makeText(MainActivity.this,"User ID: "+uname+"\nPassword: "+pwd,Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void reload() {
        wv1.loadUrl("file:///android_asset/sample.html");
    }

    public void test(View v) {

        switch (v.getId()) {
            case R.id.f:
                wv1.loadUrl("http://www.facebook.com");
                break;
            case R.id.g:

                wv1.loadUrl("http://www.google.com");
                break;
            case R.id.y:

                wv1.loadUrl("http://www.yahoo.com");
                break;
            case R.id.s:
                wv1.loadUrl("file:///android_asset/sample.html");
                break;
            default:
                EditText et1=(EditText) findViewById(R.id.et1);
                String url=et1.getText().toString().trim();
                wv1.loadUrl(url);
                break;
        }
    }
}
