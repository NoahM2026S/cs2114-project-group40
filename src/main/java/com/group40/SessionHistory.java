package com.group40;

import java.util.ArrayList;
import java.util.List;

/**
 * The session history class.
 * This class stores every tracker the user submits
 * while the app is open. The history is not saved
 * to disk, so it resets when the app closes.
 * 
 * @author Hasini Pottipati
 * @version September 23, 2026
 */
public class SessionHistory {

    // fields

    private static List<Tracker> entries = new ArrayList<>();

    // methods

    /**
     * Adds a tracker to the session history.
     * 
     * @param tracker The tracker submitted by the user.
     */
    public static void addEntry(Tracker tracker)
    {
        entries.add(tracker);
    }

    /**
     * Provides the trackers in the session history.
     * 
     * @return The list of trackers.
     */
    public static List<Tracker> getEntries()
    {
        return entries;
    }

}
