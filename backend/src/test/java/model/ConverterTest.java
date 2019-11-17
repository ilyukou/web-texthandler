package model;

import model.text.Sentence;
import model.text.textElement.TextElement;
import model.text.textElement.TextElementType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    List<Sentence> expected = new ArrayList<>();

    @BeforeEach
    void setUp() {
        List<TextElement> t1 = Arrays.asList(
                new TextElement("It", TextElementType.Word),
                new TextElement("was", TextElementType.Word),
                new TextElement("a", TextElementType.Word),
                new TextElement("cold", TextElementType.Word),
                new TextElement(".", TextElementType.PunctuationMarkForEndOfLine));

        List<TextElement> t2 = Arrays.asList(
                new TextElement("Old", TextElementType.Word),
                new TextElement("Deacon", TextElementType.Word),
                new TextElement(",", TextElementType.PunctuationMark),
                new TextElement("Joshua", TextElementType.Word),
                new TextElement("!", TextElementType.PunctuationMarkForEndOfLine));

        Sentence s1 = new Sentence(t1);
        Sentence s2 = new Sentence(t2);
        expected.add(s1);
        expected.add(s2);
    }

    @Test
    void convertListOfStringTextElementToListSentence() {
        List<String> listSentenceString = new ArrayList<>();
        listSentenceString.add("It");
        listSentenceString.add("was");
        listSentenceString.add("a");
        listSentenceString.add("cold");
        listSentenceString.add(".");

        listSentenceString.add("Old");
        listSentenceString.add("Deacon");
        listSentenceString.add(",");
        listSentenceString.add("Joshua");
        listSentenceString.add("!");

        List<Sentence> actual = Converter.convertListOfStringTextElementToListSentence(listSentenceString);

        if(actual.size() != expected.size()){
            // not equals size's
            assertTrue(false);
        }

        for (int i=0; i<actual.size(); i++){
            for (int j=0; j<actual.get(i).getTextElements().size(); j++){

                String expectedString = expected.get(i).getTextElements().get(j).getValue();
                String actualString = actual.get(i).getTextElements().get(j).getValue();

                TextElementType expectedTextElementType = expected.get(i).getTextElements().get(j).getTextElementType();
                TextElementType actualTextElementType = actual.get(i).getTextElements().get(j).getTextElementType();

                // check
                if(expectedString.equals(actualString) && expectedTextElementType == actualTextElementType){
                    // alright
                }else {
                    // not equals String value or TextElementType
                    assertTrue(false);
                }
            }
        }
    }
}