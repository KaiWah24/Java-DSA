/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author KaiWah
 */
import adt.SortedDoublyLinkedList;
import dao.CourseDao;
import dao.StudentDao;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import adt.SortedLinkedListInterface;
import entity.Billing;
import entity.Course;
import static entity.TutorialGroup.maxStudentInTutorialGroup;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import util.GeneralUtil;

public class TutorialGroupController {
   public static SortedLinkedListInterface<Course> courseList = CourseController.presetCourseList;
   public static SortedLinkedListInterface<Programme> pList;
   public static SortedLinkedListInterface<Student> sList;

   public void TutorialGroupMenu() {
       Scanner input = new Scanner(System.in);

       boolean exitTutorialMenu = false;

       while (!exitTutorialMenu) {
           displayTutorialMenu();
           int tutorialChoice = input.nextInt();
           input.nextLine(); // Consume the newline character


           switch (tutorialChoice) {
               case 0:
                   exitTutorialMenu = true;
                   break;
               case 1:
                   createTutorialGroup();
                   break;
               case 2:
                   removeTutorialGroup();
                   break;
               case 3:
                   listAllTutorialGroup();
                   break;
               case 4:
                   addStudentToTutorialGroup();
                   break;
               case 5:
                   removeStudentFromTutorialGroup();
                   break;
               case 6:
                   changeStudentTutorialGroup();
                   break;
               case 7:
                   listStudentFromTutorialGroup();
                   break;
               case 8:
                   mergeTutorialGroup();
                   break;
               case 9:
                   generateSummaryReport();
                   break;
               case 10:
                   displayTutorForATutorialGroup();
                   break;
               default:
                   System.out.println("Invalid choice! Please try again!");
           }
       }
   }

   private void displayTutorialMenu() {
       System.out.println("\n");
       System.out.println("================================================");
       System.out.println("||***         Tutorial Group  Menu         ***||");
       System.out.println("================================================");
       System.out.println("|| 1. Create New Tutorial Group               ||");
       System.out.println("|| 2. Remove Tutorial Group                   ||");
       System.out.println("|| 3. List All Tutorial Group                 ||");
       System.out.println("|| 4. Add  Student to a Tutorial Group        ||");
       System.out.println("|| 5. Remove Student from a Tutorial Groups   ||");
       System.out.println("|| 6. Change Tutorial Group for Student       ||");
       System.out.println("|| 7. List All Student in Tutorial Group      ||");
       System.out.println("|| 8. Merge Tutorial Group                    ||");
       System.out.println("|| 9. Generate Summary Reports                ||");
       System.out.println("|| 10. Display All Tutor in Tutorial Group    ||");
       System.out.println("|| 0. back to main menu                       ||");
       System.out.println("================================================");
       System.out.println("Enter your choice : ");
   }

   private boolean updateStudentTutorialGroupAfterDeleteTutorialGroup(TutorialGroup deleteTutorialGroup) {
       boolean updated = false;
       for (int i = 0; i < deleteTutorialGroup.getStudentList().getNumberOfEntries(); i++) {
           Student currentStudent = deleteTutorialGroup.getStudentList().get(i);
           if (currentStudent != null) {
               //compare group name
               if (deleteTutorialGroup.compareTo(currentStudent.getTutorialGroup()) == 0) {
                   //setting student no tutorial group since removed
                   updateStudentTutorialGroup(currentStudent, null);
               }
           }
       }
       return updated;
   }

   public static boolean updateStudentTutorialGroup(Student student, TutorialGroup tutorialGroup) {
       if (student != null) {
           student.setTutorialGroup(tutorialGroup);
           return true;
       }
       return false;
   }

   private void removeStudentFromTutorialGroup(Student removedStudent, TutorialGroup originalTutorialGroup) {
       if (removedStudent.getTutorialGroup() != null && removedStudent.getTutorialGroup().equals(originalTutorialGroup)) {
           originalTutorialGroup.removeStudentFromTutorialGroup(removedStudent);
           removedStudent.setTutorialGroup(null);
       }
   }

   private void createTutorialGroup() {
       Scanner input = new Scanner(System.in);

       boolean createAnotherTutorialGroup = true;

       while (createAnotherTutorialGroup) {
           //Display programme list
           displayProgrammeList(pList);

           int programmeIndex = 0;
           boolean validInput = false;
           while (!validInput) {
               try {
                   System.out.println("Please enter the index of the programme to create a tutorial group:");
                   programmeIndex = input.nextInt();
                   input.nextLine();
                   if (programmeIndex < 1 || programmeIndex > pList.getNumberOfEntries()) {
                       System.out.println("Invalid index, Please enter a valid number between 1 and " + pList.getNumberOfEntries());
                   } else {
                       validInput = true;
                   }
               } catch (Exception e) {
                   System.out.println("Invalid input. Please enter a valid number.");
                   input.nextLine();
               }
           }

           Programme selectedProgram = pList.getEntry(programmeIndex);
           if (selectedProgram != null) {
               System.out.println("Creating tutorial groups for " + selectedProgram.getProgCode());
               int numberOfGroups = 0;
               validInput = false;
               while (!validInput) {
                   System.out.println("How many tutorial groups do you want to create?");

                   if (input.hasNextInt()) {
                       numberOfGroups = input.nextInt();
                       input.nextLine(); // Consume newline character
                       if (numberOfGroups <= 0) {
                           System.out.println("The number of tutorial groups must be a positive integer.");
                       } else if (numberOfGroups > 5) {
                           System.out.println("You are only allowed to create 5 tutorial groups at a time.");
                       } else {
                           validInput = true; // The input is valid, exit the loop
                       }
                   } else {
                       System.out.println("Invalid input. Please enter a valid number");
                       input.nextLine(); // Consume incorrect input
                   }
               }
               for (int j = 0; j < numberOfGroups; j++) {
                   // group id is based on the programme code
                   String tutorialGroupID = selectedProgram.getProgCode();
                   System.out.println("Tutorial group ID : " + tutorialGroupID);
                   String newTutorialGroupName = "";
                   TutorialGroup existingGroup = null;

                   do {
                       // Ask user to enter group name
                       System.out.println("Enter tutorial group name : ");
                       String tutorialGroupName = input.nextLine();
                       // Validate tutorial name
                       if (tutorialGroupName.trim().isEmpty()) {
                           System.out.println("Tutorial group name cannot be empty. Please enter a name.");
                           continue;
                       }
                       newTutorialGroupName = tutorialGroupID + tutorialGroupName;
                       existingGroup = selectedProgram.findTutorialGroup(newTutorialGroupName);
                       if (existingGroup != null) {
                           System.out.println("A tutorial group with this name already existed. Please use a different group name.");
                       }
                   } while (newTutorialGroupName.isEmpty() || existingGroup != null);
                   // Add tutorial group to the selected program
                   TutorialGroup newTutorialGroup = new TutorialGroup(tutorialGroupID, newTutorialGroupName, selectedProgram);
                   selectedProgram.addTutorialGroup(newTutorialGroup);


                   //pList.replace(programmeIndex, selectedProgram);
                   newTutorialGroup.setProgramme(selectedProgram);
                   for (int i = 1; i <= selectedProgram.getTutorialGroupList().getNumberOfEntries(); i++) {
                       for (int k = 1; k <= sList.getNumberOfEntries(); k++) {
                           if (sList.getEntry(k).getProgramme() != null) {
                               if (sList.getEntry(k).getProgramme().compareTo(selectedProgram) == 0) {
                                   sList.getEntry(k).setProgramme(selectedProgram);
                               }
                           }
                       }
                   }

                   for (int i = 0; i < CourseController.presetCourseList.getNumberOfEntries(); i++) {
                       Course selectedCourse = CourseController.presetCourseList.get(i);
                       for (int x = 0; x < selectedCourse.getProgrammeList().getNumberOfEntries(); x++) {
                           if (selectedProgram.getProgCode().equals(selectedCourse.getProgrammeList().get(x).getProgCode())) {
                               if (selectedCourse.getProgrammeList().get(x).getTutorialGroupList() != null) {
                                   selectedCourse.getProgrammeList().get(x).getTutorialGroupList().add(newTutorialGroup);
                                   }
                               else{
                                   SortedLinkedListInterface<TutorialGroup> newTutorialGroupList = new SortedDoublyLinkedList<>();
                                   newTutorialGroupList.add(newTutorialGroup);
                                   selectedCourse.getProgrammeList().get(x).setTutorialGroupList(newTutorialGroupList);
                               }
                           }
                       }
                   }
                   System.out.println("----Tutorial Group Added----");
                   System.out.println("Programme: " + selectedProgram.getProgCode());
                   System.out.println("Tutorial Group: " + newTutorialGroupName);
                   System.out.println();
               }
           } else {
               System.out.println("Invalid program selection!");
           }
           boolean validChoice = false;
           while (!validChoice) {
               System.out.println("Do you want to create tutorial groups for another program? (Y/N): ");
               String choice = input.next();
               if (choice.length() == 1) {
                   char ch = choice.charAt(0);
                   if (ch == 'y' || ch == 'Y') {
                       validChoice = true;
                       createAnotherTutorialGroup = true;
                   } else if (ch == 'n' || ch == 'N') {
                       validChoice = true;
                       createAnotherTutorialGroup = false;
                   } else {
                       System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                   }
               } else {
                   System.out.println("Invalid input. Please enter 'Y' or 'N'.");
               }
           }
       }
   }

