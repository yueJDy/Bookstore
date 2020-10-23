package myFunction;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;


public class VerifyUtils {
	public static final String SITE_KEY = "6Lf_sdkZAAAAABI29FKzToJ0CasW0jgey-xxvcD6";
	public static final String SECRET_KEY = "6Lf_sdkZAAAAALeCf50vrulmpF8AoGjutmwlbI8r";
	public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	public static boolean verify(String gRecaptchaResponse) {
		if(gRecaptchaResponse == null || gRecaptchaResponse.length() == 0 ) {
			return false;
		}
		try {
			URL verifyUrl = new URL(SITE_VERIFY_URL);
			
			// Mở một kết nối (Connection) tới URL trên
			HttpsURLConnection con = (HttpsURLConnection) verifyUrl.openConnection();
			
			// Thêm các thông tin Header vào Request chuẩn bị gửi tới Server 
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
			
			// Dữ liệu được gửi tới Server
			String postParams = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;
			
			// send request
			con.setDoOutput(true);
			
			// Lấy Output Stream của kết nối tới Server
			// Ghi dữ liệu vào Output Stream - gửi thông tin đến Server
			OutputStream outStream = (OutputStream) con.getOutputStream();
			outStream.write(postParams.getBytes());
			
			outStream.flush();
			outStream.close();
			
			// Mã trả lời được gửi về từ Server
			int responseCode = con.getResponseCode();
			System.out.println("ResponseCode = " + responseCode);
			
			// Lấy Input Stream của Connection để đọc dữ liệu gửi đến từ Server.
			InputStream is = con.getInputStream();
			
			JsonReader jsonReader = Json.createReader(is);
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			
			System.out.println("Response: " + jsonObject);
			
			boolean success = jsonObject.getBoolean("success");
			return success;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
