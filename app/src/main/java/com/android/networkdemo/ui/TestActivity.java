package com.android.networkdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.networkdemo.R;
import com.android.networkdemo.utils.GlobalConsts;
import com.android.networkdemo.utils.RationUtils;

public class TestActivity extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        if(GlobalConsts.Rx == 0 || GlobalConsts.Ry == 0){
//            RationUtils.initGlobalValues(getApplicationContext());
//        }
        text = (TextView) findViewById(R.id.text);
        Toast.makeText(TestActivity.this,"RX=="+GlobalConsts.Rx+"====Ry==="+GlobalConsts.Ry,Toast.LENGTH_LONG).show();
        Log.d("liuyuting", "onCreate: (int)GlobalConsts.Rx*400==="+(int)(GlobalConsts.Rx*400));
        Log.d("liuyuting", "onCreate: (int)GlobalConsts.Ry*400==="+(int)(GlobalConsts.Ry*400));
        text.setWidth((int) (GlobalConsts.Rx*400));
        text.setHeight((int)(GlobalConsts.Ry*400));
    }
}