   private void removeTutorialGroup() {
       Scanner input = new Scanner(System.in);
       boolean removeAnotherTutorialGroup = true;




       while (removeAnotherTutorialGroup) {
           // Display programme list
           displayProgrammeList(pList);




           int programmeIndex = 0;
           boolean validInput = false;
           while (!validInput) {
               System.out.println("Please enter the index of the programme to remove a tutorial group from:");
               if (input.hasNextInt()) {
                   programmeIndex = input.nextInt();
                   input.nextLine(); // Consume the newline character
                   if (programmeIndex >= 1 && programmeIndex <= pList.getNumberOfEntries()) {
                       validInput = true;
                   } else {
                       System.out.println("Invalid index. Please enter a valid number between 1 and " + pList.getNumberOfEntries());
                   }
               } else {
                   System.out.println("Invalid input. Please enter a valid number.");
                   input.nextLine(); // Clear buffer
               }
           }




           Programme selectedProgram = pList.getEntry(programmeIndex);
           if (selectedProgram != null && selectedProgram.getTutorialGroupList() != null && !selectedProgram.getTutorialGroupList().isEmpty()) {
               System.out.println("You have selected: " + selectedProgram.getProgCode());




               // Display tutorial group list
               System.out.println();
               System.out.println("List of Tutorial Groups in " + selectedProgram.getProgCode());
               System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
               System.out.println("------------------------------------------------");
               SortedLinkedListInterface<TutorialGroup> tutorialGroupList = selectedProgram.getTutorialGroupList();
               for (int j = 1; j <= tutorialGroupList.getNumberOfEntries(); j++) {
                   TutorialGroup displayTutorialGroup = tutorialGroupList.getEntry(j);
                   System.out.printf("%d. %12s %18d\n", j, displayTutorialGroup.getTutorialGroupName(), displayTutorialGroup.getStudentList().getNumberOfEntries());
               }

               int tutorialGroupIndex = 0;
               System.out.println("Please enter the index of the tutorial group to remove: ");
               while (!input.hasNextInt() || (tutorialGroupIndex = input.nextInt()) < 1 || tutorialGroupIndex > tutorialGroupList.getNumberOfEntries()) {
                   System.out.println("Invalid index. Please enter a number between 1 and " + tutorialGroupList.getNumberOfEntries());
                   input.nextLine();
               }
               input.nextLine();


               TutorialGroup toRemove = tutorialGroupList.getEntry(tutorialGroupIndex);
               System.out.println("Are you sure you want to delete the tutorial group? (Y/N) ");
               String confirmDelete = input.nextLine();
               if (confirmDelete.equalsIgnoreCase("Y")) {
                   selectedProgram.removeTutorialGroup(toRemove);
                   //pList.replace(programmeIndex, selectedProgram);


                   for (int i = 0; i < CourseController.presetCourseList.getNumberOfEntries(); i++) {
                       Course selectedCourse = CourseController.presetCourseList.get(i);
                       for (int x = 0; x < selectedCourse.getProgrammeList().getNumberOfEntries(); x++) {
                           if (selectedProgram.getProgCode().equals(selectedCourse.getProgrammeList().get(x).getProgCode())) {
                               if (selectedCourse.getProgrammeList().get(x).getTutorialGroupList() != null) {
                                   for (int j = 0; j < selectedCourse.getProgrammeList().get(x).getTutorialGroupList().getNumberOfEntries(); j++) {
                                       if (selectedCourse.getProgrammeList().get(x).getTutorialGroupList().get(j).getTutorialGroupName().equals(toRemove.getTutorialGroupName())) {
                                           selectedCourse.getProgrammeList().get(x).getTutorialGroupList().remove(selectedCourse.getProgrammeList().get(x).getTutorialGroupList().get(j));
                                       }
                                   }
                               }
                           }
                       }
                   }
                   updateStudentTutorialGroupAfterDeleteTutorialGroup(toRemove);


                   System.out.println("Tutorial Group Deleted...");
               } else {
                   System.out.println("Delete cancelled");
               }
           } else {
               System.out.println("No tutorial groups available in " + selectedProgram.getProgCode());
           }

           System.out.println("Do you want to remove another tutorial group? (Y/N)");
           String choice = input.nextLine();
           removeAnotherTutorialGroup = choice.equalsIgnoreCase("Y");
       }
   }

   private void listAllTutorialGroup() {
       Scanner input = new Scanner(System.in);

       //display programme list
       displayProgrammeList(pList);

       int programmeIndex = 0;
       boolean validInput = false;
       while (!validInput) {
           try {
               System.out.println("Please enter the index of the programme to view tutorial group:");
               programmeIndex = input.nextInt();
               input.nextLine();
               if (programmeIndex < 1 || programmeIndex > pList.getNumberOfEntries()) {
                   System.out.println("Invalid index, Please enter a valid number between 1 and " + pList.getNumberOfEntries());
               } else {
                   validInput = true;
               }
           } catch (Exception e) {
               System.out.println("Invalid input. Please enter a valid number.");
               input.nextLine();
           }
       }

       Programme selectedProgram = pList.search(programmeIndex - 1);
       if (selectedProgram != null && selectedProgram.getTutorialGroupList() != null && !selectedProgram.getTutorialGroupList().isEmpty()) {
           System.out.println("You have selected: " + selectedProgram.getProgCode());

           // Display tutorial group list
           System.out.println();
           System.out.println("List of Tutorial Groups in " + selectedProgram.getProgCode());
           System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
           System.out.println("------------------------");
           SortedLinkedListInterface<TutorialGroup> tutorialGroupList = selectedProgram.getTutorialGroupList();
           for (int j = 1; j <= tutorialGroupList.getNumberOfEntries(); j++) {
               TutorialGroup displayTutorialGroup = tutorialGroupList.getEntry(j);
               System.out.printf("%d. %12s %18d\n", j, displayTutorialGroup.getTutorialGroupName(), displayTutorialGroup.getStudentList().getNumberOfEntries());
           }
           System.out.println("------------------------");
       } else {
           System.out.println("This programme does not have a tutorial group");
           return;
       }
       for (int i = 1; i <= selectedProgram.getTutorialGroupList().getNumberOfEntries(); i++) {
           if (sList.getEntry(i).getProgramme() != null) {
               if (sList.getEntry(i).getProgramme().compareTo(selectedProgram) == 0) {
                   sList.getEntry(i).setProgramme(selectedProgram);
               }
           }
       }
   }

