package servlets.upload;

import Database.Database;
import Database.DatabaseSwitcher;
import model.Text;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;



public class UploadServlets extends HttpServlet {
    private Database database = DatabaseSwitcher.getDatabase();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST http req");
        // fix cors polity
        resp.addHeader("Access-Control-Allow-Origin","*");
        if(ServletFileUpload.isMultipartContent(req)){
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload();

            // Parse the request
            FileItemIterator iter = null;
            try {
                iter = upload.getItemIterator(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    if (!iter.hasNext()) break;
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
                FileItemStream item = null;
                try {
                    item = iter.next();
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }

                String name = item.getFieldName();
                InputStream stream = item.openStream();
                if (item.isFormField()) {
                    String str = Streams.asString(stream);

                    System.out.println("POST STRING");
                    System.out.println(str);

                    database.setData(new Text(str));
                } else {
                    System.out.println("File field " + name + " with file name "
                            + item.getName() + " detected.");

                }
            }
        }

    }
}
