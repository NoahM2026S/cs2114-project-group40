package com.group40;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests wage calculations using the constructor order in the actual code. */
public class WageTrackerTest
{
    private static final double DELTA = 0.0001;
    private Locale originalLocale;
    private WageTracker tracker;

    @BeforeEach
    public void setUp()
    {
        originalLocale = Locale.getDefault(Locale.Category.FORMAT);
        Locale.setDefault(Locale.Category.FORMAT, Locale.US);
        // Actual order: rent, amenities, groceries, monthlyWage.
        tracker = new WageTracker(500.0, 100.0, 300.0, 1500.0);
    }

    @AfterEach
    public void tearDown()
    {
        Locale.setDefault(Locale.Category.FORMAT, originalLocale);
    }

    @Test
    public void testConstructorSetsTitleAndDescription()
    {
        assertEquals("Wage Tracker", tracker.getTitle());
        assertEquals("Is your wage enough to cover your expenses?",
            tracker.getDescription());
    }

    @Test
    public void testGetExpenses()
    {
        assertEquals(900.0, tracker.getExpenses(), DELTA);
    }

    @Test
    public void testGetRemainingPositive()
    {
        assertEquals(600.0, tracker.getRemaining(), DELTA);
    }

    @Test
    public void testGetRemainingBreakEven()
    {
        WageTracker even = new WageTracker(500.0, 100.0, 300.0, 900.0);
        assertEquals(0.0, even.getRemaining(), DELTA);
    }

    @Test
    public void testGetRemainingNegative()
    {
        WageTracker shortfall = new WageTracker(500.0, 100.0, 300.0, 500.0);
        assertEquals(-400.0, shortfall.getRemaining(), DELTA);
    }

    @Test
    public void testGetRemainingWithCents()
    {
        WageTracker cents = new WageTracker(500.25, 100.10, 300.35, 1500.95);
        assertEquals(600.25, cents.getRemaining(), DELTA);
    }

    @Test
    public void testDecimalBreakEvenWithinTolerance()
    {
        // Decimal inputs may produce a tiny floating-point difference.
        WageTracker even = new WageTracker(0.1, 0.0, 0.2, 0.3);
        assertEquals(0.0, even.getRemaining(), DELTA);
    }

    @Test
    public void testGetRemainingWithNoExpenses()
    {
        WageTracker noExpenses = new WageTracker(0.0, 0.0, 0.0, 1500.0);
        assertEquals(1500.0, noExpenses.getRemaining(), DELTA);
    }

    @Test
    public void testGetRemainingWithZeroWage()
    {
        WageTracker noWage = new WageTracker(500.0, 100.0, 300.0, 0.0);
        assertEquals(-900.0, noWage.getRemaining(), DELTA);
    }

    @Test
    public void testGetRemainingWithAllZeros()
    {
        WageTracker zero = new WageTracker(0.0, 0.0, 0.0, 0.0);
        assertEquals(0.0, zero.getRemaining(), DELTA);
    }

    @Test
    public void testGetResultPositiveThroughParentReference()
    {
        Tracker parentReference = tracker;
        assertEquals(600.0, parentReference.getResult(), DELTA);
    }

    @Test
    public void testGetResultNegative()
    {
        WageTracker shortfall = new WageTracker(500.0, 100.0, 300.0, 500.0);
        assertEquals(-400.0, shortfall.getResult(), DELTA);
    }

    @Test
    public void testGetResultBreakEven()
    {
        WageTracker even = new WageTracker(500.0, 100.0, 300.0, 900.0);
        assertEquals(0.0, even.getResult(), DELTA);
    }

    @Test
    public void testInheritedSummaryUsesWageResult()
    {
        assertEquals("Wage Tracker: $600.00", tracker.getSummary());
    }
}
