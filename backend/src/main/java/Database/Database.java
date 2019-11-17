package Database;

import model.text.Text;

public interface Database {
    void setText(Text data);
    Text getText();
}
