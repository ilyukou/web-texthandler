package servlets;

import Database.Database;
import Database.DatabaseSwitcher;
import model.text.Text;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class PostHandler {
    private static final Logger LOG = LogManager.getLogger(PostHandler.class);
    private static Database database = DatabaseSwitcher.getDatabase();

    static void handle(String str, String filePath){
        if(StringUtils.isNoneBlank(filePath) && StringUtils.isNoneBlank(str)){
            database.saveText(new Text(str),filePath);

        }else if(StringUtils.isBlank(str)){
            LOG.warn("save null Text in : "+filePath);
            database.saveText(null,filePath);
        }
    }
}
