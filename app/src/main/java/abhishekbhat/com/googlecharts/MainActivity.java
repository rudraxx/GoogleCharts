package abhishekbhat.com.googlecharts;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShow;
    String strNumberOfDays;
    WebView webView;
    float scale;

    //Spinner for Channel setting
    Spinner spinChannelField;
    ArrayAdapter<CharSequence> spinAdapter;
    String strChannelNumber;
    String strChannelName;

    //Spinner for selecting number of results:
    Spinner spinNumDays;
    ArrayAdapter<CharSequence> numDaysSpinAdapter;
    String strSpinnerNumDaysSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Scale factor to size the WebView:
        scale = getResources().getDisplayMetrics().density;

//        num1 = (EditText)findViewById(R.id.num1);
        webView = (WebView)findViewById(R.id.web);
        btnShow = (Button)findViewById(R.id.show);


        spinChannelField = (Spinner) findViewById(R.id.spinner);
        spinAdapter   = ArrayAdapter.createFromResource(this,R.array.ChannelNumbers,android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinChannelField.setAdapter(spinAdapter);
        spinChannelField.setSelection(4); // Setting to get moisture, index is zero based. So actual field num is Field(n+1)

        spinChannelField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strChannelNumber = String.valueOf(position + 1);// parent.getItemAtPosition(position).toString();

                strChannelName = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, strChannelName + " selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinNumDays = (Spinner) findViewById(R.id.spinnerNumDays);
        numDaysSpinAdapter = ArrayAdapter.createFromResource(this,R.array.numDaysSpinner,android.R.layout.simple_spinner_item);
        numDaysSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinNumDays.setAdapter(numDaysSpinAdapter);
        spinNumDays.setSelection(0); // 0 based indexing. Option 1 is 5 minutes

        spinNumDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSpinnerNumDaysSelected = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnShow.setOnClickListener(btnShowOnClickListener);
    }

    View.OnClickListener btnShowOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            strNumberOfDays = strSpinnerNumDaysSelected;

            webView.addJavascriptInterface(new WebAppInterface(), "Android");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("file:///android_asset/chart_thingspeak.html");

//            This webView.loadUrl should be an Async Task. But having problem updating webView from Async task.
//            plotGoogleChart myTask = new plotGoogleChart();
//            myTask.execute(new String[] {"http://google.com"});
        }
    };

//    private class plotGoogleChart extends AsyncTask<String,Void,String>{
//
//        @Override
//        protected String doInBackground(String... mLen) {
//            String mSze = mLen[0];
//            WebView wv = (WebView) findViewById(R.id.web);
//
////            webView.loadUrl("file:///android_asset/chart_thingspeak.html");
////            wv.loadUrl(mSze);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String String) {
//            Toast.makeText(MainActivity.this, "Page loaded", Toast.LENGTH_SHORT).show();
//
//            super.onPostExecute(String);
//        }
//    }

    public class WebAppInterface {
        @JavascriptInterface
        public String FromWebChartActivity_strNumberOfDays() {
            return strNumberOfDays;
        }

        @JavascriptInterface
        public int FromWebChartActivity_intGetWebLayoutHeight() {
            return (int) (webView.getHeight() / scale);
        }

        @JavascriptInterface
        public int FromWebChartActivity_intGetWebLayoutWidth() {
            return (int) (webView.getWidth() / scale);
        }

        @JavascriptInterface
        public String FromWebChartActivity_strChannelNumber() {
            return strChannelNumber;
        }
        @JavascriptInterface
        public String FromWebChartActivity_strChannelName() {
            return strChannelName;
        }
    }


}
