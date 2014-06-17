package com.example.afinalupload;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 测试aifnal的多文件post
 * @author liuxiuquan
 * 2014年6月13日
 */
public class MainActivity extends Activity {
	/**上传的按键*/
	private Button mbtnLogin = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AjaxParams params = new AjaxParams();
		mbtnLogin = (Button) this.findViewById(R.id.button1);
		mbtnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "进入上传", Toast.LENGTH_SHORT)
						.show();
				testUpload();
			}

		});
	}

	/** 上传的Handler */
	Handler checkUpHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {// 获取数据成功
				String result = msg.getData().getCharSequence("result")
						.toString();

			} else {
				// 代码发生错误
				Log.v("错误了", "获取数据失败!");
			}
		}
	};

	private void testUpload() {
		uploadThread checkApppdateThread = new uploadThread(
				MainActivity.this, checkUpHandler);
		AjaxParams ajaxParams = new AjaxParams();
		try {
			ajaxParams.put("username", "tom1111");
			ajaxParams.put("age", "12");
			ajaxParams.put("profile_picture1", new File("/mnt/sdcard/1.png"));
			ajaxParams.put("profile_picture2", new File("/mnt/sdcard/2.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//发送请求
		 checkApppdateThread.threadPost("http://10.129.240.159:8080/testUpload/fileupload",ajaxParams);
//		checkApppdateThread.threadPost(
//				"http://10.129.241.12:7001/initblogserver/filetest", ajaxPparams);
	}
}
