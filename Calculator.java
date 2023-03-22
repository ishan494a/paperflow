import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator implements ActionListener {
    private static JLabel label;
    private static JLabel weightLabel;
    private static JLabel success;
    private static JFrame frame;
    private static JPanel panel;
    private static JButton button;
    private static JButton button1;
    private static JButton reset;
    private static JTextField gradeText;
    private static JTextField weightText;
    private static int count=0;
    private static DefaultTableModel tableModel;
    private static JTable table;

    public static void main(String[] args) {
        panel = new JPanel();
        frame = new JFrame("Grade Calculator");
        frame.setSize(350, 550);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        label = new JLabel("Grade:");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);
        gradeText = new JTextField(20);
        gradeText.setBounds(80, 20, 165, 25);
        panel.add(gradeText);
        weightLabel = new JLabel("Weight: ");
        weightLabel.setBounds(10, 50, 80, 25);
        panel.add(weightLabel);
        weightText = new JTextField(20);
        weightText.setBounds(80, 50, 165, 25);
        panel.add(weightText);
        button = new JButton("Add more");
        button.setBounds(80, 80, 160, 25);
        button.addActionListener(new Calculator());
        panel.add(button);
        button1 = new JButton("Calculate");
        button1.setBounds(80, 110, 160, 25);
        button1.addActionListener(new Average());
        panel.add(button1);
        reset = new JButton("Reset");
        reset.setBounds(80,140,160,25);
        panel.add(reset);
        reset.addActionListener(new resetForm());
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Grade");
        tableModel.addColumn("Weight");
        table = new JTable(tableModel);
        table.setBounds(10, 180, 330, 300);
        panel.add(table);
        Object[] rowData = {"Grade", "Weight"};
        tableModel.addRow(rowData);
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table.setRowHeight(20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        success = new JLabel("");
        success.setHorizontalAlignment(JLabel.CENTER);
        success.setBounds(10, 490, 300, 25);
        panel.add(success);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Average a = new Average();
        Double grade = Double.parseDouble(gradeText.getText());
        Double weight = Double.parseDouble(weightText.getText());
        gradeText.setText("");
        weightText.setText("");
        Object[] rowData = {grade, weight};
        tableModel.addRow(rowData);
        a.setAverage(grade, weight);
        count++;

    }

    public static class Average implements ActionListener{

        public static double average;
        public static double weight;
        public String letterGrade;

        public static void setAverage(double gd, double wt){
            average+=gd*wt;
            weight+=wt;
        }
        public double getAverage(){
            return average/weight;
        }
        public void setGrade(double wa){
            if(wa>=90){
                letterGrade = "A+";
            }
            else if(wa>=85){
                letterGrade = "A";
            }
            else if(wa>=80){
                letterGrade = "A-";
            }
            else if(wa>=77){
                letterGrade = "B+";
            }
            else if(wa>=73){
                letterGrade = "B";
            }
            else if(wa>=70){
                letterGrade = "B-";
            }
            else if(wa>=67){
                letterGrade = "C+";
            }
            else if(wa>=63){
                letterGrade = "C";
            }
            else if(wa>=60){
                letterGrade = "C-";
            }
            else if(wa>=50){
                letterGrade = "D";
            }
            else{
                letterGrade = "F";
            }
        }
        public String getLetterGrade(){
            return letterGrade;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Average a = new Average();
            Double grade = Double.parseDouble(gradeText.getText());
            Double weight = Double.parseDouble(weightText.getText());
            a.setAverage(grade, weight);
            setGrade(getAverage());
            Object[] rowData = {grade, weight};
            tableModel.addRow(rowData);
            success.setText("Average: " + String.valueOf(getAverage()) + " Letter Grade: " + getLetterGrade());
            count++;
        }
    }
    public static class resetForm implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Average.weight = 0;
            Average.average = 0;
            gradeText.setText("");
            weightText.setText("");
            success.setText("");
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(1);
        }
    }
}


