package servlets;
import Database.Database;
import Database.DatabaseSwitcher;
import com.google.gson.Gson;
import model.Response;
import model.ResponseStatus;
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
import java.io.PrintWriter;

public class DataServlets extends HttpServlet{

    private Gson gson = new Gson();
    private Database database = DatabaseSwitcher.getDatabase();
    private String filePath = "/home/ilya/Desktop/Code/web-texthandler/backend/src/main/resources/file.txt";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // fix cors polity
        resp.addHeader("Access-Control-Allow-Origin","*");
        PrintWriter writer = resp.getWriter();

        Text text = database.findText(filePath);
        Response response;

        if(text.getParagraphs() != null){
            response = new Response(ResponseStatus.ok,text);
        }else {
            response = new Response(ResponseStatus.error,text);
        }

        String responseJsonString = gson.toJson(response);
        writer.println(responseJsonString);

    }

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

                        PostHandler.handle(str,filePath);
                        //database.saveText(new Text(str),filePath);
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
