package model;

import model.text.Paragraph;
import model.text.Sentence;
import model.text.Text;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class SplitUtils {
    private static final Converter converter = new Converter();

    private static final String regExForSplitTextByParagraphs = "[\\s]{2,}";

    /**
     * https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
     * пробел один или много раз " +" или
     * или ? = , или знаки пунктуации \ p{Punct} или многоточее… )
     */
    private static final String regExForSplitBySentences = " +|(?=,|\\p{Punct}|…)";

    public static Text createText(String content){

        if(StringUtils.isBlank(content)){
            throw new IllegalArgumentException("String is blank");
        }

        return new Text(getParagraphsFromString(content));
    }

    public static List<Paragraph> getParagraphsFromString(String text){

        List<Paragraph> paragraphs = new ArrayList<Paragraph>();
        List<String> paragraphStrings = splitTextByParagraphsString(text);

        for (String string : paragraphStrings){
            paragraphs.add(new Paragraph(getSentencesFromParagraphString(string)));
        }
        return paragraphs;
    }

    public static List<Sentence> getSentencesFromParagraphString(String paragraph){

        List<String> strings = splitParagraphStringBySentenceStrings(paragraph);
        System.out.println();
        return Converter.convertListOfStringTextElementToListSentence(strings);
    }


    private static List<String> splitTextByParagraphsString(String text) throws IllegalArgumentException {
        if(StringUtils.isBlank(text)){
            throw new IllegalArgumentException("String is null or length 0");
        }

        List<String> list = removeBlankString(Arrays.asList(text.split(regExForSplitTextByParagraphs)));

        return list;
    }

    private static List<String> splitParagraphStringBySentenceStrings(String text) throws IllegalArgumentException {
        if(StringUtils.isBlank(text)){
            throw new IllegalArgumentException("String is null or length 0");
        }
        List<String> list = Arrays.asList(text.split(regExForSplitBySentences));
        System.out.println();
        return removeBlankString(list);
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
