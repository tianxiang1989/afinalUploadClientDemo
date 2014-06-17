package com.example.afinalupload;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.message.BasicNameValuePair;







import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class uploadThread  {
	Context context = null;
	Handler handler = null;
	/** load对话框 */
	Message msg = null;

	/**
	 * 构造方法 
	 * 
	 */
	public uploadThread(Context context, Handler handler
			) {
		this.context = context;
		this.handler = handler;
		msg = new Message();
	}

	private static ObjFinalHttp instance = null;

	/**
	 * post方式提交
	 * @param url 提交的url
	 * @param params 提交的参数
	 */
	public void threadPost(String url, AjaxParams ajaxPparams) {
		Log.v("--LoginAndSysUsingThread thread post--", "url=====" + url);

		FinalHttp http = ObjFinalHttp.getFinalHttp();
		http.post(url, ajaxPparams,
				new AjaxCallBack<String>() {
					
					
			

					@Override
					public void onFailure(Throwable t, String strMsg) {
						super.onFailure(t, strMsg);
						Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT)
						.show();
					}

					// 成功的处理
					@Override
					public void onSuccess(String result) {
						Log.v("--LoginAndSysUsingThread post onSuccess result:--",
								result);
						Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT)
						.show();
						
						// 成功what置为0
						msg.what = 0;
						Bundle data = new Bundle();
						data.putCharSequence("result", result);
						msg.setData(data);
						handler.sendMessage(msg);
					}
				});
	}
}
