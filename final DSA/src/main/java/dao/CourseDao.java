/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Ming King
 */
import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import entity.Course;
import entity.Programme;
import entity.Faculty;

public class CourseDao {


   public static SortedLinkedListInterface<Faculty> presetFacultyList() {
       SortedLinkedListInterface<Faculty> facList = new SortedDoublyLinkedList<>();
       facList.add(new Faculty("FOCS", "Faculty of Computing And Information Technology"));
       facList.add(new Faculty("FAFB", "Faculty of Accountancy, Finance And Business"));
       facList.add(new Faculty("FCCI", "Faculty of Communication And Creative Industries"));
       return facList;
   }


    public static SortedLinkedListInterface<Course> presetCourseList() {
        SortedLinkedListInterface<Course> courseList = new SortedDoublyLinkedList<>();
        //FOCS
        courseList.add(new Course("BACS2063", "DATA STRUCTURES AND ALGORITHMS", "ADVANCED", "MAIN", "COMPULSORY", 3, 777, CourseDao.presetProgFOCS(),TutorDao.presetFocsTutorList()));
        courseList.add(new Course("BAIT3003", "DATA WAREHOUSE TECHNOLOGY", "ADVANCED", "MAIN", "ELECTIVE", 3, 777, CourseDao.presetProgFOCS(),TutorDao.presetFocsTutorList()));
        //FAFB
        courseList.add(new Course("ABFA1173", "PRINCIPLES OF ACCOUNTING", "INTRODUCTORY", "MAIN", "COMPULSORY", 3, 777, CourseDao.presetProgFAFB(),TutorDao.presetFafbTutorList()));


        //FCCI
        courseList.add(new Course("BAMS3012", "INTRODUCTION TO MASS COMMUNICATION", "INTRODUCTORY", "MAIN", "COMPULSORY", 3, 777, CourseDao.presetProgFCCI(),TutorDao.presetFcciTutorList()));


        //MPU
        courseList.add(new Course("MPU-3302", "INTEGRITY AND ANTI-CORRUPTION", "INTERMEDIATE", "MPU", "ELECTIVE", 2, 478, CourseDao.presetProgFOCS(),TutorDao.presetFocs2TutorList()));
        courseList.add(new Course("MPU-3133", "FALSAFAH DAN ISU SEMASA", "INTRODUCTORY", "MPU", "COMPULSORY", 1, 239, CourseDao.presetProgFOCS(),TutorDao.presetFocs2TutorList()));
        return courseList;
    }


   public static SortedLinkedListInterface<Programme> presetProgrammeList() {
       SortedLinkedListInterface<Programme> progList = new SortedDoublyLinkedList<>();


       progList.add(new Programme("REIY2S1", "Enterprise Information System", "Y2S1", "Degree", "FOCS", CourseDao.presetCourseFOCS(),TutorialGroupDao.daoREIY2S1()));
       progList.add(new Programme("REIY2S2", "Enterprise Information System", "Y2S2", "Degree", "FOCS", CourseDao.presetCourseFOCS(),TutorialGroupDao.daoREIY2S2()));
       progList.add(new Programme("REIY2S3", "Enterprise Information System", "Y2S3", "Degree", "FOCS", CourseDao.presetCourseFOCS(),TutorialGroupDao.daoREIY2S3()));


       progList.add(new Programme("DISY2S1", "Information System", "Y2S1", "Diploma", "FOCS", CourseDao.presetCourseFOCS(),TutorialGroupDao.daoDISY2S1()));
       progList.add(new Programme("DISY2S2", "Information System", "Y2S2", "Diploma", "FOCS", CourseDao.presetCourseFOCS(),TutorialGroupDao.daoDISY2S2()));
       progList.add(new Programme("DISY2S3", "Information System", "Y2S3", "Diploma", "FOCS", CourseDao.presetCourseFOCS(), TutorialGroupDao.daoDISY2S3()));


       progList.add(new Programme("DFAY1S1", "ACCOUNTING", "Y1S1", "Diploma", "FAFB", CourseDao.presetCourseFAFB(),TutorialGroupDao.daoDFAY1S1()));
       progList.add(new Programme("DFAY1S2", "ACCOUNTING", "Y1S2", "Diploma", "FAFB", CourseDao.presetCourseFAFB(),TutorialGroupDao.daoDFAY1S2()));
       progList.add(new Programme("DFAY1S3", "ACCOUNTING", "Y1S3", "Diploma", "FAFB", CourseDao.presetCourseFAFB(),TutorialGroupDao.daoDFAY1S3()));


       progList.add(new Programme("DFMY1S1", "MEDIA STUDIES", "Y1S1", "Diploma", "FCCI", CourseDao.presetCourseFCCI(),TutorialGroupDao.daoDFMY1S1()));
       progList.add(new Programme("DFMY1S2", "MEDIA STUDIES", "Y1S2", "Diploma", "FCCI", CourseDao.presetCourseFCCI(),TutorialGroupDao.daoDFMY1S2()));
       progList.add(new Programme("DFMY1S3", "MEDIA STUDIES", "Y1S3", "Diploma", "FCCI", CourseDao.presetCourseFCCI(),TutorialGroupDao.daoDFMY1S3()));
       return progList;
   }


