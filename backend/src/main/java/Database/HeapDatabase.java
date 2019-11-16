package Database;

import model.text.Text;

final class HeapDatabase implements Database {
    private static Text text = null;

    @Override
    public void setData(Text text) {
        HeapDatabase.text = text;
    }

    @Override
    public Text getData(){
        return HeapDatabase.text;
    }
}
