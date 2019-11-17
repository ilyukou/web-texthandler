package Database;

public final class DatabaseSwitcher {

    private static Database fileDatabase = new FileDatabase();

    public static Database getDatabase() {
        boolean condition = true;

        if (condition) {
            return fileDatabase;
        }
        throw new IllegalArgumentException("Not Found correct Database");
    }
}
