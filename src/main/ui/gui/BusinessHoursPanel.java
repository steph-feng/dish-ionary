package ui.gui;

import model.BusinessHours;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*
 * Displays the panel for input of restaurant hours
 */


public class BusinessHoursPanel {
    private JPanel businessHoursPanel;
    private JComboBox mondayOpen;
    private JComboBox mondayClose;
    private JComboBox tuesdayOpen;
    private JComboBox tuesdayClose;
    private JComboBox wednesdayOpen;
    private JComboBox wednesdayClose;
    private JComboBox thursdayOpen;
    private JComboBox thursdayClose;
    private JComboBox fridayOpen;
    private JComboBox fridayClose;
    private JComboBox saturdayOpen;
    private JComboBox saturdayClose;
    private JComboBox sundayOpen;
    private JComboBox sundayClose;

    // EFFECTS: initializes fields and panel
    public BusinessHoursPanel() {
        businessHoursPanel = new JPanel(new GridLayout(9, 3));
        businessHoursPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        setOpenHours();
        JLabel blank = new JLabel();
        JLabel monday = new JLabel("Monday:");
        JLabel tuesday = new JLabel("Tuesday");
        JLabel wednesday = new JLabel("Wednesday:");
        JLabel thursday = new JLabel("Thursday:");
        JLabel friday = new JLabel("Friday:");
        JLabel saturday = new JLabel("Saturday:");
        JLabel sunday = new JLabel("Sunday:");

        JLabel open = new JLabel("Opening Time");
        JLabel close = new JLabel("Closing Time");
        addElementsToPanel(blank, monday, tuesday, wednesday, thursday, friday, saturday, sunday, open, close);

    }

    // MODIFIES: this
    // EFFECTS: adds labels and buttons to the panel
    private void addElementsToPanel(JLabel blank, JLabel monday, JLabel tuesday, JLabel wednesday, JLabel thursday,
                                    JLabel friday, JLabel saturday, JLabel sunday, JLabel open, JLabel close) {
        addLabelsToPanel(blank, open, close);
        businessHoursPanel.add(monday);
        businessHoursPanel.add(mondayOpen);
        businessHoursPanel.add(mondayClose);
        businessHoursPanel.add(tuesday);
        businessHoursPanel.add(tuesdayOpen);
        businessHoursPanel.add(tuesdayClose);
        businessHoursPanel.add(wednesday);
        businessHoursPanel.add(wednesdayOpen);
        businessHoursPanel.add(wednesdayClose);
        businessHoursPanel.add(thursday);
        businessHoursPanel.add(thursdayOpen);
        businessHoursPanel.add(thursdayClose);
        businessHoursPanel.add(friday);
        businessHoursPanel.add(fridayOpen);
        businessHoursPanel.add(fridayClose);
        businessHoursPanel.add(saturday);
        businessHoursPanel.add(saturdayOpen);
        businessHoursPanel.add(saturdayClose);
        businessHoursPanel.add(sunday);
        businessHoursPanel.add(sundayOpen);
        businessHoursPanel.add(sundayClose);
    }

    // MODIFIES: this
    // EFFECTS: adds title labels to panel
    private void addLabelsToPanel(JLabel blank, JLabel open, JLabel close) {
        businessHoursPanel.add(blank);
        businessHoursPanel.add(open);
        businessHoursPanel.add(close);
    }


    // MODIFIES: this
    // EFFECTS: initializes JComboBoxes with possible times
    private void setOpenHours() {
        String[] timeOptions = {"00:00", "00:30", "01:00", "01:30", "02:30", "03:00", "03:30", "04:00", "04:30",
                "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00",
                "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00",
                "21:30", "22:00", "22:30", "23:00", "23:30"};

        mondayOpen = new JComboBox(timeOptions);
        mondayClose = new JComboBox(timeOptions);
        tuesdayOpen = new JComboBox(timeOptions);
        tuesdayClose = new JComboBox(timeOptions);
        wednesdayOpen = new JComboBox(timeOptions);
        wednesdayClose = new JComboBox(timeOptions);
        thursdayOpen = new JComboBox(timeOptions);
        thursdayClose = new JComboBox(timeOptions);
        fridayOpen = new JComboBox(timeOptions);
        fridayClose = new JComboBox(timeOptions);
        saturdayOpen = new JComboBox(timeOptions);
        saturdayClose = new JComboBox(timeOptions);
        sundayOpen = new JComboBox(timeOptions);
        sundayClose = new JComboBox(timeOptions);
    }

    // EFFECTS: returns businessHoursPanel
    public JPanel getBusinessHoursPanel() {
        return businessHoursPanel;
    }

