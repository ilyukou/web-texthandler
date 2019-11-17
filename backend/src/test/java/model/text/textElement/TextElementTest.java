package model.text.textElement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextElementTest {

    @Test
    void getTypeOfElement_whenElementsIsWord() {
        Assertions.assertEquals(TextElementType.Word,TextElement.getTypeOfElement("word"));
    }
    @Test
    void getTypeOfElement_whenElementsIsPunctuationMark() {
        Assertions.assertEquals(TextElementType.PunctuationMark, TextElement.getTypeOfElement(","));
    }
    @Test
    void getTypeOfElement_whenElementsIsPunctuationMarkForEndOfLine() {
        Assertions.assertEquals(TextElementType.PunctuationMarkForEndOfSentence, TextElement.getTypeOfElement("."));
    }
}