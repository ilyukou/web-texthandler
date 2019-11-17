package Database;

import model.text.Text;

public interface Database {
    void saveText(Text data, String filePath);
    Text findText(String filePath);
    String getRawContentAsString(String filePath);
}
