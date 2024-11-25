/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;

/**
 *
 * @author KaiWah
 */


public class TutorialGroup implements Comparable<TutorialGroup> {


   private String tutorialGroupID;
   private String tutorialGroupName;


   public static final int maxStudentInTutorialGroup  = 25;


   private SortedLinkedListInterface<Student> studentList = new SortedDoublyLinkedList<>();
   private SortedLinkedListInterface<Tutor> tutorList;


   private SortedLinkedListInterface<Course> courseList;


   private Programme programme;


   public TutorialGroup(String tutorialGroupName) {
        this.tutorList = new SortedDoublyLinkedList<>();
   }

    public TutorialGroup(SortedLinkedListInterface<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public TutorialGroup() {
        
    }

    

   
   public TutorialGroup(String tutorialGroupID, String tutorialGroupName, Programme programme) {
       this.tutorialGroupID = tutorialGroupID;
       this.tutorialGroupName = tutorialGroupName;
       this.programme = programme;
       this.tutorList = new SortedDoublyLinkedList<>();
   }


   public Programme getProgramme() {
       return programme;
   }


   public void setProgramme(Programme programme) {
       this.programme = programme;
   }


   public TutorialGroup(String tutorialGroupID, String tutorialGroupName) {
       this.tutorialGroupID = tutorialGroupID;
       this.tutorialGroupName = tutorialGroupName;
       this.tutorList = new SortedDoublyLinkedList<>();
   }


   public String getTutorialGroupID() {
       return tutorialGroupID;
   }


   public SortedLinkedListInterface<Tutor> getTutorList() {
       return tutorList;
   }


   public void setTutorList(SortedLinkedListInterface<Tutor> tutorList) {
       this.tutorList = tutorList;
   }
  
   public void setTutorialGroupID(String tutorialGroupID) {
       this.tutorialGroupID = tutorialGroupID;
   }


   public void setTutorialGroupName(String tutorialGroupName) {
       this.tutorialGroupName = tutorialGroupName;
   }


   public String getTutorialGroupName() {
       return tutorialGroupName;
   }




   public SortedLinkedListInterface<Student> getStudentList() {
       return studentList;
   }


   public void setStudentList(SortedLinkedListInterface<Student> studentList) {
       this.studentList = studentList;
   }






   @Override
   public String toString() {
       return "TutorialGroup{" +
               "tutorialGroupID='" + tutorialGroupID + '\'' +
               ", tutorialGroupName='" + tutorialGroupName + '\'' +
               '}';
   }


   @Override
   public int compareTo(TutorialGroup tg) {
       return this.tutorialGroupName.compareTo(tg.tutorialGroupName);
   }




   public void addStudentToTutorialGroup(Student student){
       if(student != null)
           studentList.add(student);
   }


   public void removeStudentFromTutorialGroup(Student student){
       if(student != null)
           studentList.remove(student);
   }


}