   public static SortedLinkedListInterface<Programme> presetProgFOCS() {
       SortedLinkedListInterface<Programme> progList = new SortedDoublyLinkedList<>();
       progList.add(new Programme("REIY2S1", "Enterprise Information System", "Y2S1", "Degree", "FOCS",null,TutorialGroupDao.daoREIY2S1()));
       progList.add(new Programme("REIY2S2", "Enterprise Information System", "Y2S2", "Degree", "FOCS",null,TutorialGroupDao.daoREIY2S2()));
       progList.add(new Programme("REIY2S3", "Enterprise Information System", "Y2S3", "Degree", "FOCS",null,TutorialGroupDao.daoREIY2S3()));
       progList.add(new Programme("DISY2S1", "Information System", "Y2S1", "Diploma", "FOCS",null,TutorialGroupDao.daoDISY2S1()));
       progList.add(new Programme("DISY2S2", "Information System", "Y2S2", "Diploma", "FOCS",null,TutorialGroupDao.daoDISY2S2()));
       progList.add(new Programme("DISY2S3", "Information System", "Y2S3", "Diploma", "FOCS",null,TutorialGroupDao.daoDISY2S3()));
       return progList;
   }


   public static SortedLinkedListInterface<Programme> presetProgFAFB() {
       SortedLinkedListInterface<Programme> progList = new SortedDoublyLinkedList<>();
       progList.add(new Programme("DFAY1S1", "ACCOUNTING", "Y1S1", "Diploma", "FAFB",null,TutorialGroupDao.daoDFAY1S1()));
       progList.add(new Programme("DFAY1S2", "ACCOUNTING", "Y1S2", "Diploma", "FAFB",null,TutorialGroupDao.daoDFAY1S2()));
       progList.add(new Programme("DFAY1S3", "ACCOUNTING", "Y1S3", "Diploma", "FAFB",null,TutorialGroupDao.daoDFAY1S3()));
       return progList;
   }


   public static SortedLinkedListInterface<Programme> presetProgFCCI() {
       SortedLinkedListInterface<Programme> progList = new SortedDoublyLinkedList<>();
       progList.add(new Programme("DFMY1S1", "MEDIA STUDIES", "Y1S1", "Diploma", "FCCI",null,TutorialGroupDao.daoDFMY1S1()));
       progList.add(new Programme("DFMY1S2", "MEDIA STUDIES", "Y1S2", "Diploma", "FCCI",null,TutorialGroupDao.daoDFMY1S2()));
       progList.add(new Programme("DFMY1S3", "MEDIA STUDIES", "Y1S3", "Diploma", "FCCI",null,TutorialGroupDao.daoDFMY1S3()));
       return progList;
   }


