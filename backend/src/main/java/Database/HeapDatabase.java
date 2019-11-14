package Database;

import model.Text;

final class HeapDatabase implements Database {
    private static Text text = new Text("default value");

    @Override
    public void setData(Text text) {
        HeapDatabase.text = text;
    }

    @Override
    public Text getData(){
        return HeapDatabase.text;
    }
}
