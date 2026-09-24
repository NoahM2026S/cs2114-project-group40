package com.group40;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests the shared session history list. */
public class SessionHistoryTest
{
    /** Clears the list before every test, since entries is shared/static. */
    @BeforeEach
    public void setUp()
    {
        SessionHistory.clearAll();
    }

    @Test
    public void testStartsEmpty()
    {
        assertTrue(SessionHistory.getEntries().isEmpty());
    }

    @Test
    public void testAddEntry()
    {
        WageTracker tracker = new WageTracker(500.0, 100.0, 300.0, 1500.0);
        SessionHistory.addEntry(tracker);
        assertEquals(1, SessionHistory.getEntries().size());
    }

    @Test
    public void testAddEntryPreservesOrder()
    {
        WageTracker first = new WageTracker(500.0, 100.0, 300.0, 1500.0);
        CostsTracker second = new CostsTracker(500.0, 100.0, 300.0, 20.0);

        SessionHistory.addEntry(first);
        SessionHistory.addEntry(second);

        assertEquals(first, SessionHistory.getEntries().get(0));
        assertEquals(second, SessionHistory.getEntries().get(1));
    }

    @Test
    public void testClearAll()
    {
        SessionHistory.addEntry(new WageTracker(500.0, 100.0, 300.0, 1500.0));
        SessionHistory.addEntry(new CostsTracker(500.0, 100.0, 300.0, 20.0));

        SessionHistory.clearAll();

        assertTrue(SessionHistory.getEntries().isEmpty());
    }

    @Test
    public void testDeleteEntry()
    {
        WageTracker first = new WageTracker(500.0, 100.0, 300.0, 1500.0);
        CostsTracker second = new CostsTracker(500.0, 100.0, 300.0, 20.0);

        SessionHistory.addEntry(first);
        SessionHistory.addEntry(second);
        SessionHistory.deleteEntry(0);

        assertEquals(1, SessionHistory.getEntries().size());
        assertEquals(second, SessionHistory.getEntries().get(0));
    }

    @Test
    public void testDeleteEntryThrowsOnInvalidIndex()
    {
        SessionHistory.addEntry(new WageTracker(500.0, 100.0, 300.0, 1500.0));
        assertThrows(IndexOutOfBoundsException.class, () -> SessionHistory.deleteEntry(5));
    }

    @Test
    public void testRecalculate()
    {
        WageTracker original = new WageTracker(500.0, 100.0, 300.0, 1500.0);
        WageTracker updated = new WageTracker(500.0, 100.0, 300.0, 2000.0);

        SessionHistory.addEntry(original);
        SessionHistory.recalculate(0, updated);

        assertEquals(updated, SessionHistory.getEntries().get(0));
    }

    @Test
    public void testRecalculateThrowsOnInvalidIndex()
    {
        SessionHistory.addEntry(new WageTracker(500.0, 100.0, 300.0, 1500.0));
        WageTracker updated = new WageTracker(500.0, 100.0, 300.0, 2000.0);
        assertThrows(IndexOutOfBoundsException.class, () -> SessionHistory.recalculate(5, updated));
    }
}