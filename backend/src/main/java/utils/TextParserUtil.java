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

public final class TextParserUtil {

    // space two or more times
    private static final String regExForSplitTextByParagraphs = "[\\s]{2,}";

    /**
     * space one or more times " +"
     * OR
     * ( ? = , OR punctuation mark OR ...
     */
    private static final String regExForSplitByTextElements = " +|(?=,|\\p{Punct}|…)";

    /**
     * @param content of the text
     * @return Text object which contains paragraphs,
     * sentences and TextElements
     */
    public static Text parse(String content){

        if(StringUtils.isBlank(content)){
            throw new IllegalArgumentException("String is blank");
        }

        return new Text(getParagraphs(content));
    }

    private static List<Paragraph> getParagraphs(String text){

        List<Paragraph> paragraphs = new ArrayList<Paragraph>();
        List<String> paragraphStrings = removeBlankString(Arrays.asList(text.split(regExForSplitTextByParagraphs)));

        for (String string : paragraphStrings){
            paragraphs.add(new Paragraph(getSentences(string)));
        }
        return paragraphs;
    }

    private static List<Sentence> getSentences(String paragraph){

        List<String> strings = removeBlankString(Arrays.asList(paragraph.split(regExForSplitByTextElements)));

        List<Sentence> returnableSentenceList = new ArrayList<>();

        List<String> sentence = new ArrayList<>();

        for (int i=0; i<strings.size(); i++){

            // for on punctuationMarkForEndOfSentence arrays
            for (String punctuationMarkForEndOfSentence : TextElement.getPunctuationMarkForEndOfSentence()){

                // length punctuation mark can't be more than one
                if(strings.get(i).length() > 1){
                    sentence.add(strings.get(i));
                    break;
                }

                if (strings.get(i).equals(punctuationMarkForEndOfSentence)){

                    sentence.add(strings.get(i));
                    // end of sentence
                    returnableSentenceList.add(new Sentence(getTextElements(sentence)));
                    // clear the list
                    sentence.clear();
                    break;
                }else {
                    // not end of sentence
                    sentence.add(strings.get(i));
                    break;
                }
            }
        }
        return returnableSentenceList;
    }

    /**
     * @param list of String text elements like : "word", "!", "?"
     * @return List TextElements where TextElement.value -> string
     * and TextElement.TextElementType contains type of contains string :
     * "Word", "PunctuationMark", "PunctuationMarkForEndOfSentence"
     */
    private static List<TextElement> getTextElements(List<String> list){
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
        return textElements;
    }


    /**
     * Removing in List elements which blank
     * Before method { "Hi","","Tom","!"}
     * After { "Hi", "Tom","!"}
     * @param list of string text elements after regEx splitting
     * @return List of string text elements without elements which blank
     */
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
