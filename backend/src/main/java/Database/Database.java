package Database;

import model.text.Text;

public interface Database {
    void setText(Text data, String filePath);
    Text getText(String filePath);
    String getRawContentAsString(String filePath);
}
