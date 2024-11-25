/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;

/**
 *
 * @author chinkhang
 */
public class Tutor implements Comparable<Tutor>{
  private static int nextNumber = 10014;
  private String name;
  private String position;
  private String educationTittle;
  private String email;
  private String educationLevel;
  private String id;
  private String type;
  private String faculty;
  private int creditHour;
  
  private SortedLinkedListInterface<TutorialGroup> tutorialGroupList;

    public Tutor(String id,String name, String position, String educationTittle, String email, String educationLevel,String type,String faculty,int creditHour,boolean newTutor) {
        if(newTutor){
        this.id = "T"+nextNumber++;
        }
        this.id = id;
        this.name = name;
        this.position = position;
        this.educationTittle = educationTittle;
        this.email = email;
        this.educationLevel = educationLevel;
        this.type = type;
        this.faculty = faculty;
        this.creditHour = creditHour;
        this.tutorialGroupList = new SortedDoublyLinkedList<>();

    }
    
    public Tutor(String name, String position, String educationTittle, String email, String educationLevel,String type,String faculty,int creditHour,boolean newTutor) {
        if(newTutor){
        this.id = "T"+nextNumber++;
        }
        this.name = name;
        this.position = position;
        this.educationTittle = educationTittle;
        this.email = email;
        this.educationLevel = educationLevel;
        this.type = type;
        this.faculty = faculty;
        this.creditHour = creditHour;
        this.tutorialGroupList = new SortedDoublyLinkedList<>();

    }
    public Tutor(String id,String name, String position, String educationTittle, String email, String educationLevel,String type,String faculty,int creditHour) {
        this.id =id;
        this.name = name;
        this.position = position;
        this.educationTittle = educationTittle;
        this.email = email;
        this.educationLevel = educationLevel;
        this.type = type;
        this.faculty = faculty;
        this.creditHour = creditHour;
    
    }
    
    public Tutor() {
        this.tutorialGroupList = new SortedDoublyLinkedList<>();
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s%-10s%-10s%-10s%-10s%-10s%-20s\n",
                this.getId(), this.getName(), this.getPosition(),this.getEducationTittle(),
                this.getEmail(), this.getEducationLevel(),this.getType(),this.getTutorialGroupList());
    }

    
    

    public SortedLinkedListInterface<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }
    public static int getNextNumber() {
        return nextNumber;
    }
    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public String getFaculty() {
        return faculty;
    }
    public int getCreditHour() {
        return creditHour;
    }
    public String getEducationTittle() {
        return educationTittle;
    }
    public void setName(String name) {
        this.name = name;
    }
     public String getType() {
        return type;
    }
      public String getEducationLevel() {
        return educationLevel;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public static void setNextNumber(int nextNumber) {
        Tutor.nextNumber = nextNumber;
    }
    public void setEducationTittle(String educationTittle) {
        this.educationTittle = educationTittle;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }
    public void setId(String  id) {
        this.id = id;
    }

    public void setTutorialGroupList(SortedLinkedListInterface<TutorialGroup> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }
    
  @Override
  public int compareTo(Tutor t) {
    return name.compareTo(t.name);
  }  
}

