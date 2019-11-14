package Database;

public final class DatabaseSwitcher {
    private static Database heapDatabase = new HeapDatabase();

    public static Database getDatabase(){
        return heapDatabase;
    }
}
