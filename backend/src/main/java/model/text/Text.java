package model.text;

import model.SplitUtils;

import java.util.List;

public class Text {

    public Text build(String text) {
        return SplitUtils.createText(text);
    }
    public Text(){

    }


    private List<Paragraph> paragraphs;

    public Text(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public String getText(){

        StringBuilder stringBuilder = new StringBuilder();

        for (Paragraph paragraph: getParagraphs()){
            stringBuilder.append("\n").append(paragraph.getParagraphAsString());
        }

        return stringBuilder.toString();

    }
}
