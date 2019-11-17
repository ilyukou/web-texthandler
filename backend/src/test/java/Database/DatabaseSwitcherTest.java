package Database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseSwitcherTest {

    @Test
    void getDatabase() {
        assertFalse(DatabaseSwitcher.getDatabase() == null);
    }
}