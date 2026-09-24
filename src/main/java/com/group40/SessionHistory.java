package com.group40;

import java.util.ArrayList;
import java.util.List;

/**
 * The session history class.
 * This class owns the running list of completed tracker runs
 * for this session. The history is not saved to disk,
 * so it resets when the app closes.
 * 
 * @author Hasini Pottipati
 * @version September 23, 2026
 */
public class SessionHistory
{

    // fields

    private static List<Tracker> entries = new ArrayList<>();

    // methods

    /**
     * Appends a completed tracker run to the end of the list.
     * 
     * @param tracker The tracker submitted by the user.
     */
    public static void addEntry(Tracker tracker)
    {
        entries.add(tracker);
    }

    /**
     * Provides the trackers in the order they were added.
     * 
     * @return The list of trackers.
     */
    public static List<Tracker> getEntries()
    {
        return entries;
    }

    /**
     * Clears all entries from the session history.
     * Added by Ame! :)
     */
    public static void clearAll()
    {
        entries.clear();
    }

    /**
     * Removes the entry at the given index.
     * 
     * @param index The index of the entry to remove.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public static void deleteEntry(int index)
    {
        entries.remove(index);
    }

    /**
     * Replaces the entry at the given index with an updated tracker.
     * 
     * @param index   The index of the entry to replace.
     * @param updated The tracker with the new inputs.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public static void recalculate(int index, Tracker updated)
    {
        entries.set(index, updated);
    }

}