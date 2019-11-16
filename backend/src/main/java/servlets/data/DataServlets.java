package servlets.data;
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

public class DataServlets extends HttpServlet{

    private Gson gson = new Gson();
    private Database database = DatabaseSwitcher.getDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // fix cors polity
        resp.addHeader("Access-Control-Allow-Origin","*");
        PrintWriter writer = resp.getWriter();

        Text text = database.getData();
        Response response;

        if(text != null){
            response = new Response(ResponseStatus.ok,text);
        }else {
            response = new Response(ResponseStatus.error,text);
        }

        String responseJsonString = gson.toJson(response);
        writer.println(responseJsonString);

    }

}