   public static SortedLinkedListInterface<Programme> presetProgramme() {
       SortedLinkedListInterface<Programme> progList = new SortedDoublyLinkedList<>();
      
       progList.add(new Programme("DFAY1S1", "ACCOUNTING", "Y1S1", "Diploma", "FAFB"));
       progList.add(new Programme("DFAY1S2", "ACCOUNTING", "Y1S2", "Diploma", "FAFB"));
       progList.add(new Programme("DFAY1S3", "ACCOUNTING", "Y1S3", "Diploma", "FAFB"));
       progList.add(new Programme("REIY2S1", "Enterprise Information System", "Y2S1", "Degree", "FOCS"));
       progList.add(new Programme("REIY2S2", "Enterprise Information System", "Y2S2", "Degree", "FOCS"));
       progList.add(new Programme("REIY2S3", "Enterprise Information System", "Y2S3", "Degree", "FOCS"));
       progList.add(new Programme("DISY2S1", "Information System", "Y2S1", "Diploma", "FOCS"));
       progList.add(new Programme("DISY2S2", "Information System", "Y2S2", "Diploma", "FOCS"));
       progList.add(new Programme("DISY2S3", "Information System", "Y2S3", "Diploma", "FOCS"));
       progList.add(new Programme("DFMY1S1", "MEDIA STUDIES", "Y1S1", "Diploma", "FCCI"));
       progList.add(new Programme("DFMY1S2", "MEDIA STUDIES", "Y1S2", "Diploma", "FCCI"));
       progList.add(new Programme("DFMY1S3", "MEDIA STUDIES", "Y1S3", "Diploma", "FCCI"));
       return progList;
   }


   public static SortedLinkedListInterface<Course> presetCourseFOCS() {
       SortedLinkedListInterface<Course> courseList = new SortedDoublyLinkedList<>();
      
       courseList.add(new Course("BACS2063", "DATA STRUCTURES AND ALGORITHMS", "ADVANCED", "MAIN", "COMPULSORY", 3, 777, false));
       courseList.add(new Course("BAIT3003", "DATA WAREHOUSE TECHNOLOGY", "ADVANCED", "MAIN", "COMPULSORY", 3, 777, false));
       courseList.add(new Course("MPU-3302", "INTEGRITY AND ANTI-CORRUPTION", "INTERMEDIATE", "MPU", "COMPULSORY", 2, 478, false));
       courseList.add(new Course("MPU-3133", "FALSAFAH DAN ISU SEMASA", "INTRODUCTORY", "MPU", "COMPULSORY", 1, 239, false));
       return courseList;
   }


   public static SortedLinkedListInterface<Course> presetCourseFAFB() {
       SortedLinkedListInterface<Course> courseList = new SortedDoublyLinkedList<>();


       courseList.add(new Course("ABFA1173", "PRINCIPLES OF ACCOUNTING", "INTRODUCTORY", "MAIN", "COMPULSORY", 3, 777, false));
       courseList.add(new Course("MPU-3302", "INTEGRITY AND ANTI-CORRUPTION", "INTERMEDIATE", "MPU", "COMPULSORY", 2, 478, false));
       courseList.add(new Course("MPU-3133", "FALSAFAH DAN ISU SEMASA", "INTRODUCTORY", "MPU", "COMPULSORY", 1, 239, false));
       return courseList;
   }


   public static SortedLinkedListInterface<Course> presetCourseFCCI() {
       SortedLinkedListInterface<Course> courseList = new SortedDoublyLinkedList<>();


       courseList.add(new Course("BAMS3012", "INTRODUCTION TO MASS COMMUNICATION", "INTRODUCTORY", "MAIN", "COMPULSORY", 3, 777, false));
       courseList.add(new Course("MPU-3302", "INTEGRITY AND ANTI-CORRUPTION", "INTERMEDIATE", "MPU", "COMPULSORY", 2, 478, false));
       courseList.add(new Course("MPU-3133", "FALSAFAH DAN ISU SEMASA", "INTRODUCTORY", "MPU", "COMPULSORY", 1, 239, false));
       return courseList;
   }
   
   public static SortedLinkedListInterface<Course> presetCourseTestStudent() {
   SortedLinkedListInterface<Course> courseList = new SortedDoublyLinkedList<>();
  
   courseList.add(new Course("MPU-3302", "INTEGRITY AND ANTI-CORRUPTION", "INTERMEDIATE", "MPU", "COMPULSORY", 2, 478, false));
   courseList.add(new Course("MPU-3133", "FALSAFAH DAN ISU SEMASA", "INTRODUCTORY", "MPU", "COMPULSORY", 1, 239, false));
   return courseList;
}



}



