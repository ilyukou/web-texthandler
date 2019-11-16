package model.text;

import model.SplitUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Text {

    public Text build(String text) {
        return SplitUtils.createText(text);
    }
    public Text(){

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

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
