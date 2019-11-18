package utils;

import model.text.Paragraph;
import model.text.Sentence;
import model.text.Text;
import model.text.textElement.TextElement;
import model.text.textElement.TextElementType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class TextParser {

    private static final String regExForSplitTextByParagraphs = "[\\s]{2,}";

    /**
     * https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     * пробел один или много раз " +" или
     * или ? = , или знаки пунктуации \ p{Punct} или многоточее… )
     */
    private static final String regExForSplitByTextElements = " +|(?=,|\\p{Punct}|…)";

    public static Text parse(String content){

        if(StringUtils.isBlank(content)){
            throw new IllegalArgumentException("String is blank");
        }

        return new Text(getParagraphsFromString(content));
    }

    public static List<Paragraph> getParagraphsFromString(String text){

        List<Paragraph> paragraphs = new ArrayList<Paragraph>();
        List<String> paragraphStrings = removeBlankString(Arrays.asList(text.split(regExForSplitTextByParagraphs)));

        for (String string : paragraphStrings){
            paragraphs.add(new Paragraph(getSentencesFromParagraphString(string)));
        }
        return paragraphs;
    }

    public static List<Sentence> getSentencesFromParagraphString(String paragraph){

        List<String> strings = removeBlankString(Arrays.asList(paragraph.split(regExForSplitByTextElements)));

        return getSentences(strings);
    }

    public static List<Sentence> getSentences(List<String> paragraphAsTextElements){
        System.out.println();
        List<Sentence> sentenceList = new ArrayList<>();

        List<String> sentence = new ArrayList<>();

        for (int i=0; i<paragraphAsTextElements.size(); i++){

            // for on punctuationMarkForEndOfSentence arrays
            for (String punctuationMarkForEndOfSentence : TextElement.getPunctuationMarkForEndOfSentence()){

                // length punctuation mark can't be more than 1
                if(paragraphAsTextElements.get(i).length() > 1){
                    sentence.add(paragraphAsTextElements.get(i));
                    break;
                }

                if (paragraphAsTextElements.get(i).equals(punctuationMarkForEndOfSentence)){

                    sentence.add(paragraphAsTextElements.get(i));
                    // end of sentence
                    sentenceList.add(new Sentence(getTextElements(sentence)));
                    // clear the list
                    sentence.clear();
                    break;
                }else {
                    // not end of sentence
                    sentence.add(paragraphAsTextElements.get(i));
                    break;
                }
            }
        }
        System.out.println();
        return sentenceList;
    }

    public static List<TextElement> getTextElements(List<String> list){
        System.out.println();
        List<TextElement> textElements = new ArrayList<>();

        for (String string : list){

            switch (TextElement.getTypeOfElement(string)){
                case Word:
                    textElements.add(new TextElement(string, TextElementType.Word));
                    break;
                case PunctuationMark:
                    textElements.add(new TextElement(string, TextElementType.PunctuationMark));
                    break;
                case PunctuationMarkForEndOfSentence:
                    textElements.add(new TextElement(string, TextElementType.PunctuationMarkForEndOfSentence));
                    break;
                default:
                    throw new IllegalArgumentException("Not found correct TextElementType for "+string);

            }
        }
        System.out.println();
        return textElements;
    }


    private static List<String> removeBlankString(List<String> list) {

        List<String> linkedList = new LinkedList<String>(list);

        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println();
            if (StringUtils.isBlank(linkedList.get(i))) {
                linkedList.remove(i);
            }
        }
        return linkedList;
    }

}
