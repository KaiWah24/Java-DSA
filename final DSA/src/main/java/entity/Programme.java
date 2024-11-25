/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import dao.TutorialGroupDao;

/**
 *
 * @author MingKing
 */
public class Programme implements Comparable<Programme> {
    private String progCode; //e.g. REI
    private String progName; //e.g. Enterprise Information System
    private String progSemester; // e.g. Y2S1
    private String progLevel; // e.g. Diploma, Degree
    private String facultyCode; // e.g. FOCS
    private SortedLinkedListInterface<Course> courseList;
    private SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();

    // todo tutorGroupList


    public Programme(String progCode, String progName, String progSemester, String progLevel, String facultyCode) {
        this.progCode = progCode;
        this.progName = progName;
        this.progSemester = progSemester;
        this.progLevel = progLevel;
        this.facultyCode = facultyCode;
    }

    public Programme(String progCode, String progName, String progSemester, String progLevel, String facultyCode, SortedLinkedListInterface<Course> courseList, SortedLinkedListInterface<TutorialGroup> tutorialGroupList) {
        this.progCode = progCode;
        this.progName = progName;
        this.progSemester = progSemester;
        this.progLevel = progLevel;
        this.facultyCode = facultyCode;
        this.courseList = courseList;
        this.tutorialGroupList = tutorialGroupList;
    }

    public Programme(String progCode, String progName, String progSemester, String progLevel, String facultyCode, SortedLinkedListInterface<Course> courseList) {
        this.progCode = progCode;
        this.progName = progName;
        this.progSemester = progSemester;
        this.progLevel = progLevel;
        this.facultyCode = facultyCode;
        this.courseList = courseList;
    }

    public String getProgCode() {
        return progCode;
    }
    public String getFacultyCode() {
        return facultyCode;
    }
    public void setProgCode(String progCode) {
        this.progCode = progCode;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public String getProgSemester() {
        return progSemester;
    }

    public void setprogSemester(String progSemester) {
        this.progSemester = progSemester;
    }

    public String getProgLevel() {
        return progLevel;
    }

    public void setProgLevel(String progLevel) {
        this.progLevel = progLevel;
    }

    public SortedLinkedListInterface<Course> getCourseList() {
        return courseList;
    }


    public void setCourseList(SortedLinkedListInterface<Course> courseList) {
        this.courseList = courseList;
    }

    public SortedLinkedListInterface<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void setTutorialGroupList(SortedLinkedListInterface<TutorialGroup> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }

    
    
    @Override
    public int compareTo(Programme p) {
        return progCode.compareTo(p.progCode);
    }

    @Override
    public String toString() {
        return
                "Programme Code: " + progCode + '\n' +
                "Programme Name: " + progName + '\n' +
                "Programme Duration: " + progSemester + '\n' +
                "Programme Level: " + progLevel;
    }

    public String toStringByRow() {
        return String.format("%3s%-15s%-40s%-14s%-16s%-4s",
                " ",progCode, progName, progSemester, progLevel,facultyCode);
    }


   public void addTutorialGroup(TutorialGroup tg){
        if (tg != null) {
            tutorialGroupList.add(tg);
        }
    }


    public void removeTutorialGroup(TutorialGroup tg){
        if(tg != null)
            tutorialGroupList.remove(tg);
    }

    public TutorialGroup findTutorialGroup(String tutorialGroupName) {
        if (tutorialGroupList != null) {
            System.out.println("Searching for tutorial group: " + tutorialGroupName);

            for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
                TutorialGroup findGroup = tutorialGroupList.getEntry(i);
                if (findGroup != null && findGroup.getTutorialGroupName().equals(tutorialGroupName)) {
                    System.out.println("Found matching group at index: " + i);
                    return findGroup;
                }
            }
            System.out.println("No matching tutorial group found.");
            return null;
        } else {
            return null;
        }
    }



    public static void printHeader() {
        System.out.printf("%-5s %-30s %-30s\n", "No.", "Programme", "Programme Code");
    }
}
