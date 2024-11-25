/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author FungPin
 */
import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;

import java.time.LocalDate;

import static util.FormatUtil.dateFormatter;

public class Billing implements Comparable<Billing> {
   private Long billId;
   private double amountDue;
   private double amountPaid;
   private LocalDate paidAt;
   private SortedLinkedListInterface<Course> courseList;
   private Long billCounter = 100000L;


   public double getAmountDue() {
       return amountDue;
   }


   public void setAmountDue(double amountDue) {
       this.amountDue = amountDue;
   }


   public double getAmountPaid() {
       return amountPaid;
   }


   public void setAmountPaid(double amountPaid) {
       this.amountPaid = amountPaid;
   }


   public LocalDate getPaidAt() {
       return paidAt;
   }


   public void setPaidAt(LocalDate paidAt) {
       this.paidAt = paidAt;
   }




   public Billing() {
       billId = billCounter++;
       amountDue = 0;
       amountPaid = 0;


   }


   public Billing(double amountDue) {
       billId = billCounter++;
       this.amountDue = amountDue;
       amountPaid = 0;
   }


   public Billing(SortedLinkedListInterface<Course> courseList) {
       billId = billCounter++;


       amountPaid = 0;
       this.courseList = courseList;


       for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
           amountDue += courseList.get(i).getCourseFee();
       }
   }


   public SortedLinkedListInterface<Course> getCourseList() {
       if (courseList == null) {
           courseList = new SortedDoublyLinkedList<>();
       }
       return courseList;
   }


   public void setCourseList(SortedLinkedListInterface<Course> courseList) {
       this.courseList = courseList;
   }


   public Long getBillId() {
       return billId;
   }


   public void setBillId(Long billId) {
       this.billId = billId;
   }


   @Override
   public int compareTo(Billing b) {
       return billId.compareTo(b.billId);
   }


   @Override
   public String toString() {
       StringBuilder result = new StringBuilder();


       result.append(String.format("%-19d RM %-20.2f RM %-30.2f %-30s %-20d %s\n",
               billId, amountDue, amountPaid, (paidAt != null) ? dateFormatter.format(paidAt) : "Not Paid",
               courseList.getNumberOfEntries(), (paidAt != null) ? "Cash" : "-"));


       result.append("-".repeat(145)).append("\n");


       result.append("Course Information: ").append("\n");
       // Courses information
       for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
           Course course = courseList.get(i);
           result.append(String.format("%-6d", i + 1)).append(course.toStringByRow()).append("\n");
       }


       result.append("-".repeat(145)).append("\n");


       return result.toString();
   }


   public String toStringWithoutCourse() {
       StringBuilder result = new StringBuilder();
       String border = new String(new char[145]).replace('\0', '-');


       result.append(String.format("%-19d RM %-20.2f RM %-30.2f %-30s %-20d %s",
               billId, amountDue, amountPaid, (paidAt != null) ? dateFormatter.format(paidAt) : "Not Paid",
               courseList.getNumberOfEntries(), (paidAt != null) ? "Cash" : "-"));


       result.append("\n").append(border);


       return result.toString();
   }
}





