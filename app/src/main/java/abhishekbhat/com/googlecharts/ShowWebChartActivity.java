package abhishekbhat.com.googlecharts;

/**
 * Created by abhishekbhat on 11/17/2015.
 */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled")
public class ShowWebChartActivity extends ActionBarActivity {

    WebView webView;
    String strNumberOfResults;
    int num1, num2, num3, num4, num5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webchart);

        Intent intent = getIntent();
        strNumberOfResults = intent.getStringExtra("FromMainActivity_strNumberOfResults");
//        num2 = intent.getIntExtra("NUM2", 0);
//        num3 = intent.getIntExtra("NUM3", 0);
//        num4 = intent.getIntExtra("NUM4", 0);
//        num5 = intent.getIntExtra("NUM5", 0);

        webView = (WebView)findViewById(R.id.web);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/chart_thingspeak.html");
    }

    public class WebAppInterface {

        @JavascriptInterface
        public String FromWebChartActivity_strNumberOfResults() {
            return strNumberOfResults;
        }

//        @JavascriptInterface
//        public int getNum1() {
//            return num1;
//        }

//        @JavascriptInterface
//        public int getNum2() {
//            return num2;
//        }
//
//        @JavascriptInterface
//        public int getNum3() {
//            return num3;
//        }
//
//        @JavascriptInterface
//        public int getNum4() {
//            return num4;
//        }
//
//        @JavascriptInterface
//        public int getNum5() {
//            return num5;
//        }
    }

}