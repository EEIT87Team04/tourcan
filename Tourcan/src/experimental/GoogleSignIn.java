package experimental;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

@SuppressWarnings("serial")
public class GoogleSignIn extends HttpServlet {

	public static final String CLIENT_ID = "370795963240-3rr3nug80mgjiea92n00qjk5siboabii.apps.googleusercontent.com";

	public static void main(String[] args) {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Enumeration<String> headers = req.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			System.out.println(header + ": " + req.getHeader(header));
		}
		System.out.println("---------------------");
		Enumeration<String> params = req.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			System.out.println(param + ": " + req.getParameter(param));
		}
		System.out.println("\\\\\\doGet  Finish///");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idTokenString = req.getParameter("idtoken");
//		System.out.println(idTokenString);

		// If you retrieved the token on Android using the Play Services 8.3 API
		// or newer, set
		// the issuer to "https://accounts.google.com". Otherwise, set the
		// issuer to
		// "accounts.google.com". If you need to verify tokens from multiple
		// sources, build
		// a GoogleIdTokenVerifier for each issuer and try them both.
		NetHttpTransport transport = new NetHttpTransport();
		JsonFactory jsonFactory = new GsonFactory();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Arrays.asList(CLIENT_ID)).setIssuer("accounts.google.com").build();
		try {
			GoogleIdToken idToken = verifier.verify(idTokenString);
			if (idToken != null) {
				Payload payload = idToken.getPayload();
				System.out.println("Subject = " + payload.getSubject());
				System.out.println("Email   = " + payload.getEmail() + " ("+(payload.getEmailVerified()?"verified":"unverified")+")");
//				System.out.println(payload.getEmailVerified());
//				System.out.println((String) payload.getAudience());
				System.out.println("\\\\\\doPost Finish///");
				
				PrintWriter out = resp.getWriter();
				out.println(payload.getEmail());
			} else {
				System.out.println("Invalid ID token.");
			}
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
	}

}
