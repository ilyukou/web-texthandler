package model.text;

import java.util.List;

public class Paragraph {
    private List<Sentence> sentences;

    public Paragraph(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public String getParagraphAsString(){
        StringBuilder paragraph = new StringBuilder();

        final List<Sentence> sentences = getSentences();

        for (int i=0; i<sentences.size(); i++){

            if(i == 0){
                // if it's first sentence
                paragraph.append(sentences.get(i).getSentenceAsString());

            }else {
                // if isn't first sentence then add space between before sentence and now sentence
                paragraph.append(" ").append(sentences.get(i).getSentenceAsString());
            }
        }
        return paragraph.toString();
    }
}
