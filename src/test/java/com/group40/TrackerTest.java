package com.group40;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests the shared behavior of the abstract Tracker class. */
public class TrackerTest
{
    private static final double DELTA = 0.0001;
    private Locale originalLocale;
    private Tracker tracker;

    /** Uses a small concrete subclass because Tracker itself is abstract. */
    private static class SampleTracker extends Tracker
    {
        private final double result;

        SampleTracker(String title, String description, double rent,
            double amenities, double groceries, double result)
        {
            super(title, description, rent, amenities, groceries);
            this.result = result;
        }

        @Override
        public double getResult()
        {
            return result;
        }
    }

    /** Creates fresh data and makes decimal formatting predictable. */
    @BeforeEach
    public void setUp()
    {
        originalLocale = Locale.getDefault(Locale.Category.FORMAT);
        Locale.setDefault(Locale.Category.FORMAT, Locale.US);
        tracker = new SampleTracker("Sample", "Sample description",
            500.0, 100.0, 300.0, 600.0);
    }

    /** Restores the machine's formatting setting after each test. */
    @AfterEach
    public void tearDown()
    {
        Locale.setDefault(Locale.Category.FORMAT, originalLocale);
    }

    @Test
    public void testGetTitle()
    {
        assertEquals("Sample", tracker.getTitle());
    }

    @Test
    public void testGetDescription()
    {
        assertEquals("Sample description", tracker.getDescription());
    }

    @Test
    public void testGetExpenses()
    {
        assertEquals(900.0, tracker.getExpenses(), DELTA);
    }

    @Test
    public void testGetExpensesAllZero()
    {
        Tracker zero = new SampleTracker("Zero", "No expenses",
            0.0, 0.0, 0.0, 0.0);
        assertEquals(0.0, zero.getExpenses(), DELTA);
    }

    @Test
    public void testGetExpensesWithCents()
    {
        Tracker cents = new SampleTracker("Cents", "Decimal expenses",
            500.25, 100.10, 300.35, 0.0);
        assertEquals(900.70, cents.getExpenses(), DELTA);
    }

    @Test
    public void testGetExpensesWithOneZeroCategory()
    {
        Tracker mixed = new SampleTracker("Mixed", "No amenities",
            500.0, 0.0, 300.0, 0.0);
        assertEquals(800.0, mixed.getExpenses(), DELTA);
    }

    @Test
    public void testGetSummaryPositive()
    {
        assertEquals("Sample: $600.00", tracker.getSummary());
    }

    @Test
    public void testGetSummaryNegative()
    {
        Tracker negative = new SampleTracker("Deficit", "Negative result",
            0.0, 0.0, 0.0, -400.0);
        assertEquals("Deficit: $-400.00", negative.getSummary());
    }

    @Test
    public void testGetSummaryZero()
    {
        Tracker zero = new SampleTracker("Zero", "Zero result",
            0.0, 0.0, 0.0, 0.0);
        assertEquals("Zero: $0.00", zero.getSummary());
    }

    @Test
    public void testGetSummaryRoundsToTwoDecimalPlaces()
    {
        Tracker rounded = new SampleTracker("Rounded", "Rounding",
            0.0, 0.0, 0.0, 12.346);
        assertEquals("Rounded: $12.35", rounded.getSummary());
    }
}
