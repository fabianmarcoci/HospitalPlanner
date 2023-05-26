package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private int cornerRadius = 10;
    private Color backgroundColor;
    public RoundedButton(String text, int radius, Color bgColor) {
        super(text);
        cornerRadius = radius;
        backgroundColor = bgColor;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        graphics.setColor(backgroundColor);
        graphics.fill(roundedRectangle);

        graphics.setColor(getForeground());
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textX = (width - fontMetrics.stringWidth(getText())) / 2;
        int textY = (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        graphics.drawString(getText(), textX, textY);

        graphics.dispose();
    }
}
