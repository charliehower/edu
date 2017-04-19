package org.platform.snail.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * @author 陈晓克
 * @version 2013-4-16 下午5:09:12
 */

public class HttpUtils {

	public static String http(String url, Map<String, String> p)
			throws Exception {
		HttpParams params = new BasicHttpParams();
		HttpClient httpClient = new DefaultHttpClient(params);
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (p != null) {
				Iterator it = p.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry e = (Map.Entry) it.next();
					Object v = e.getValue();
					if (v instanceof String[]) {
						String[] vs = (String[]) v;
						for (int i = 0; i < vs.length; i++) {
							nvps.add(new BasicNameValuePair((String) e.getKey(), vs[i]));
						}
					} else {
						nvps.add(new BasicNameValuePair((String) e.getKey(),(String) v));
					}
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));// 将参数传入post方法中
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new Exception(response.getStatusLine().getReasonPhrase());
			}
			return EntityUtils.toString(response.getEntity());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		
	}
	public static Map<String,String> getParams(String p){
		Map<String,String> params=new HashMap<String,String>();
		if(p==null||p.equals("")){
			return params;
		}
		String [] tmp1=p.split("&");
		for(int i=0;i<tmp1.length;i++){
			String []tmp2=tmp1[i].split("=");
			//System.out.println(tmp2[0]+" "+tmp2[1]);
			params.put(tmp2[0], tmp2[1]);
		}
		return params;
	}
	public static void main(String [] args){
		HttpUtils.getParams("result=0&message=短信发送成功&smsid=2015011621283047");
	}
}
