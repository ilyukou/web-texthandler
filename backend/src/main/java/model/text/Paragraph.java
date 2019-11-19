package model.text;

import java.util.List;

public class Paragraph {
    private List<Sentence> sentenceList;

    public Paragraph(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }

    public String getParagraphAsString(){
        StringBuilder paragraph = new StringBuilder();
//        paragraph.append("\t");
        final List<Sentence> sentences = getSentenceList();
//        for (Sentence sentence : getSentenceList()){
//            paragraph.append(sentence.getSentenceAsString());
//        }
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
