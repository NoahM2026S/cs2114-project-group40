package com.group40;

import javax.swing.*;
import java.awt.*;

/**
 * This is the picker screen class. It'll display the two
 * buttons for the user to choose which tracker they want to use.
 * It will also display the icon, title, and a message for the user.
 * 
 * @author Amelia Vasquez Rosario
 * @version September 23, 2026
 */

public class PickerScreen extends JPanel 
{

    public PickerScreen(CardLayout cardLayout, JPanel container)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(24, 40, 32, 40));
        setBackground(new Color(0xFBF1EC));

        // icon, in a soft circle badge
        JPanel iconBadge = createIconBadge();
        iconBadge.setAlignmentX(Component.CENTER_ALIGNMENT);

        // title
        JLabel titleLabel = new JLabel("Shepherd");
        titleLabel.setFont(new Font("Quicksand", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0x4A2E3B));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // subtitle
        JLabel subtitleLabel = new JLabel("Pick a tracker to get started");
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 24));
        subtitleLabel.setFont(new Font("Quicksand", Font.BOLD, 13));
        subtitleLabel.setForeground(new Color(0xB08691));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // buttons
        JButton wageButton = createTrackerButton("Wage Tracker", new Color(0xF3C6BE), new Color(0x6B2E28), "/darkredpaw.png");
        wageButton.addActionListener(e -> cardLayout.show(container, "wageForm"));

        JButton costsButton = createTrackerButton("Costs Tracker", new Color(0xC3D9F0), new Color(0x24406B), "/darkbluepaw.png");
        costsButton.addActionListener(e -> cardLayout.show(container, "costsForm"));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 22, 0));
        buttonRow.setOpaque(false);
        buttonRow.add(wageButton);
        buttonRow.add(costsButton);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(iconBadge);
        add(Box.createVerticalStrut(10));
        add(titleLabel);
        add(Box.createVerticalStrut(4));
        add(subtitleLabel);
        add(Box.createVerticalStrut(34));
        add(buttonRow);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); // fills the background color first

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Image topPaw = new ImageIcon(getClass().getResource("/lightredpaw.png")).getImage();
        g2.drawImage(topPaw, 22, 18, 34, 34, this);

        Image bottomPaw = new ImageIcon(getClass().getResource("/lightbluepaw.png")).getImage();
        g2.drawImage(bottomPaw, getWidth() - 54, getHeight() - 54, 26, 26, this);

        g2.dispose();
    }

    private JPanel createIconBadge()
    {
        JPanel badge = new JPanel() {
            @Override
            protected void paintComponent(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(201, 122, 99, 60)); // faded shadow
                g2.fillOval(4, 8, 92, 92);

                g2.setColor(new Color(0xF6DED0)); // circle backdrop
                g2.fillOval(0, 0, 92, 92);

                g2.dispose();
                super.paintComponent(g);
            }
        };
        badge.setPreferredSize(new Dimension(96, 100));
        badge.setMaximumSize(new Dimension(96, 100));
        badge.setOpaque(false);
        badge.setLayout(new GridBagLayout());

        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/icon.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        badge.add(new JLabel(new ImageIcon(scaledImage)));

        return badge;
    }

    private JButton createTrackerButton(String text, Color background, Color textColor, String pawFile)
    {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(210, 74));
        button.setBackground(background);
        button.setForeground(textColor);
        button.setFont(new Font("Quicksand", Font.BOLD, 15));
        button.putClientProperty("FlatLaf.style", "arc: 999");
        button.setFocusPainted(false);

        ImageIcon rawPaw = new ImageIcon(getClass().getResource(pawFile));
        Image scaledPaw = rawPaw.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledPaw));
        button.setIconTextGap(8);

        Color hoverColor = background.darker();

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            Timer timer;

            public void mouseEntered(java.awt.event.MouseEvent e) {
                animateTo(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                animateTo(background);
            }

            private void animateTo(Color target) {
                if (timer != null && timer.isRunning()) timer.stop();
                Color start = button.getBackground();
                int steps = 10;
                int[] count = {0};

                timer = new Timer(15, e -> {
                    count[0]++;
                    float ratio = count[0] / (float) steps;
                    int r = (int) (start.getRed() + (target.getRed() - start.getRed()) * ratio);
                    int g = (int) (start.getGreen() + (target.getGreen() - start.getGreen()) * ratio);
                    int b = (int) (start.getBlue() + (target.getBlue() - start.getBlue()) * ratio);
                    button.setBackground(new Color(r, g, b));
                    if (count[0] >= steps) timer.stop();
                });
                timer.start();
            }
        });

        return button;
    }

}