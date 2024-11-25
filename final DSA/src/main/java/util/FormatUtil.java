/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author FungPin
 */
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatUtil {
   public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);


   public static String printStudentHeader() {
       StringBuilder result = new StringBuilder();
       result.append("-".repeat(266));
       result.append(String.format("\n%-10s |%-25s |%-15s |%-8s |%-25s |%-12s |%-50s |%-25s |%-30s |%-30s |%-30s",
               "ID", "First Name", "Last Name", "Gender", "NRIC", "Mobile", "Email", "Enrollment Date" ,"Faculty", "Programme", "Tutorial Group"));


       return result.toString();
   }


   public static String printStudentHeaderSimple() {
       StringBuilder result = new StringBuilder();
       result.append("-".repeat(80)).append("\n"); // Adjust the number
       result.append(String.format("%-4s %-20s %-40s %-30s %-10s",
               "No", "Student ID", "First Name", "Last Name", "Fee Paid"));
       result.append("\n").append("-".repeat(80)); // Adjust the number
       return result.toString();
   }


   public static String printBillingHeader() {
       StringBuilder result = new StringBuilder();
       String border = new String(new char[145]).replace('\0', '-');
       result.append(border).append("\n");
       // Header
       result.append(String.format("%-4s %-15s %-24s %-35s %-30s %-20s %s\n",
               "No.", "Bill ID", "Amount Due", "Amount Paid", "Paid At", "Total Courses", "Payment Method"));
       result.append(border);
       return result.toString();
   }


   public static String printBillingHeaderWithoutNumber() {
       StringBuilder result = new StringBuilder();
       String border = new String(new char[145]).replace('\0', '-');
       result.append(border).append("\n");
       // Header
       result.append(String.format("%-15s %-24s %-35s %-30s %-20s %s\n",
               "Bill ID", "Amount Due", "Amount Paid", "Paid At", "Total Courses", "Payment Method"));
       result.append(border);
       return result.toString();
   }




   public static String printDottedLine(int n) {
       return "-".repeat(n);
   }
}




