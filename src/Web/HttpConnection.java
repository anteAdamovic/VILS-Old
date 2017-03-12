package Web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class HttpConnection {
	private URL url;
	private HttpURLConnection connection;
	
	public HttpConnection(String url) throws IOException{
		this.url = new URL(url);
		this.connection = (HttpURLConnection) this.url.openConnection();
	}
	
	public void connect() throws IOException{
		connection.connect();
	}
	
	public URLConnection getConnection(){
		return connection;
	}
	
	public InputStream getInputStream() throws IOException{
		
		return connection.getInputStream();
	}
	
	public OutputStream getOutputStream() throws IOException{
		return connection.getOutputStream();
	}
	
	public boolean isConnected() throws Exception{
		int flag;
		try {
			flag = connection.getResponseCode();
		} catch (Exception e ){
			return false;
		}
		
		if(flag >= 400 || flag == -1)
			return false;
		
		return true;
	}
	
	public void disconnect(){
		connection.disconnect();
	}
}
