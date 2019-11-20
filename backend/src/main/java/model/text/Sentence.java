package model.text;

import model.text.textElement.TextElement;
import model.text.textElement.TextElementType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Sentence {
    private static final Logger LOG = LogManager.getLogger(Sentence.class);

    private List<TextElement> textElements;

    public Sentence(List<TextElement> textElements) {
        this.textElements = textElements;
    }

    public List<TextElement> getTextElements() {
        return textElements;
    }

    public void setTextElements(List<TextElement> textElements) {
        this.textElements = textElements;
    }

    public int getCountOfTextElements(){
        return getTextElements().size();
    }

    public int getCountOfWords(){
        int count = 0;
        for (TextElement textElement : getTextElements()){
            if(textElement.getTextElementType() == TextElementType.Word){
                count++;
            }
        }
        return count;
    }

    public String getSentenceAsString(){
        StringBuilder sentence = new StringBuilder();

        for (int i=0; i<getTextElements().size(); i++) {
            if(getTextElements().get(i).getTextElementType() == TextElementType.Word){

                if(i == 0){
                    sentence.append(getTextElements().get(i).getValue());
                }else {
                    sentence.append(" ").append(getTextElements().get(i).getValue());
                }


            }else if(getTextElements().get(i).getTextElementType() == TextElementType.PunctuationMark){
                sentence.append(getTextElements().get(i).getValue());

            }else if(getTextElements().get(i).getTextElementType() == TextElementType.PunctuationMarkForEndOfSentence){
                sentence.append(getTextElements().get(i).getValue());

            }else {
                LOG.error("Illegal text element type");
                throw new IllegalArgumentException("Illegal text element type");
            }
        }

        return sentence.toString();
    }
}