   private void addStudentToTutorialGroup() {
       Scanner input = new Scanner(System.in);




       boolean addingStudents = true;
       while (addingStudents) {
           SortedLinkedListInterface<Student> studentWithoutTutorialGroupList = new SortedDoublyLinkedList<>();
           for (int i = 1; i <= sList.getNumberOfEntries(); i++) {
               Student student = sList.getEntry(i);
               if (student.getTutorialGroup() == null) {
                   studentWithoutTutorialGroupList.add(student);
               }
           }




           if (studentWithoutTutorialGroupList.isEmpty()) {
               System.out.println("All students are already assigned to tutorial groups or there are no students available.");
               break;
           }




           System.out.println("List of Unassigned Students:");
           System.out.printf("%-5s  %-15s  %-15s  %-15s%n", "No.", "Student ID", "First Name", "Last Name");
           for (int i = 0; i <= studentWithoutTutorialGroupList.getNumberOfEntries(); i++) {
               Student student = studentWithoutTutorialGroupList.get(i);
               if (student != null) {
                   System.out.printf("%2d. |  %-15s| %-15s |%-15s%n", i + 1, student.getStudentID(), student.getFirstName(), student.getLastName());
               }
           }




           boolean studentInput = false;
           int studentIndex = 0;




           while (!studentInput) {
               try {
                   System.out.println("Please enter the index of the student to add (Enter 0 to finish add):");
                   studentIndex = input.nextInt();
                   input.nextLine(); // Consume the newline character




                   if (studentIndex < 0 || studentIndex > studentWithoutTutorialGroupList.getNumberOfEntries()) {
                       System.out.println("Invalid index. Please enter a number between 0 and " + studentWithoutTutorialGroupList.getNumberOfEntries());
                   } else {
                       studentInput = true;
                   }
               } catch (Exception e) {
                   System.out.println("Invalid input. Please enter a valid integer index.");
                   input.nextLine(); // Consume the invalid input
               }
           }




           if (studentIndex == 0) {
               addingStudents = false;
           } else if (studentIndex >= 1 && studentIndex <= studentWithoutTutorialGroupList.getNumberOfEntries()) {
               Student selectedStudent = studentWithoutTutorialGroupList.getEntry(studentIndex);
               Programme originalProgramme = selectedStudent.getProgramme();




               if (originalProgramme != null) {
                   System.out.println(selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + " is in " + originalProgramme.getProgCode());
                   System.out.println();
                   System.out.println("List of Tutorial Groups in " + originalProgramme.getProgCode());
                   System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
                   System.out.println("------------------------------------------------");
                   SortedLinkedListInterface<TutorialGroup> displayTutorialGroupList = originalProgramme.getTutorialGroupList();
                   if (displayTutorialGroupList == null || displayTutorialGroupList.getNumberOfEntries() == 0) {
                       System.out.println("There are no tutorial groups in this programme.");
                       return;
                   } else {
                       for (int j = 1; j <= displayTutorialGroupList.getNumberOfEntries(); j++) {
                           TutorialGroup displayTutorialGroup = displayTutorialGroupList.getEntry(j);
                           System.out.printf("%d. %12s %18d\n", j, displayTutorialGroup.getTutorialGroupName(), displayTutorialGroup.getStudentList().getNumberOfEntries());
                       }
                   }
                   System.out.println("------------------------");




                   System.out.println("Please enter the index of the tutorial group to add the student:");
                   int selectedTutorialGroupIndex = input.nextInt();
                   if (selectedTutorialGroupIndex < 1 || selectedTutorialGroupIndex > displayTutorialGroupList.getNumberOfEntries()) {
                       System.out.println("Invalid tutorial group selection");
                       return;
                   } else {
                       TutorialGroup selectedTutorialGroup = displayTutorialGroupList.getEntry(selectedTutorialGroupIndex);
                       // check if adding this student will exceed the maximum limit for the tutorial group
                       if (selectedTutorialGroup.getStudentList().getNumberOfEntries() >= maxStudentInTutorialGroup) {
                           System.out.println("The tutorial group already has the maximum number of students.");
                           return;
                       }
                       updateStudentTutorialGroup(selectedStudent, selectedTutorialGroup);

                       Programme progTest = selectedStudent.getProgramme();


                       for (int i = 0; i < pList.getNumberOfEntries(); i++) {
                           if (pList.get(i).compareTo(progTest) == 0) {
                               SortedLinkedListInterface<TutorialGroup> tgList = pList.get(i).getTutorialGroupList();
                               for (int j = 0; j < tgList.getNumberOfEntries(); j++) {
                                   if (tgList.get(j).compareTo(selectedTutorialGroup) == 0) {
                                       tgList.get(j).getStudentList().add(selectedStudent);
                                   }
                               }
                           }
                       }

                       selectedStudent.setCourseList(originalProgramme.getCourseList());
                       selectedStudent.setProgramme(originalProgramme);
                       System.out.println("Student " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + " has been added to tutorial group " + selectedStudent.getTutorialGroup().getTutorialGroupName());
                   }
               } else {
                   // Student does not have a programme assigned
                   System.out.println(selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + " does not have any programme assigned.");
                   System.out.println("Displaying Available Programmes:");
                   displayProgrammeList(pList);

                   int selectedProgrammeIndex = 0;
                   boolean validInput = false;
                   while (!validInput) {
                       try {
                           System.out.println("Please enter the index of the programme to assign the student:");
                           selectedProgrammeIndex = input.nextInt();
                           input.nextLine();
                           if (selectedProgrammeIndex < 1 || selectedProgrammeIndex > pList.getNumberOfEntries()) {
                               System.out.println("Invalid index, Please enter a valid number between 1 and " + pList.getNumberOfEntries());
                           } else {
                               validInput = true;
                           }
                       } catch (Exception e) {
                           System.out.println("Invalid input. Please enter a valid number.");
                           input.nextLine();
                       }
                   }

                   if (selectedProgrammeIndex < 1 || selectedProgrammeIndex > pList.getNumberOfEntries()) {
                       System.out.println("Invalid programme selection");
                       return;
                   } else {
                       Programme selectedProgramme = pList.getEntry(selectedProgrammeIndex);
                       System.out.println();
                       System.out.println("List of Tutorial Groups in " + selectedProgramme.getProgCode());
                       System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
                       System.out.println("------------------------------------------------");
                       SortedLinkedListInterface<TutorialGroup> displayTutorialGroupList = selectedProgramme.getTutorialGroupList();

                       if (displayTutorialGroupList == null || displayTutorialGroupList.getNumberOfEntries() == 0) {
                           System.out.println("There are no tutorial groups in this programme.");
                           return;
                       } else {
                           for (int j = 1; j <= displayTutorialGroupList.getNumberOfEntries(); j++) {
                               TutorialGroup displayTutorialGroup = displayTutorialGroupList.getEntry(j);
                               System.out.printf("%d. %12s %18d\n", j, displayTutorialGroup.getTutorialGroupName(), displayTutorialGroup.getStudentList().getNumberOfEntries());
                           }
                       }
                       System.out.println("------------------------");

                       boolean tutorialGroupValidInput = false;
                       int selectedTutorialGroupIndex = 0;

                       while (!tutorialGroupValidInput) {
                           System.out.println("Please enter the index of the tutorial group to add the student: ");
                           while (!input.hasNextInt()) {
                               System.out.println("Invalid input. Please enter a valid integer index.");
                               input.nextLine(); // Clear the invalid input
                           }
                           selectedTutorialGroupIndex = input.nextInt();
                           input.nextLine(); // Consume the newline character

                           if (selectedTutorialGroupIndex < 1 || selectedTutorialGroupIndex > displayTutorialGroupList.getNumberOfEntries()) {
                               System.out.println("Invalid index. Please enter a number between 1 and " + displayTutorialGroupList.getNumberOfEntries());
                           } else {
                               tutorialGroupValidInput = true;
                           }
                       }

                       if (selectedTutorialGroupIndex < 1 || selectedTutorialGroupIndex > displayTutorialGroupList.getNumberOfEntries()) {
                           System.out.println("Invalid tutorial group selection");
                           return;
                       } else {
                           for (int i = 1; i <= selectedProgramme.getTutorialGroupList().getNumberOfEntries(); i++) {
                               if (sList.getEntry(i).getProgramme() != null) {
                                   if (sList.getEntry(i).getProgramme().compareTo(selectedProgramme) == 0) {
                                       sList.getEntry(i).setProgramme(selectedProgramme);
                                   }
                               }
                           }
                           TutorialGroup selectedTutorialGroup = displayTutorialGroupList.getEntry(selectedTutorialGroupIndex);
                           // Check if adding this student will exceed the maximum limit for the tutorial group
                           if (selectedTutorialGroup.getStudentList().getNumberOfEntries() >= maxStudentInTutorialGroup) {
                               System.out.println("The tutorial group already has the maximum number of students.");
                               return;
                           }

                           SortedLinkedListInterface<Course> selectedCourseList = new SortedDoublyLinkedList<>();

                           if (selectedProgramme.getCourseList() != null && selectedProgramme.getCourseList().getNumberOfEntries() > 0) {
                               for (int i = 0; i < selectedProgramme.getCourseList().getNumberOfEntries(); i++) {
                                   selectedCourseList.add(selectedProgramme.getCourseList().get(i));
                               }
                               selectedTutorialGroup.addStudentToTutorialGroup(selectedStudent);
                               updateStudentTutorialGroup(selectedStudent, selectedTutorialGroup);

                               selectedStudent.setCourseList(selectedCourseList);
                               selectedStudent.setProgramme(selectedProgramme);
                               GeneralUtil.updateStudentIDBasedOnProgramme(selectedStudent);


                               if(!selectedStudent.getStudentID().contains("WMD") && !selectedStudent.getStudentID().contains("WMR") ) {
                                   GeneralUtil.updateStudentIDBasedOnProgramme(selectedStudent);
                               }

                               SortedLinkedListInterface<Billing> billingList = new SortedDoublyLinkedList<>();
                               billingList.add(new Billing(selectedCourseList));
                               selectedStudent.setBillingList(billingList);
                           } else {
                               System.out.println("This programme does not have a course yet...");
                               System.out.println("Please select another programme to add the student");
                               return;
                           }
                           System.out.println("Student " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + " has been added to tutorial group " + selectedTutorialGroup.getTutorialGroupName());
                       }
                   }
               }
           }
       }
   }

