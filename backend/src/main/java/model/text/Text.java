package model.text;

import utils.Parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Text {

    private List<Paragraph> paragraphs;

    public Text(){}

    public Text(String text){
        this.paragraphs = Parser.createText(text).getParagraphs();
    }

    public Text(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    private static List<Sentence> compare(List<Sentence> sentences){
        final Comparator<Sentence> COMPARATOR = Comparator.comparing(Sentence::getCountOfTextElements);
        sentences.sort(COMPARATOR);
        return sentences;
    }
    public static Text sortTextSentencesByLengthOfWords(Text text){

        List<Sentence> sentences = new ArrayList<>();
        for (Paragraph paragraph : text.getParagraphs()){
            sentences.addAll(paragraph.getSentenceList());
        }

        List<Paragraph> sortedSentenceInParagraph = new ArrayList<>();
        sortedSentenceInParagraph.add(new Paragraph(compare(sentences)));

        return new Text(sortedSentenceInParagraph);
    }

    public String getTextAsString(){

        StringBuilder stringBuilder = new StringBuilder();

        for (Paragraph paragraph: getParagraphs()){
            stringBuilder.append("\n").append(paragraph.getParagraphAsString());
        }

        return stringBuilder.toString();

    }


    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }


}
