package all.donordarah.app;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ServerClass {
	
	public static String getUrl(String subUrl){
		//return "http://japanvarietyshow.com/donor_darah/"+subUrl; //koneksi melalui internet
		return "http://192.168.2.228/donor_darah/"+subUrl; //koneksi dengan teathering atau jaringan lokal
		//return "http://10.0.2.2/donor_darah/"+subUrl; // koneksi menggunakan emulator bawaan
	}
	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
		//client.setMaxRetriesAndTimeout(0, 10000);
		try {
			AsyncHttpClient client = new AsyncHttpClient();
			//client.setTimeout(30*1000);
			client.post(getUrl(url), params, responseHandler);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
