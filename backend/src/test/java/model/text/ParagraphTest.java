package model.text;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest {
    private Sentence firstSentence = Mockito.mock(Sentence.class);
    private Sentence secondSentence = Mockito.mock(Sentence.class);
    private Sentence thirdSentence = Mockito.mock(Sentence.class);

    @BeforeEach
    void setUp() {

    }

    @Test
    void getParagraphAsString() {
        when(firstSentence.getSentenceAsString()).thenReturn("Hi, what are you name?");
        when(secondSentence.getSentenceAsString()).thenReturn("Is real!");
        when(thirdSentence.getSentenceAsString()).thenReturn("Bla bla blllalalal.");

        String expected = "\tHi, what are you name? Is real! Bla bla blllalalal.";

        Paragraph paragraph = new Paragraph(Arrays.asList(firstSentence,secondSentence,thirdSentence));
        assertEquals(expected, paragraph.getParagraphAsString());
    }
}