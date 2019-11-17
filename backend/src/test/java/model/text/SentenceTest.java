package model.text;

import model.text.textElement.TextElement;
import model.text.textElement.TextElementType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SentenceTest {

    private List<TextElement> textElements = new ArrayList<>();

    @BeforeEach
    void setUp() {
        textElements.add(new TextElement("Hi", TextElementType.Word));
        textElements.add(new TextElement(",", TextElementType.PunctuationMark));
        textElements.add(new TextElement("what", TextElementType.Word));
        textElements.add(new TextElement("are", TextElementType.Word));
        textElements.add(new TextElement("you", TextElementType.Word));
        textElements.add(new TextElement("name", TextElementType.Word));
        textElements.add(new TextElement("?", TextElementType.PunctuationMarkForEndOfSentence));
    }

    @Test
    void getSentenceAsString() {
        assertEquals("Hi, what are you name?", new Sentence(textElements).getSentenceAsString());
    }
}