package org.platform.snail.utils;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

//自定义证书管理类来导入证书
public class MyX509TrustManager implements X509TrustManager {

	/*
	 * The default X509TrustManager returned by SunX509. We'll delegate
	 * decisions to it, and fall back to the logic in this class if the default
	 * X509TrustManager doesn't trust it.
	 */
	private X509TrustManager sunJSSEX509TrustManager;

	MyX509TrustManager() throws Exception {
		// create a "default" JSSE X509TrustManager.
		InputStream ins = MyX509TrustManager.class.getResourceAsStream("cacert.pem");		//读取根证书，相对路径和绝对路径均可,推荐绝对路径
        CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
        Certificate cer = cerFactory.generateCertificate(ins);
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(null, null);		
		ks.setCertificateEntry("trust", cer);		//使用自定义的信任证书
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
		tmf.init(ks);
		TrustManager tms[] = tmf.getTrustManagers();
		/*
		 * Iterate over the returned trustmanagers, look for an instance of
		 * X509TrustManager. If found, use that as our "default" trust manager.
		 */
		for (int i = 0; i < tms.length; i++) {
			if (tms[i] instanceof X509TrustManager) {
				sunJSSEX509TrustManager = (X509TrustManager) tms[i];
				return;
			}
		}
		/*
		 * Find some other way to initialize, or else we have to fail the
		 * constructor.
		 */
		throw new Exception("Couldn't initialize");
	}


	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		// TODO Auto-generated method stub
		try {
			sunJSSEX509TrustManager.checkClientTrusted(chain, authType);
		} catch (CertificateException excep) {
			// do any special handling here, or rethrow exception.

		}
	}


	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		// TODO Auto-generated method stub
		try {
			sunJSSEX509TrustManager.checkServerTrusted(chain, authType);
		} catch (CertificateException excep) {
			/*
			 * Possibly pop up a dialog box asking whether to trust the cert
			 * chain.
			 */
		}
	}

	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return sunJSSEX509TrustManager.getAcceptedIssuers();
	}

}
