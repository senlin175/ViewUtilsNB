package com.itheima.day18.demo5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

	@ViewInject(R.id.tv1)
	private TextView tv1;
	@ViewInject(R.id.tv2)
	private TextView tv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtilsTest.inject(this);

		Log.d("MainActivity","fix a bug ,no thanks");
	}
	
	@Onclick({R.id.btn1,R.id.btn2})
	private void onClick(View view){
		switch (view.getId()) {
		case R.id.btn1:
			tv1.setText("btn1");
			Toast.makeText(MainActivity.this, "btn1"+tv1.getText(), Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn2:
			tv2.setText("btn2");
			Toast.makeText(MainActivity.this, "btn2"+tv2.getText(), Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
