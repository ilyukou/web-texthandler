package servlets;

import Database.Database;
import Database.DatabaseSwitcher;
import model.text.Text;
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

            System.out.println("isMultipartContent");
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload();

            try {

                // Parse the request
                FileItemIterator iter = upload.getItemIterator(req);

                while (true) {
                    System.out.println("while");
                    if (!iter.hasNext()) break;

                    FileItemStream item = iter.next();;

                    String name = item.getFieldName();
                    InputStream stream = item.openStream();

                    if (item.isFormField()) {

                        String str = Streams.asString(stream);

                        System.out.println("POST STRING");
                        System.out.println(str);

                        database.setData(new Text().build(str));
                    } else {
                        System.out.println("File field " + name + " with file name "
                                + item.getName() + " detected.");
                    }
                }
            }catch (FileUploadException e){
                e.printStackTrace();
            }
        }
    }
}
