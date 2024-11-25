/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import dao.TutorDao;
import entity.Course;
import entity.Programme;
import entity.Tutor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author chinkhang
 */
public class TutorController {

    public static SortedLinkedListInterface<Course> courseList = CourseController.presetCourseList;
    public static SortedLinkedListInterface<Tutor> tutorList = TutorDao.tutorList();
    public static SortedLinkedListInterface<Programme> programmeList = CourseController.presetProgrammeList;

    public void tutorMenu() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("**************************");
            System.out.printf("%-1s %16s %7s\n", "|", "Tutor Menu", "|");
            System.out.println("**************************");
            System.out.println("1. Add New Tutor");
            System.out.println("2. Assigning Tutor");
            System.out.println("3. Search Tutor");
            System.out.println("4. List Tutor");
            System.out.println("5. Filter Tutor");
            System.out.println("6. Delete Tutor");
            System.out.println("7. Edit Tutor");
            System.out.println("8. View Tutor");
            System.out.println("9. Generate Report");
            System.out.println("0. Exit");
            System.out.print("Enter your option : ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    addTutor();
                    break;
                case 2:
                    assignTutor();
                    break;
                case 3:
                    searchTutor();
                    break;
                case 4:
                    listTutor();
                    break;
                case 5:
                    filterTutor();
                    break;

                case 6:
                    removeTutor();
                    break;

                case 7:
                    editTutor();
                    break;
                case 8:
                    viewTutor();
                    break;
                case 9:
                    printReport();
                    break;
                case 0:
                    break;
                default:
                    System.out.print("Invalid choice try again!!");
                    break;
            }
        } while (choice != 0);

    }
    private void addTutor() {
        Tutor[] tutor;
        Scanner input = new Scanner(System.in);
        int count = 0, choice;

        System.out.println("\n**************************");
        System.out.printf("%-1s %16s %7s\n", "|", "Add Tutor", "|");
        System.out.println("**************************\n");

        System.out.println("1.Add 1 tutor");
        System.out.println("2.Add multiple tutor");
        System.out.println("3.Exit");
        System.out.print("Enter your option : ");
        choice = input.nextInt();
        String name, position, educationTittle, educationLevel, email, faculty;
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[\\a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);

        switch (choice) {
            case 1:
                input.nextLine();

                System.out.print("\nEnter tutor name (eg.CK): ");
                name = input.nextLine();

                while (name.isEmpty() == true) {
                    System.out.print("\nName field is empty please enter again!");
                    System.out.print("\nRe-Enter name: ");
                    name = input.nextLine();
                }
                System.out.print("\nEnter tutor position (eg.Dean): ");
                position = input.nextLine();

                while (position.isEmpty() == true) {
                    System.out.print("\nPosition  field is empty please enter again!");
                    System.out.print("\nRe-Enter position: ");
                    position = input.nextLine();
                }

                System.out.print("\nEnter tutor educationTitle (eg.Senior Lecture): ");
                educationTittle = input.nextLine();
                while (educationTittle.isEmpty() == true) {
                    System.out.print("\nEducation Tittle  field is empty please enter again!");
                    System.out.print("\nRe-Enter Education Title: ");
                    educationTittle = input.nextLine();
                }
                System.out.print("\nEnter tutor educationLevel (eg.MBA): ");
                educationLevel = input.nextLine();

                while (educationLevel.isEmpty() == true) {
                    System.out.print("\nEducation Level field is empty please enter again!");
                    System.out.print("\nRe-Enter Education Level: ");
                    educationLevel = input.nextLine();
                }

                System.out.print("\nEnter tutor email (eg.user@gmail.com): ");
                email = input.nextLine();

                while (email.isEmpty() == true || pattern.matcher(email).matches() != true) {
                    if (email.isEmpty() == true) {
                        System.out.print("\nEmail field is empty please enter again!");
                        System.out.print("\nRe-Enter Email (user@gmail.com):");
                        email = input.nextLine();

                    } else if (pattern.matcher(email).matches() != true) {
                        System.out.print("\nIncorrect Email format please follow this format (user123@gamil.com)");
                        System.out.print("\nRe-Enter Email (user@gmail.com):");
                        email = input.nextLine();
                    }
                }
                System.out.print("\nEnter tutor faculty (eg.FOCS,FAFB): ");
                faculty = input.nextLine();

                while (faculty.isEmpty() == true) {
                    System.out.print("\nFaculty is empty please enter again!");
                    System.out.print("\nRe-Enter Education Faculty: ");
                    faculty = input.nextLine();
                }
                Tutor singleTutor = new Tutor(name, position, educationTittle, email, educationLevel, null, faculty.toUpperCase(), 0, true);
                tutorList.add(singleTutor);
                break;
            case 2:
                System.out.print("How many tutor u want to insert? :");
                int numOfTutor = input.nextInt();

                tutor = new Tutor[numOfTutor];

                input.nextLine();

                for (int i = 0; i < numOfTutor; i++) {
                    System.out.println("\nTutor" + " " + (i + 1) + ":");

                    System.out.print("\nEnter tutor name (eg.CK): ");
                    name = input.nextLine();

                    while (name.isEmpty() == true) {
                        System.out.print("\nName field is empty please enter again!");
                        System.out.print("\nRe-Enter name: ");
                        name = input.nextLine();
                    }
                    System.out.print("\nEnter tutor position (eg.Dean): ");
                    position = input.nextLine();

                    while (position.isEmpty() == true) {
                        System.out.print("\nPosition  field is empty please enter again!");
                        System.out.print("\nRe-Enter position: ");
                        position = input.nextLine();
                    }

                    System.out.print("\nEnter tutor educationTitle  (eg.Senior Lecture): ");
                    educationTittle = input.nextLine();
                    while (educationTittle.isEmpty() == true) {
                        System.out.print("\nEducation Title  field is empty please enter again!");
                        System.out.print("\nRe-Enter Education Title: ");
                        educationTittle = input.nextLine();
                    }
                    System.out.print("\nEnter tutor educationLevel  (eg.MBA): ");
                    educationLevel = input.nextLine();

                    while (educationLevel.isEmpty() == true) {
                        System.out.print("\nEducation Level field is empty please enter again!");
                        System.out.print("\nRe-Enter Education Level: ");
                        educationLevel = input.nextLine();
                    }

                    System.out.print("\nEnter tutor email  (eg.user@gmail.com): ");
                    email = input.nextLine();

                    while (email.isEmpty() == true || pattern.matcher(email).matches() != true) {
                        if (email.isEmpty() == true) {
                            System.out.print("\nEmail field is empty please enter again!");
                            System.out.print("\nRe-Enter Email (user@gmail.com):");
                            email = input.nextLine();

                        } else if (pattern.matcher(email).matches() != true) {
                            System.out.print("\nIncorrect Email format please follow this format (user123@gamil.com)");
                            System.out.print("\nRe-Enter Email (user@gmail.com):");
                            email = input.nextLine();
                        }
                    }
                    System.out.print("\nEnter tutor faculty  (eg.FOCS,FAFB): ");
                    faculty = input.nextLine();

                    while (faculty.isEmpty() == true) {
                        System.out.print("\nFaculty is empty please enter again!");
                        System.out.print("\nRe-Enter Education Faculty: ");
                        faculty = input.nextLine();
                    }

                    tutor[i] = new Tutor(name, position, educationTittle, email, educationLevel, null, faculty.toUpperCase(), 0, true);
                    tutorList.add(tutor[i]);

                    count++;
                }
                System.out.print("\nYou have sucessfully added" + " " + count + "Tutor!! \n");
                break;
            case 3:
                break;
            default:
                System.out.print("Invalid choice please try again!");
                break;

        }

    }
    private void viewTutor() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n**************************");
        System.out.printf("%-1s %16s %7s\n", "|", "View Tutor", "|");
        System.out.println("**************************\n");

        System.out.println("\n*******************************************************************************************************************************************************************");
        System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s%-40s%-20s",
                "|No", "Tutor ID", "Tutor Name", "Tutor Email", "Education Level", "Education Title", "Position", "Faculty|");
        System.out.println("\n*******************************************************************************************************************************************************************");

        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s%-40s%-20s\n",
                    i, tutorList.getEntry(i).getId(), tutorList.getEntry(i).getName(),
                    tutorList.getEntry(i).getEmail(), tutorList.getEntry(i).getEducationLevel(),
                    tutorList.getEntry(i).getEducationTittle(), tutorList.getEntry(i).getPosition(), tutorList.getEntry(i).getFaculty());
        }

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }
    private void removeTutor() {
        Tutor deleteTutor = new Tutor();
        Scanner input = new Scanner(System.in);
        boolean recordFound = false;

        System.out.println("\n**************************");
        System.out.printf("%-1s %16s %7s\n", "|", "Remove Tutor", "|");
        System.out.println("**************************\n");
        System.out.println("1. Remove Tutor");
        System.out.println("2. Remove Tutor from Course");
        System.out.println("3. Exit");
        System.out.println("Enter your choice :");
        int choice = input.nextInt();

        switch (choice) {
            case 1:

                System.out.println("\n**********************************************************************************************************************************************************************");
                System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s%-40s%-20s",
                        "|No", "Tutor ID", "Tutor Name", "Tutor Email", "Education Level", "Education Title", "Position", "Faculty|");
                System.out.println("\n***********************************************************************************************************************************************************************");

                for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                    System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s%-40s%-20s\n",
                            i, tutorList.getEntry(i).getId(), tutorList.getEntry(i).getName(),
                            tutorList.getEntry(i).getEmail(), tutorList.getEntry(i).getEducationLevel(),
                            tutorList.getEntry(i).getEducationTittle(), tutorList.getEntry(i).getPosition(), tutorList.getEntry(i).getFaculty());
                }

                input.nextLine();
                System.out.println("Enter the tutor ID u want to delete (eg.T1001): ");
                String tutorID = input.nextLine();
                while (tutorID.isEmpty() == true) {
                    System.out.print("\nID field is empty please enter again!");
                    System.out.print("\nRe-Enter ID: ");
                    tutorID = input.nextLine();
                }

                for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                    if (tutorList.getEntry(i).getId().equals(tutorID.toUpperCase())) {
                        deleteTutor = tutorList.getEntry(i);
                        recordFound = true;
                    }
                }
                if (!recordFound) {
                    System.out.println("\nNo ID can be match please try again!");
                    break;
                }
                tutorList.remove(deleteTutor);
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    for (int j = 1;courseList.getEntry(i).getTutorList()!=null&&j <= courseList.getEntry(i).getTutorList().getNumberOfEntries(); j++) {
                        if (courseList.getEntry(i).getTutorList().getEntry(j).getId().equals(deleteTutor.getId())) {
                            courseList.getEntry(i).getTutorList().remove(courseList.getEntry(i).getTutorList().getEntry(j));
                        }
                    }
                }
                System.out.printf("You have deleted Tutor: " + deleteTutor.getName()+"\n");
                break;
            case 2:
                System.out.println("\n**********************************************************************************************************************");
                System.out.printf("%-10s%-25s%-40s%-30s%-30s",
                        "|No", "Course Code", "Course Name", "Course Level", "Credit Hour|");
                System.out.println("\n**********************************************************************************************************************");
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    System.out.printf("%-10s %-25s%-40s%-30s%-30s\n",
                            i, courseList.getEntry(i).getCourseCode(), courseList.getEntry(i).getCourseName(), courseList.getEntry(i).getCourseLevel(), courseList.getEntry(i).getCreditHours());
                }

                System.out.print("Enter the course(eg.1,2,3) : ");
                int selectCourse = input.nextInt();

                while (selectCourse > courseList.getNumberOfEntries() || selectCourse <= 0) {
                    System.out.print("\nInvalid number please try again");
                    System.out.print("\nRe-Enter number(eg.1,2,3): ");
                    selectCourse = input.nextInt();
                }

                System.out.println("\n******************************************************************************************************************************************************************");
                System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s%-40s%-20s",
                        "|No", "Tutor ID", "Tutor Name", "Tutor Email", "Education Level", "Education Title", "Position", "Faculty|");
                System.out.println("\n******************************************************************************************************************************************************************");

                if (courseList.getEntry(selectCourse).getTutorList() != null) {
                    for (int j = 1; j <= courseList.getEntry(selectCourse).getTutorList().getNumberOfEntries(); j++) {
                        System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s%-40s%-20s\n",
                                j, courseList.getEntry(selectCourse).getTutorList().getEntry(j).getId(), courseList.getEntry(selectCourse).getTutorList().getEntry(j).getName(),
                                courseList.getEntry(selectCourse).getTutorList().getEntry(j).getEmail(), courseList.getEntry(selectCourse).getTutorList().getEntry(j).getEducationLevel(),
                                courseList.getEntry(selectCourse).getTutorList().getEntry(j).getEducationTittle(), courseList.getEntry(selectCourse).getTutorList().getEntry(j).getPosition(), courseList.getEntry(selectCourse).getTutorList().getEntry(j).getFaculty());
                    }

                    input.nextLine();

                    System.out.print("Enter the tutor (eg.T1001) : ");
                    String selectedTutor = input.nextLine();
                    while (selectedTutor.isEmpty() == true) {
                        System.out.print("\nID field is empty please enter again!");
                        System.out.print("\nRe-Enter ID: ");
                        selectedTutor = input.nextLine();
                    }

                    for (int t = 1; t <= courseList.getEntry(selectCourse).getTutorList().getNumberOfEntries(); t++) {
                        if (courseList.getEntry(selectCourse).getTutorList().getEntry(t).getId().equals(selectedTutor.toUpperCase())) {
                            deleteTutor = courseList.getEntry(selectCourse).getTutorList().getEntry(t);
                            recordFound = true;
                        }
                    }

                    if (!recordFound) {
                        System.out.println("\nNo ID can be match please try again!");
                        break;
                    }
                    courseList.getEntry(selectCourse).getTutorList().remove(deleteTutor);
                    System.out.printf("You have deleted Tutor: " + deleteTutor.getName() + "\n");
                    break;
                } else {
                    System.out.printf("There have no tutor available \n");
                    break;
                }
            case 3:
                break;
            default:
                System.out.println("Invalid choice please try again!!");
                break;
        }

    }
    private void editTutor() {
        Tutor editTutor = new Tutor();
        Scanner input = new Scanner(System.in);
        boolean recordFound = false;
        String tutorfaculty = " ";

        System.out.println("\n**************************");
        System.out.printf("%-1s %16s %7s\n", "|", "Edit Tutor", "|");
        System.out.println("**************************\n");

        System.out.println("\n*******************************************************************************************************************************************************************");
        System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s%-40s%-20s",
                "|No", "Tutor ID", "Tutor Name", "Tutor Email", "Education Level", "Education Title", "Position", "Faculty|");
        System.out.println("\n*******************************************************************************************************************************************************************");

        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s%-40s%-20s\n",
                    i, tutorList.getEntry(i).getId(), tutorList.getEntry(i).getName(),
                    tutorList.getEntry(i).getEmail(), tutorList.getEntry(i).getEducationLevel(),
                    tutorList.getEntry(i).getEducationTittle(), tutorList.getEntry(i).getPosition(), tutorList.getEntry(i).getFaculty());
        }

        System.out.print("\nEnter the tutor ID u want to edit (eg.T1001): ");
        String tutorID = input.nextLine();

        while (tutorID.isEmpty() == true) {
            System.out.print("\nID field is empty please enter again!");
            System.out.print("\nRe-Enter ID: ");
            tutorID = input.nextLine();
        }

        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            if (tutorList.getEntry(i).getId().equals(tutorID.toUpperCase())) {
                editTutor = tutorList.getEntry(i);
                recordFound = true;
            }
        }

        if (!recordFound) {
            System.out.println("\nNo ID can be match please try again!");

        } else if (recordFound) {
            System.out.print("\nEnter enter new faculty (eg.FOCS): ");
            tutorfaculty = input.nextLine();

            while (tutorfaculty.isEmpty() == true || tutorfaculty.toUpperCase().equals(editTutor.getFaculty())) {
                if (tutorfaculty.isEmpty() == true) {
                    System.out.print("\nFaculty field is empty please enter again!");
                    System.out.print("\nRe-Enter Faculty: ");
                    tutorfaculty = input.nextLine();

                } else if (tutorfaculty.toUpperCase().equals(editTutor.getFaculty())) {
                    System.out.print("\nNew faculty enter by u same with old faculty record!");
                    System.out.print("\nRe-Enter Faculty: ");
                    tutorfaculty = input.nextLine();
                }

            }

            System.out.printf("You have edited Tutor  " + editTutor.getName() + " from original faculty <<" + editTutor.getFaculty() + ">> to <<" + tutorfaculty.toUpperCase() + ">>\n");
            editTutor.setFaculty(tutorfaculty.toUpperCase());
            for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                for (int j = 1;courseList.getEntry(i).getTutorList()!=null && j <= courseList.getEntry(i).getTutorList().getNumberOfEntries(); j++) {
                    if (courseList.getEntry(i).getTutorList().getEntry(j).getId().equals(editTutor.getId())) {
                        courseList.getEntry(i).getTutorList().getEntry(j).setFaculty(tutorfaculty);
                    }
                }
            }
        }
    }
    private void assignTutor() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        String sentence = "";
        int selectedGroup = 0;
        int tutorCount = 0;
        int count = 1;
        int totalCreditHours = 0;
        int foundCount = 0;
        int notFoundCount = 1;
        int deleteCount = 1;
        boolean recordFound = false;
        boolean duplicate = true;
        boolean lectureDuplicate = true;
        int tutorToTutorialFound = 0;
        int selectedtutor = 0;
        int selectedTGroup = 0;
        int selectedProgramme = 0;
        Tutor assignTutor = new Tutor();
        int tutorFoundCount =0;

        System.out.println("\n**************************");
        System.out.printf("%-1s %16s %7s\n", "|", "Assign Tutor", "|");
        System.out.println("**************************\n");

        System.out.println("1. Assign Tutor to Course");
        System.out.println("2. Assign Tutorial Groups to Tutor");
        System.out.println("3. Add Tutors To A Tutorial Group For A Course");
        System.out.println("4. Exit");
        System.out.print("Enter your option : ");
        choice = input.nextInt();

        switch (choice) {
            case 1:
                outer2:
                System.out.println("\n**********************************************************************************************************************");
                System.out.printf("%-10s%-25s%-40s%-30s%-30s",
                        "|No", "Course Code", "Course Name", "Course Level", "Credit Hour|");
                System.out.println("\n***********************************************************************************************************************");
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    System.out.printf("%-10s %-25s%-40s%-30s%-30s\n",
                            i, courseList.getEntry(i).getCourseCode(), courseList.getEntry(i).getCourseName(), courseList.getEntry(i).getCourseLevel(), courseList.getEntry(i).getCreditHours());
                }

                System.out.println("Enter which course u want to assign a tutor (eg.1,2,3): ");
                int number = input.nextInt();

                while (number > courseList.getNumberOfEntries() || number <= 0) {
                    System.out.print("\nInvalid number please try again");
                    System.out.print("\nRe-Enter number(eg.1,2,3:) ");
                    number = input.nextInt();
                }

                if(courseList.getEntry(number).getTutorList()!=null){
                    if(courseList.getEntry(number).getTutorList().getNumberOfEntries()>=5){
                        System.out.print("One course only can assign 5 tutor !!\n");
                        break;
                    }
                }

                System.out.println("\n***********************************************************************************");
                System.out.printf("%-10s%-20s%-20s%-25s%-20s",
                        "|No", "Tutor ID", "Tutor Name", "Tutor Email", "Faculty|");
                System.out.println("\n***********************************************************************************");
                for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                    for (int j = 1; courseList.getEntry(number).getProgrammeList()!= null&&j <= courseList.getEntry(number).getProgrammeList().getNumberOfEntries(); j++) {
                        if (courseList.getEntry(number).getProgrammeList().getEntry(j).getFacultyCode().equals(tutorList.getEntry(i).getFaculty())) {
                            System.out.printf("%-10s%-20s%-20s%-25s%-20s\n",
                                    count++, tutorList.getEntry(i).getId(), tutorList.getEntry(i).getName(), tutorList.getEntry(i).getEmail(), tutorList.getEntry(i).getFaculty());
                            tutorCount++;
                            break;
                        }
                    }
                }
                if (tutorCount <= 0) {
                    System.out.print("There have no tutor available for this course" + " " + courseList.getEntry(number).getCourseName()+"\n");
                    break;
                }
                input.nextLine();

                System.out.println("Enter the which tutor u wanted to assign (eg.T1002) : ");//enter by id
                String tutorID = input.nextLine();

                while (tutorID.toUpperCase().isEmpty() == true) {
                    System.out.print("\nID field is empty please enter again!");
                    System.out.print("\nRe-Enter ID: ");
                    tutorID = input.nextLine();
                }

                for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                    if (tutorList.getEntry(i).getId().equals(tutorID.toUpperCase())) {
                        assignTutor = tutorList.getEntry(i);
                        recordFound = true;
                    }
                }

                if (!recordFound) {
                    System.out.println("\nNo ID can be match please try again!");
                    break;
                }

                for(int i=1;i<=courseList.getNumberOfEntries();i++){
                    for(int j=1;courseList.getEntry(i).getTutorList()!=null &&j<= courseList.getEntry(i).getTutorList().getNumberOfEntries();j++){
                        if(courseList.getEntry(i).getTutorList().getEntry(j).getId().equals(assignTutor.getId())){
                            tutorFoundCount++;
                        }
                    }
                }

                if(tutorFoundCount>=2){
                    System.out.println("One tutor only can handle two course\n");
                    tutorFoundCount =0;
                    break;
                }
                if (assignTutor.getTutorialGroupList().getNumberOfEntries() <= 0) {

                    System.out.println("Which type of tutor u want to assign(P/L/T) : ");
                    char type = input.next().charAt(0);

                    switch (type) {
                        case 'P':
                        case 'p':
                            sentence = "Practical";
                            break;
                        case 'L':
                        case 'l':
                            sentence = "Lecture";
                            break;
                        case 'T':
                        case 't':
                            sentence = "Tutorial";
                            break;
                    }

                    if (assignTutor.getCreditHour() > 0) {
                        totalCreditHours = assignTutor.getCreditHour() + courseList.getEntry(number).getCreditHours();
                    } else {
                        totalCreditHours = courseList.getEntry(number).getCreditHours();
                    }

                    Tutor selectTutor = new Tutor(assignTutor.getId(), assignTutor.getName(), assignTutor.getPosition(), assignTutor.getEducationTittle(),
                            assignTutor.getEmail(), assignTutor.getEducationLevel(), sentence, assignTutor.getFaculty(), totalCreditHours);
                    Course selectCourse = courseList.getEntry(number);

                    if (selectCourse.getTutorList() != null) {
                        selectCourse.getTutorList().add(selectTutor);
                    } else {
                        SortedLinkedListInterface<Tutor> tutorList = new SortedDoublyLinkedList<>();
                        tutorList.add(selectTutor);
                        selectCourse.setTutorList(tutorList);
                    }
                    assignTutor.setCreditHour(totalCreditHours);
                    System.out.println("You assign successfully !!");
                    break;
                } else if (assignTutor.getTutorialGroupList().getNumberOfEntries() > 0) {
                    outer:
                    for (int i = 1;assignTutor.getTutorialGroupList()!=null &&i <= assignTutor.getTutorialGroupList().getNumberOfEntries(); i++) {
                        for (int j = 1; courseList.getEntry(number).getProgrammeList()!= null&&j <= courseList.getEntry(number).getProgrammeList().getNumberOfEntries(); j++) {
                            String str1 = assignTutor.getTutorialGroupList().getEntry(i).getTutorialGroupID();
                            String str2 = courseList.getEntry(number).getProgrammeList().getEntry(j).getProgCode();

                            String firstThreeChars1 = str1.substring(0, Math.min(str1.length(), 3));
                            String firstThreeChars2 = str2.substring(0, Math.min(str2.length(), 3));

                            if (firstThreeChars1.equals(firstThreeChars2)) {
                                foundCount++;
                                System.out.println("Which type of tutor u want to assign(P/L/T) : ");
                                char type = input.next().charAt(0);

                                switch (type) {
                                    case 'P':
                                    case 'p':
                                        sentence = "Practical";
                                        break;
                                    case 'L':
                                    case 'l':
                                        sentence = "Lecture";
                                        break;
                                    case 'T':
                                    case 't':
                                        sentence = "Tutorial";
                                        break;
                                }

                                Tutor selectTutor = new Tutor(assignTutor.getId(), assignTutor.getName(), assignTutor.getPosition(), assignTutor.getEducationTittle(),
                                        assignTutor.getEmail(), assignTutor.getEducationLevel(), sentence, assignTutor.getFaculty(), totalCreditHours);
                                Course selectCourse = courseList.getEntry(number);
                                selectCourse.getTutorList().add(selectTutor);
                                assignTutor.setCreditHour(totalCreditHours);
                                System.out.println("You assign successfully !!");
                                break;

                            } else if (!firstThreeChars1.equals(firstThreeChars2)) {
                                System.out.println("Does not offer for these tutorial group:");
                                System.out.println("\n****************************************************************");
                                System.out.printf("%-10s %-20s%-20s%-30s",
                                        "|No", "TutorialGroup ID", "Tutor Name", "Course Name|");
                                System.out.println("\n****************************************************************");
                                System.out.printf("%-10s%-20s%-20s%-30s\n",
                                        notFoundCount++, assignTutor.getTutorialGroupList().getEntry(i).getTutorialGroupID(), assignTutor.getName(), courseList.getEntry(number).getCourseName());

                                System.out.println("Do u want to drop the tutorial group (Y/N):");
                                char select = input.next().charAt(0);

                                input.nextLine();
                                if (select == 'Y' || select == 'y') {
                                    System.out.println("Please enter the tutorial group name u want to drop:");
                                    String groupName = input.nextLine();

                                    for (int t = 1; t <= tutorList.getNumberOfEntries(); t++) {
                                        if (tutorList.getEntry(t).getTutorialGroupList()!=null) {
                                            for (int gp = 1; gp <= tutorList.getEntry(t).getTutorialGroupList().getNumberOfEntries(); gp++) {
                                                if (tutorList.getEntry(t).getTutorialGroupList().getEntry(gp).getTutorialGroupName().equals(groupName.toUpperCase())) {
                                                    tutorList.getEntry(t).getTutorialGroupList().remove(tutorList.getEntry(t).getTutorialGroupList().getEntry(gp));
                                                    System.out.println("u have sucessfully dropped the tutorial group!\n");
                                                    deleteCount++;
                                                    break outer;
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                    if (foundCount <= 0 && deleteCount <= 0) {
                        System.out.println("The Tutorial Group belonged to this tutor does not offer in this course!\n");
                    }
                }
                break;
            case 2:
                System.out.println("\n***********************************************************************************");
                System.out.printf("%-10s%-20s%-20s%-25s%-20s",
                        "|No", "Tutor ID", "Tutor Name", "Tutor Email", "Faculty|");
                System.out.println("\n***********************************************************************************");

                for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                    System.out.printf("%-10s%-20s%-20s%-25s%-20s\n",
                            i, tutorList.getEntry(i).getId(), tutorList.getEntry(i).getName(), tutorList.getEntry(i).getEmail(), tutorList.getEntry(i).getFaculty());
                }

                System.out.print("Which tutor u want assign a tutorial group (eg.1,2,3):");
                int selectedTutor = input.nextInt();

                while (selectedTutor > tutorList.getNumberOfEntries() || selectedTutor <= 0) {
                    System.out.print("\nInvalid number please try again");
                    System.out.print("\nRe-Enter number(eg.1,2,3): ");
                    selectedTutor = input.nextInt();
                }

                System.out.print("\n***********************************************************************************************\n");
                System.out.printf("%-10s %-50s %-20s%-15s",
                        "|No", "Programme Name", "Progamme Code", "Faculty Code|");
                System.out.print("\n***********************************************************************************************\n");
                for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
                    System.out.printf("%-10s %-50s %-20s%-15s\n",
                            i, programmeList.getEntry(i).getProgName(), programmeList.getEntry(i).getProgCode(), programmeList.getEntry(i).getFacultyCode());
                }
                System.out.println("Which programme u want to assign (eg.1,2,3):");
                int selectProgramme = input.nextInt();

                while (selectProgramme > programmeList.getNumberOfEntries() || selectProgramme <= 0) {
                    System.out.print("\nInvalid number please try again");
                    System.out.print("\nRe-Enter number(eg.1,2,3): ");
                    selectProgramme = input.nextInt();
                }

                if (programmeList.getEntry(selectProgramme).getTutorialGroupList()!= null) {
                    System.out.print("\n**************************************************\n");
                    System.out.printf("%-10s %-20s%-20s",
                            "|No", "TutorialGroup ID", "TutorialGroup Name|");
                    System.out.print("\n**************************************************\n");

                    for (int i = 1; i <= programmeList.getEntry(selectProgramme).getTutorialGroupList().getNumberOfEntries(); i++) {
                        System.out.printf("%-10s %-20s%-20s\n",
                                i, programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupID(),
                                programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupName());
                    }

                    System.out.println("1. Assign Multiple Tutorial Groups To Tutor");
                    System.out.println("2. Assign One Tutorial Groups To Tutor");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice :");
                    int option = input.nextInt();
                    switch (option) {
                        case 1:

                            System.out.print("\n**************************************************\n");
                            System.out.printf("%-10s %-20s%-20s",
                                    "|No", "TutorialGroup ID", "TutorialGroup Name|");
                            System.out.print("\n**************************************************\n");

                            for (int i = 1; i <= programmeList.getEntry(selectProgramme).getTutorialGroupList().getNumberOfEntries(); i++) {
                                System.out.printf("%-10s %-20s%-20s\n",
                                        i, programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupID(),
                                        programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupName());

                            }

                            System.out.println("How many groups u want to assign :");
                            int option2 = input.nextInt();

                            while (option2 > programmeList.getEntry(selectProgramme).getTutorialGroupList().getNumberOfEntries() || option2 <= 0) {
                                System.out.print("\nInvalid number please try again");
                                System.out.print("\nRe-Enter number(eg.1,2,3): ");
                                option2 = input.nextInt();
                            }

                            for (int i = 0; i < option2; i++) {
                                System.out.println("Select the group (eg.1,2,3):");
                                selectedGroup = input.nextInt();
                                while (selectedGroup > programmeList.getEntry(selectProgramme).getTutorialGroupList().getNumberOfEntries() || selectedGroup <= 0) {
                                    System.out.print("\nInvalid number please try again");
                                    System.out.print("\nRe-Enter number(eg.1,2,3): ");
                                    selectedGroup = input.nextInt();
                                }

                                if (tutorList.getEntry(selectedTutor).getTutorialGroupList().getNumberOfEntries() > 0) {
                                    for (int j = 1; j <= tutorList.getEntry(selectedTutor).getTutorialGroupList().getNumberOfEntries(); j++) {
                                        if (!tutorList.getEntry(selectedTutor).getTutorialGroupList().getEntry(j).getTutorialGroupName().equals(programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectedGroup).getTutorialGroupName())) {
                                            tutorList.getEntry(selectedTutor).getTutorialGroupList().add(programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectedGroup));
                                            System.out.println("You have successfully assigned!!");
                                            duplicate = false;
                                        }
                                    }
                                } else {
                                    tutorList.getEntry(selectedTutor).getTutorialGroupList().add(programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectedGroup));
                                    System.out.println("You have successfully assigned!!");
                                    duplicate = false;
                                }

                                if (duplicate) {
                                    System.out.println("The tutorial group  " + programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectedGroup).getTutorialGroupID() + " u selected has been assigned to this tutor " + tutorList.getEntry(selectedTutor).getName());
                                    break;
                                }

                                break;
                            }

                            break;
                        case 2:
                            System.out.print("\n**************************************************\n");
                            System.out.printf("%-10s %-20s%-20s",
                                    "|No", "TutorialGroup ID", "TutorialGroup Name|");
                            System.out.print("\n**************************************************\n");

                            if (programmeList.getEntry(selectProgramme).getTutorialGroupList() != null) {
                                for (int i = 1; i <= programmeList.getEntry(selectProgramme).getTutorialGroupList().getNumberOfEntries(); i++) {
                                    System.out.printf("%-10s %-20s%-20s\n",
                                            i, programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupID(),
                                            programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupName());

                                }

                                System.out.println("Select the group (eg.1,2,3):");
                                selectedGroup = input.nextInt();

                                while (selectedGroup > programmeList.getEntry(selectProgramme).getTutorialGroupList().getNumberOfEntries() || selectedGroup <= 0) {
                                    System.out.print("\nInvalid number please try again");
                                    System.out.print("\nRe-Enter number(eg.1,2,3:) ");
                                    selectedGroup = input.nextInt();
                                }
                            } else {
                                System.out.print("\n There have not available tutorial group can be choose \n");
                                break;
                            }

                            if (tutorList.getEntry(selectedTutor).getTutorialGroupList().getNumberOfEntries() > 0) {
                                for (int j = 1; j <= tutorList.getEntry(selectedTutor).getTutorialGroupList().getNumberOfEntries(); j++) {
                                    if (!tutorList.getEntry(selectedTutor).getTutorialGroupList().getEntry(j).getTutorialGroupName().equals(programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectedGroup).getTutorialGroupName())) {
                                        tutorList.getEntry(selectedTutor).getTutorialGroupList().add(programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectedGroup));
                                        System.out.println("You have successfully assigned!!");
                                        duplicate = false;
                                    }
                                }
                            } else {
                                tutorList.getEntry(selectedTutor).getTutorialGroupList().add(programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectedGroup));
                                System.out.println("You have successfully assigned!!");
                                duplicate = false;
                            }

                            if (duplicate) {
                                System.out.println("The tutorial group  " + programmeList.getEntry(selectProgramme).getTutorialGroupList().getEntry(selectedGroup).getTutorialGroupID() + " u selected has been assigned to this tutor " + tutorList.getEntry(selectedTutor).getName());
                                break;
                            }

                            break;
                    }
                } else {
                    System.out.println("There have no tutorial group for this programme!!");
                }
                break;
            case 3:
                System.out.println("\n**********************************************************************************************************************");
                System.out.printf("%-10s%-25s%-40s%-30s%-30s",
                        "|No", "Course Code", "Course Name", "Course Level", "Credit Hour|");
                System.out.println("\n**********************************************************************************************************************");
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    System.out.printf("%-10s %-25s%-40s%-30s%-30s\n",
                            i, courseList.getEntry(i).getCourseCode(), courseList.getEntry(i).getCourseName(), courseList.getEntry(i).getCourseLevel(), courseList.getEntry(i).getCreditHours());
                }
                System.out.println("Select the course (eg.1,2,3):");
                int selectedCourse = input.nextInt();

                while (selectedCourse > courseList.getNumberOfEntries() || selectedCourse <= 0) {
                    System.out.print("\nInvalid number please try again");
                    System.out.print("\nRe-Enter number(eg.1,2,3:) ");
                    selectedCourse = input.nextInt();
                }

                System.out.print("\n***********************************************************************************************\n");
                System.out.printf("%-10s %-50s %-20s%-15s",
                        "|No", "Programme Name", "Progamme Code", "Faculty Code|");
                System.out.print("\n***********************************************************************************************\n");
                if (courseList.getEntry(selectedCourse).getProgrammeList().getNumberOfEntries()>0) {

                    for (int i = 1; i <= courseList.getEntry(selectedCourse).getProgrammeList().getNumberOfEntries(); i++) {
                        System.out.printf("%-10s %-50s %-20s%-15s\n",
                                i, courseList.getEntry(selectedCourse).getProgrammeList().getEntry(i).getProgName(), courseList.getEntry(selectedCourse).getProgrammeList().getEntry(i).getProgCode(), courseList.getEntry(selectedCourse).getProgrammeList().getEntry(i).getFacultyCode());
                    }

                    System.out.println("Select the programme (eg.1,2,3):");
                    selectedProgramme = input.nextInt();

                    while (selectedProgramme > courseList.getEntry(selectedCourse).getProgrammeList().getNumberOfEntries() || selectedProgramme <= 0) {
                        System.out.print("\nInvalid number please try again");
                        System.out.print("\nRe-Enter number(eg.1,2,3:) ");
                        selectedProgramme = input.nextInt();
                    }
                }else{
                    System.out.print("\nNo programme available!\n");
                    break;
                }

                System.out.print("\n**************************************************\n");
                System.out.printf("%-10s %-20s%-20s",
                        "|No", "TutorialGroup ID", "TutorialGroup Name|");
                System.out.print("\n**************************************************\n");

                if (courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getNumberOfEntries() >0) {
                    for (int i = 1; i <= courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getNumberOfEntries(); i++) {
                        System.out.printf("%-10s %-20s%-20s\n",
                                i, courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupID(),
                                courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getEntry(i).getTutorialGroupName());
                    }

                    System.out.println("Select the Group (eg.1,2,3):");
                    selectedTGroup = input.nextInt();

                    while (selectedTGroup > courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getNumberOfEntries() || selectedTGroup <= 0) {
                        System.out.print("\nInvalid number please try again");
                        System.out.print("\nRe-Enter number(eg.1,2,3:) ");
                        selectedTGroup = input.nextInt();
                    }
                } else {
                    System.out.print("\n There have not available tutorial group can be choose \n");
                    break;
                }

                System.out.println("\n*************************************************************************************************************");
                System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s",
                        "|No", "Tutor ID", "Tutor Name", "Tutor Email", "Faculty", "Teaching Type|");
                System.out.println("\n*************************************************************************************************************");

                if (courseList.getEntry(selectedCourse).getTutorList() != null) {
                    for (int i = 1; courseList.getEntry(selectedCourse).getTutorList() != null && i <= courseList.getEntry(selectedCourse).getTutorList().getNumberOfEntries(); i++) {
                        System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s\n",
                                i, courseList.getEntry(selectedCourse).getTutorList().getEntry(i).getId(), courseList.getEntry(selectedCourse).getTutorList().getEntry(i).getName(), courseList.getEntry(selectedCourse).getTutorList().getEntry(i).getEmail(),
                                courseList.getEntry(selectedCourse).getTutorList().getEntry(i).getFaculty(), courseList.getEntry(selectedCourse).getTutorList().getEntry(i).getType());
                    }

                    System.out.println("Select the tutor (eg.1,2,3):");
                    selectedtutor = input.nextInt();
                    while (selectedtutor > courseList.getEntry(selectedCourse).getTutorList().getNumberOfEntries() || selectedtutor <= 0) {
                        System.out.print("\nInvalid number please try again");
                        System.out.print("\nRe-Enter number(eg.1,2,3:) ");
                        selectedtutor = input.nextInt();
                    }

                } else {
                    System.out.print("\n There have not available tutor can be choose \n");
                    break;

                }

                lectureDuplicate = true;
                if (courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getEntry(selectedTGroup).getTutorList().getNumberOfEntries() > 0) {
                    for (int j = 1; j <= courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getEntry(selectedTGroup).getTutorList().getNumberOfEntries(); j++) {
                        if (!courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getEntry(selectedTGroup).getTutorList().getEntry(j).getId().equals(courseList.getEntry(selectedCourse).getTutorList().getEntry(selectedtutor).getId())) {
                            courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getEntry(selectedTGroup).getTutorList().add(courseList.getEntry(selectedCourse).getTutorList().getEntry(selectedtutor));
                            System.out.print("\nYou have successfully added");
                            duplicate = false;
                            tutorToTutorialFound = 1;
                        } else {
                            System.out.println("This Tutor  " + courseList.getEntry(selectedCourse).getTutorList().getEntry(selectedtutor).getName() + " has been added to this Tutorial Group " + courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getEntry(selectedTGroup).getTutorialGroupID());
                            break;
                        }
                    }
                } else {
                    courseList.getEntry(selectedCourse).getProgrammeList().getEntry(selectedProgramme).getTutorialGroupList().getEntry(selectedTGroup).getTutorList().add(courseList.getEntry(selectedCourse).getTutorList().getEntry(selectedtutor));
                    System.out.print("\nYou have successfully added\n");
                    duplicate = false;
                    tutorToTutorialFound = 0;
                    break;
                }
                break;

            case 4:
                break;

            default:
                System.out.print("Invalid choice please try again!");
                break;
        }
    }
    private void searchTutor() {
        Scanner input = new Scanner(System.in);
        int selectedCourse = 0;
        int option = 0;
        int count =1;

        System.out.println("\n**************************");
        System.out.printf("%-1s %16s %7s\n", "|", "Search Tutor", "|");
        System.out.println("**************************\n");
        System.out.println("1.Search Courses Under A Tutor");
        System.out.println("2.Search Tutors For A Course(T,P,L)");
        System.out.println("3.Exit");
        System.out.print("Enter your option : ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:

                System.out.println("\n***********************************************************************************");
                System.out.printf("%-10s%-20s%-20s%-25s%-20s",
                        "|No", "Tutor ID", "Tutor Name", "Tutor Email", "Faculty|");
                System.out.println("\n***********************************************************************************");

                for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                    System.out.printf("%-10s%-20s%-20s%-25s%-20s\n",
                            i, tutorList.getEntry(i).getId(), tutorList.getEntry(i).getName(), tutorList.getEntry(i).getEmail(), tutorList.getEntry(i).getFaculty());
                }
                System.out.print("Enter your choice (eg.1,2,3):");
                option = input.nextInt();

                while (option > tutorList.getNumberOfEntries() || option <= 0) {
                    System.out.print("\nInvalid number please try again");
                    System.out.print("\nRe-Enter number(eg.1,2,3:) ");
                    option = input.nextInt();
                }

                System.out.println("\n*****************************************************************************************");
                System.out.printf("%-10s%-50s%-15s%-15s", "|No", " Course Name", "Tutor Name", "Teaching Type|");
                System.out.println("\n*****************************************************************************************");
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    if (courseList.getEntry(i).getTutorList()!= null) {
                        for (int j = 1; j <= courseList.getEntry(i).getTutorList().getNumberOfEntries(); j++) {
                            if (courseList.getEntry(i).getTutorList().getEntry(j).getName().equals(tutorList.getEntry(option).getName())) {
                                System.out.printf("%-10s%-50s%-15s%-15s\n", count++, courseList.getEntry(i).getCourseName(), courseList.getEntry(i).getTutorList().getEntry(j).getName(), courseList.getEntry(i).getTutorList().getEntry(j).getType());
                            }
                        }
                    }
                }
                break;

            case 2:

                System.out.println("\n*********************************************************************************************************************");
                System.out.printf("%-10s%-25s%-40s%-30s%-30s",
                        "|No", "Course Code", "Course Name", "Course Level", "Credit Hour|");
                System.out.println("\n*********************************************************************************************************************");
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    System.out.printf("%-10s %-25s%-40s%-30s%-30s\n",
                            i, courseList.getEntry(i).getCourseCode(), courseList.getEntry(i).getCourseName(), courseList.getEntry(i).getCourseLevel(), courseList.getEntry(i).getCreditHours());
                }

                System.out.print("Enter your choice(eg.1,2,3) :");
                selectedCourse = input.nextInt();

                while (selectedCourse > courseList.getNumberOfEntries() || selectedCourse <= 0) {
                    System.out.print("\nInvalid number please try again");
                    System.out.print("\nRe-Enter number(eg.1,2,3): ");
                    selectedCourse = input.nextInt();
                }
                if (courseList.getEntry(selectedCourse).getTutorList() != null) {
                    System.out.println("\n***********************************************************************************");
                    System.out.printf("%-10s%-40s%-20s%-20s", "|No", " Course Name", "Tutor Name", "Teaching Type|");
                    System.out.println("\n************************************************************************************");
                    for (int i = 1; i <= courseList.getEntry(selectedCourse).getTutorList().getNumberOfEntries(); i++) {
                        System.out.printf("%-10s%-40s%-20s%-20s\n", i, courseList.getEntry(selectedCourse).getCourseName(), courseList.getEntry(selectedCourse).getTutorList().getEntry(i).getName(), courseList.getEntry(selectedCourse).getTutorList().getEntry(i).getType());
                    }
                } else {

                    System.out.print("You does not have assign any tutor to this course" + " " + courseList.getEntry(selectedCourse).getCourseName()+"\n");
                }

                break;

            case 3:
                break;

            default:
                System.out.print("Invalid choice please try again!");
                break;

        }
    }

    private void listTutor() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int count = 1;
        int recordCount = 1;
        int tutorCount = 1;
        int recordFindCount = 1;
        int tutorialGroupCount = 1;

        System.out.println("\n**************************");
        System.out.printf("%-1s %16s %7s\n", "|", "List Tutor", "|");
        System.out.println("**************************\n");
        System.out.println("1. List tutors and tutorial group for a course");
        System.out.println("2. List courses for each tutor");
        System.out.println("3. List tutorial group for tutor");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (eg.1,2,3): ");
        choice = input.nextInt();

        switch (choice) {
            case 1:

                System.out.println("\n*********************************************************************************************************************");
                System.out.printf("%-10s%-25s%-40s%-30s%-30s",
                        "|No", "Course Code", "Course Name", "Course Level", "Credit Hour|");
                System.out.println("\n*********************************************************************************************************************");
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    System.out.printf("%-10s %-25s%-40s%-30s%-30s\n",
                            i, courseList.getEntry(i).getCourseCode(), courseList.getEntry(i).getCourseName(), courseList.getEntry(i).getCourseLevel(), courseList.getEntry(i).getCreditHours());
                }

                System.out.println("Select Course (eg.1,2,3):");
                int chooseCourse = input.nextInt();

                System.out.println("\n************************************************************************************");
                System.out.printf("%-10s%-40s%-20s%-20s", "|No", " Course Name", "Tutor Name", "Tutor Faculty|");
                System.out.println("\n************************************************************************************");

                if (courseList.getEntry(chooseCourse).getTutorList()!=null) {
                    for (int j = 1; j <= courseList.getEntry(chooseCourse).getTutorList().getNumberOfEntries(); j++) {
                        System.out.printf("%-10s%-40s%-20s%-20s\n", tutorCount++, courseList.getEntry(chooseCourse).getCourseName(),
                                courseList.getEntry(chooseCourse).getTutorList().getEntry(j).getName(),
                                courseList.getEntry(chooseCourse).getTutorList().getEntry(j).getFaculty());
                    }
                }

                System.out.println("\n******************************************************************************************");
                System.out.printf("%-10s%-40s%-20s%-20s", "|No", " Course Name", "Tutorial Group ID", "Tutorial Group Name|");
                System.out.println("\n******************************************************************************************");

                for (int j = 1; courseList.getEntry(chooseCourse).getProgrammeList() !=null &&j <= courseList.getEntry(chooseCourse).getProgrammeList().getNumberOfEntries(); j++) {
                    for (int tg = 1; courseList.getEntry(chooseCourse).getProgrammeList().getEntry(j).getTutorialGroupList()!= null &&tg <= courseList.getEntry(chooseCourse).getProgrammeList().getEntry(j).getTutorialGroupList().getNumberOfEntries(); tg++) {
                        System.out.printf("%-10s%-40s%-20s%-20s\n", tutorialGroupCount++, courseList.getEntry(chooseCourse).getCourseName(),
                                courseList.getEntry(chooseCourse).getProgrammeList().getEntry(j).getTutorialGroupList().getEntry(tg).getTutorialGroupID(),
                                courseList.getEntry(chooseCourse).getProgrammeList().getEntry(j).getTutorialGroupList().getEntry(tg).getTutorialGroupName());
                    }
                }

                break;

            case 2:
                System.out.println("\n************************************************************************");
                System.out.printf("%-10s%-15s%-40s%-17s", "|No", " Tutor Name", "Course Nmae", "Taken|");
                System.out.println("\n************************************************************************");

                for (int tutorIndex = 1; tutorIndex <= tutorList.getNumberOfEntries(); tutorIndex++) {
                    String tutorName = tutorList.getEntry(tutorIndex).getName();

                    for (int courseIndex = 1; courseIndex <= courseList.getNumberOfEntries(); courseIndex++) {
                        String courseName = courseList.getEntry(courseIndex).getCourseName();
                        String status = "No Taken";

                        if (courseList.getEntry(courseIndex).getTutorList() != null) {
                            for (int tutorInCourseIndex = 1; tutorInCourseIndex <= courseList.getEntry(courseIndex).getTutorList().getNumberOfEntries(); tutorInCourseIndex++) {
                                String currentTutorName = courseList.getEntry(courseIndex).getTutorList().getEntry(tutorInCourseIndex).getName();

                                if (currentTutorName.equals(tutorName)) {
                                    status = "Taken";
                                    break; // Tutor found, no need to continue loop
                                }
                            }
                        }

                        System.out.printf("%-10s%-15s%-40s%-17s\n", recordCount, tutorName, courseName, status);
                        recordCount++;
                    }
                }

                break;

            case 3:
                System.out.println("\n************************************************************************");
                System.out.printf("%-10s%-20s%-20s%-20s", "|No", " Tutor Name", "Tutorial Group ID", "Tutorial Group Name|");
                System.out.println("\n************************************************************************");

                for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                    for (int j = 1; j <= tutorList.getEntry(i).getTutorialGroupList().getNumberOfEntries(); j++) {
                        System.out.printf("%-10s%-20s%-20s%-20s\n", recordFindCount++, tutorList.getEntry(i).getName(),
                                tutorList.getEntry(i).getTutorialGroupList().getEntry(j).getTutorialGroupID(),
                                tutorList.getEntry(i).getTutorialGroupList().getEntry(j).getTutorialGroupName());
                    }
                }
                break;

            case 4:
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private void filterTutor() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int tutorCount = 1;

        System.out.println("\n**************************");
        System.out.printf("%-1s %16s %7s\n", "|", "Filter Tutor", "|");
        System.out.println("**************************\n");
        System.out.println("1. Filter Tutor Under Particular Faculty");
        System.out.println("2. Filter Tutor Based On Credit Hours");
        System.out.println("3. Exit");
        System.out.println("Enter your choice");
        choice = input.nextInt();
        switch (choice) {
            case 1:
                int maxFaculties = tutorList.getNumberOfEntries();
                String[] uniqueFaculties = new String[maxFaculties];
                int uniqueIndex = 0;

                for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                    boolean alreadyExists = false;
                    String printFaculty = tutorList.getEntry(i).getFaculty().toUpperCase();

                    // Check if the faculty already exists in the uniqueFaculties array
                    for (int j = 0; j < uniqueIndex; j++) {
                        if (uniqueFaculties[j].equals(printFaculty)) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists) {
                        uniqueFaculties[uniqueIndex] = printFaculty;
                        uniqueIndex++;
                    }
                }
                System.out.println("\n*****************************");
                System.out.printf("%-10s%-15s", "|No", " Available Faculty|");
                System.out.println("\n*****************************");
                for (int f = 0; f < uniqueFaculties.length; f++) {
                    if (uniqueFaculties[f] != null) {
                        System.out.printf("%-11s%-15s\n", f + 1, uniqueFaculties[f]);
                    }
                }

                input.nextLine();
                System.out.println("Enter Faculty (eg.FOCS,FAFB):");
                String faculty = input.nextLine();

                while (faculty.isEmpty() == true) {
                    System.out.print("\nFaculty field is empty please enter again!");
                    System.out.print("\nRe-Enter Faculty: ");
                    faculty = input.nextLine();
                }

                System.out.println("\n******************************************************************************************");
                System.out.printf("%-10s%-20s%-20s%-25s%-20s", "|No", " Tutor Name", "Tutor Faculty", "Tutor Email", "Tutor Position|");
                System.out.println("\n******************************************************************************************");
                for (int t = 1; t <= tutorList.getNumberOfEntries(); t++) {
                    if (tutorList.getEntry(t).getFaculty().toUpperCase().equals(faculty.toUpperCase())) {
                        System.out.printf("%-10s%-20s%-20s%-25s%-20s\n", tutorCount++, tutorList.getEntry(t).getName(), tutorList.getEntry(t).getFaculty(), tutorList.getEntry(t).getEmail(), tutorList.getEntry(t).getPosition());
                    }

                }
                break;
            case 2:

                int maxHours = tutorList.getNumberOfEntries();
                int[] uniqueHours = new int[maxHours];
                int uniqueIndex2 = 0;

                for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                    boolean alreadyExists = false;
                    int printHours = tutorList.getEntry(i).getCreditHour();

                    // Check if the faculty already exists in the uniqueFaculties array
                    for (int j = 0; j < uniqueIndex2; j++) {
                        if (uniqueHours[j] == printHours) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists) {
                        uniqueHours[uniqueIndex2] = printHours;
                        uniqueIndex2++;
                    }
                }

                System.out.println("\n***************************");
                System.out.printf("%-10s%-15s", "|No", " Available Hours|");
                System.out.println("\n***************************");
                for (int f = 0; f < uniqueHours.length; f++) {
                    if (uniqueHours[f] > 0) {
                        System.out.printf("%-11s%-15s\n", f + 1, uniqueHours[f]);
                    }
                }
                System.out.println("Enter credit hours (eg.1,2,3):");
                int creditHours = input.nextInt();

                System.out.println("\n*****************************************************************************************************************");
                System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s", "|No", " Tutor Name", "Tutor Faculty", "Tutor Email", "Tutor Position", "Credit Hours|");
                System.out.println("\n****************************************************************************************************************");

                for (int t = 1; t <= tutorList.getNumberOfEntries(); t++) {
                    if (tutorList.getEntry(t).getCreditHour() == creditHours) {
                        System.out.printf("%-10s%-20s%-20s%-25s%-20s%-20s\n", tutorCount++, tutorList.getEntry(t).getName(), tutorList.getEntry(t).getFaculty(), tutorList.getEntry(t).getEmail(), tutorList.getEntry(t).getPosition(), tutorList.getEntry(t).getCreditHour());
                    }
                }
                break;
            case 3:
                break;

            default:
                System.out.println("Invalid choice !!");
                break;
        }

    }

    private void printReport() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        String formattedDateTime = now.format(formatter);
        int practicalCount = 0;
        int lectureCount = 0;
        int tutorialCount = 0;
        int totalCount = 0;
        String[] highestCourseNames = new String[courseList.getNumberOfEntries()];
        String[] highestCourseCodes = new String[courseList.getNumberOfEntries()];
        String[] lowestCourseNames = new String[courseList.getNumberOfEntries()];
        String[] lowestCourseCodes = new String[courseList.getNumberOfEntries()];
        int highestCount = 0;
        int lowestCount = Integer.MAX_VALUE; // Initialize to a large value
        int highestCourseCount = 0;
        int lowestCourseCount = 0;
        int focsCount = 0;
        int foasCount = 0;
        int fobeCount = 0;
        int foetCount = 0;
        int fcciCount = 0;
        int fsshCount = 0;
        int fafbCount = 0;
        int tutorCount = 0;
        int focsFacultyCount = 0;
        int foasFacultyCount = 0;
        int fobeFacultyCount = 0;
        int foetFacultyCount = 0;
        int fcciFacultyCount = 0;
        int fsshFacultyCount = 0;
        int fafbFacultyCount = 0;
        int tutoFacultyrCount = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("\n**************************");
        System.out.printf("%-1s %16s %7s\n", "|", "Summary Report", "|");
        System.out.println("**************************\n");

        System.out.print("=========================================================================================================================================================");
        System.out.printf("\n%100s\n", "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.printf("%85s\n\n", "TEACHING ASSIGNMENT SUBSYSTEM");
        System.out.printf("%30s\n", ".___..___.__. __ .  .._..  ..__   .__. __. __.._..__ .  ..  ..___.  ..___.   __..  ..  ..  ..__..__ .   ,  .__ .___.__ .__..__ .___.");
        System.out.printf("%30s\n", "  |  [__ [__]/  `|__| | |\\ |[ __  [__](__ (__  | [ __|\\ ||\\/|[__ |\\ |  |    (__ |  ||\\/||\\/|[__][__) \\./   [__)[__ [__)|  |[__)  |  ");
        System.out.printf("%30s\n", "  |  [___|  |\\__.|  |_|_| \\|[_./  |  |.__).__)_|_[_./| \\||  |[___| \\|  |    .__)|__||  ||  ||  ||  \\  |    |  \\[___|   |__||  \\  |  ");
        System.out.println("\nGenerate Time: " + formattedDateTime);

        System.out.printf("\n%-10s%-20s%-45s%-20s%-20s%-20s%-20s\n", "No", "Course Code", "Course Name", "Credit Hours", "Practical", "Lecture", "Tutorial");

        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            for (int j = 1; courseList.getEntry(i).getTutorList() != null && j <= courseList.getEntry(i).getTutorList().getNumberOfEntries(); j++) {
                if (courseList.getEntry(i).getTutorList().getEntry(j).getType().equals("Practical")) {
                    practicalCount++;
                } else if (courseList.getEntry(i).getTutorList().getEntry(j).getType().equals("Tutorial")) {
                    tutorialCount++;
                } else {
                    lectureCount++;
                }
            }

            totalCount = practicalCount + tutorialCount + lectureCount;
            if (totalCount > highestCount) {
                highestCount = totalCount;
                highestCourseCount = 0; // Reset count
                highestCourseNames[highestCourseCount] = courseList.getEntry(i).getCourseName();
                highestCourseCodes[highestCourseCount] = courseList.getEntry(i).getCourseCode();
                highestCourseCount++;
            } else if (totalCount == highestCount) {
                highestCourseNames[highestCourseCount] = courseList.getEntry(i).getCourseName();
                highestCourseCodes[highestCourseCount] = courseList.getEntry(i).getCourseCode();
                highestCourseCount++;
            }

            if (totalCount < lowestCount) {
                lowestCount = totalCount;
                lowestCourseCount = 0; // Reset count
                lowestCourseNames[lowestCourseCount] = courseList.getEntry(i).getCourseName();
                lowestCourseCodes[lowestCourseCount] = courseList.getEntry(i).getCourseCode();
                lowestCourseCount++;
            } else if (totalCount == lowestCount) {
                lowestCourseNames[lowestCourseCount] = courseList.getEntry(i).getCourseName();
                lowestCourseCodes[lowestCourseCount] = courseList.getEntry(i).getCourseCode();
                lowestCourseCount++;
            }

            System.out.printf("%-10s%-20s%-45s%-20s%-20s%-20s%-20s\n", i, courseList.getEntry(i).getCourseCode(), courseList.getEntry(i).getCourseName(), courseList.getEntry(i).getCreditHours(), practicalCount, lectureCount, tutorialCount);
            practicalCount =0;
            tutorialCount =0;
            lectureCount =0;
        }
        System.out.print("\nThe course(s) with the highest number of tutors assigned:\n");
        for (int k = 0; k < highestCourseCount; k++) {
            System.out.print("[ " + highestCount + " ] Tutors <" + highestCourseCodes[k] + " > <" + highestCourseNames[k] + " >\n");
        }

        System.out.print("\nThe course(s) with the lowest number of tutors assigned:\n");
        for (int k = 0; k < lowestCourseCount; k++) {
            System.out.print("[ " + lowestCount + " ] Tutors <" + lowestCourseCodes[k] + " > <" + lowestCourseNames[k] + " >\n");
        }

        System.out.printf("\n%80s\n", "End");

        System.out.print("=========================================================================================================================================================");

        System.out.print("\n=========================================================================================================================================================");
        System.out.printf("\n%100s\n", "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.printf("%85s\n\n", "TEACHING ASSIGNMENT SUBSYSTEM");
        System.out.printf("%30s\n", ".___..___.__. __ .  .._..  ..__   .__. __. __.._..__ .  ..  ..___.  ..___.   __..  ..  ..  ..__..__ .   ,  .__ .___.__ .__..__ .___.");
        System.out.printf("%30s\n", "  |  [__ [__]/  `|__| | |\\ |[ __  [__](__ (__  | [ __|\\ ||\\/|[__ |\\ |  |    (__ |  ||\\/||\\/|[__][__) \\./   [__)[__ [__)|  |[__)  |  ");
        System.out.printf("%30s\n", "  |  [___|  |\\__.|  |_|_| \\|[_./  |  |.__).__)_|_[_./| \\||  |[___| \\|  |    .__)|__||  ||  ||  ||  \\  |    |  \\[___|   |__||  \\  |  ");
        System.out.println("\nGenerate Time: " + formattedDateTime);

        System.out.printf("\n%-10s%-20s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "No", "Number Of Tutor", "FOCS", "FOAS", "FOBE", "FOET", "FCCI", "FSSH", "FAFB");

        String faculty;
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            tutorCount++;
            faculty = tutorList.getEntry(i).getFaculty();
            switch (faculty.toUpperCase()) {
                case "FOCS":
                    focsCount++;
                    focsFacultyCount = 1;
                    break;
                case "FOAS":
                    foasCount++;
                    foasFacultyCount = 1;
                    break;
                case "FOBE":
                    fobeCount++;
                    fobeFacultyCount = 1;
                    break;
                case "FOET":
                    foetCount++;
                    foetFacultyCount = 1;
                    break;
                case "FCCI":
                    fcciCount++;
                    fcciFacultyCount = 1;
                    break;
                case "FSSH":
                    fsshCount++;
                    fsshFacultyCount = 1;
                    break;
                case "FAFB":
                    fafbCount++;
                    fafbFacultyCount = 1;
                    break;

            }
            System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", i, tutorList.getEntry(i).getName(), focsFacultyCount, foasFacultyCount, fobeFacultyCount, foetFacultyCount, fcciFacultyCount, fsshFacultyCount, fafbFacultyCount);
            focsFacultyCount = 0;
            foasFacultyCount = 0;
            fobeFacultyCount = 0;
            foetFacultyCount = 0;
            fcciFacultyCount = 0;
            fsshFacultyCount = 0;
            fafbFacultyCount = 0;

        }

        int smallestCount = Integer.MAX_VALUE;
        int largestCount = Integer.MIN_VALUE;
        String smallestFaculty = "";
        String largestFaculty = "";

        int[] counts = {focsCount, foasCount, fobeCount, foetCount, fcciCount, fsshCount, fafbCount};
        String[] faculties = {"FOCS", "FOAS", "FOBE", "FOET", "FCCI", "FSSH", "FAFB"};

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] < smallestCount) {
                smallestCount = counts[i];
                smallestFaculty = faculties[i];
            }
            if (counts[i] > largestCount) {
                largestCount = counts[i];
                largestFaculty = faculties[i];
            }
        }
        System.out.print("\nThe most largest number of tutors for a faculty :\n");
        System.out.print("[ " + largestCount + " ] Tutors <" + largestFaculty + "> Faculty\n\n");
        System.out.print("\nThe most smallest number of tutors for a faculty :\n");
        System.out.print("[ " + smallestCount + " ] Tutors <" + smallestFaculty + "> Faculty\n\n");

        System.out.printf("\n%80s\n", "End");

        System.out.print("=========================================================================================================================================================\n");

        System.out.println("Press Enter to continue...");
        input.nextLine();
    }
}
///