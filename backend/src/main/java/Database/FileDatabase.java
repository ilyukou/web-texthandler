package Database;

import model.SplitUtils;
import model.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileDatabase implements Database{

    private static final String filePath = "/home/ilya/Desktop/Code/web-texthandler/backend/src/main/resources/file.txt";
    @Override
    public void setData(Text data) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            fileWriter.write(data.getText());

            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Text getData() {
        String content = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String text;

            while ((text = br.readLine()) != null) {

                content += "\n"+text;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SplitUtils.createText(content);
    }
}
