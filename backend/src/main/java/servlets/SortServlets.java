package servlets;

import Database.Database;
import Database.DatabaseSwitcher;
import com.google.gson.Gson;
import model.Response;
import model.ResponseStatus;
import model.text.Text;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SortServlets extends HttpServlet {

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

        System.out.println();
        if(text.getParagraphs() == null){
            response = new Response(ResponseStatus.error,text);
        }else {
            System.out.println();
            text = Text.sortTextSentencesByLengthOfWords(text);
            response = new Response(ResponseStatus.ok,text);
        }

        String responseJsonString = gson.toJson(response);
        writer.println(responseJsonString);
    }
}
