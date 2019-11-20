package Database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DatabaseSwitcher {
    private static final Logger LOG = LogManager.getLogger(DatabaseSwitcher.class);
    private static Database fileDatabase = new FileDatabase();

    public static Database getDatabase() {
        boolean condition = true;

        if (condition) {
            return fileDatabase;
        }
        LOG.error("Not Found correct Database");
        throw new IllegalArgumentException("Not Found correct Database");
    }
}
