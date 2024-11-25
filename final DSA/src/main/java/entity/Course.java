/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;

/**
 *
 * @author MingKing
 */




public class Course implements Comparable<Course> {
   private String courseCode; //e.g. BACS2060
   private String courseName; //e.g. Data Structure and Algorithms
   private String courseLevel; //e.g. Introductory,Intermediate,Advanced
   private String courseCategory; //e.g. Main, MPU
   private String courseType; // e.g. Compulsory, Elective
   private int creditHours; //e.g. 1,2,3
   private double courseFee; //e.g. 259
   private SortedLinkedListInterface<Tutor> tutorList;
   public static int nextCourseCode = 2000;
  


   //private SortedListInterface<Tutor> tutorList;
   //private SortedListInterface<Student> studentList;
   private SortedLinkedListInterface<Programme> programmeList;




   // todo add studentList, tutorList, programmeList
   private SortedLinkedListInterface<Student> studentList;




   public Course(String courseCode, String courseName, String courseLevel, String courseCategory, String courseType, int creditHours, double courseFee, boolean newCourse) {
       if (newCourse) {
           this.courseCode = courseCode;
           this.courseName = courseName;
           this.courseLevel = courseLevel;
           this.courseCategory = courseCategory;
           this.courseType = courseType;
           this.creditHours = creditHours;
           this.courseFee = courseFee;
           nextCourseCode++;


       } else {
           this.courseCode = courseCode;
           this.courseName = courseName;
           this.courseLevel = courseLevel;
           this.courseCategory = courseCategory;
           this.courseType = courseType;
           this.creditHours = creditHours;
           this.courseFee = courseFee;
       }
   }


   public Course(String courseCode, String courseName, String courseLevel, String courseCategory, String courseType, int creditHours, double courseFee, SortedLinkedListInterface<Programme> programmeList, SortedLinkedListInterface<Tutor> tutorList) {
       this.courseCode = courseCode;
       this.courseName = courseName;
       this.courseLevel = courseLevel;
       this.courseCategory = courseCategory;
       this.courseType = courseType;
       this.creditHours = creditHours;
       this.courseFee = courseFee;
       this.programmeList = programmeList;
       this.tutorList = tutorList;
   }


   public Course(String courseCode, String courseName, String courseLevel, String courseCategory, String courseType, int creditHours, double courseFee, SortedLinkedListInterface<Tutor> tutorList) {
       this.courseCode = courseCode;
       this.courseName = courseName;
       this.courseLevel = courseLevel;
       this.courseCategory = courseCategory;
       this.courseType = courseType;
       this.creditHours = creditHours;
       this.courseFee = courseFee;
       this.programmeList = new SortedDoublyLinkedList<>();
       this.tutorList = tutorList;
   }




   public void setCourseCode(String courseCode) {
       this.courseCode = courseCode;
   }

    public void setTutorList(SortedLinkedListInterface<Tutor> tutorList) {
        this.tutorList = tutorList;
    }
   
   

   public SortedLinkedListInterface<Tutor> getTutorList() {
       return tutorList;
   }






   public void setCourseCategory(String courseCategory) {
       this.courseCategory = courseCategory;
   }


   public String getCourseCategory() {
       return courseCategory;
   }


   public static int getNextCourseCode() {
       return nextCourseCode;
   }


   public String getCourseCode() {
       return courseCode;
   }


   public String getCourseName() {
       return courseName;
   }


   public String getCourseLevel() {
       return courseLevel;
   }


   public String getCourseType() {
       return courseType;
   }


   public int getCreditHours() {
       return creditHours;
   }


   public double getCourseFee() {
       return courseFee;
   }


   public SortedLinkedListInterface<Programme> getProgrammeList() {
       return programmeList;
   }


   public void setCourseType(String courseType) {
       this.courseType = courseType;
   }


   public void setCourseName(String courseName) {
       this.courseName = courseName;
   }


   public void setCourseLevel(String courseLevel) {
       this.courseLevel = courseLevel;
   }


   public void setCreditHours(int creditHours) {
       this.creditHours = creditHours;
   }


   public void setCourseFee(double courseFee) {
       this.courseFee = courseFee;
   }


   public void setProgrammeList(SortedLinkedListInterface<Programme> programmeList) {
       this.programmeList = programmeList;
   }


   public SortedLinkedListInterface<Student> getStudentList() {
       return studentList;
   }
  
  
  


   @Override
   public int compareTo(Course course) {
       return courseCode.compareTo(course.courseCode);
   }


   public String toStringByRow() {
       return String.format("%3s%-15s%-40s%-20s%-10s%-10s%02d%-8sRM %-16.2f",
               " ",courseCode, courseName, courseLevel, courseCategory,
               " ",creditHours," ", courseFee);
   }


   public String toStringByRow2() {
       return String.format("%3s%-15s%-40s%-20s%-4s%-11s%-20s%02d%-8sRM %-7.2f ",
               " ",courseCode, courseName, courseLevel, courseCategory," ",
               courseType,creditHours," ", courseFee);
   }




   @Override
   public String toString() {
       return
               "Course Code: " + courseCode + '\n' +
               "Course Name: " + courseName + '\n' +
               "Course Level:" + courseLevel + '\n' +
               "Course Category: " + courseCategory + '\n' +
               "Credit Hours: " + creditHours + '\n'+
               "Course Fee:" + courseFee + '\n';
   }


   public String toStringByColumn() {
       return String.format("| %-20s: %-35s |%n| %-20s: %-35s |%n| %-20s: %-35s |%n| %-20s: %-35s |%n| %-20s: %02d%-33s |%n| %-20s: RM %7.2f%-25s |%n",
               "Course Code", courseCode,
               "Course Name", courseName,
               "Course Level", courseLevel,
               "Course Category", courseCategory,
               "Credit Hours", creditHours," ",
               "Course Fee", courseFee," ");
   }


   public String toStringByColumn2() {
       return String.format("| %-20s: %-35s |%n| %-20s: %-35s |%n| %-20s: %-35s |%n| %-20s: %-35s |%n| %-20s: %-35s |%n| %-20s: %02d%-33s |%n| %-20s: RM %7.2f%-25s |%n",
               "Course Code", courseCode,
               "Course Name", courseName,
               "Course Level", courseLevel,
               "Course Category", courseCategory,
               "Course Type", courseType,
               "Credit Hours", creditHours," ",
               "Course Fee", courseFee," ");
   }


  
   public static String courseHeader() {
       String header = String.format("%-6s%-15s%-40s%-20s%-10s%-15s%-16s",
               "No.", "Course Code", "Course Name", "Level", "Category", "Credit Hours", "Course Fee");


       // Calculate the length of the header string
       int headerLength = header.length();


       // Create a dotted line string of the same length as the header
       String line = new String(new char[headerLength]).replace('\0', '-');


       // Concatenate the dotted line before and after the header
       return line + "\n" + header + "\n" + line;
   }

}










