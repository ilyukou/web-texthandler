import Database.Database;
import Database.DatabaseSwitcher;
import model.text.Text;
import utils.TextParserUtil;

public class Main {
    public static void main(String[] args) {
        Database db = DatabaseSwitcher.getDatabase();
        Text text = TextParserUtil
                .parse(
                        db.getRawContentAsString
                                ("src/main/resources/text.txt"));
        String s1 = text.getParagraphs().get(0).getSentences().get(0).getSentenceAsString();
        String s2 = text.getParagraphs().get(0).getSentences().get(1).getSentenceAsString();
        System.out.println();
    }
}
