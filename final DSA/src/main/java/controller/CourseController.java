/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import entity.Course;
import entity.Faculty;
import entity.Programme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author MingKing
 */


public class CourseController {
    public final static double MAIN_COURSE_FEE = 259;
    public final static double MPU_COURSE_FEE = 239;
    public static SortedLinkedListInterface<Course> presetCourseList;
    public static SortedLinkedListInterface<Programme> presetProgrammeList;
    public static SortedLinkedListInterface<Faculty> presetFacultyList;
    ProgrammeController programmeManagement = new ProgrammeController();


    // course menu
    public void courseMenu() {
        Scanner input = new Scanner(System.in);
        int option;
        do {
            System.out.println(printLine(26));
            System.out.println("| Course Management Menu | ");
            System.out.println(printLine(26));
            System.out.println("| 1. Add Course          |");
            System.out.println("| 2. Search Course       |");
            System.out.println("| 3. Amend Course        |");
            System.out.println("| 4. Remove Course       |");
            System.out.println("| 5. Course Report       |");
            System.out.println("| 0. Back To Main Menu   |");
            System.out.println(printLine(26));
            System.out.print("Select an option:");
            option = input.nextInt();
            switch (option) {
                case 1:
                    addCourseMenu();
                    break;
                case 2:
                    searchCourseMenu();
                    break;
                case 3:
                    amendCourseMenu();
                    break;
                case 4:
                    removeCourseMenu();
                    break;
                case 5:
                    courseReportMenu();
                    break;
                case 0:
                    input.nextLine();
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 0 and 5.");
            }
        } while (option != 0);
    }


    private void addCourseMenu() {
        Scanner input = new Scanner(System.in);
        int option;
        System.out.println("\n" + printLine(37));
        System.out.println("|           Add Course              |");
        System.out.println(printLine(37));
        System.out.println("| 1. Add New Course                 |");
        System.out.println("| 2. Add Course To A Programme      |");
        System.out.println("| 0. Back To Course Management Menu |");
        System.out.println(printLine(37));


        System.out.print("Select an option:");
        option = input.nextInt();
        switch (option) {
            case 1:
                addNewCourse();
                break;
            case 2:
                addCourseToProgramme();
                break;
            case 0:
                input.nextLine();
                break;
            default:
                System.out.println("Invalid option. Please enter a number between 0 and 2.");
        }
    }


    private void searchCourseMenu() {
        Scanner input = new Scanner(System.in);
        int option;


        System.out.println(functionHeader("  Search Course   "));
        System.out.println("|1. Search Course Offered In A Semester          |");
        System.out.println("|2. List All Course Taken By Different Faculties |");
        System.out.println("|3. List All Course For A Programme              |");
        System.out.println("|4. List All Courses                             |");
        System.out.println("|0. Back to Course Management Menu               |");
        System.out.println(printLine(50));
        System.out.print("Select an option:");
        option = input.nextInt();
        switch (option) {
            case 1:
                searchCourseInSemester();
                break;
            case 2:
                listAllCourseFromFaculty();
                break;
            case 3:
                listAllCourseForProgramme();
                break;
            case 4:
                listAllCourse();
                break;
            case 0:
                input.nextLine();
                break;
            default:
                System.out.println("Invalid option. Please enter a number between 0 and 4.");
        }
    }


    private void amendCourseMenu() {
        Scanner input = new Scanner(System.in);
        int option;


        System.out.println(functionHeader(" Amend Course"));
        System.out.println("| 1. Amend Course Details For All Programme |");
        System.out.println("| 2. Amend Course Details For A Programme   |");
        System.out.println("| 0. Back To Course Management Menu         |");
        System.out.println(printLine(45));
        System.out.print("Select an option:");
        option = input.nextInt();
        switch (option) {
            case 1:
                amendCourse();
                break;
            case 2:
                amendCourseDetailForProgramme();
                break;
            case 0:
                input.nextLine();
                break;
            default:
                System.out.println("Invalid option. Please enter a number between 0 and 2.");
        }
    }


    private void removeCourseMenu() {
        Scanner input = new Scanner(System.in);
        int option;


        System.out.println(functionHeader("Remove Course"));
        System.out.println("| 1. Remove A Course                        |");
        System.out.println("| 2. Remove Programme From A Course         |");
        System.out.println("| 0. Back To Course Management Menu         |");
        System.out.println(printLine(45));
        System.out.print("Select an option:");
        option = input.nextInt();
        switch (option) {
            case 1:
                removeCourse();
                break;
            case 2:
                removeProgrammeFromCourse();
                break;
            case 0:
                input.nextLine();
                break;
            default:
                System.out.println("Invalid option. Please enter a number between 0 and 2.");
        }


    }


    private void courseReportMenu() {
        Scanner input = new Scanner(System.in);
        int option;


        System.out.println(functionHeader("Course Report"));
        System.out.println("| 1. Course Summary Report                  |");
        System.out.println("| 2. Course Summary Report By Semester      |");
        System.out.println("| 0. Back To Course Management Menu         |");
        System.out.println(printLine(45));
        System.out.print("Select an option:");
        option = input.nextInt();
        switch (option) {
            case 1:
                courseSummaryReport();
                break;
            case 2:
                courseSemesterReport();
                break;
            case 0:
                input.nextLine();
                break;
            default:
                System.out.println("Invalid option. Please enter a number between 0 and 2.");
        }
    }


    //----------------------------------------------------------------------------------------------


    //add function
    private void addNewCourse() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder courseCode = null;
        String courseName, courseType, courseCategory = null;
        String courseLevel = null;
        int selectedLevel, creditHours, selectedCategory;
        double courseFee;
        char yesNo;


