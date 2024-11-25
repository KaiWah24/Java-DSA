/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package boundary;

import adt.SortedLinkedListInterface;
import controller.CourseController;
import controller.ProgrammeController;
import controller.StudentController;
import controller.TutorController;
import controller.TutorialGroupController;
import dao.CourseDao;
import dao.StudentDao;
import entity.Course;
import entity.Faculty;
import entity.Programme;
import java.util.Scanner;


/**
 *
 * @author chink
 */
public class MainUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choice =0;
        Scanner input = new Scanner(System.in);
        SortedLinkedListInterface<Course> presetCourseList = CourseDao.presetCourseList();
        SortedLinkedListInterface<Programme> presetProgrammeList = CourseDao.presetProgrammeList();
        SortedLinkedListInterface<Faculty> presetFacultyList = CourseDao.presetFacultyList();

        CourseController.presetCourseList = presetCourseList;
        CourseController.presetProgrammeList = presetProgrammeList;
        CourseController.presetFacultyList = presetFacultyList;
        StudentController.studentList = StudentDao.initStudents();
        TutorialGroupController.sList = StudentController.studentList;
        TutorialGroupController.pList = CourseController.presetProgrammeList;
        CourseController courseManagement = new CourseController();
        TutorController tutorManagement = new TutorController();
        StudentController studentManagement = new StudentController();
        ProgrammeController programmeManagement = new ProgrammeController(); 
        TutorialGroupController tutorialGroupManagement = new TutorialGroupController();
        

        do{
            System.out.println("\n**************************");
            System.out.printf("%-1s %16s %7s\n","|","Main Menu","|");
            System.out.println("**************************\n");
            System.out.println("1. Student Menu");
            System.out.println("2. Course Menu");
            System.out.println("3. Programme Menu");//Pending
            System.out.println("4. Tutorial Group Menu");
            System.out.println("5. Tutor Menu");
            System.out.println("6. Exit");
            System.out.print("Enter your option : ");
            choice = input.nextInt();
            switch(choice){
              case 1:
                    studentManagement.studentMenu();
                 break; 
              case 2 :
                    courseManagement.courseMenu();;
                 break; 
              case 3:
                    programmeManagement.progMenu();
                  break;
              case 4:
                    tutorialGroupManagement.TutorialGroupMenu();
                  break;
              case 5:
                    tutorManagement.tutorMenu();
                  break;
              case 6:
                  break;
               default : 
                   System.out.print("Invalid choice please try again!");
                   break;
            }
        }while(choice!=6);
    }
}
