package model;

import Database.Database;
import Database.DatabaseSwitcher;
import model.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TextParserUtil;

import static org.junit.jupiter.api.Assertions.*;

class TextParserUtilTest {

    private Text text;
    private Database database;
    private String path = "/home/ilya/Desktop/Code/web-texthandler/backend/src/test/resources/forTextParserTest.txt";

    @BeforeEach
    void setUp() {
        database = DatabaseSwitcher.getDatabase();
        text = database.findText(path);
    }

    @Test
    void createTextCheckEquals() {
        Text t = TextParserUtil.parse(database.getRawContentAsString(path));
        assertEquals(text.getTextAsString(),t.getTextAsString());
    }


    @Test
    void comparisonSizeOfTextElementsSizeInSentence() {
        Text t = TextParserUtil.parse(database.getRawContentAsString(path));

        for (int pIndex=0; pIndex<t.getParagraphs().size(); pIndex++){
            for (int sIndex = 0; sIndex<t.getParagraphs().get(pIndex).getSentences().size(); sIndex++){
                if(t.getParagraphs().get(pIndex).getSentences().get(sIndex).getTextElements().size()
                        !=
                text.getParagraphs().get(pIndex).getSentences().get(sIndex).getTextElements().size()){
                    fail();
                }
            }
        }
    }

    @Test
    void comparisonSizeOfSentenceInParagraphs() {
        Text t = TextParserUtil.parse(database.getRawContentAsString(path));

        for (int pIndex=0; pIndex<t.getParagraphs().size(); pIndex++){

            if(t.getParagraphs().get(pIndex).getSentences().size()
                    != text.getParagraphs().get(pIndex).getSentences().size()){
                fail();
            }
        }
    }

    @Test
    void comparisonSizeOfParagraphsInText() {
        Text t = TextParserUtil.parse(database.getRawContentAsString(path));

        if(t.getParagraphs().size()
                != text.getParagraphs().size()){
            fail();
        }
    }
}
