package Database;

import model.text.Text;

public interface Database {
    void setData(Text data);
    Text getData();
}
