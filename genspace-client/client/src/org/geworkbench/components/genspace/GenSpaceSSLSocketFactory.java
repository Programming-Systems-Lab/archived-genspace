package org.geworkbench.components.genspace;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class GenSpaceSSLSocketFactory implements X509TrustManager {

	private static SSLContext SSL_CONTEXT;
	private static X509Certificate cert;
	static
	{
		

		 
		try {
//			InputStream inStream = new FileInputStream("components/genspace/src/org/geworkbench/components/genspace/genspace-ssl-dev-key");
			InputStream inStream = new FileInputStream("devKey");
			 CertificateFactory cf = CertificateFactory.getInstance("X.509");
			  cert = (X509Certificate)cf.generateCertificate(inStream);
			 inStream.close();
			 
	        SSL_CONTEXT = SSLContext.getInstance("SSL");
	        SSL_CONTEXT.init(null, new TrustManager[] { new GenSpaceSSLSocketFactory() }, null);
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Unable to initialise SSL context", e);
	    } catch (KeyManagementException e) {
	        throw new RuntimeException("Unable to initialise SSL context", e);
	    } catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static Socket createSocket(String host, int port) throws UnknownHostException, IOException
	{
		return SSL_CONTEXT.getSocketFactory().createSocket(host, port);
	}
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		throw new CertificateException("Shouldn't ever use this to authenticate clients");
	}
	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
			for(X509Certificate c : chain)
			{
				if(cert.equals(c))
					return;
			}
			throw new CertificateException("GenSpace server SSL certificate does not match certificate on file");
		
	}
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

}
