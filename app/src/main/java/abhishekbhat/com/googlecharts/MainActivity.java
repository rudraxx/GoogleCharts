package abhishekbhat.com.googlecharts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText num1;
//    EditText num2, num3, num4, num5;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText)findViewById(R.id.num1);
//        num2 = (EditText)findViewById(R.id.num2);
//        num3 = (EditText)findViewById(R.id.num3);
//        num4 = (EditText)findViewById(R.id.num4);
//        num5 = (EditText)findViewById(R.id.num5);
        btnShow = (Button)findViewById(R.id.show);

        btnShow.setOnClickListener(btnShowOnClickListener);
    }

    View.OnClickListener btnShowOnClickListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this,
                        ShowWebChartActivity.class);

                intent.putExtra("FromMainActivity_strNumberOfResults", String.valueOf(getNum(num1))); // Send string
//                intent.putExtra("NUM2", getNum(num2));
//                intent.putExtra("NUM3", getNum(num3));
//                intent.putExtra("NUM4", getNum(num4));
//                intent.putExtra("NUM5", getNum(num5));

                startActivity(intent);
            }

        };

    private int getNum(EditText editText){

        int num = 0;

        String stringNum = editText.getText().toString();
        if(!stringNum.equals("")){
            num = Integer.valueOf(stringNum);
        }
        return (num);
    }

}
