package Database;


import model.text.Text;
import org.apache.commons.lang3.StringUtils;
import utils.TextParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileDatabase implements Database{

    private static final String filePath = "/home/ilya/Desktop/Code/web-texthandler/backend/src/main/resources/file.txt";
    @Override
    public void saveText(Text data, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            fileWriter.write(data.getTextAsString());

            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Text findText(String filePath) {
        String str = getRawContentAsString(filePath);
        if(StringUtils.isBlank(str)){
            return new Text();
        }
        return TextParser.parse(str);
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