        do {
            System.out.println("\n" + functionHeader("Add new course"));
            //course name
            System.out.print("Course Name:");
            courseName = scanner.nextLine();


            //course category
            do {
                System.out.println("\nCourse Category:");
                System.out.println("1.Main");
                System.out.println("2.General Studies/Mata Pelajaran Umum (MPU)");
                System.out.print("Select Course Category:");
                selectedCategory = scanner.nextInt();


                switch (selectedCategory) {
                    case 1:
                        courseCode = new StringBuilder("BAC");
                        courseCategory = "MAIN";
                        break;
                    case 2:
                        courseCode = new StringBuilder("MPU");
                        courseCategory = "MPU";
                        break;
                    default:
                        System.out.println("Please Enter (1-2)");
                }
            } while (selectedCategory < 1 || selectedCategory > 2);


            //course level
            do {
                System.out.println("\nCourse Level:");
                System.out.println("1.Introductory");
                System.out.println("2.Intermediate");
                System.out.println("3.Advanced");
                System.out.print("Select Course level:");
                selectedLevel = scanner.nextInt();


                switch (selectedLevel) {
                    case 1:
                        if (selectedCategory == 1) {
                            courseCode = new StringBuilder(courseCode + "S");
                        } else {
                            courseCode = new StringBuilder(courseCode + "-");
                        }
                        courseLevel = "INTRODUCTORY";
                        break;
                    case 2:
                        if (selectedCategory == 1) {
                            courseCode = new StringBuilder(courseCode + "T");
                        } else {
                            courseCode = new StringBuilder(courseCode + "-");
                        }
                        courseLevel = "INTERMEDIATE";
                        break;
                    case 3:
                        if (selectedCategory == 1) {
                            courseCode = new StringBuilder(courseCode + "I");
                        } else {
                            courseCode = new StringBuilder(courseCode + "-");
                        }
                        courseLevel = "ADVANCED";
                        break;
                    default:
                        System.out.println("Please Enter (1-3)");
                }
            } while (selectedLevel >= 4 || selectedLevel < 1);


            // credit hours
            do {
                System.out.print("\nEnter Credit Hours: ");
                creditHours = scanner.nextInt();


                if (creditHours > 4 || creditHours < 1) {
                    System.out.println("Please Enter (1-4)");
                }
            } while (creditHours < 1 || creditHours > 4);


            //course type
            if (courseCategory.equals("MPU")) {
                courseType = "COMPULSORY";
            } else {
                courseType = " ";
            }


            //course code
            courseCode.append(Course.getNextCourseCode());


            //course fee
            if (courseCategory.equals("MAIN")) {
                courseFee = MAIN_COURSE_FEE * creditHours;
            } else {
                courseFee = MPU_COURSE_FEE * creditHours;
            }


            Course newCourse = new Course(courseCode.toString(), courseName.toUpperCase(), courseLevel, courseCategory, courseType, creditHours, courseFee, true);


            //show course details
            courseDetailsHeader();


            if (newCourse.getCourseType().equals("COMPULSORY")) {
                System.out.print(newCourse.toStringByColumn2());
                System.out.println(printLine(61));
            } else {
                System.out.print(newCourse.toStringByColumn());
                System.out.println(printLine(61));
            }


            System.out.print("Confirm to add " + courseCode + " " + courseName + "?(Y=YES / N=NO): ");
            yesNo = scanner.next().charAt(0);


            if (Character.toUpperCase(yesNo) == 'Y') {
                //check duplicate course
                boolean found = false;


                for (int i = 0; !found && i < presetCourseList.getNumberOfEntries(); i++) {
                    if (courseName.equals(presetCourseList.get(i).getCourseName())) {
                        System.out.println("This course already exist");
                        found = true;
                    }
                }


                if (!found) {
                    presetCourseList.add(newCourse);
                    System.out.println("Course added successful");


                    //add course to programme
                    System.out.print("\nDo you want to add " + courseCode + " to a programme?(Y=YES / N=NO):");
                    yesNo = scanner.next().charAt(0);
                    if (Character.toUpperCase(yesNo) == 'Y') {
                        addNewCourseToProgramme(newCourse);
                    } else {
                        SortedLinkedListInterface<Programme> progList = new SortedDoublyLinkedList<>();
                        newCourse.setProgrammeList(progList);
                    }
                }
            }
            System.out.print("\nContinue to add new course?(Y=YES / N=NO): ");
            yesNo = scanner.next().charAt(0);
            scanner.nextLine();


        } while (Character.toUpperCase(yesNo) != 'N');
    }//addNewCourse(1.1)


    private void addNewCourseToProgramme(Course newCourse) {
        Scanner input = new Scanner(System.in);
        int progIndex, facIndex;
        char yesNo;


        listAllFaculty();
        System.out.print("Select a faculty:");
        facIndex = input.nextInt();

        if (facIndex > presetFacultyList.getNumberOfEntries() || facIndex == 0) {
            System.out.println("Invalid faculty index");
        } else {
            Faculty selectedFaculty = presetFacultyList.getEntry(facIndex);

            String facCode = selectedFaculty.getFacCode();


            //filter the programme based on faculty
            progIndex = programmeManagement.listProgrammeAtFaculty(facCode);
            Programme selectedProgramme = presetProgrammeList.getEntry(progIndex);


            courseDetailsHeader();
            if (newCourse.getCourseType().equals("COMPULSORY")) {
                System.out.print(newCourse.toStringByColumn2());
                System.out.println(printLine(61));
            } else if (newCourse.getCourseType().equals(" ")) {
                System.out.print(newCourse.toStringByColumn());
                System.out.println(printLine(61));
                System.out.print(selectedProgramme.getProgCode() + " compulsory to take " + newCourse.getCourseCode() + "?(Yes=Y / No=N):");
                yesNo = input.next().charAt(0);


                if (Character.toUpperCase(yesNo) == 'Y') {
                    newCourse.setCourseType("COMPULSORY");
                } else {
                    newCourse.setCourseType("ELECTIVE");
                }


                courseDetailsHeader();
                System.out.print(newCourse.toStringByColumn2());
                System.out.println(printLine(61));
            }


            System.out.print("Confirm to add " + newCourse.getCourseCode() + " into " + selectedProgramme.getProgCode() + "?(Y=YES / N=NO):");
            yesNo = input.next().charAt(0);


            Course selectedCourse;
            if (Character.toUpperCase(yesNo) == 'Y') {


                //add programme at the programmeList in course
                SortedLinkedListInterface<Programme> newProgList = new SortedDoublyLinkedList<>();
                newProgList.add(selectedProgramme);


                for (int i = 0; i < presetCourseList.getNumberOfEntries(); i++) {
                    if (presetCourseList.get(i).getCourseCode().equals(newCourse.getCourseCode())) {
                        selectedCourse = presetCourseList.get(i);
                        selectedCourse.setProgrammeList(newProgList);
                    }
                }


                // add course at the courseList in programme
                if (selectedProgramme.getCourseList() != null) {
                    selectedProgramme.getCourseList().add(newCourse);
                } else {
                    SortedLinkedListInterface<Course> newCourseList = new SortedDoublyLinkedList<>();
                    newCourseList.add(newCourse);
                    selectedProgramme.setCourseList(newCourseList);
                }
                System.out.println(newCourse.getCourseCode() + " added into " + selectedProgramme.getProgCode() + " successfully");
                StudentController.updateStudentCourseList(newCourse);
            }
        }

    }//addNewCourseToProgramme(1.2)


    private void addCourseToProgramme() {
        Scanner input = new Scanner(System.in);
        int courseIndex, progIndex;
        char yesNo;


        do {
            listAllCourse();
            System.out.print("\nSelect a course：");
            courseIndex = input.nextInt();


            if (courseIndex > presetCourseList.getNumberOfEntries() || courseIndex == 0) {
                System.out.println("Invalid course index");
            } else {
                Course selectedCourse = presetCourseList.getEntry(courseIndex);


                //get the course without programmeList
                Course course = getCourse(selectedCourse);


                //check which programme did not take the selected course
                SortedLinkedListInterface<Programme> progList = new SortedDoublyLinkedList<>();
                if (selectedCourse.getProgrammeList().getNumberOfEntries() == presetProgrammeList.getNumberOfEntries()) {
                    System.out.println(selectedCourse.getCourseCode() + " " + selectedCourse.getCourseName() + " is taken by all the programme");
                } else {
                    for (int i = 0; i < presetProgrammeList.getNumberOfEntries(); i++) {
                        boolean found = false;


                        if (selectedCourse.getProgrammeList() != null) {
                            for (int x = 0; x < selectedCourse.getProgrammeList().getNumberOfEntries(); x++) {
                                if (presetProgrammeList.get(i).getProgCode().equals(selectedCourse.getProgrammeList().get(x).getProgCode())) {
                                    found = true;
                                }
                            }
                            if (!found) {
                                String progCode, progName, progSemester, progLevel, facultyCode;
                                progCode = presetProgrammeList.get(i).getProgCode();
                                progName = presetProgrammeList.get(i).getProgName();
                                progSemester = presetProgrammeList.get(i).getProgSemester();
                                progLevel = presetProgrammeList.get(i).getProgLevel();
                                facultyCode = presetProgrammeList.get(i).getFacultyCode();
                                Programme nonSelectedProg = new Programme(progCode, progName, progSemester, progLevel, facultyCode);
                                progList.add(nonSelectedProg);
                            }
                        } else {
                            progList.add(presetProgrammeList.get(i));
                        }
                    }


                    if (progList.getNumberOfEntries() > 0) {
                        System.out.println("\nList of all the programme");
                        System.out.println(programmeManagement.programmeHeader());
                        for (int i = 0; i < progList.getNumberOfEntries(); i++) {
                            System.out.println((i + 1) + ". " + progList.get(i).toStringByRow());
                            System.out.println(printLine(106));
                        }


                        System.out.print("\nSelect a programme: ");
                        progIndex = input.nextInt();

                        if (progIndex > progList.getNumberOfEntries() || progIndex == 0) {
                            System.out.println("Invalid programme index");
                        } else {
                            Programme selectedProgramme = progList.getEntry(progIndex);
                            String progCode = selectedProgramme.getProgCode();


                            System.out.print(progCode + " are compulsory to take " + course.getCourseCode() + "?(Y=YES / N=NO):");
                            yesNo = input.next().charAt(0);


                            if (Character.toUpperCase(yesNo) == 'Y') {
                                selectedCourse.setCourseType("COMPULSORY");
                                course.setCourseType("COMPULSORY");
                            } else {
                                selectedCourse.setCourseType("ELECTIVE");
                                course.setCourseType("ELECTIVE");
                            }


                            System.out.print("Confirm to add " + course.getCourseCode() + " into " + progCode + "?(Yes=Y / No=N):");
                            yesNo = input.next().charAt(0);


                            if (Character.toUpperCase(yesNo) == 'Y') {


                                //add programme to the programmeList in the presetCourseList
                                if (selectedCourse.getProgrammeList() != null) {
                                    selectedCourse.getProgrammeList().add(selectedProgramme);
                                }


                                //add course to the courseList in the presetProgrammeList
                                for (int i = 0; i < presetProgrammeList.getNumberOfEntries(); i++) {
                                    if (presetProgrammeList.get(i).getCourseList() != null && progCode.equals(presetProgrammeList.get(i).getProgCode())) {

                                        presetProgrammeList.get(i).getCourseList().add(course);
                                    }
                                }
                                System.out.println("Course " + course.getCourseCode() + " added into " + progCode + " successful");
                            }
                        }

                    }
                }
            }


            System.out.print("\nContinue to add new course to programme?(Yes=Y / No=N):");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }//addCourseToProgramme(2)


    //----------------------------------------------------------------------------------------------


    //search function
    private void searchCourseInSemester() {
        Scanner input = new Scanner(System.in);
        String progSemester;
        char yesNo;
        do {
            SortedLinkedListInterface<Course> courseList = new SortedDoublyLinkedList<>();
            progSemester = programmeManagement.semesterList();


            if (presetProgrammeList.getNumberOfEntries() > 1) {
                for (int i = 0; i < presetProgrammeList.getNumberOfEntries(); i++) {
                    if (presetProgrammeList.get(i).getProgSemester().equals(progSemester)) {
                        for (int x = 0; x < presetProgrammeList.get(i).getCourseList().getNumberOfEntries(); x++) {
                            courseList.add(presetProgrammeList.get(i).getCourseList().get(x));
                        }
                    }
                }
            }


            if (courseList.getNumberOfEntries() > 1) {
                int num = 1;
                System.out.println("\nAll the courses offered in " + progSemester);
                System.out.println(courseHeader());
                for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
                    if ((i + 1) < courseList.getNumberOfEntries() && courseList.get(i).getCourseCode().equals(courseList.get(i + 1).getCourseCode())) {
                        courseList.remove(courseList.get(i + 1));


                    } else {
                        System.out.println(num + ". " + courseList.get(i).toStringByRow());
                        System.out.println(printLine(127));
                        num++;
                    }
                }


            } else {
                System.out.println("No courses are being offered in " + progSemester);
            }
            System.out.print("\nContinue to search course in a semester?(Yes=Y / No=No):");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }//searchCourseInSemester(1)


    private void listAllCourseFromFaculty() {
        Scanner input = new Scanner(System.in);
        int facIndex;
        char yesNo;
        do {
            listAllFaculty();
            System.out.print("Select a Faculty : ");
            facIndex = input.nextInt();

            if (facIndex > presetFacultyList.getNumberOfEntries() || facIndex == 0) {
                System.out.println("Invalid faculty index");
            } else {
                Faculty selectedFaculty = presetFacultyList.getEntry(facIndex);
                String facCode = selectedFaculty.getFacCode();
                SortedLinkedListInterface<Course> courseList = new SortedDoublyLinkedList<>();


                //add all the course from different programme into 1 courseList
                for (int i = 0; i < presetProgrammeList.getNumberOfEntries(); i++) {
                    if (facCode.equals(presetProgrammeList.get(i).getFacultyCode())) {
                        for (int x = 0; x < presetProgrammeList.get(i).getCourseList().getNumberOfEntries(); x++) {
                            courseList.add(presetProgrammeList.get(i).getCourseList().get(x));
                        }//for
                    }//if
                }//for


                int num = 1;


                System.out.println("\nList of All the Course");
                System.out.println(courseHeader());
                for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
                    if (courseList.get(i + 1) != null && courseList.get(i).getCourseCode().equals(courseList.get(i + 1).getCourseCode())) {
                        Course duplicateCourse = courseList.get(i + 1);
                        courseList.remove(duplicateCourse);
                    } else {
                        System.out.println((num) + ". " + courseList.get(i).toStringByRow());
                        System.out.println(printLine(127));
                        num++;
                    }
                }
            }


            System.out.print("Continue search course from a faculty?(Y=YES / N=NO):");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }//listAllCourseFromFaculty(2)


    private void listAllCourseForProgramme() {
        Scanner input = new Scanner(System.in);
        int progIndex, facIndex;
        char yesNo;


        do {
            listAllFaculty();
            System.out.print("Select a Faculty : ");
            facIndex = input.nextInt();


            if (facIndex > presetFacultyList.getNumberOfEntries() || facIndex == 0) {
                System.out.println("Invalid faculty index");
            } else {
                Faculty selectedFaculty = presetFacultyList.getEntry(facIndex);
                String facCode = selectedFaculty.getFacCode();


                //filter the programme based on faculty
                progIndex = programmeManagement.listProgrammeAtFaculty(facCode);
                if (progIndex != 0) {
                    Programme selectedProgramme = presetProgrammeList.getEntry(progIndex);
                    System.out.println("\nList of All the Course");
                    System.out.println(courseHeader2());
                    for (int i = 0; i < selectedProgramme.getCourseList().getNumberOfEntries(); i++) {
                        System.out.println((i + 1) + ". " + selectedProgramme.getCourseList().get(i).toStringByRow2());
                        System.out.println(printLine(142));
                    }
                } else {
                    System.out.println("Invalid programme index");
                }


            }

            System.out.print("Continue search course from a programme?(Y=YES / N=NO):");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }//listAllCourseForProgramme(3)


    //----------------------------------------------------------------------------------------------


    //amend function
    private void amendCourse() {
        Scanner input = new Scanner(System.in);
        int option, courseIndex, newCreditHours, selectedLevel, selectedCategory;
        String newCourseName, newCourseLevel = null, oldCourseCode, newCourseCategory = null;
        double newCourseFee;
        String newCourseCode = null;
        char newChar = 0, yesNo;


        do {
            listAllCourse();
            System.out.print("Select a course : ");
            courseIndex = input.nextInt();

            if (courseIndex > presetCourseList.getNumberOfEntries() || courseIndex == 0) {
                System.out.println("Invalid course index");
            } else {
                Course selectedCourse = presetCourseList.getEntry(courseIndex);


                oldCourseCode = selectedCourse.getCourseCode();
                System.out.println(functionHeader("Course Details"));
                System.out.println("| 1. Course Name                             |");
                System.out.println("| 2. Course Level                            |");
                System.out.println("| 3. Course Category                         |");
                System.out.println("| 4. Course Credit Hours                     |");
                System.out.println(printLine(46));
                System.out.print("Select a course details to amend : ");
                option = input.nextInt();


                switch (option) {
                    case 1://change course name
                        System.out.print("\nEnter the new course name:");
                        input.nextLine();
                        newCourseName = input.nextLine();
                        System.out.print("Confirm to amend " + selectedCourse.getCourseName() + " to " + newCourseName.toUpperCase() + "?(Yes=Y / No=N):");
                        yesNo = input.next().charAt(0);
                        if (Character.toUpperCase(yesNo) == 'Y') {
                            System.out.println("Amend course name successful");
                            selectedCourse.setCourseName(newCourseName.toUpperCase());
                        }
                        break;


                    case 2://change course level
                        do {
                            System.out.println("\nCourse Level:");
                            System.out.println("1.Introductory");
                            System.out.println("2.Intermediate");
                            System.out.println("3.Advanced");
                            System.out.print("Select Course level:");
                            selectedLevel = input.nextInt();


                            switch (selectedLevel) {
                                case 1:
                                    newChar = 'S';
                                    newCourseLevel = "INTRODUCTORY";
                                    break;
                                case 2:
                                    newChar = 'T';
                                    newCourseLevel = "INTERMEDIATE";
                                    break;
                                case 3:
                                    newChar = 'I';
                                    newCourseLevel = "ADVANCED";
                                    break;
                                default:
                                    System.out.println("Please Enter (1-3)");
                            }


                        } while (selectedLevel >= 4 || selectedLevel < 1);


                        System.out.print("Confirm to amend " + selectedCourse.getCourseLevel() + " to " + newCourseLevel + "?(Yes=Y / No=N):");
                        yesNo = input.next().charAt(0);


                        if (Character.toUpperCase(yesNo) == 'Y') {
                            selectedCourse.setCourseLevel(newCourseLevel);
                            //change courseCode
                            if (selectedCourse.getCourseCategory().equals("MAIN")) {
                                newCourseCode = selectedCourse.getCourseCode().substring(0, 3) + newChar + selectedCourse.getCourseCode().substring(4);
                                selectedCourse.setCourseCode(newCourseCode);
                            }
                            System.out.println("Amend course level successful");
                        }
                        break;


                    case 3://change course category
                        System.out.println("\nCourse Category:");
                        System.out.println("1.Main");
                        System.out.println("2.General Studies/Mata Pelajaran Umum (MPU)");
                        System.out.print("Select Course Category:");
                        selectedCategory = input.nextInt();


                        switch (selectedCategory) {
                            case 1:
                                newCourseCategory = "MAIN";


                                newChar = switch (selectedCourse.getCourseLevel()) {
                                    case "INTRODUCTORY" -> 'S';
                                    case "INTERMEDIATE" -> 'T';
                                    case "ADVANCED" -> 'I';
                                    default -> newChar;
                                };


                                newCourseCode = "BAC" + newChar + selectedCourse.getCourseCode().substring(4);
                                break;
                            case 2:
                                newCourseCategory = "MPU";
                                newCourseCode = "MPU-" + selectedCourse.getCourseCode().substring(4);
                                break;
                        }


                        System.out.print("\nConfirm to amend " + selectedCourse.getCourseCategory() + " to " + newCourseCategory + "?(Yes=Y / No=N):");
                        yesNo = input.next().charAt(0);


                        if (Character.toUpperCase(yesNo) == 'Y') {


                            selectedCourse.setCourseCategory(newCourseCategory);
                            selectedCourse.setCourseType("COMPULSORY");
                            //change courseCode
                            selectedCourse.setCourseCode(newCourseCode);


                            if (selectedCourse.getCourseCategory().equals(newCourseCategory)) {
                                newCourseFee = selectedCourse.getCreditHours() * MAIN_COURSE_FEE;
                                selectedCourse.setCourseFee(newCourseFee);
                            }


                            if (selectedCourse.getCourseCategory().equals(newCourseCategory)) {
                                newCourseFee = selectedCourse.getCreditHours() * MPU_COURSE_FEE;
                                selectedCourse.setCourseFee(newCourseFee);
                            }
                            System.out.println("Amend course category successful");
                        }
                        break;


                    case 4://change creditHours
                        do {
                            System.out.print("\nEnter Credit Hours: ");
                            newCreditHours = input.nextInt();


                            if (newCreditHours > 4 || newCreditHours < 1) {
                                System.out.println("Please Enter (1-4)");
                            }
                        } while (newCreditHours < 1 || newCreditHours > 4);


                        System.out.print("Confirm to amend course credit hours from " + selectedCourse.getCreditHours() + " to " + newCreditHours + "?(Yes=Y / No=N):");
                        yesNo = input.next().charAt(0);


                        if (Character.toUpperCase(yesNo) == 'Y') {
                            selectedCourse.setCreditHours(newCreditHours);


                            if (selectedCourse.getCourseCategory().equals("MAIN")) {
                                newCourseFee = newCreditHours * MAIN_COURSE_FEE;
                                selectedCourse.setCourseFee(newCourseFee);
                            }


                            if (selectedCourse.getCourseCategory().equals("MPU")) {
                                newCourseFee = newCreditHours * MPU_COURSE_FEE;
                                selectedCourse.setCourseFee(newCourseFee);
                            }
                            System.out.println("Amend course credit hours successful");
                        }
                        break;
                    default:
                        System.out.println("Invalid option");
                }


                courseDetailsHeader();
                System.out.print(selectedCourse.toStringByColumn2());
                System.out.println(printLine(61));


                //sort the presetCourseList after amend course details
                presetCourseList.sort();


                //update the course detail in presetProgrammeList
                for (int i = 0; i < presetProgrammeList.getNumberOfEntries(); i++) {
                    SortedLinkedListInterface<Course> courseList = presetProgrammeList.get(i).getCourseList();


                    Course course = getCourse(selectedCourse);
                    for (int x = 0; x < courseList.getNumberOfEntries(); x++) {
                        if (courseList.get(x).getCourseCode().equals(oldCourseCode)) {
                            courseList.remove(courseList.get(x));
                            courseList.add(course);
                            StudentController.updateStudentCourseList(course);
                            break;
                        }
                    }
                    presetProgrammeList.get(i).setCourseList(courseList);
                }
            }

            System.out.print("Continue to amend course details?(Y=YES / N=NO):");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }//amendCourse(1)

    private void amendCourseDetailForProgramme() {
        Scanner input = new Scanner(System.in);
        char yesNo;
        int progIndex, courseIndex, facIndex;

        do {
            listAllFaculty();
            System.out.print("Select a Programme : ");
            facIndex = input.nextInt();

            if (facIndex > presetFacultyList.getNumberOfEntries() || facIndex == 0) {
                System.out.println("Invalid faculty index");
            } else {
                Faculty selectedFaculty = presetFacultyList.getEntry(facIndex);
                String facCode = selectedFaculty.getFacCode();


                //filter the programme based on faculty
                progIndex = programmeManagement.listProgrammeAtFaculty(facCode);
                if (progIndex == 0) {
                    System.out.println("Invalid programme index");
                } else {
                    Programme selectedProgramme = presetProgrammeList.getEntry(progIndex);


                    System.out.println("\n" + courseHeader2());
                    for (int i = 0; i < selectedProgramme.getCourseList().getNumberOfEntries(); i++) {
                        System.out.println((i + 1) + ". " + selectedProgramme.getCourseList().get(i).toStringByRow2());
                        System.out.println(printLine(142));
                    }


                    System.out.print("\nSelect a course : ");
                    courseIndex = input.nextInt();


                    Course selectedCourse = selectedProgramme.getCourseList().getEntry(courseIndex);
                    int option;
                    int selectedType;
                    String courseType = null;
                    courseDetailsHeader();
                    System.out.print(selectedCourse.toStringByColumn2());
                    System.out.println(printLine(61));


                    System.out.println(functionHeader("Course details"));
                    System.out.println("| 1. Course Type                             |");
                    System.out.println("| 0. Cancel amend details                    |");
                    System.out.println(printLine(46));
                    System.out.print("Select a course detail to amend : ");
                    option = input.nextInt();


                    if (option == 1) {
                        if (selectedCourse.getCourseCategory().equals("MAIN")) {
                            do {
                                System.out.println("\nCourse Type:");
                                System.out.println("1. Compulsory");
                                System.out.println("2. Elective");
                                System.out.print("Select a course type : ");
                                selectedType = input.nextInt();


                                switch (selectedType) {
                                    case 1:
                                        courseType = "COMPULSORY";
                                        break;
                                    case 2:
                                        courseType= "ELECTIVE";
                                        break;
                                    default:
                                        System.out.println("Please insert 1 or 2");
                                }
                            } while (selectedType < 1 || selectedType > 2);

                            System.out.print("Confirm to amend course type " + selectedCourse.getCourseType() + " to " + courseType + " ?(Y=YES / N=NO)");
                            yesNo = input.next().charAt(0);

                            if (Character.toUpperCase(yesNo) == 'Y'){
                                selectedCourse.setCourseType(courseType);
                                System.out.println("\nAmend course details successful");
                                courseDetailsHeader();
                                System.out.print(selectedCourse.toStringByColumn2());
                                System.out.println(printLine(61));
                            }

                        } else {
                            System.out.println(selectedCourse.getCourseCode() + " is MPU course and it is Compulsory for all programme.");
                        }
                    }

                }

            }

            System.out.print("\nContinue to amend course details for a programme?(Yes=Y / No=N):");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }//amendCourseDetailForProgramme(2)


    //----------------------------------------------------------------------------------------------


    //remove
    private void removeCourse() {
        Scanner input = new Scanner(System.in);
        int courseIndex;
        char yesNo;
        do {
            listAllCourse();
            System.out.print("Select a course : ");
            courseIndex = input.nextInt();

            if (courseIndex > presetCourseList.getNumberOfEntries() || courseIndex == 0) {
                System.out.println("Invalid course index");
            } else {
                Course selectedCourse = presetCourseList.getEntry(courseIndex);


                System.out.print("Confirm to remove " + selectedCourse.getCourseCode() + " " + selectedCourse.getCourseName() + " ?(Y=YES / N=NO):");
                yesNo = input.next().charAt(0);


                if (Character.toUpperCase(yesNo) == 'Y') {
                    StudentController.updateStudentCourseListAfterDeleteCourse(selectedCourse);
                    presetCourseList.remove(selectedCourse);


                    //update the course detail in presetProgrammeList
                    for (int i = 0; i < presetProgrammeList.getNumberOfEntries(); i++) {
                        SortedLinkedListInterface<Course> courseList = presetProgrammeList.get(i).getCourseList();
                        String courseCode = selectedCourse.getCourseCode();


                        for (int x = 0; x < courseList.getNumberOfEntries(); x++) {
                            if (courseList.get(x).getCourseCode().equals(courseCode)) {
                                courseList.remove(courseList.get(x));
                            }
                        }
                        presetProgrammeList.get(i).setCourseList(courseList);
                    }
                    System.out.println("Remove " + selectedCourse.getCourseCode() + " successful");
                }
            }

            System.out.print("\nContinue to remove a course?(Y=YES / N=NO): ");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }//removeCourse(1)


    private void removeProgrammeFromCourse() {
        Scanner input = new Scanner(System.in);
        int courseIndex, progIndex;
        char yesNo;


        do {
            //select a course
            listAllCourse();
            System.out.print("\nSelect a course：");
            courseIndex = input.nextInt();

            if (courseIndex > presetCourseList.getNumberOfEntries() || courseIndex == 0) {
                System.out.println("Invalid course index");
            } else {
                Course selectedCourse = presetCourseList.getEntry(courseIndex);
                String courseCode;
                courseCode = selectedCourse.getCourseCode();


                if (selectedCourse.getProgrammeList() != null) {
                    //show all the programme in the selectedCourse
                    System.out.println(programmeManagement.programmeHeader());
                    for (int i = 0; i < selectedCourse.getProgrammeList().getNumberOfEntries(); i++) {
                        System.out.println((i + 1) + ". " + selectedCourse.getProgrammeList().get(i).toStringByRow());
                        System.out.println(printLine(106));
                    }
                    System.out.print("\nSelect a programme: ");
                    progIndex = input.nextInt();


                    if (progIndex > selectedCourse.getProgrammeList().getNumberOfEntries() || progIndex == 0) {
                        System.out.println("Invalid programme index");
                    } else {
                        // get the selectedProgramme from the programmeList in the Course
                        Programme removedProgram = selectedCourse.getProgrammeList().getEntry(progIndex);
                        String progCode = removedProgram.getProgCode();


                        System.out.print("Confirm to remove " + progCode + " from " + courseCode + "?(Y=YES / N=NO):");
                        yesNo = input.next().charAt(0);


                        if (Character.toUpperCase(yesNo) == 'Y') {


                            //remove programme at presetCourseList
                            selectedCourse.getProgrammeList().remove(removedProgram);


                            //remove course at presetProgrammeList
                            for (int i = 0; i < presetProgrammeList.getNumberOfEntries(); i++) {
                                if (progCode.equals(presetProgrammeList.get(i).getProgCode())) {
                                    for (int x = 0; x < presetProgrammeList.get(i).getCourseList().getNumberOfEntries(); x++) {
                                        if (courseCode.equals(presetProgrammeList.get(i).getCourseList().get(x).getCourseCode())) {
                                            Course course = presetProgrammeList.get(i).getCourseList().get(x);
                                            presetProgrammeList.get(i).getCourseList().remove(course);
                                            System.out.println("Programme remove successful");
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    System.out.println(courseCode + " haven't add to any Programme yet");
                    System.out.print("Add" + courseCode + " to a programme?(Y=YES / N=NO):");
                    yesNo = input.next().charAt(0);
                    if (Character.toUpperCase(yesNo) == 'Y') {
                        addNewCourseToProgramme(selectedCourse);
                        courseMenu();
                    }
                }

            }

            System.out.print("\nContinue to remove the course from a programme?(Y=YES / N=NO):");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }//removeCourseFromProgramme(2)


    //----------------------------------------------------------------------------------------------


    private void courseSummaryReport() {
        SortedLinkedListInterface<Course> courseListReport = new SortedDoublyLinkedList<>();


        //report header
        courseSummaryReportHeader();
        System.out.println("\n" + "All course details:");
        System.out.println(courseHeaderReport());


        //get the total course taken by different programme
        for (int i = 0; i < presetCourseList.getNumberOfEntries(); i++) {
            if (presetCourseList.get(i).getProgrammeList().getNumberOfEntries() > 0) {
                courseListReport.add(presetCourseList.get(i));
            }
        }


        // 0 programme take the course is not counted
        int totalCourse = courseListReport.getNumberOfEntries();


        //calculate the total of main and mpu course
        int totalMainCourse = 0;
        int totalMPUCourse = 0;


        for (int i = 0; i < courseListReport.getNumberOfEntries(); i++) {


            if (courseListReport.get(i).getCourseCategory().equals("MAIN")) {
                totalMainCourse++;
            } else if (courseListReport.get(i).getCourseCategory().equals("MPU")) {
                totalMPUCourse++;
            }
        }


        //calculate each course have how many faculty take and programme take
        for (int i = 0; i < courseListReport.getNumberOfEntries(); i++) {
            Course selectedCourse = courseListReport.get(i);
            int totalFacultyTaken = 0;
            int facFOCS = 0, facFAFB = 0, facFCCI = 0;


            //get each course have how many programme take
            int totalProgrammeOffered = selectedCourse.getProgrammeList().getNumberOfEntries();


            //get each course have how many faculty take
            for (int x = 0; x < selectedCourse.getProgrammeList().getNumberOfEntries(); x++) {
                if (selectedCourse.getProgrammeList().get(x).getFacultyCode().equals("FOCS")) {
                    facFOCS++;
                } else if (selectedCourse.getProgrammeList().get(x).getFacultyCode().equals("FAFB")) {
                    facFAFB++;
                } else if (selectedCourse.getProgrammeList().get(x).getFacultyCode().equals("FCCI")) {
                    facFCCI++;
                }
            }


            if (facFOCS > 0) {
                totalFacultyTaken++;
            }
            if (facFAFB > 0) {
                totalFacultyTaken++;
            }
            if (facFCCI > 0) {
                totalFacultyTaken++;
            }


            System.out.printf(" %02d.%3s%-10s%-10s%-50s%5s%-10S%8s%02d%4s/%9s%02d\n", (i + 1), " ", courseListReport.get(i).getCourseCode(), " ", courseListReport.get(i).getCourseName(), " ", courseListReport.get(i).getCourseCategory(), " ", totalFacultyTaken, " ", " ", totalProgrammeOffered);
        }


        System.out.println("\n" + printLine(126));
        System.out.printf("\n %5s %02d %-7s%8s %02d %4s  |  %02d %3s", "TOTAL", totalCourse, "COURSES:", " ", totalMainCourse, "MAIN", totalMPUCourse, "MPU");
        System.out.println("\n\n [ NOTE: MPU = General Studies/Mata Pelajaran Umum ]");
        System.out.println("\n" + printLine(126));


        int highestProgrammeOffered = courseListReport.get(0).getProgrammeList().getNumberOfEntries();
        int lowestProgrammeOffered = courseListReport.get(0).getProgrammeList().getNumberOfEntries();
        SortedLinkedListInterface<Course> highestCourseList = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Course> lowestCourseList = new SortedDoublyLinkedList<>();


        //get the highest programme offered
        for (int i = 0; i < courseListReport.getNumberOfEntries(); i++) {
            Course selectedCourse = courseListReport.get(i);


            if (selectedCourse.getProgrammeList().getNumberOfEntries() > highestProgrammeOffered) {
                highestProgrammeOffered = selectedCourse.getProgrammeList().getNumberOfEntries();
            }
        }


        //check whether the highest course have more than 1 or not
        for (int x = 0; x < courseListReport.getNumberOfEntries(); x++) {
            Course selectedCourse = courseListReport.get(x);
            if (selectedCourse.getProgrammeList().getNumberOfEntries() == highestProgrammeOffered) {
                highestCourseList.add(selectedCourse);
            }
        }


        //get the lowest programme offered
        for (int k = 0; k < courseListReport.getNumberOfEntries(); k++) {
            Course selectedCourse = courseListReport.get(k);


            if (selectedCourse.getProgrammeList().getNumberOfEntries() < lowestProgrammeOffered) {
                lowestProgrammeOffered = selectedCourse.getProgrammeList().getNumberOfEntries();
            }
        }


        //check whether the lowest course have more than 1 or not
        for (int z = 0; z < courseListReport.getNumberOfEntries(); z++) {
            Course selectedCourse = courseListReport.get(z);


            if (selectedCourse.getProgrammeList().getNumberOfEntries() == lowestProgrammeOffered) {
                lowestCourseList.add(selectedCourse);
            }
        }


        //display the highest programme offered course
        System.out.print(" HIGHEST PROGRAMMES OFFERED: ");
        if (highestCourseList.getNumberOfEntries() > 1) {
            System.out.printf("[ %02d %s ]", highestProgrammeOffered, "PROGRAMMES");
            System.out.printf(" <%8S> %s\n", highestCourseList.get(0).getCourseCode(), highestCourseList.get(0).getCourseName());
            for (int i = 1; i < highestCourseList.getNumberOfEntries(); i++) {
                System.out.printf("%46s <%8S> %s\n", " ", highestCourseList.get(i).getCourseCode(), highestCourseList.get(i).getCourseName());
            }
        } else {
            System.out.printf(" [%02d %s] <%8S> %s\n", highestProgrammeOffered, "PROGRAMMES", highestCourseList.get(0).getCourseCode(), highestCourseList.get(0).getCourseName());
        }


        //display the lowest programme offered course
        System.out.print("\n LOWEST PROGRAMMES OFFERED : ");
        if (lowestCourseList.getNumberOfEntries() > 1) {
            System.out.printf("[ %02d %s ]", lowestProgrammeOffered, "PROGRAMMES");
            System.out.printf(" <%8S> %s\n", lowestCourseList.get(0).getCourseCode(), lowestCourseList.get(0).getCourseName());
            for (int i = 1; i < lowestCourseList.getNumberOfEntries(); i++) {
                System.out.printf("%46s <%8S> %s\n", " ", lowestCourseList.get(i).getCourseCode(), lowestCourseList.get(i).getCourseName());
            }
        } else {
            System.out.printf(" [%02d %s]  <%8S> %s\n", lowestProgrammeOffered, "PROGRAMMES", lowestCourseList.get(0).getCourseCode(), lowestCourseList.get(0).getCourseName());
        }
        System.out.println("\n [ NOTE: 0 PROGRAMME OFFERED IS NOT COUNTED ]");
        System.out.println("\n" + printLine(47) + "END OF THE COURSE SUMMARY REPORT" + printLine(47));
    }


    private void courseSemesterReport() {
        Scanner input = new Scanner(System.in);
        char yesNo;
        String progSemester;


        do {
            SortedLinkedListInterface<Course> courseListReport = new SortedDoublyLinkedList<>();
            progSemester = programmeManagement.semesterList();

            if (progSemester == null) {
                System.out.println("Invalid semester index");
            } else {
                //get the total course taken by different programme
                for (int i = 0; i < presetCourseList.getNumberOfEntries(); i++) {
                    boolean found = false;
                    if (presetCourseList.get(i).getProgrammeList().getNumberOfEntries() > 0) {
                        for (int x = 0; x < presetCourseList.get(i).getProgrammeList().getNumberOfEntries(); x++) {
                            if (presetCourseList.get(i).getProgrammeList().get(x).getProgSemester().equals(progSemester)) {
                                found = true;
                                break;
                            }
                        }
                    }
                    if (found) {
                        courseListReport.add(presetCourseList.get(i));
                    }
                }


                if (courseListReport.getNumberOfEntries() > 1) {
                    courseSemesterReportHeader(progSemester);
                    System.out.println("\n" + "All course details offered in " + progSemester + ":");
                    System.out.println(courseHeaderReport());


                    // 0 programme take the course is not counted
                    int totalCourse = courseListReport.getNumberOfEntries();


                    //calculate the total of main and mpu course
                    int totalMainCourse = 0;
                    int totalMPUCourse = 0;


                    for (int i = 0; i < courseListReport.getNumberOfEntries(); i++) {


                        if (courseListReport.get(i).getCourseCategory().equals("MAIN")) {
                            totalMainCourse++;
                        } else if (courseListReport.get(i).getCourseCategory().equals("MPU")) {
                            totalMPUCourse++;
                        }
                    }


                    //calculate each course have how many faculty take and programme take
                    for (int i = 0; i < courseListReport.getNumberOfEntries(); i++) {
                        Course selectedCourse = courseListReport.get(i);
                        int totalFacultyTaken = 0;
                        int facFOCS = 0, facFAFB = 0, facFCCI = 0;
                        //get each course have how many programme take
                        int totalProgrammeOffered = selectedCourse.getProgrammeList().getNumberOfEntries();


                        if (selectedCourse.getProgrammeList() != null) {
                            //get each course have how many faculty take
                            for (int x = 0; x < selectedCourse.getProgrammeList().getNumberOfEntries(); x++) {
                                if (selectedCourse.getProgrammeList().get(x).getFacultyCode().equals("FOCS")) {
                                    facFOCS++;
                                } else if (selectedCourse.getProgrammeList().get(x).getFacultyCode().equals("FAFB")) {
                                    facFAFB++;
                                } else if (selectedCourse.getProgrammeList().get(x).getFacultyCode().equals("FCCI")) {
                                    facFCCI++;
                                }
                            }


                            if (facFOCS > 0) {
                                totalFacultyTaken++;
                            }
                            if (facFAFB > 0) {
                                totalFacultyTaken++;
                            }
                            if (facFCCI > 0) {
                                totalFacultyTaken++;
                            }
                        }
                        System.out.printf(" %02d.%3s%-10s%-10s%-50s%5s%-10S%8s%02d%4s/%9s%02d\n", (i + 1), " ", courseListReport.get(i).getCourseCode(), " ", courseListReport.get(i).getCourseName(), " ", courseListReport.get(i).getCourseCategory(), " ", totalFacultyTaken, " ", " ", totalProgrammeOffered);
                    }


                    System.out.println("\n" + printLine(126));
                    System.out.printf("\n %5s %02d %-7s%8s %02d %4s  |  %02d %3s", "TOTAL", totalCourse, "COURSES:", " ", totalMainCourse, "MAIN", totalMPUCourse, "MPU");
                    System.out.println("\n\n [ NOTE: MPU = General Studies/Mata Pelajaran Umum ]");
                    System.out.println("\n" + printLine(126));


                    if (courseListReport.get(0).getProgrammeList() != null) {
                        int highestProgrammeOffered = courseListReport.get(0).getProgrammeList().getNumberOfEntries();
                        int lowestProgrammeOffered = courseListReport.get(0).getProgrammeList().getNumberOfEntries();


                        SortedLinkedListInterface<Course> highestCourseList = new SortedDoublyLinkedList<>();
                        SortedLinkedListInterface<Course> lowestCourseList = new SortedDoublyLinkedList<>();


                        //get the highest programme offered
                        for (int i = 0; i < courseListReport.getNumberOfEntries(); i++) {
                            Course selectedCourse = courseListReport.get(i);


                            if (selectedCourse.getProgrammeList().getNumberOfEntries() > highestProgrammeOffered) {
                                highestProgrammeOffered = selectedCourse.getProgrammeList().getNumberOfEntries();
                            }
                        }


                        //check whether the highest course have more than 1 or not
                        for (int x = 0; x < courseListReport.getNumberOfEntries(); x++) {
                            Course selectedCourse = courseListReport.get(x);
                            if (selectedCourse.getProgrammeList().getNumberOfEntries() == highestProgrammeOffered) {
                                highestCourseList.add(selectedCourse);
                            }
                        }


                        //get the lowest programme offered
                        for (int k = 0; k < courseListReport.getNumberOfEntries(); k++) {
                            Course selectedCourse = courseListReport.get(k);


                            if (selectedCourse.getProgrammeList().getNumberOfEntries() < lowestProgrammeOffered) {
                                lowestProgrammeOffered = selectedCourse.getProgrammeList().getNumberOfEntries();
                            }
                        }


                        //check whether the lowest course have more than 1 or not
                        for (int z = 0; z < courseListReport.getNumberOfEntries(); z++) {
                            Course selectedCourse = courseListReport.get(z);


                            if (selectedCourse.getProgrammeList().getNumberOfEntries() == lowestProgrammeOffered) {
                                lowestCourseList.add(selectedCourse);
                            }
                        }


                        //display the highest programme offered course
                        System.out.print(" HIGHEST PROGRAMMES OFFERED: ");
                        if (highestCourseList.getNumberOfEntries() > 1) {
                            System.out.printf("[ %02d %s ]", highestProgrammeOffered, "PROGRAMMES");
                            System.out.printf(" <%8S> %s\n", highestCourseList.get(0).getCourseCode(), highestCourseList.get(0).getCourseName());
                            for (int i = 1; i < highestCourseList.getNumberOfEntries(); i++) {
                                System.out.printf("%46s <%8S> %s\n", " ", highestCourseList.get(i).getCourseCode(), highestCourseList.get(i).getCourseName());
                            }
                        } else {
                            System.out.printf(" [%02d %s] <%8S> %s\n", highestProgrammeOffered, "PROGRAMMES", highestCourseList.get(0).getCourseCode(), highestCourseList.get(0).getCourseName());
                        }


                        //display the lowest programme offered course
                        System.out.print("\n LOWEST PROGRAMMES OFFERED : ");
                        if (lowestCourseList.getNumberOfEntries() > 1) {
                            System.out.printf("[ %02d %s ]", lowestProgrammeOffered, "PROGRAMMES");
                            System.out.printf(" <%8S> %s\n", lowestCourseList.get(0).getCourseCode(), lowestCourseList.get(0).getCourseName());
                            for (int i = 1; i < lowestCourseList.getNumberOfEntries(); i++) {
                                System.out.printf("%46s <%8S> %s\n", " ", lowestCourseList.get(i).getCourseCode(), lowestCourseList.get(i).getCourseName());
                            }
                        } else {
                            System.out.printf(" [%02d %s] <%8S> %s\n", lowestProgrammeOffered, "PROGRAMMES", lowestCourseList.get(0).getCourseCode(), lowestCourseList.get(0).getCourseName());
                        }
                        System.out.println("\n [ NOTE: 0 PROGRAMME OFFERED IS NOT COUNTED ]");
                        System.out.println("\n" + printLine(47) + "END OF THE COURSE SUMMARY REPORT" + printLine(47));
                    } else {
                        System.out.println("This Semester did not have any course taken by any programme");
                    }
                } else {
                    System.out.println("This semester did not offered any course");
                }
            }

            System.out.print("Continue to generate a Course Summary Report by Semester?(Y=YES / N=NO): ");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }


    //extra
    public static void listAllCourse() {
        System.out.println("\nList of All the Course");
        System.out.println(courseHeader());


        for (int i = 0; i < presetCourseList.getNumberOfEntries(); i++) {
            System.out.println((i + 1) + ". " + presetCourseList.get(i).toStringByRow());
            System.out.println(printLine(127));
        }
    }


    private Course getCourse(Course selectedCourse) {
        String courseCode, courseName, courseLevel, courseCategory, courseType;
        courseCode = selectedCourse.getCourseCode();
        courseName = selectedCourse.getCourseName();
        courseLevel = selectedCourse.getCourseLevel();
        courseCategory = selectedCourse.getCourseCategory();
        courseType = selectedCourse.getCourseType();
        int creditHours = selectedCourse.getCreditHours();
        double courseFee = selectedCourse.getCourseFee();
        return new Course(courseCode, courseName, courseLevel, courseCategory, courseType, creditHours, courseFee, false);
    }


    public static boolean checkDuplicatedCourse(Course selectedCourse, String progCode) {
        boolean found = false;


        for (int i = 0; !found && i < selectedCourse.getProgrammeList().getNumberOfEntries(); i++) {
            if (progCode.equals(selectedCourse.getProgrammeList().get(i).getProgCode())) {
                System.out.println("This Course already taken by " + progCode);
                return true;
            }
        }//for
        return found;
    }


    //----------------------------------------------------------------------------------------------


    //header
    public static String functionHeader(String functionName) {
        int nameLength = functionName.length();
        String header = String.format("|%-15s%-" + nameLength + "s%-15s|", " ", functionName, " ");


        int headerLength = header.length();
        // Create a dotted line string of the same length as the header
        String line = new String(new char[headerLength]).replace('\0', '-');


        // Concatenate the dotted line before and after the header
        return line + "\n" + header + "\n" + line;
    }


    public static String courseHeader() {
        String header = String.format("%-6s%-15s%-40s%-20s%-15s%-15s%-16s",
                "No.", "Course Code", "Course Name", "Level", "Category", "Credit Hours", "Course Fee");


        // Calculate the length of the header string
        int headerLength = header.length();


        // Create a dotted line string of the same length as the header
        String line = new String(new char[headerLength]).replace('\0', '-');


        // Concatenate the dotted line before and after the header
        return line + "\n" + header + "\n" + line;
    } //without course type(COMPULSORY,ELECTIVE)


    public static String courseHeader2() {
        String header = String.format("%-6s%-15s%-40s%-20s%-15s%-15s%-15s%-16s",
                "No.", "Course Code", "Course Name", "Level", "Category", "Type", "Credit Hours", "Course Fee");

        // Calculate the length of the header string
        int headerLength = header.length();

        // Create a dotted line string of the same length as the header
        String line = new String(new char[headerLength]).replace('\0', '-');

        // Concatenate the dotted line before and after the header
        return line + "\n" + header + "\n" + line;
    }//with course type(COMPULSORY,ELECTIVE)


    public void courseDetailsHeader() {
        String functionName = "Course Details";
        System.out.println("\n" + printLine(61));
        System.out.printf("|%22s%-14s%22s |\n", " ", functionName, " ");
        System.out.println(printLine(61));
    }


    public static String facultyHeader() {
        String header = String.format("%-6s%-15s%-50s",
                "No.", "Faculty", "Faculty Name");

        // Calculate the length of the header string
        int headerLength = header.length();

        // Create a dotted line string of the same length as the header
        String line = new String(new char[headerLength]).replace('\0', '-');

        // Concatenate the dotted line before and after the header
        return line + "\n" + header + "\n" + line;
    }


    public static void listAllFaculty() {
        int num = 71;


        System.out.println("\nList of all the Faculty");
        System.out.println(facultyHeader());
        for (int i = 0; i < presetFacultyList.getNumberOfEntries(); i++) {
            System.out.println((i + 1) + ". " + presetFacultyList.get(i).toStringByRow());
            System.out.println(printLine(num));
        }
    }

    public static String printLine(int num) {
        return new String(new char[num]).replace('\0', '-');
    }

    public void courseSummaryReportHeader() {
        System.out.println(printLine(126));
        System.out.printf("\n%-32s %s\n", " ", "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.printf("%-46s %s\n", " ", "COURSE MANAGEMENT SUBSYSTEM");
        System.out.printf("\n%-49s %s\n", " ", "COURSE SUMMARY REPORT");
        System.out.println("\n" + printLine(126));
        System.out.println("\nGenerated at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, hh:mm a")));
    }


    public void courseSemesterReportHeader(String progSemester) {
        System.out.println(printLine(126));
        System.out.printf("\n%-32s %s\n", " ", "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.printf("%-46s %s\n", " ", "COURSE MANAGEMENT SUBSYSTEM");
        System.out.printf("\n%-45s %s\n", " ", "COURSE SUMMARY REPORT ON " + progSemester);
        System.out.println("\n" + printLine(126));
        System.out.println("\nGenerated at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, hh:mm a")));
    }

    public static String courseHeaderReport() {
        String header = String.format("%-7s%-20s%-50s%-20s%-15s",
                " NO.", "COURSE CODE", "COURSE NAME", "COURSE CATEGORY", "FACULTIES / PROGRAMME OFFERED ");
        // Calculate the length of the header string
        int headerLength = header.length();

        // Create a dotted line string of the same length as the header
        String line = new String(new char[headerLength]).replace('\0', '-');

        // Concatenate the dotted line before and after the header
        return line + "\n" + header + "\n" + line;
    } //without course type(COMPULSORY,ELECTIVE)
}
//
