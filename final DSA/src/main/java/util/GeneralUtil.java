/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author FungPin
 */
import adt.SortedLinkedListInterface;
import controller.CourseController;
import dao.CourseDao;
import entity.Billing;
import entity.Programme;
import entity.Student;
import static entity.Student.totalRegStudCounter;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtil {




   private static final String emailDomain = "student@tarc.edu.my";


   public static String getCurrentEmailDomain() {
       return emailDomain;
   }


   public static String getCurrentYear() {
       try {
           return String.valueOf(LocalDateTime.now().getYear());
       } catch (Exception e) {
           System.out.println("Parse error");
       }
       return null;
   }




   public static String getLastTwoDigitsOfCurrentYear() {
       if (getCurrentYear() != null) {
           return getCurrentYear().substring(2);
       } else {
           return null;
       }
   }


   public static String extractLastTwoDigitsOfGivenYear(Integer givenYear) {
       return givenYear.toString().substring(2);
   }




   public static String extractStudentFirstName(String firstName) {
       Pattern pattern = Pattern.compile("(?<=^|\\s)\\S|(?<=\\S)[\\sA-Z](?=\\S|$)");
       Matcher matcher = pattern.matcher(firstName);
       StringBuilder extractedName = new StringBuilder();


       while (matcher.find()) {
           if (!matcher.group().equals(" "))
               extractedName.append(matcher.group());
       }


       return extractedName.toString().toLowerCase();
   }


   public static boolean isAlphanumeric(String target) {
       String regex = "^[a-zA-Z0-9 ]*$";
       Pattern pattern = Pattern.compile(regex);


       return pattern.matcher(target).matches();
   }


   public static boolean isValidNRIC(String NRIC) {
       if (NRIC == null || NRIC.length() != 12) {
           return false;
       }
       return true;
   }


   public static boolean isValidNRIC(String NRIC, SortedLinkedListInterface<Student> studentList) {


       if (NRIC == null || NRIC.length() != 12) {
           return false;
       }


       for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
           if (studentList.get(i).getNRIC().equalsIgnoreCase(NRIC)) {
               System.out.println("Student already registered");
               return false;
           }
       }
       return true;
   }


   public static boolean isValidMobileContact(String input) {
       String regex = "^(\\+?6?01)[02-46-9]-*[0-9]{7}$|^(\\+?6?01)[1]-*[0-9]{8}$";
       Pattern pattern = Pattern.compile(regex);


       return pattern.matcher(input).matches();
   }


   public static double getMainCourseFee() {
       return CourseController.MAIN_COURSE_FEE;
   }


   public static double getMPUCourseFee() {
       return CourseController.MPU_COURSE_FEE;
   }


   public static SortedLinkedListInterface<Billing> createNewStudentBilling(SortedLinkedListInterface<Billing> billingList) {
       if (!billingList.isEmpty()) {
           billingList.add(new Billing());
       }
       return billingList;
   }


   public void createBilling(SortedLinkedListInterface<Billing> billingList) {
       createNewStudentBilling(billingList);
   }


   public static Programme getProgramme(String programmeCode) {
       SortedLinkedListInterface<Programme> allProgrammeList = CourseController.presetProgrammeList;
       Programme matchProgramme = null;


       for (int j = 0; j < allProgrammeList.getNumberOfEntries(); j++) {
           if (programmeCode.equals(allProgrammeList.get(j).getProgCode())) {
               matchProgramme = allProgrammeList.get(j);
               break;
           }
       }
       return matchProgramme;
   }


   public static void updateStudentIDBasedOnProgramme(Student student) {
       if (student != null) {
           if (GeneralUtil.getLastTwoDigitsOfCurrentYear() != null) {
               String studentProgLevel = "";
               if (student.getProgramme() != null) {
                   studentProgLevel = student.getProgramme().getProgLevel().equalsIgnoreCase("diploma") ? "WMD" : "WMR";
               }


               student.setStudentID(GeneralUtil.getLastTwoDigitsOfCurrentYear() + studentProgLevel + String.format("%05d", totalRegStudCounter));
           }
       }
   }
}




