package com.aspose.omrdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

public class ViewResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_result);

        TextView tvResult =  (TextView) findViewById(R.id.tvResult);

        tvResult.setMovementMethod(new ScrollingMovementMethod());

        Bundle b = getIntent().getExtras();
        tvResult.setText(b.getString("result"));
    }
}
