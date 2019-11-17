package Database;

import model.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileDatabaseTest {

    private String databaseFileTestPath;
    private FileDatabase fileDatabase;
    private String textAsString;

    @BeforeEach
    void setUp() {
        databaseFileTestPath = "/home/ilya/Desktop/Code/web-texthandler/backend/src/test/resources/databaseFileTest.txt";
        fileDatabase = new FileDatabase();
        textAsString = "Bla blaf 2f2lfa fle2lf.";
    }

    // how it's are testing ?
    @Test
    void setText() {
        fileDatabase.saveText(new Text(textAsString),databaseFileTestPath);
    }

    @Test
    void getText() {
        assertEquals(new Text(textAsString).getTextAsString(),
                fileDatabase.findText(databaseFileTestPath).getTextAsString());
    }

    @Test
    void getRawContentAsString() {
        assertEquals(new Text(textAsString).getTextAsString(),
                new Text(fileDatabase.getRawContentAsString(databaseFileTestPath)).getTextAsString());
    }
}