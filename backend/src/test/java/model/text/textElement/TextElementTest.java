package model.text.textElement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Assertions.assertEquals(TextElementType.PunctuationMarkForEndOfLine, TextElement.getTypeOfElement("."));
    }
}