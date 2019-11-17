package servlets;

import Database.Database;
import Database.DatabaseSwitcher;
import model.text.Text;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;

class PostHandler {
    private static Database database = DatabaseSwitcher.getDatabase();

    static void handle(String str, String filePath){
        if(StringUtils.isNoneBlank(filePath) && StringUtils.isNoneBlank(str)){
            database.saveText(new Text(str),filePath);

        }else if(StringUtils.isBlank(str)){
            System.out.println();
            database.saveText(null,filePath);
        }
    }
}
