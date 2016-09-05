

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/PhotoTest")
@MultipartConfig
public class PhotoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PhotoTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Start:get");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Start:post");
		System.out.println(request.getContentType());
		
		Part photoFile = request.getPart("imgs");
		byte[] photo_file = new byte[(int) photoFile.getSize()];
		InputStream is = photoFile.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		bis.read(photo_file);
		
		System.out.println(photo_file.length);
		
	}

}
