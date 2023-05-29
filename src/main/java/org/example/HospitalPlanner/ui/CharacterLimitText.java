package org.example.HospitalPlanner.ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CharacterLimitText extends DocumentFilter {
    private int limit;

    public CharacterLimitText(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
        if ((fb.getDocument().getLength() + str.length()) <= limit) {
            super.insertString(fb, offset, str, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet attr) throws BadLocationException {
        if ((fb.getDocument().getLength() + str.length() - length) <= limit) {
            super.replace(fb, offset, length, str, attr);
        }
    }
}