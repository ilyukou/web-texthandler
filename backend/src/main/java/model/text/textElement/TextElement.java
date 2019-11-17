package model.text.textElement;

public class TextElement {
    private static final String[] PUNCTUATION_MARKS = {",",":"};
    private static final String[] PUNCTUATION_MARK_FOR_END_OF_LINE = {".","?","!",";"};

    private TextElementType textElementType;
    private String value;

    public TextElement(String value, TextElementType textElementType) {
        this.textElementType = textElementType;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TextElementType getTextElementType() {
        return textElementType;
    }

    public void setTextElementType(TextElementType textElementType) {
        this.textElementType = textElementType;
    }

    public static String[] getPunctuationMarks() {
        return PUNCTUATION_MARKS;
    }

    public static String[] getPunctuationMarkForEndOfLine() {
        return PUNCTUATION_MARK_FOR_END_OF_LINE;
    }

    public static TextElementType getTypeOfElement(String value){
        // how to rewrite this ?
        for (String marks : PUNCTUATION_MARKS){
            if(marks.equals(value)){
                return TextElementType.PunctuationMark;
            }
        }
        for (String marks : PUNCTUATION_MARK_FOR_END_OF_LINE){
            if(marks.equals(value)){
                return TextElementType.PunctuationMarkForEndOfSentence;
            }
        }
        return TextElementType.Word;
    }
}
