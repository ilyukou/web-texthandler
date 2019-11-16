package Database;

public final class DatabaseSwitcher {
    private static Database heapDatabase = new HeapDatabase();
    private static Database fileDatabase = new FileDatabase();

    public static Database getDatabase(){
        boolean condition = true;

        if(condition){
            return fileDatabase;
        }
        return heapDatabase;
    }
}
