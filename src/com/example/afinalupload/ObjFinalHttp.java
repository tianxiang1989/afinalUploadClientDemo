package com.example.afinalupload;

import net.tsz.afinal.FinalHttp;

/**
 * afinal连接工具类
 * @author liuxiuquan
 * 2014年6月13日
 */
public class ObjFinalHttp extends FinalHttp {
	private static ObjFinalHttp instance = null;

	private ObjFinalHttp() {

	}

	/**
	 * 单例
	 */
	public static ObjFinalHttp getFinalHttp() {

		if (instance == null) {
			instance = new ObjFinalHttp();
		}
		return instance;
	}
}
