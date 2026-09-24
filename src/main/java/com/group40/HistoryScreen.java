package com.group40;

import javax.swing.*;
import java.awt.*;

/**
 * The history screen class that will
 * visually display the session history list.
 * Each entry shows the summary of a submitted tracker.
 * 
 * @author Hasini Pottipati
 * @version September 23, 2026
 */

public class HistoryScreen extends JPanel {

    private DefaultListModel<String> listModel;

    public HistoryScreen()
    {
        setLayout(new BorderLayout());

        add(new JLabel("Session History"), BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        JList<String> historyList = new JList<>(listModel);
        add(new JScrollPane(historyList), BorderLayout.CENTER);
    }

    /**
     * Reloads the list with the current session history.
     */
    public void refresh()
    {
        listModel.clear();

        for (Tracker tracker: SessionHistory.getEntries()) {
            listModel.addElement(tracker.getSummary());
        }
    }

}