    // EFFECTS: returns Monday's business hours
    public BusinessHours getMondayHours() {
        String open = (String) mondayOpen.getSelectedItem();
        String close = (String) mondayClose.getSelectedItem();
        BusinessHours hours = new BusinessHours(1, open, close);

        mondayOpen.setSelectedIndex(0);
        mondayClose.setSelectedIndex(0);
        return hours;
    }

    // EFFECTS: returns Tuesday's business hours
    public BusinessHours getTuesdayHours() {
        String open = (String) tuesdayOpen.getSelectedItem();
        String close = (String) tuesdayClose.getSelectedItem();
        BusinessHours hours = new BusinessHours(2, open, close);

        tuesdayOpen.setSelectedIndex(0);
        tuesdayClose.setSelectedIndex(0);
        return hours;
    }

    // EFFECTS: returns Wednesday's business hours
    public BusinessHours getWednesdayHours() {
        String open = (String) wednesdayOpen.getSelectedItem();
        String close = (String) wednesdayClose.getSelectedItem();
        BusinessHours hours = new BusinessHours(3, open, close);

        wednesdayOpen.setSelectedIndex(0);
        wednesdayClose.setSelectedIndex(0);
        return hours;
    }

    // EFFECTS: returns Thursday's business hours
    public BusinessHours getThursdayHours() {
        String open = (String) thursdayOpen.getSelectedItem();
        String close = (String) thursdayClose.getSelectedItem();
        BusinessHours hours = new BusinessHours(4, open, close);

        thursdayOpen.setSelectedIndex(0);
        thursdayClose.setSelectedIndex(0);
        return hours;
    }

    // EFFECTS: returns Friday's business hours
    public BusinessHours getFridayHours() {
        String open = (String) fridayOpen.getSelectedItem();
        String close = (String) fridayClose.getSelectedItem();
        BusinessHours hours = new BusinessHours(5, open, close);

        fridayOpen.setSelectedIndex(0);
        fridayClose.setSelectedIndex(0);
        return hours;
    }

    // EFFECTS: returns Saturday's business hours
    public BusinessHours getSaturdayHours() {
        String open = (String) saturdayOpen.getSelectedItem();
        String close = (String) saturdayClose.getSelectedItem();
        BusinessHours hours = new BusinessHours(6, open, close);

        saturdayOpen.setSelectedIndex(0);
        saturdayClose.setSelectedIndex(0);
        return hours;
    }

    // EFFECTS: returns Sunday's business hours
    public BusinessHours getSundayHours() {
        String open = (String) sundayOpen.getSelectedItem();
        String close = (String) sundayClose.getSelectedItem();
        BusinessHours hours = new BusinessHours(7, open, close);

        sundayOpen.setSelectedIndex(0);
        sundayClose.setSelectedIndex(0);
        return hours;
    }

    // EFFECTS: returns Monday's open time
    public JComboBox getMondayOpen() {
        return mondayOpen;
    }

    // EFFECTS: returns Monday's closing time
    public JComboBox getMondayClose() {
        return mondayClose;
    }

    // EFFECTS: returns Tuesday's open time
    public JComboBox getTuesdayOpen() {
        return tuesdayOpen;
    }

    // EFFECTS: returns Tuesday's closing time
    public JComboBox getTuesdayClose() {
        return tuesdayClose;
    }

    // EFFECTS: returns Wednesday's open time
    public JComboBox getWednesdayOpen() {
        return wednesdayOpen;
    }

    // EFFECTS: returns Wednesday's closing time
    public JComboBox getWednesdayClose() {
        return wednesdayClose;
    }

    // EFFECTS: returns Thursday's open time
    public JComboBox getThursdayOpen() {
        return thursdayOpen;
    }

    // EFFECTS: returns Thursday's closing time
    public JComboBox getThursdayClose() {
        return thursdayClose;
    }

    // EFFECTS: returns Friday's open time
    public JComboBox getFridayOpen() {
        return fridayOpen;
    }

    // EFFECTS: returns Friday's closing time
    public JComboBox getFridayClose() {
        return fridayClose;
    }

    // EFFECTS: returns Saturday's open time
    public JComboBox getSaturdayOpen() {
        return saturdayOpen;
    }

    // EFFECTS: returns Saturday's closing time
    public JComboBox getSaturdayClose() {
        return saturdayClose;
    }

    // EFFECTS: returns Sunday's open time
    public JComboBox getSundayOpen() {
        return sundayOpen;
    }

    // EFFECTS: returns Sunday's closing time
    public JComboBox getSundayClose() {
        return sundayClose;
    }
}