   private void removeStudentFromTutorialGroup() {
       Scanner input = new Scanner(System.in);

       //display programme list
       displayProgrammeList(pList);

       int programmeIndex = 0;
       boolean validInput = false;
       while (!validInput) {
           try {
               System.out.println("Please enter the index of the programme to display  tutorial group:");
               programmeIndex = input.nextInt();
               input.nextLine();
               if (programmeIndex < 1 || programmeIndex > pList.getNumberOfEntries()) {
                   System.out.println("Invalid index, Please enter a valid number between 1 and " + pList.getNumberOfEntries());
               } else {
                   validInput = true;
               }
           } catch (Exception e) {
               System.out.println("Invalid input. Please enter a valid number.");
               input.nextLine();
           }
       }

       //if the selected program does not have tutorial group
       Programme selectedProgram = pList.getEntry(programmeIndex);
       if (selectedProgram != null && selectedProgram.getTutorialGroupList() != null && !selectedProgram.getTutorialGroupList().isEmpty()) {
           System.out.println("You have selected: " + selectedProgram.getProgCode());

           // Display tutorial group list
           System.out.println();
           System.out.println("List of Tutorial Groups in " + selectedProgram.getProgCode());
           System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
           System.out.println("------------------------------------------------");
           SortedLinkedListInterface<TutorialGroup> tutorialGroupList = selectedProgram.getTutorialGroupList();
           for (int j = 1; j <= tutorialGroupList.getNumberOfEntries(); j++) {
               TutorialGroup displayTutorialGroup = tutorialGroupList.getEntry(j);
               System.out.printf("%d. %12s %18d\n", j, displayTutorialGroup.getTutorialGroupName(), displayTutorialGroup.getStudentList().getNumberOfEntries());
           }

           boolean tutorialGroupValidInput = false;
           int tutorialGroupIndex = 0;


           while (!tutorialGroupValidInput) {
               try {
                   System.out.println("Please enter the index of the tutorial group to remove the students:");
                   tutorialGroupIndex = input.nextInt();
                   input.nextLine(); // Consume the newline character


                   if (tutorialGroupIndex < 1 || tutorialGroupIndex > tutorialGroupList.getNumberOfEntries()) {
                       System.out.println("Invalid index. Please enter a number between 1 and " + tutorialGroupList.getNumberOfEntries());
                   } else {
                       tutorialGroupValidInput = true;
                   }
               } catch (Exception e) {
                   System.out.println("Invalid input. Please enter a valid integer index.");
                   input.nextLine();
               }
           }


           if (tutorialGroupIndex >= 1 && tutorialGroupIndex <= tutorialGroupList.getNumberOfEntries()) {
               TutorialGroup selectedTutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);

               if (selectedTutorialGroup.getStudentList().getNumberOfEntries() == 0) {
                   System.out.println("There are no students in the selected tutorial group.");
                   return;
               }

               System.out.println("List of students in " + selectedTutorialGroup.getTutorialGroupName() + ":");
               System.out.printf("%-5s  %-15s  %-15s  %-15s%n", "No.", "Student ID", "First Name", "Last Name");
               System.out.println("----------------------------------------------");
               for (int i = 0; i < selectedTutorialGroup.getStudentList().getNumberOfEntries(); i++) {
                   Student student = selectedTutorialGroup.getStudentList().get(i);
                   if (student != null) {
                       System.out.printf("%2d. |  %-15s| %-15s |%-15s%n", i + 1, student.getStudentID(), student.getFirstName(), student.getLastName());
                   }
               }

               boolean studentInput = false;
               int removeIndex = 0;

               while (!studentInput) {
                   try {
                       System.out.println("Please enter the index of the student you wish you remove: ");
                       removeIndex = input.nextInt();
                       input.nextLine(); // Consume the newline character

                       if (removeIndex < 1 || removeIndex > selectedTutorialGroup.getStudentList().getNumberOfEntries()) {
                           System.out.println("Invalid index. Please enter a number between 1 and " + selectedTutorialGroup.getStudentList().getNumberOfEntries());
                       } else {
                           studentInput = true;
                       }
                   } catch (Exception e) {
                       System.out.println("Invalid input. Please enter a valid integer index.");
                       input.nextLine();
                   }
               }

               if (removeIndex >= 1 && removeIndex <= selectedTutorialGroup.getStudentList().getNumberOfEntries()) {
                   Student removedStudent = selectedTutorialGroup.getStudentList().getEntry(removeIndex);
                   System.out.println(removedStudent.getFirstName() + " " + removedStudent.getLastName() + " is removed from " + removedStudent.getTutorialGroup().getTutorialGroupName());
//                       removeStudentFromTutorialGroup(removedStudent, selectedTutorialGroup);
                   selectedTutorialGroup.removeStudentFromTutorialGroup(removedStudent);
                   removedStudent.setTutorialGroup(null);
//                       updateStudentTutorialGroupAfterDeleteTutorialGroup(selectedTutorialGroup);
               } else {
                   System.out.println("Invalid student selection");
               }
           } else {
               System.out.println("Invalid tutorial group selection.");
           }
       } else {
           System.out.println("No tutorial group within " + selectedProgram.getProgCode());
       }
   }

   private void changeStudentTutorialGroup() {
       Scanner input = new Scanner(System.in);


       // Display list of programmes
       displayProgrammeList(pList);




       int programmeIndex = 0;
       boolean validInput = false;
       while (!validInput) {
           try {
               System.out.println("Please enter the index of the programme to display tutorial groups:");
               programmeIndex = input.nextInt();
               input.nextLine();
               if (programmeIndex < 1 || programmeIndex > pList.getNumberOfEntries()) {
                   System.out.println("Invalid index, Please enter a valid number between 1 and " + pList.getNumberOfEntries());
               } else {
                   validInput = true;
               }
           } catch (Exception e) {
               System.out.println("Invalid input. Please enter a valid number.");
               input.nextLine();
           }
       }

       Programme selectedProgram = pList.getEntry(programmeIndex);
       if (selectedProgram != null && selectedProgram.getTutorialGroupList() != null && !selectedProgram.getTutorialGroupList().isEmpty()) {
           if (selectedProgram.getTutorialGroupList().getNumberOfEntries() > 1) {
               System.out.println("You have selected: " + selectedProgram.getProgCode());


               // Display tutorial group list
               System.out.println();
               System.out.println("List of Tutorial Groups in " + selectedProgram.getProgCode());
               System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
               System.out.println("------------------------------------------------");
               SortedLinkedListInterface<TutorialGroup> tutorialGroupList = selectedProgram.getTutorialGroupList();
               for (int j = 1; j <= tutorialGroupList.getNumberOfEntries(); j++) {
                   TutorialGroup displayTutorialGroup = tutorialGroupList.getEntry(j);
                   System.out.printf("%d. %12s %18d\n", j, displayTutorialGroup.getTutorialGroupName(), displayTutorialGroup.getStudentList().getNumberOfEntries());
               }
               System.out.println("------------------------");

               for (int i = 1; i <= selectedProgram.getTutorialGroupList().getNumberOfEntries(); i++) {
                   if (sList.getEntry(i).getProgramme() != null) {
                       if (sList.getEntry(i).getProgramme().compareTo(selectedProgram) == 0) {
                           sList.getEntry(i).setProgramme(selectedProgram);
                       }
                   }
               }

               boolean tutorialGroupValidInput = false;
               int tutorialGroupIndex = 0;

               while (!tutorialGroupValidInput) {
                   try {
                       System.out.println("Please enter the index of the tutorial group to view the list of students:");
                       tutorialGroupIndex = input.nextInt();
                       input.nextLine(); // Consume the newline character

                       if (tutorialGroupIndex < 1 || tutorialGroupIndex > tutorialGroupList.getNumberOfEntries()) {
                           System.out.println("Invalid index. Please enter a number between 1 and " + tutorialGroupList.getNumberOfEntries());
                       } else if (tutorialGroupList.getEntry(tutorialGroupIndex).getStudentList().getNumberOfEntries() >= maxStudentInTutorialGroup) {
                           System.out.println("The tutorial group already has the maximum number of students.");
                       }
                       else {
                           tutorialGroupValidInput = true;
                       }
                   } catch (Exception e) {
                       System.out.println("Invalid input. Please enter a valid integer index.");
                       input.nextLine();
                   }
               }

               if (tutorialGroupIndex >= 1 && tutorialGroupIndex <= tutorialGroupList.getNumberOfEntries()) {
                   TutorialGroup selectedTutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);

                   if (selectedTutorialGroup.getStudentList().getNumberOfEntries() == 0) {
                       System.out.println("There are no students in the selected tutorial group.");
                       return;
                   }
                   System.out.println("List of students in " + selectedTutorialGroup.getTutorialGroupName() + ":");
                   System.out.printf("%-5s  %-15s  %-15s  %-15s%n", "No.", "Student ID", "First Name", "Last Name");
                   System.out.println("----------------------------------------------");
                   for (int i = 0; i < selectedTutorialGroup.getStudentList().getNumberOfEntries(); i++) {
                       Student student = selectedTutorialGroup.getStudentList().get(i);
                       if (student != null) {
                           System.out.printf("%2d. |  %-15s| %-15s |%-15s%n", i + 1, student.getStudentID(), student.getFirstName(), student.getLastName());
                       }
                   }

                   boolean studentInput = false;
                   int studentIndex = 0;

                   while (!studentInput) {
                       try {
                           System.out.println("Select the index of the student to change the tutorial group: ");
                           studentIndex = input.nextInt();
                           input.nextLine(); // Consume the newline character

                           if (studentIndex < 1 || studentIndex > selectedTutorialGroup.getStudentList().getNumberOfEntries()) {
                               System.out.println("Invalid index. Please enter a number between 1 and " + selectedTutorialGroup.getStudentList().getNumberOfEntries());
                           } else {
                               studentInput = true;
                           }
                       } catch (Exception e) {
                           System.out.println("Invalid input. Please enter a valid integer index.");
                           input.nextLine();
                       }
                   }

                   //remove student from current tutorial group
                   Student studentToChange = selectedTutorialGroup.getStudentList().getEntry(studentIndex);
//                   selectedTutorialGroup.removeStudentFromTutorialGroup(studentToChange);

                   //select a new tutorial group;
                   System.out.println();


                   System.out.println("Select the new tutorial group for " + studentToChange.getFirstName() + " " + studentToChange.getLastName());
                   System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
                   System.out.println("------------------------------------------------");
                   for (int j = 1; j <= tutorialGroupList.getNumberOfEntries(); j++) {
                       TutorialGroup tutorialGroup = tutorialGroupList.getEntry(j);
                       System.out.printf("%d. %12s %18d\n", j, tutorialGroup.getTutorialGroupName(), tutorialGroup.getStudentList().getNumberOfEntries());
                       System.out.println();
                   }

                   boolean newTutorialGroupValidInput = false;
                   int newGroupIndex = 0;




                   while (!newTutorialGroupValidInput) {
                       try {
                           System.out.println("Please enter the index of the tutorial group to move the student:");
                           newGroupIndex = input.nextInt();
                           input.nextLine(); // Consume the newline character

                           // Check if the student is selecting their current tutorial group
                           while (newGroupIndex == tutorialGroupIndex) {
                               System.out.println("You are already in this tutorial group. Please select a different tutorial group.");
                               System.out.println("Select the new tutorial group for " + studentToChange.getFirstName() + " " + studentToChange.getLastName());
                               System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
                               System.out.println("------------------------------------------------");
                               for (int j = 1; j <= tutorialGroupList.getNumberOfEntries(); j++) {
                                   TutorialGroup tutorialGroup = tutorialGroupList.getEntry(j);
                                   System.out.printf("%d. %12s %18d", j, tutorialGroup.getTutorialGroupName(), tutorialGroup.getStudentList().getNumberOfEntries());
                                   System.out.println();
                               }
                               newGroupIndex = input.nextInt();
                           }

                           if (newGroupIndex < 1 || newGroupIndex > tutorialGroupList.getNumberOfEntries()) {
                               System.out.println("Invalid index. Please enter a number between 1 and " + tutorialGroupList.getNumberOfEntries());
                           } else if (tutorialGroupList.getEntry(newGroupIndex).getStudentList().getNumberOfEntries() >= maxStudentInTutorialGroup) {
                               System.out.println("The tutorial group already has the maximum number of students.");
                           } else if (newGroupIndex == tutorialGroupIndex) {
                               System.out.println("This student already in this tutorial group. Please select a different tutorial group.");
                           }else {
                               newTutorialGroupValidInput = true;
                           }
                       } catch (Exception e) {
                           System.out.println("Invalid input. Please enter a valid integer index.");
                           input.nextLine();
                       }
                   }


                   selectedTutorialGroup.removeStudentFromTutorialGroup(studentToChange);

                   // Add student to new group
                   TutorialGroup newTutorialGroup = selectedProgram.getTutorialGroupList().getEntry(newGroupIndex);
                   newTutorialGroup.addStudentToTutorialGroup(studentToChange);
                   // Update the student to new group
                   updateStudentTutorialGroup(studentToChange, newTutorialGroup);

                   System.out.println("Student " + studentToChange.getFirstName() + " " + studentToChange.getLastName() + " has been moved to " + newTutorialGroup.getTutorialGroupName());
               } else {
                   System.out.println("Invalid tutorial group selection.");
               }
           } else {
               System.out.println("This programme must have at least 2 tutorial group!");
           }
       } else {
           System.out.println("No tutorial group within " + selectedProgram.getProgCode());
       }
   }

   private void listStudentFromTutorialGroup() {
       Scanner input = new Scanner(System.in);

       // Display list of programmes
       displayProgrammeList(pList);

       int programmeIndex = 0;
       boolean validInput = false;
       while (!validInput) {
           try {
               System.out.println("Please enter the index of the programme to display tutorial groups:");
               programmeIndex = input.nextInt();
               input.nextLine();
               if (programmeIndex < 1 || programmeIndex > pList.getNumberOfEntries()) {
                   System.out.println("Invalid index, Please enter a valid number between 1 and " + pList.getNumberOfEntries());
               } else {
                   validInput = true;
               }
           } catch (Exception e) {
               System.out.println("Invalid input. Please enter a valid number.");
               input.nextLine();
           }
       }



       if (programmeIndex >= 1 && programmeIndex <= pList.getNumberOfEntries()) {
           Programme selectedProgram = pList.getEntry(programmeIndex);
           if (!selectedProgram.getTutorialGroupList().isEmpty()) {
               System.out.println("Selected Programme: " + selectedProgram.getProgCode());




               System.out.println();
               System.out.println("List of Tutorial Groups in " + selectedProgram.getProgCode());
               System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
               System.out.println("------------------------------------------------");
               SortedLinkedListInterface<TutorialGroup> tutorialGroupList = selectedProgram.getTutorialGroupList();
               if (tutorialGroupList == null || tutorialGroupList.getNumberOfEntries() == 0) {
                   System.out.println("There are no tutorial groups in this programme.");
                   return;
               } else {
                   for (int j = 1; j <= tutorialGroupList.getNumberOfEntries(); j++) {
                       TutorialGroup displayTutorialGroup = tutorialGroupList.getEntry(j);
                       System.out.printf("%d. %12s %18d\n", j, displayTutorialGroup.getTutorialGroupName(), displayTutorialGroup.getStudentList().getNumberOfEntries());
                   }
               }
               for (int i = 1; i <= selectedProgram.getTutorialGroupList().getNumberOfEntries(); i++) {
                   if (sList.getEntry(i).getProgramme() != null) {
                       if (sList.getEntry(i).getProgramme().compareTo(selectedProgram) == 0) {
                           sList.getEntry(i).setProgramme(selectedProgram);
                       }
                   }
               }


               boolean tutorialGroupValidInput = false;
               int tutorialGroupIndex = 0;

               while (!tutorialGroupValidInput) {
                   try {
                       System.out.println("Please enter the index of the tutorial group to view the list of students:");
                       tutorialGroupIndex = input.nextInt();
                       input.nextLine(); // Consume the newline character


                       if (tutorialGroupIndex < 1 || tutorialGroupIndex > tutorialGroupList.getNumberOfEntries()) {
                           System.out.println("Invalid index. Please enter a number between 1 and " + tutorialGroupList.getNumberOfEntries());
                       } else {
                           tutorialGroupValidInput = true;
                       }
                   } catch (Exception e) {
                       System.out.println("Invalid input. Please enter a valid integer index.");
                       input.nextLine();
                   }
               }

               if (tutorialGroupIndex >= 1 && tutorialGroupIndex <= tutorialGroupList.getNumberOfEntries()) {
                   TutorialGroup selectedTutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);

                   if (selectedTutorialGroup.getStudentList().getNumberOfEntries() == 0) {
                       System.out.println("There are no students in the selected tutorial group.");
                       return;
                   }
                   System.out.println("List of students in " + selectedTutorialGroup.getTutorialGroupName() + ":");
                   System.out.printf("%-5s  %-15s  %-15s  %-15s%n", "No.", "Student ID", "First Name", "Last Name");
                   System.out.println("----------------------------------------------");
                   for (int i = 0; i < selectedTutorialGroup.getStudentList().getNumberOfEntries(); i++) {
                       Student student = selectedTutorialGroup.getStudentList().get(i);
                       if (student != null) {
                           System.out.printf("%2d. |  %-15s| %-15s |%-15s%n", i + 1, student.getStudentID(), student.getFirstName(), student.getLastName());
                       }
                   }
               } else {
                   System.out.println("Invalid tutorial group selection.");
               }
           } else {
               System.out.println("No tutorial groups within " + selectedProgram.getProgCode());
           }
       } else {
           System.out.println("Invalid program selection.");
       }
   }

   private void mergeTutorialGroup() {
       Scanner input = new Scanner(System.in);

       int criteriaChoice;
       do {
           System.out.println("Please select a criteria for merging tutorial groups:");
           System.out.println("1. Merge Tutorial Group ");
//            System.out.println("2. Merge based on Tutor");
           System.out.println("0. Exit");
           System.out.println("Enter your choice: ");
           criteriaChoice = input.nextInt();
           input.nextLine(); // Consume newline character

           switch (criteriaChoice) {
               case 0:
                   break;
               case 1:
                   // Display list of programmes
                   displayProgrammeList(pList);

                   int programmeIndex = 0;
                   boolean validInput = false;
                   while (!validInput) {
                       System.out.println("Please enter the index of the programme to merge tutorial groups:");
                       if (input.hasNextInt()) {
                           programmeIndex = input.nextInt();
                           input.nextLine(); // Consume newline character
                           if (programmeIndex >= 1 && programmeIndex <= pList.getNumberOfEntries()) {
                               validInput = true;
                           } else {
                               System.out.println("Invalid index, please enter a number between 1 and " + pList.getNumberOfEntries());
                           }
                       } else {
                           System.out.println("Invalid input. Please enter a valid number.");
                           input.nextLine(); // Clear incorrect input
                       }
                   }

                   SortedLinkedListInterface<TutorialGroup> selectedTutorialGroupList = new SortedDoublyLinkedList<>();

                   Programme selectedProgram = pList.getEntry(programmeIndex);
                   if (selectedProgram != null && selectedProgram.getTutorialGroupList() != null && !selectedProgram.getTutorialGroupList().isEmpty()) {
                       if (selectedProgram.getTutorialGroupList().getNumberOfEntries() >= 2) {
                           if (!selectedProgram.getTutorialGroupList().isEmpty()) {

                               System.out.println("Selected Programme: " + selectedProgram.getProgCode());

                               boolean addingMoreGroups = true;
                               while (addingMoreGroups) {
                                   System.out.println();
                                   System.out.println("List of Tutorial Groups in " + selectedProgram.getProgCode() + ":");
                                   System.out.printf("%-5s %-15s %-15s%n", "No.", "Tutorial Group", "Current Number of Students");
                                   System.out.println("------------------------------------------------");

                                   //get the selected program's tutorial group to displayTutorialGroup list
                                   SortedLinkedListInterface<TutorialGroup> displayTutorialGroupList = selectedProgram.getTutorialGroupList();
                                   if (displayTutorialGroupList.getNumberOfEntries() >= 2) {
                                       for (int j = 1; j <= displayTutorialGroupList.getNumberOfEntries(); j++) {
                                           TutorialGroup displayTutorialGroup = displayTutorialGroupList.getEntry(j);
                                           System.out.printf("%d. %12s %18d\n", j, displayTutorialGroup.getTutorialGroupName(), displayTutorialGroup.getStudentList().getNumberOfEntries());
                                       }
                                   }

                                   if (displayTutorialGroupList.isEmpty()) {
                                       System.out.println("There are no available tutorial groups to merge.");
                                       break;
                                   }

                                   System.out.println("Select the index of the tutorial group to merge (Enter 0 to finish merge): ");
                                   int tutorialGroupIndex = input.nextInt();
                                   input.nextLine(); //Consume newline character

                                   // If user selects 0, exit
                                   if (tutorialGroupIndex == 0) {
                                       addingMoreGroups = false;
                                   } else if (tutorialGroupIndex >= 1 && tutorialGroupIndex <= displayTutorialGroupList.getNumberOfEntries()) {
                                       TutorialGroup selectedTutorialGroup = displayTutorialGroupList.getEntry(tutorialGroupIndex);

                                       boolean alreadySelected = false;

                                       // Check if the selected tutorial group has already been selected
                                       for (int i = 1; i <= selectedTutorialGroupList.getNumberOfEntries(); i++) {
                                           if (selectedTutorialGroupList.getEntry(i).equals(selectedTutorialGroup)) {
                                               alreadySelected = true;
                                               break;
                                           }
                                       }

                                       if (alreadySelected) {
                                           System.out.println("This tutorial group has already been selected. Please choose another one.");
//                                            continue; // Skip the rest of the loop iteration
                                       } else {
                                           selectedTutorialGroupList.add(selectedTutorialGroup); // Add the selected group to the list
                                       }
                                   }
                               }
                           }

                           // Check if there are enough groups selected for merging
                           if (selectedTutorialGroupList.getNumberOfEntries() < 2) {
                               System.out.println(" Please select at least two groups for merging.");
                               break; // Exit the switch case
                           }


                           // Check if selected groups have students and the total number of students doesn't exceed 25
                           boolean anyGroupHasStudents = false;
                           int totalStudents = 0;
                           for (int i = 1; i <= selectedTutorialGroupList.getNumberOfEntries(); i++) {
                               TutorialGroup group = selectedTutorialGroupList.getEntry(i);
                               if (!group.getStudentList().isEmpty()) {
                                   anyGroupHasStudents = true;
                                   totalStudents += group.getStudentList().getNumberOfEntries();
                               }
                           }

                           if (!anyGroupHasStudents) {
                               System.out.println("None of the selected tutorial groups have students. Please select groups with students for merging.");
                               break; // Exit the switch case
                           }

                           if (totalStudents > 25) {
                               System.out.println("The total number of students in the selected tutorial groups exceeds 25. Please select fewer groups or groups with fewer students for merging.");
                               break;
                           }

                           String newTutorialGroupName = "";
                           for (int i = 1; i <= selectedTutorialGroupList.getNumberOfEntries(); i++) {
                               TutorialGroup group = selectedTutorialGroupList.getEntry(i);
                               if (i == 1) {
                                   newTutorialGroupName += group.getTutorialGroupName();
                               } else {
                                   newTutorialGroupName += "+" + group.getTutorialGroupName();
                               }
                           }

                           TutorialGroup mergedTutorialGroup = new TutorialGroup();

                           for (int i = 1; i <= selectedTutorialGroupList.getNumberOfEntries(); i++) {
                               TutorialGroup group = selectedTutorialGroupList.getEntry(i);
                               SortedLinkedListInterface<Student> students = group.getStudentList();
                               for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                                   Student student = students.getEntry(j);
                                   mergedTutorialGroup.addStudentToTutorialGroup(student);
                                   updateStudentTutorialGroup(student, mergedTutorialGroup);
                               }
                           }
                           mergedTutorialGroup.setTutorialGroupName(newTutorialGroupName);

                           // Remove selected groups from the program's list
                           for (int i = 1; i <= selectedTutorialGroupList.getNumberOfEntries(); i++) {
                               TutorialGroup group = selectedTutorialGroupList.getEntry(i);
                               selectedProgram.removeTutorialGroup(group);
                           }
                           // Add the merged tutorial group to the program's list
                           selectedProgram.addTutorialGroup(mergedTutorialGroup);
                           mergedTutorialGroup.setProgramme(selectedProgram);
                           //pList.replace(programmeIndex, selectedProgram);

                           System.out.println("Students have been successfully moved to the merged tutorial group.");

                           // Update student's programme information
                           for (int i = 1; i <= selectedProgram.getTutorialGroupList().getNumberOfEntries(); i++) {
                               if (sList.getEntry(i).getProgramme() != null) {
                                   if (sList.getEntry(i).getProgramme().compareTo(selectedProgram) == 0) {
                                       sList.getEntry(i).setProgramme(selectedProgram);
                                   }
                               }
                           }
                       }
                   } else {
                       System.out.println("This programme must have at least 2 tutorial group!");
                   }
           }


       } while (criteriaChoice != 0);
   }

   private void generateSummaryReport() {
       Scanner input = new Scanner(System.in);




       System.out.println("====================================================");
       System.out.println("||***    Tutorial Group Summary Report Menu   *** ||");
       System.out.println("====================================================");
       System.out.println("|| 1. Tutorial Group Summary Report               ||");
       System.out.println("|| 2. Generate Tutorial Group Analytics Report    ||");
       System.out.println("|| 0. Return to Tutorial Group Menu               ||");
       System.out.println("====================================================");
       System.out.print("Enter your choice: ");
       int reportChoice = input.nextInt();
       input.nextLine(); // Consume the newline character




       switch (reportChoice) {
           case 1:
               printReportHeader();
               int counterReport1 = 1;
               System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t==================================================================================================================================================");
               System.out.printf("%62s %40s %30s %23s %27s %n", "Programme Name", "Programme Code", "Number of Tutorial Groups", "Total Students", "Capacity Utilization");
               System.out.println("\t\t\t\t\t\t\t\t\t\t\t==================================================================================================================================================");




               for (int i = 1; i <= pList.getNumberOfEntries(); i++) {
                   Programme programme = pList.getEntry(i);
                   SortedLinkedListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupList();




                   int tutorialGroupCount = tutorialGroups.getNumberOfEntries();
                   int totalStudentsinTutorialGroup = 0;
                   double capacityUtilization = 0.0;




                   if (tutorialGroupCount > 0) {
                       for (int j = 1; j <= tutorialGroups.getNumberOfEntries(); j++) {
                           TutorialGroup group = tutorialGroups.getEntry(j);
                           int tutorialGroupSize = group.getStudentList().getNumberOfEntries();
                           totalStudentsinTutorialGroup += tutorialGroupSize;
                           capacityUtilization += (double) tutorialGroupSize / maxStudentInTutorialGroup;
                       }




                       capacityUtilization = (capacityUtilization / tutorialGroupCount) * 100;
                   }




                   System.out.printf("%46d. %-40s %-30s %-30d %-22d %-1.2f %-6s %n",
                           counterReport1++,
                           programme.getProgName() + " " + programme.getProgSemester(),
                           programme.getProgCode(),
                           tutorialGroupCount,
                           totalStudentsinTutorialGroup,
                           capacityUtilization,
                           "%");
               }




               int totalProgrammes = pList.getNumberOfEntries();
               int totalStudents = 0;




               for (int i = 1; i <= pList.getNumberOfEntries(); i++) {
                   Programme programme = pList.getEntry(i);
                   SortedLinkedListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupList();
                   for (int j = 1; j <= tutorialGroups.getNumberOfEntries(); j++) {
                       TutorialGroup group = tutorialGroups.getEntry(j);
                       totalStudents += group.getStudentList().getNumberOfEntries();
                   }
               }




               int highestStudent = 0;
               String highestStudentTutorialGroup = "";
               int lowestStudent = Integer.MAX_VALUE;
               String lowestStudentTutorialGroup = "";




               System.out.println();




               System.out.printf("%49s %d %s : %d %s%n", "Total", totalProgrammes, "Programmes", totalStudents, "Students");
               System.out.println("\t\t\t\t\t\t\t\t\t\t\t==================================================================================================================================================");




               for (int i = 1; i <= pList.getNumberOfEntries(); i++) {
                   Programme programme = pList.getEntry(i);
                   SortedLinkedListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupList();
                   for (int j = 1; j <= tutorialGroups.getNumberOfEntries(); j++) {
                       TutorialGroup tutorialGroup = tutorialGroups.getEntry(j);




                       // Check if group or its student list is null before accessing
                       if (tutorialGroup != null && tutorialGroup.getStudentList() != null) {
                           int studentCount = tutorialGroup.getStudentList().getNumberOfEntries();




                           //check for highest
                           if (studentCount > highestStudent) {
                               highestStudent = studentCount;
                               highestStudentTutorialGroup = tutorialGroup.getTutorialGroupName();
                           }




                           //check for lowest
                           if (studentCount < lowestStudent && studentCount > 0) {
                               lowestStudent = studentCount;
                               lowestStudentTutorialGroup = tutorialGroup.getTutorialGroupID();
                           }
                       }
                   }
               }




               if (highestStudent > 0) {
                   System.out.printf("%86s", "HIGHEST STUDENT ENROLLMENT TUTORIAL GROUP\n");
                   System.out.printf("%53s with %d students.\n",
                           highestStudentTutorialGroup, highestStudent);
               } else {
                   System.out.printf("\n%86s", "HIGHEST STUDENT ENROLLMENT TUTORIAL GROUP\n");
               }
               System.out.printf("\n%86s", "-----------------------------------------\n");
               if (lowestStudent != Integer.MAX_VALUE) {
                   System.out.printf("%85s", "LOWEST STUDENT ENROLLMENT TUTORIAL GROUP\n");
                   System.out.printf("%51s with %d students.\n",
                           lowestStudentTutorialGroup, lowestStudent);
               } else {
                   System.out.printf("%85s", "LOWEST STUDENT ENROLLMENT TUTORIAL GROUP\n");
               }
               System.out.printf("\n\n%96s", "[NOTE: 0 STUDENTS IN TUTORIAL GROUP IS NOT COUNTED]\n\n");
               printReportFooter();
               break;
           case 2:
               printReportHeader();
               int counterReport2 = 1;
               System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t==================================================================================================================================================");
               System.out.printf("%70s %52s %61s%n", "Tutorial Group Name", "Number of Students", "Average Course Taken per Student");
               System.out.println("\t\t\t\t\t\t\t\t\t\t\t==================================================================================================================================================");




               double highestAvgCourses = 0;
               String highestAvgCoursesGroup = "";
               double lowestAvgCourses = Double.MAX_VALUE;
               String lowestAvgCoursesGroup = "";




               for (int i = 1; i <= pList.getNumberOfEntries(); i++) {
                   Programme programme = pList.getEntry(i);
                   SortedLinkedListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupList();




                   for (int j = 1; j <= tutorialGroups.getNumberOfEntries(); j++) {
                       TutorialGroup group = tutorialGroups.getEntry(j);
                       int groupSize = group.getStudentList().getNumberOfEntries();
                       int totalCourses = 0;




                       for (int k = 1; k <= groupSize; k++) {
                           Student student = group.getStudentList().getEntry(k);
                           totalCourses += student.getCourseList().getNumberOfEntries();
                       }




                       double avgCoursesPerStudent = groupSize > 0 ? (double) totalCourses / groupSize : 0;




                       if (avgCoursesPerStudent > highestAvgCourses) {
                           highestAvgCourses = avgCoursesPerStudent;
                           highestAvgCoursesGroup = group.getTutorialGroupName();
                       }
                       if (avgCoursesPerStudent < lowestAvgCourses && avgCoursesPerStudent > 0) {
                           lowestAvgCourses = avgCoursesPerStudent;
                           lowestAvgCoursesGroup = group.getTutorialGroupName();
                       }




                       System.out.printf("%49d. %-40s %22d %55.2f%n",
                               counterReport2++,
                               group.getTutorialGroupName(),
                               groupSize,
                               avgCoursesPerStudent);
                   }
               }
               int totalTutorialGroup = 0;
               int totalStudentInTutorialGroup = 0;




               for (int o = 1; o <= pList.getNumberOfEntries(); o++) {
                   Programme reportProgramme = pList.getEntry(o);
                   totalTutorialGroup += reportProgramme.getTutorialGroupList().getNumberOfEntries();
                   SortedLinkedListInterface<TutorialGroup> tutorialGroupListReport = reportProgramme.getTutorialGroupList();
                   for (int j = 1; j <= tutorialGroupListReport.getNumberOfEntries(); j++) {
                       TutorialGroup reportTutorialGroup = tutorialGroupListReport.getEntry(j);
                       totalStudentInTutorialGroup += reportTutorialGroup.getStudentList().getNumberOfEntries();
                   }
               }
               System.out.println();
               System.out.printf("%49s %d %s %s %d %s%n", "Total", totalTutorialGroup, "Tutorial Groups", "with total", totalStudentInTutorialGroup, "Students");
               System.out.println("\t\t\t\t\t\t\t\t\t\t\t==================================================================================================================================================");




               if (highestAvgCourses > 0) {
                   System.out.printf("%104s%n", "TUTORIAL GROUP WITH THE HIGHEST AVERAGE COURSES PER STUDENT:");
                   System.out.printf("%53s with %.2f average courses per student.\n",
                           highestAvgCoursesGroup, highestAvgCourses);
               } else {
                   System.out.printf("\n%108s%n", "TUTORIAL GROUP WITH THE HIGHEST AVERAGE COURSES PER STUDENT: N/A");
               }
               System.out.printf("\n%108s", "---------------------------------------------------------------\n");




               if (lowestAvgCourses != Double.MAX_VALUE) {
                   System.out.printf("%104s%n", "TUTORIAL GROUP WITH THE LOWEST AVERAGE COURSES PER STUDENT:");
                   System.out.printf("%53s with %.2f average courses per student.\n",
                           lowestAvgCoursesGroup, lowestAvgCourses);
               } else {
                   System.out.printf("%108s%n", "TUTORIAL GROUP WITH THE LOWEST AVERAGE COURSES PER STUDENT: N/A\n");
               }




               System.out.printf("\n%101s", "[NOTE: TUTORIAL GROUPS WITH 0 STUDENTS ARE NOT COUNTED]\n\n");




               printReportFooter();
               break;
           case 0:
               return;
           default:
               System.out.println("Invalid choice! Please try again!");
       }
   }

   private void printReportHeader() {
       System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t==================================================================================================================================================");
       System.out.printf("%143s\n", "TUNkU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
       System.out.printf("%133s\n\n", "*** TUTORIAL GROUP MANAGEMENT SUBSYSTEM ***");
       System.out.printf("%127s\n", "TUTORIAL GROUP SUMMARY REPORT");
       System.out.printf("%132s", "--------------------------------------");




       LocalDateTime now = LocalDateTime.now();




       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, hh:mma");




       String formattedDateTime = now.format(formatter);
       System.out.printf("\n\n%58s %s %n", "Generated at: ", formattedDateTime);
   }

   private void printReportFooter() {
       System.out.printf("%132s", "END OF TUTORIAL GROUP SUMMARY REPORT");
       System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t==================================================================================================================================================");
       System.out.printf("%130s", "PRESS <ENTER> key to continue...");
       Scanner scanner = new Scanner(System.in);
       scanner.nextLine();
   }

   private void displayProgrammeList(SortedLinkedListInterface<Programme> pList) {
       if (pList.isEmpty()) {
           System.out.println("No programmes are available.");
           return;
       }




       System.out.println("List of Programmes:");
       Programme.printHeader();
       System.out.println("------------------------------------------------------");
       for (int i = 1; i <= pList.getNumberOfEntries(); i++) {
           Programme displayProgramme = pList.getEntry(i);
           System.out.printf("%d.    %-30s | %-20s\n", i, displayProgramme.getProgName(), displayProgramme.getProgCode());
           System.out.println("--------------------------------------------------");
       }
   }

   private void displayTutorForATutorialGroup() {
       Scanner input = new Scanner(System.in);

       displayProgrammeList(pList);
       System.out.println("Please select a programme (eg.1,2,3):");
       int selectProgramme = input.nextInt();
       while (selectProgramme > pList.getNumberOfEntries() || selectProgramme <= 0) {
           System.out.print("\nInvalid number please try again");
           System.out.print("\nRe-Enter number(eg.1,2,3): ");
           selectProgramme = input.nextInt();
       }


       System.out.print("\n**************************************************\n");
       System.out.printf("%-10s %-20s%-20s",
               "|No", "TutorialGroup ID", "TutorialGroup Name|");
       System.out.print("\n**************************************************\n");


       for (int i = 1; i <= pList.getEntry(selectProgramme).getTutorialGroupList().getNumberOfEntries(); i++) {
           System.out.printf("%-10s %-20s%-20s\n",
                   i, pList.getEntry(selectProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupID(),
                   pList.getEntry(selectProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupName());
       }


       System.out.println("Please select a tutorial group (eg.1,2,3):");
       int selectTutorialGroup = input.nextInt();
       while (selectTutorialGroup > pList.getEntry(selectProgramme).getTutorialGroupList().getNumberOfEntries() || selectTutorialGroup <= 0) {
           System.out.print("\nInvalid number please try again");
           System.out.print("\nRe-Enter number(eg.1,2,3): ");
           selectTutorialGroup = input.nextInt();
       }


       System.out.print("\n**********************************************************************************************************************************\n");
       System.out.printf("%-10s %-40s %-20s %-20s %-20s %-20s",
               "|No", "Course Name", "Programe Code", "Tutorial Name", "Tutor Name", "Teaching Type|");
       System.out.print("\n**********************************************************************************************************************************\n");


       for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            for (int j = 1; j <= courseList.getEntry(i).getProgrammeList().getNumberOfEntries(); j++) {
                for (int tg = 1; tg <= courseList.getEntry(i).getProgrammeList().getEntry(j).getTutorialGroupList().getNumberOfEntries(); tg++) {
                    if(courseList.getEntry(i).getProgrammeList().getEntry(j).getTutorialGroupList() != null){
                        if (pList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectTutorialGroup).getTutorialGroupName().equals(courseList.getEntry(i).getProgrammeList().getEntry(j).getTutorialGroupList().getEntry(tg).getTutorialGroupName())) {
                            for (int tr = 1; tr <= courseList.getEntry(i).getProgrammeList().getEntry(j).getTutorialGroupList().getEntry(tg).getTutorList().getNumberOfEntries(); tr++) {
                                System.out.printf("%-10s %-40s %-20s %-20s %-20s %-20s\n",
                                        i, courseList.getEntry(i).getCourseName(), pList.getEntry(selectProgramme).getProgCode(), pList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectTutorialGroup).getTutorialGroupName(),
                                        courseList.getEntry(i).getProgrammeList().getEntry(j).getTutorialGroupList().getEntry(tg).getTutorList().getEntry(tr).getName(), courseList.getEntry(i).getProgrammeList().getEntry(j).getTutorialGroupList().getEntry(tg).getTutorList().getEntry(tr).getType());
                            }
                        }
                    }
                    else {
                        System.out.println("Not tutorial group in this programme");
                    }

                }
            }

        }
   }

   /////okokokmou
}
