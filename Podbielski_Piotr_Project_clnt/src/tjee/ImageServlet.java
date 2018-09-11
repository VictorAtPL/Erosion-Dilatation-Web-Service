package tjee;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.sun.prism.Image;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3783943484115143967L;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String filename = request.getPathInfo().substring(1);
    	File image = new File(UploadPage.tempDir + "/" + filename);
    	MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
    	String mimeType = mimeTypesMap.getContentType(filename);
    	
    	response.setContentType(mimeType);
    	
    	try {
            OutputStream os = response.getOutputStream();
            byte[] buf = new byte[8192];
            InputStream is = new FileInputStream(image);
            int length = 0;
            int c = 0;
            while ((c = is.read(buf, 0, buf.length)) > 0) {
                os.write(buf, 0, c);
                os.flush();
                length += c;
            }
            os.close();
            is.close();
            
            response.setContentLength(length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }

}