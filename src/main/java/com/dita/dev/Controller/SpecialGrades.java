/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dita.dev.Controller;

import com.dita.dev.View.Grades;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author brian-kamau
 */
public class SpecialGrades {
    static{
        try{
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex){
            Logger.getLogger(SpecialGrades.class.getName()).log(Level.SEVERE,null,ex);
        }
    
}
    static Grades gradesview = new Grades();
    static GradesHandler grades = new GradesHandler();
    static AddItemsHandler add = new AddItemsHandler();
    static RemoveBarHandler remove = new RemoveBarHandler();
    static RemoveTabHandler remove2 = new RemoveTabHandler();
    static DecimalFormat decimal = new DecimalFormat(".##");
    static PrinterHandler printer = new PrinterHandler();
    private static int counter = 0;
    private static final SimpleDateFormat simple = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private File logFile;
    private static  BufferedWriter logFileWriter;
    
    public SpecialGrades() throws IOException{
        logFile = new File(".log");
        try{
            logFileWriter = new BufferedWriter(new FileWriter(logFile));
            logFileWriter.append("Log Files For Exceptions"+" "+"Created At: "+getTimeStamp());
            logFileWriter.flush();
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
   
    
    static class GradesHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                String course_code = gradesview.getCourseCode().getText();
                String admission = gradesview.getAdmission().getText();
               
                if(admission.isEmpty()){
                    JOptionPane.showMessageDialog(gradesview,"Enter Admission Number To Continue");
                    return;
                }
                if(course_code.isEmpty()){
                    JOptionPane.showMessageDialog(gradesview,"Enter Course Code");
                    return;
                }
                double initialScore =Double.parseDouble(gradesview.getInitialScore().getText());
                double score = Double.parseDouble(gradesview.getScore1().getText());
                double _outOf =Double.parseDouble(gradesview.getOutOf().getText());
                int initial_weight = Integer.parseInt(gradesview.getWeighted().getText());
                double weight = Double.parseDouble(gradesview.getWeighted().getText());
                
                if(initialScore>=100.0){
                    JOptionPane.showMessageDialog(gradesview,"Initial Grade Cannot Be Greater Than 100");
                    gradesview.getInitialScore().setText("");
                    return;
                }if(counter == 0){
                     double _final = initialScore + generateGrade(score,_outOf,weight);
                     gradesview.getFinalCat().setText(gradesview.getInitialScore().getText());
                     gradesview.showItem1().setText(String.valueOf(decimal.format(generateGrade(score,_outOf,weight))));
                     gradesview.getFinalMark().setText(String.valueOf(decimal.format(_final)));
                     gradesview.getFinalGrade().setText(String.valueOf(generateGradeSymbol(_final)));
                     gradesview.getItem1Label().setText(gradesview.getItemName().getText());
                     gradesview.displayAdmission().setText(gradesview.getAdmission().getText());
                     gradesview.displayCourseCode().setText(gradesview.getCourseCode().getText());
                     
                     System.out.println("Your Final Grade is "+ _final + "Grade is " + generateGradeSymbol(_final));
                     return;
                }
                    double _score2 = Double.parseDouble(gradesview.getScore2().getText());
                    double _outof2 = Double.parseDouble(gradesview.getOutOf2().getText());
                    double _weight2 = Double.parseDouble(gradesview.getWeighted2().getText());
                    
                if(counter == 1){
                   double  _final2 = (initialScore) + generateGrade(score,_outOf,weight) + generateGrade(_score2,_outof2,_weight2);
                   gradesview.getItem1Label().setText(gradesview.getItemName().getText());
                   gradesview.getItem2Label().setText(gradesview.getItem2().getText());
                   gradesview.getFinalCat().setText(gradesview.getInitialScore().getText());
                   gradesview.showItem1().setText(decimal.format(generateGrade(score,_outOf,weight)));
                   gradesview.showItem2().setText(decimal.format(generateGrade(_score2,_outof2,_weight2)));
                   gradesview.getFinalMark().setText(String.valueOf(decimal.format(_final2)));
                   gradesview.getFinalGrade().setText(String.valueOf(generateGradeSymbol(_final2)));
                   gradesview.displayAdmission().setText(gradesview.getAdmission().getText());
                   gradesview.displayCourseCode().setText(gradesview.getCourseCode().getText());
                   System.out.println("Score is "+ decimal.format(_final2));
                    return;
                }
                    double _score3 = Double.parseDouble(gradesview.getScore3().getText());
                    double _outOf3 = Double.parseDouble(gradesview.getOutOf3().getText());
                    double _weight3 = Double.parseDouble(gradesview.getWeighted3().getText());
                if(counter == 2){
                      double _final3 = (initialScore) + generateGrade(score,_outOf,weight) + generateGrade(_score2,_outof2,_weight2)+
                            generateGrade(_score3,_outOf3,_weight3);
                   gradesview.getFinalCat().setText(gradesview.getInitialScore().getText());
                   gradesview.getItem1Label().setText(gradesview.getItemName().getText());
                   gradesview.getItem2Label().setText(gradesview.getItem2().getText());
                   gradesview.getItem3Label().setText(gradesview.getItem3().getText());
                   gradesview.getFinalExam().setText(decimal.format(generateGrade(_score3,_outOf3,_weight3)));
                   gradesview.getFinalCat().setText(gradesview.getInitialScore().getText());
                   gradesview.showItem1().setText(decimal.format(generateGrade(score,_outOf,weight)));
                   gradesview.showItem2().setText(decimal.format(generateGrade(_score2,_outof2,_weight2)));
                   gradesview.getFinalMark().setText(String.valueOf(decimal.format(_final3)));
                   gradesview.getFinalGrade().setText(String.valueOf(generateGradeSymbol(_final3)));
                   gradesview.displayAdmission().setText(gradesview.getAdmission().getText());
                   gradesview.displayCourseCode().setText(gradesview.getCourseCode().getText());
                   

                }
                
                
            }catch(Exception ex){
                ex.printStackTrace();
                try {
                    logFileWriter.append("At: "+getTimeStamp()+ " "+ex.getMessage());
                } catch (IOException ex1) {
                    Logger.getLogger(SpecialGrades.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }      
    }
    static class AddItemsHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{               
                    if(counter == 0){
                        activateTab();
                        counter++;
                    }else if(counter == 1){
                        activateTab2();
                        counter++;
                    }else{
                        JOptionPane.showMessageDialog(gradesview,"Maximum Number of Items Reached");
                    }
                    
            }catch(Exception ex){
                ex.printStackTrace();
            }            
        }       
    }
    static class RemoveBarHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                gradesview.getItem2().setEnabled(false);
                gradesview.getScore2().setEnabled(false);
                gradesview.getOutOf2().setEnabled(false);
                gradesview.getWeighted2().setEnabled(false);
                gradesview.DisableTab().setEnabled(false);
                counter--;
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
    }
    static class PrinterHandler implements Printable,ActionListener{

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if(pageIndex>0){
                return NO_SUCH_PAGE;
            }
            Graphics2D graphics2d = (Graphics2D) graphics;
            graphics2d.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
            graphics.drawString("This is a Sample Page", 100, 100);
            return PAGE_EXISTS;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            boolean prints = job.printDialog();
            if(prints){
                try{
                    job.print();
                }catch(PrinterException ex){
                    ex.printStackTrace();
                }
            }
            
        }
        
    }
    static class RemoveTabHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                gradesview.getItem3().setEnabled(false);
                gradesview.getScore3().setEnabled(false);
                gradesview.getOutOf3().setEnabled(false);
                gradesview.getWeighted3().setEnabled(false);
                gradesview.removeTab3().setEnabled(false);
                counter--;
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
    }
    
    
    public static double generateGrade(double score, double limit,double weight){
        double computed=0.0;
        try{
            
            computed = (score/limit) * (weight);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return computed;
    }
    public static  String generateGradeSymbol(double totals){
        String _grade = "";
                
        try{
            if(totals>=91){
                _grade = "A";
            }else if(totals>=81){
                _grade = "A-";
            }else if(totals>=76){
                _grade = "B+";
            }else if(totals>=71){
                _grade = "B";
            }else if(totals>=66){
                _grade = "B-";
            }else if(totals>=61){
                _grade = "C+";
            }else if(totals>=56){
                _grade = "C";
            }else if(totals>=51){
                _grade = "C-";
            }else if(totals>=46){
                _grade = "D+";
            }else if(totals>=41){
                _grade = "D";
            }else if(totals<=40){
                _grade = "F";
            }
                      
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return _grade;
    }
    public static void activateTab(){          
        gradesview.getItem2().setEnabled(true);
        gradesview.getScore2().setEnabled(true);
        gradesview.getOutOf2().setEnabled(true);
        gradesview.getWeighted2().setEnabled(true);
        gradesview.DisableTab().setEnabled(true);   
    }
    public static void activateTab2(){
        gradesview.getItem3().setEnabled(true);
        gradesview.getScore3().setEnabled(true);
        gradesview.getOutOf3().setEnabled(true);
        gradesview.getWeighted3().setEnabled(true);
        gradesview.removeTab3().setEnabled(true);
    }
  
    
    public static void main(String [] args) throws IOException{
        SpecialGrades grade = new SpecialGrades();
        gradesview.setLocationRelativeTo(null);
        gradesview.setResizable(false);
        gradesview.setTitle("Special Grades");
        gradesview.getGrade().addActionListener(grades);
        gradesview.addItems().addActionListener(add);
        gradesview.DisableTab().addActionListener(remove);
        gradesview.removeTab3().addActionListener(remove2);
        gradesview.getPrinter().addActionListener(printer);
        gradesview.setVisible(true);
        
    }
    public static String getTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }
}
