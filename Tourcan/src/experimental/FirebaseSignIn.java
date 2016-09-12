package experimental;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.OnFailureListener;
import com.google.firebase.tasks.OnSuccessListener;

@SuppressWarnings("serial")
public class FirebaseSignIn extends HttpServlet implements OnSuccessListener<FirebaseToken>, OnFailureListener {

	// private static FirebaseOptions options = null;
	FirebaseToken result = null;

	public static void main(String[] args) {
		String idTokenString = "eyJhbGciOiJSUzI1NiIsImtpZCI6Ijc4N2YzNjBjNjUyMjUwZjRkOTcwOWY1MjczYjRlMzQ4YTQ4YzJlYTQifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdG91cmNhbi0xMzczIiwiYXVkIjoidG91cmNhbi0xMzczIiwiYXV0aF90aW1lIjoxNDY5MTYzOTUxLCJ1c2VyX2lkIjoiSGxna1lIMjFaNWF0RnBFU2ZUdk5VUFZ3MUx0MiIsInN1YiI6IkhsZ2tZSDIxWjVhdEZwRVNmVHZOVVBWdzFMdDIiLCJpYXQiOjE0NjkxNzMzNTYsImV4cCI6MTQ2OTE3Njk1NiwiZW1haWwiOiJwaW5ndTgwMDdAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbInBpbmd1ODAwN0BnbWFpbC5jb20iLCJwaW5ndTgwMDdAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.rQOp1C0EWrgOL7vs9DTvS_pNhTsPtO5ZBQ4TQxMucKBuBeDNfgQZH86VWohNojTfvcNxzpz0Em2HLE_2jfOsytQaJJone8keNyWr07SARhzYnccFpC7BWhzZ3j2TeKXQzps8sbRskAC5zY9B_j74aWUBszUikYRskEBHbyRKrHMoS9ey_jou2hA4JbtaMp24G0qdJ6H-bqu9MgsJt5LIUdceaSavxv25Ld40ZM_xGDaBJ3KayevLrnMcblUAdNPhtrGihtb899Y8J65HjHGV0n54D4VFvo1eUxcPv12Vyc_SPRnBskHnt4XvlhWSDXTtMALApOqHPZRdoLOF94QIHQ";
		// System.out.println(idTokenString);
		FirebaseSignIn fsi = new FirebaseSignIn();
		FirebaseAuth.getInstance().verifyIdToken(idTokenString).addOnSuccessListener(fsi);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\\\\\\ main Finish ///");
	}

	// static {
	// try {
	// options = new FirebaseOptions.Builder()
	// .setServiceAccount(new FileInputStream("E:\\Tourcan-758283a500f1.json"))
	// // .setDatabaseUrl("https://databaseName.firebaseio.com/")
	// .build();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// }
	// FirebaseApp.initializeApp(options);
	// System.out.println("FirebaseApp init...");
	// }

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
		// FirebaseAuth.getInstance().verifyIdToken(idTokenString).addOnSuccessListener(this);
		FirebaseAuth.getInstance().verifyIdToken(idTokenString)
				.addOnSuccessListener(new OnSuccessListener<FirebaseToken>() {
					@Override
					public void onSuccess(FirebaseToken result) {
						for (Entry<String, Object> e : result.getClaims().entrySet()) {
							System.out.format("%16s : %s --%s\n", e.getKey(), e.getValue(),
									e.getValue().getClass().getSimpleName());
						}
					}
				});

		PrintWriter out = resp.getWriter();
		out.println("test");
		System.out.println("\\\\\\doPost Finish///");
	}

	@Override
	public void onFailure(Exception e) {
		e.printStackTrace();
	}

	@Override
	public void onSuccess(FirebaseToken result) {
		this.result = result;
		for (Entry<String, Object> e : result.getClaims().entrySet()) {
			System.out.println(e.getKey() + "\t: " + e.getValue() + " (" + e.getValue().getClass().getName() + ")");
		}
		System.out.println(new Date(1000 * (Long) result.getClaims().get("exp")).after(new Date()));																									// valid.
	}
}
