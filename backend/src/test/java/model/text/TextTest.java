package model.text;

import Database.Database;
import Database.DatabaseSwitcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TextTest {
    private Text text;
    private String filePath;
    private Database database;

    @Before
    public void setUp() throws Exception {
        filePath = "/home/ilya/Desktop/Code/web-texthandler/backend/src/test/resources/text.txt";
        database = DatabaseSwitcher.getDatabase();
        text = database.findText(filePath);
    }

    @Test
    public void getTextAsString() {
        String actual = text.getTextAsString();

        String expected = database.getRawContentAsString(filePath);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void sortTextSentencesByLengthOfWords() {
        text = Text.sortTextSentencesByLengthOfWords(text);

        // All sorted sentence contains in 0 paragraphs
        for (int i = 1; i<text.getParagraphs().get(0).getSentences().size(); i++){
            if (text.getParagraphs().get(0).getSentences().size() == 1){
                break;
            }
            // in correct sorted this condition never been true
            if(text.getParagraphs().get(0).getSentences().get(i).getCountOfTextElements() <
                    text.getParagraphs().get(0).getSentences().get(i-1).getCountOfTextElements()){
                Assertions.assertTrue(false);
            }
        }
        Assertions.assertTrue(true);
    }
}
