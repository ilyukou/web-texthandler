package model;

import model.text.Sentence;
import model.text.textElement.TextElement;
import model.text.textElement.TextElementType;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<Sentence> convert(List<String> paragraphStringList){

        List<List<String>> sentencesStringList = new ArrayList<>();

        /**
         * Разбиение листа строк ( слово или знак препинания) абзаца на лист предложения содержащий лист строк.
         */
        int from = 0;
        for (int i=0; i<paragraphStringList.size(); i++){
            for (String s : TextElement.getPunctuationMarkForEndOfLine()){
                if (paragraphStringList.get(i).equals(s)){
                    /**
                     * 1 потому что я хочу сохранить точку
                     */
                    sentencesStringList.add(getListOfStrings(paragraphStringList,from,i+1));

                    from = i+1;
                }
            }
        }
        return convertSentenceStringListToSentenceList(sentencesStringList);
    }

    private static List<String> getListOfStrings(List<String> list, int from, int to){
        List<String> strings = new ArrayList<>();

        for (int i=from; i<to; i++){
            strings.add(list.get(i));
        }
        return strings;
    }
    private static List<Sentence> convertSentenceStringListToSentenceList(List<List<String>> sentencesStringList){
        List<Sentence> sentences = new ArrayList<>();

        for (List<String> list : sentencesStringList){

            List<TextElement> textElements = new ArrayList<>();

            for (String string : list){

                if(TextElement.getTypeOfElement(string) == TextElementType.PunctuationMark){
                    textElements.add(new TextElement(string, TextElementType.PunctuationMark));

                }else if(TextElement.getTypeOfElement(string) == TextElementType.Word) {
                    textElements.add(new TextElement(string, TextElementType.Word));

                }else if(TextElement.getTypeOfElement(string) == TextElementType.PunctuationMarkForEndOfLine) {
                    textElements.add(new TextElement(string, TextElementType.PunctuationMarkForEndOfLine));
                }else {
                    throw new IllegalArgumentException("Not found correct TextElementType for "+string);
                }
            }
            sentences.add(new Sentence(textElements));
        }

        return sentences;
    }
}
