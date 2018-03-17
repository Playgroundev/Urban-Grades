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
import java.text.DecimalFormat;
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
    static DecimalFormat decimal = new DecimalFormat(".##");
    static PrinterHandler printer = new PrinterHandler();
    
   
    
    static class GradesHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
               
                String admission = gradesview.getAdmission().getText();
                double initialScore =(double) gradesview.getInitialScore().getValue();
                double score = Double.parseDouble(gradesview.getScore1().getText());
                double _outOf =(double) gradesview.getOutOf().getValue();
                int initial_weight = (int) gradesview.getInitialWeight().getValue();
                double weight = (double) gradesview.getWeighted().getValue();
                
                if(admission.isEmpty()){
                    JOptionPane.showMessageDialog(gradesview,"Enter Admission Number To Continue");
                }
                if(initialScore>=100.0 || initialScore == 0){
                    JOptionPane.showMessageDialog(gradesview,"Initial Grade Cannot Be Greater Than or Equal to 100 or Equal To 0");
                    gradesview.getInitialScore().setValue(0);
                    return;
                }else{
                    double _final = ((initialScore/100)*initial_weight) + generateGrade(score,_outOf,weight);
                    gradesview.getFinalCat().setText(String.valueOf(decimal.format((initialScore/100)*initial_weight)));
                    gradesview.getFinalExam().setText(String.valueOf(generateGrade(score,_outOf,weight)));
                    gradesview.getFinalMark().setText(String.valueOf(decimal.format(_final)));
                    gradesview.getFinalGrade().setText(String.valueOf(generateGradeSymbol(_final)));
                    System.out.println("Your Final Grade is "+ _final + "Grade is " + generateGradeSymbol(_final));
                }
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }      
    }
    static class AddItemsHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{               
                    activateTab();                
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
  
    
    public static void main(String [] args){
        gradesview.setLocationRelativeTo(null);
        gradesview.setResizable(false);
        gradesview.setTitle("Special Grades");
        gradesview.getGrade().addActionListener(grades);
        gradesview.addItems().addActionListener(add);
        gradesview.DisableTab().addActionListener(remove);
        gradesview.getPrinter().addActionListener(printer);
        gradesview.setVisible(true);
        
    }
}
