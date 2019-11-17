import Database.Database;
import Database.DatabaseSwitcher;
import model.SplitUtils;
import model.text.Text;

public class Main {
    public static void main(String[] args) {
        Database db = DatabaseSwitcher.getDatabase();
        Text text = SplitUtils
                .createText(
                        db.getRawContentAsString
                                ("src/main/resources/file.txt"));
        String s1 = text.getParagraphs().get(0).getSentenceList().get(0).getSentenceAsString();
        String s2 = text.getParagraphs().get(0).getSentenceList().get(1).getSentenceAsString();
        System.out.println();
    }
}
