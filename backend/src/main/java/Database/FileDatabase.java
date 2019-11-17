package Database;

import model.SplitUtils;
import model.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileDatabase implements Database{

    private static final String filePath = "/home/ilya/Desktop/Code/web-texthandler/backend/src/main/resources/file.txt";
    @Override
    public void setText(Text data, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            fileWriter.write(data.getTextAsString());

            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Text getText(String filePath) {
        return SplitUtils.createText(getRawContentAsString(filePath));
    }

    @Override
    public String getRawContentAsString(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String text;

            while ((text = br.readLine()) != null) {

                content.append("\n").append(text);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
