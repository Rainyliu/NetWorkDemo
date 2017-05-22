package com.android.networkdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.android.networkdemo.R;

public class KeyCodeActivity extends AppCompatActivity {
    private static String TAG = "liuyuting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_code);
        Log.d(TAG, "onCreate: ");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
                
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_DOWN:
                Log.d(TAG, "onKeyDown: 按了下键");
                Toast.makeText(this, "onKeyDown: 按了下键", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Log.d(TAG, "onKeyDown: keyCode==="+keyCode+"====event=="+event.getAction());
                Toast.makeText(this, "keyCode==="+keyCode+"====event=="+event.getDisplayLabel()+"==="+event.getKeyCode(), Toast.LENGTH_SHORT).show();
                return true;
        }
//        return super.onKeyDown(keyCode, event);
    }
}
