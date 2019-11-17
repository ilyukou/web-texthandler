package utils;

import model.text.Sentence;
import model.text.textElement.TextElement;
import model.text.textElement.TextElementType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Converter {

    public static List<Sentence> convertListOfStringTextElementToListSentence(List<String> sentenceStringList){

        List<List<String>> sentencesStringList = new ArrayList<>();

        /**
         * Разбиение листа строк ( слово или знак препинания) абзаца на лист предложения содержащий лист строк.
         */
        int from = 0;
        for (int i=0; i<sentenceStringList.size(); i++){
            for (String s : TextElement.getPunctuationMarkForEndOfLine()){
                if (sentenceStringList.get(i).equals(s)){
                    /**
                     * 1 потому что я хочу сохранить точку
                     */
                    sentencesStringList.add(getListOfStrings(sentenceStringList,from,i+1));

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

            sentences.add(convertFromTextElementListToSentence(list));
        }

        return sentences;
    }

    private static Sentence convertFromTextElementListToSentence(List<String> list){
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
        return new Sentence(textElements);
    }
}
