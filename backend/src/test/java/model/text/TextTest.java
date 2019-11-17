package model.text;

import Database.Database;
import Database.DatabaseSwitcher;
import model.SplitUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class TextTest {
    private Text text;
    private String filePath;
    private Database database;

    @Before
    public void setUp() throws Exception {
        filePath = "/home/ilya/Desktop/Code/web-texthandler/backend/src/test/resources/text.txt";
        database = DatabaseSwitcher.getDatabase();
        text = database.getText(filePath);
    }

    @Test
    public void sortTextSentencesByLengthOfWords() {
        text = Text.sortTextSentencesByLengthOfWords(text);

        // All sorted sentence contains in 0 paragraphs
        for (int i=1; i<text.getParagraphs().get(0).getSentenceList().size(); i++){
            if (text.getParagraphs().get(0).getSentenceList().size() == 1){
                break;
            }
            // in correct sorted this condition never been true
            if(text.getParagraphs().get(0).getSentenceList().get(i).getCountOfTextElements() <
                    text.getParagraphs().get(0).getSentenceList().get(i-1).getCountOfTextElements()){
                Assertions.assertTrue(false);
            }
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void getTextAsString() {
        String actual = text.getTextAsString();
        String expected = database.getRawContentAsString(filePath);

        Assertions.assertEquals(expected,actual);
    }
}
