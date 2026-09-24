package com.group40;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests costs calculations using the constructor order in the actual code. */
public class CostsTrackerTest
{
    private static final double DELTA = 0.0001;
    private Locale originalLocale;
    private CostsTracker tracker;

    @BeforeEach
    public void setUp()
    {
        originalLocale = Locale.getDefault(Locale.Category.FORMAT);
        Locale.setDefault(Locale.Category.FORMAT, Locale.US);
        // Actual order: rent, amenities, groceries, hourlyWage.
        tracker = new CostsTracker(500.0, 100.0, 300.0, 20.0);
    }

    @AfterEach
    public void tearDown()
    {
        Locale.setDefault(Locale.Category.FORMAT, originalLocale);
    }

    @Test
    public void testConstructorSetsTitleAndDescription()
    {
        assertEquals("Costs Tracker", tracker.getTitle());
        assertEquals("How many hours do you need to work to cover your expenses?",
            tracker.getDescription());
    }

    @Test
    public void testGetExpenses()
    {
        assertEquals(900.0, tracker.getExpenses(), DELTA);
    }

    @Test
    public void testGetHoursNeeded()
    {
        assertEquals(45.0, tracker.getHoursNeeded(), DELTA);
    }

    @Test
    public void testGetHoursNeededWithCents()
    {
        CostsTracker cents = new CostsTracker(500.25, 100.10, 300.35, 20.0);
        assertEquals(45.035, cents.getHoursNeeded(), DELTA);
    }

    @Test
    public void testGetHoursNeededWithNoExpenses()
    {
        CostsTracker noExpenses = new CostsTracker(0.0, 0.0, 0.0, 20.0);
        assertEquals(0.0, noExpenses.getHoursNeeded(), DELTA);
    }

    @Test
    public void testGetHoursNeededThrowsOnZeroWage()
    {
        CostsTracker zeroWage = new CostsTracker(500.0, 100.0, 300.0, 0.0);
        assertThrows(IllegalArgumentException.class, zeroWage::getHoursNeeded);
    }

    @Test
    public void testGetHoursNeededThrowsOnNegativeWage()
    {
        CostsTracker negativeWage = new CostsTracker(500.0, 100.0, 300.0, -5.0);
        assertThrows(IllegalArgumentException.class, negativeWage::getHoursNeeded);
    }

    @Test
    public void testGetResultThroughParentReference()
    {
        Tracker parentReference = tracker;
        assertEquals(45.0, parentReference.getResult(), DELTA);
    }

    @Test
    public void testGetSummary()
    {
        assertEquals("Costs Tracker: You need to work 45.0 hours to cover your expenses.", tracker.getSummary());
    }
}