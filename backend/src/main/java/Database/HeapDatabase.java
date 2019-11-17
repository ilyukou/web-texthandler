package Database;

import model.text.Text;

final class HeapDatabase implements Database {
    private static Text text = null;

    @Override
    public void setText(Text text) {
        HeapDatabase.text = text;
    }

    @Override
    public Text getText(){
        return HeapDatabase.text;
    }
}
