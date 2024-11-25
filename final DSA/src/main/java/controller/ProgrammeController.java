/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import dao.CourseDao;
import entity.Course;
import entity.Faculty;
import entity.Programme;
import java.util.Scanner;

/**
 *
 * @author MingKing
 */


public class ProgrammeController {

    public static SortedLinkedListInterface<Faculty> presetFacultyList = CourseDao.presetFacultyList();
    public static SortedLinkedListInterface<Programme> pList = CourseController.presetProgrammeList;
    public static SortedLinkedListInterface<Course> cList = CourseController.presetCourseList;

    public void progMenu() {
        Scanner input = new Scanner(System.in);
        int option;

        do {
            System.out.println(CourseController.functionHeader("Programme Management"));
            System.out.println("| 1. Add New Programme                             |");
            System.out.println("| 2. Add A Programme To Course                     |");
            System.out.println("| 3. Remove Programme                              |");
            System.out.println("| 4. Remove A Programme From A Course              |");
            System.out.println("| 5. List All Programme                            |");
            System.out.println("| 0. Back to Main Menu                             |");
            System.out.println(CourseController.printLine(52));
            System.out.print("Select an option:");
            option = input.nextInt();

            switch (option) {
                case 1:
                    addNewProgramme();
                    break;
                case 2:
                    addProgrammeToCourse();
                    break;
                case 3:
                    removeProgramme();
                    break;
                case 4:
                    removeCourseFromProgramme();
                    break;
                case 5:
                    listAllProgramme();
                case 0:
                    input.nextLine();
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 0 and 3.");
            }
        } while (option != 0);
    }


    //add programme function
    private void addNewProgramme() {
        Scanner input = new Scanner(System.in);

        int facIndex, option;
        char yesNo;
        String progName, progLevel = null, progCode = null, progSemester;
        String facultyCode;

        do {
            System.out.println("List of All the Faculty");
            for (int i = 0; i < presetFacultyList.getNumberOfEntries(); i++) {
                System.out.println((i + 1) + ". " + presetFacultyList.get(i).getFacCode());
            }//for loop

            System.out.print("Select 1 faculty:");
            facIndex = input.nextInt();

            if (facIndex > presetFacultyList.getNumberOfEntries() || facIndex == 0) {
                System.out.println("Invalid faculty index");
            } else {
                facultyCode = presetFacultyList.get(facIndex - 1).getFacCode();
                input.nextLine();
                System.out.print("Enter Programme Name: ");
                progName = input.nextLine();

                do {
                    System.out.println("Programme Level: ");
                    System.out.println("1. Diploma");
                    System.out.println("2. Degree");
                    System.out.print("Select Programme Level:");
                    option = input.nextInt();

                    switch (option) {
                        case 1:
                            progLevel = "Diploma";
                            progCode = "D";
                            break;
                        case 2:
                            progLevel = "Degree";
                            progCode = "R";
                            break;
                        default:
                            System.out.println("Please select (1-2)");
                    }
                } while (option > 3 || option < 1);

                do {
                    progSemester = semesterList();
                    if (progSemester == null) {
                        System.out.println("Invalid semester index");
                    }
                } while (progSemester == null);

                progCode = getProgCode(progCode, progName, progSemester);

                boolean found = false;
                System.out.print("Confirm to create new programme? (YES=Y / NO=N)");
                yesNo = input.next().charAt(0);

                if (Character.toUpperCase(yesNo) == 'Y') {
                    for (int i = 0; !found && i < pList.getNumberOfEntries(); i++) {
                        if (progName.equalsIgnoreCase(pList.get(i).getProgName()) && progSemester.equals(pList.get(i).getProgSemester())) {
                            System.out.println("The programme already exist");
                            found = true;
                        }
                    }
                    if (!found) {

                        pList.add(new Programme(progCode, progName.toUpperCase(), progSemester, progLevel, facultyCode));
                        System.out.println("Programme " + progCode + " create successful");
                    }
                }
            }


            System.out.print("Continue to create new programme? (YES=Y / NO=N)");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) != 'N');

    }//addProgramme(1)

    private void addProgrammeToCourse() {
        Scanner input = new Scanner(System.in);
        int progIndex, courseIndex, facIndex;
        char yesNo;

        do {
            CourseController.listAllFaculty();
            System.out.print("Select a Programme:");
            facIndex = input.nextInt();

            if (facIndex > presetFacultyList.getNumberOfEntries() || facIndex == 0) {
                System.out.println("Invalid faculty index");
            } else {
                Faculty selectedFaculty = presetFacultyList.getEntry(facIndex);
                String facCode = selectedFaculty.getFacCode();

                //filter the programme based on faculty
                progIndex = listProgrammeAtFaculty(facCode);
                if (progIndex > pList.getNumberOfEntries() || progIndex == 0) {
                    System.out.println("Invalid programme index");
                } else {
                    Programme selectedProgramme = pList.getEntry(progIndex);

                    String progCode;
                    progCode = selectedProgramme.getProgCode();

                    Programme programme = getProgramme(selectedProgramme);

                    CourseController.listAllCourse();
                    System.out.print("Select a course：");
                    courseIndex = input.nextInt();

                    if (courseIndex > cList.getNumberOfEntries() || courseIndex == 0) {
                        System.out.println("Invalid course index");
                    } else {
                        //selected course data
                        Course selectedCourse = cList.getEntry(courseIndex);

                        String courseCode, courseName, courseLevel, courseCategory, courseType;
                        courseCode = selectedCourse.getCourseCode();
                        courseName = selectedCourse.getCourseName();
                        courseLevel = selectedCourse.getCourseLevel();
                        courseCategory = selectedCourse.getCourseCategory();
                        courseType = selectedCourse.getCourseType();
                        int creditHours = selectedCourse.getCreditHours();
                        double courseFee = selectedCourse.getCourseFee();

                        Course course = new Course(courseCode, courseName, courseLevel, courseCategory, courseType, creditHours, courseFee, false);

                        boolean found;
                        System.out.print("Confirm to add " + progCode + " to " + courseCode + "?(Yes=Y / No=N):");
                        yesNo = input.next().charAt(0);

                        if (Character.toUpperCase(yesNo) == 'Y') {

                            //check whether the programme already exist at selected course or not
                            if (selectedCourse.getProgrammeList() != null) {
                                found = CourseController.checkDuplicatedCourse(selectedCourse, progCode);
                                if (!found) {
                                    System.out.print(selectedProgramme.getProgCode() + " compulsory to take this course?(Y=YES / N=NO):");
                                    yesNo = input.next().charAt(0);
                                    if (Character.toUpperCase(yesNo) == 'Y') {
                                        course.setCourseType("COMPULSORY");
                                    } else {
                                        course.setCourseType("ELECTIVE");
                                    }
                                    selectedCourse.getProgrammeList().add(programme);


                                    //update presetProgrammeList
                                    if (selectedProgramme.getCourseList() != null) {
                                        selectedProgramme.getCourseList().add(course);
                                    } else {
                                        SortedLinkedListInterface<Course> newCourseList = new SortedDoublyLinkedList<>();
                                        newCourseList.add(course);
                                        selectedProgramme.setCourseList(newCourseList);

                                    }
                                    StudentController.updateStudentCourseList(course);
                                    System.out.println("Programme added successful");
                                }
                            }
                        }


                    }
                }
            }


            System.out.print("Continue to add course into programme ?(Y=YES / N=NO): ");
            yesNo = input.next().charAt(0);
        }
        while (Character.toUpperCase(yesNo) == 'Y');
    }//addProgrammeToCourse(2)
    //---------------------------------------------------------------------------------------

    //remove programme function
    private void removeProgramme() {
        Scanner input = new Scanner(System.in);
        int progIndex, facIndex;
        char yesNo;
        do {
            CourseController.listAllFaculty();
            System.out.print("Select a Faculty:");
            facIndex = input.nextInt();

            if (facIndex > presetFacultyList.getNumberOfEntries() || facIndex == 0) {
                System.out.println("Invalid faculty index");
            } else {
                Faculty selectedFaculty = presetFacultyList.getEntry(facIndex);
                String facCode = selectedFaculty.getFacCode();

                //filter the programme based on faculty
                progIndex = listProgrammeAtFaculty(facCode);

                if (progIndex == 0) {
                    System.out.println("Invalid programme index");
                } else {
                    Programme selectedProgramme = pList.getEntry(progIndex);
                    String progCode = selectedProgramme.getProgCode();

                    System.out.print("Confirm to remove " + progCode + "?(Y=YES / N=NO):");
                    yesNo = input.next().charAt(0);
                    if (Character.toUpperCase(yesNo) == 'Y') {
                        //remove the programme from programmeList at presetCourseList
                        for (int i = 0; i < cList.getNumberOfEntries(); i++) {
                            for (int x = 0; x < cList.get(i).getProgrammeList().getNumberOfEntries(); x++) {
                                if (progCode.equals(cList.get(i).getProgrammeList().get(x).getProgCode())) {
                                    cList.get(i).getProgrammeList().remove(cList.get(i).getProgrammeList().get(x));
                                    break;
                                }
                            }
                        }

                        //remove programme in presetProgrammeList
                        pList.remove(selectedProgramme);
                        StudentController.updateStudentProgramme(selectedProgramme);
                        System.out.println("Remove programme successful");
                    }
                }
            }

            System.out.print("Continue to remove programme?(Y=YES / N=NO):");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');

    }

    private void removeCourseFromProgramme() {
        Scanner input = new Scanner(System.in);
        int courseIndex, progIndex, facIndex;
        char yesNo;
        do {

            CourseController.listAllFaculty();
            System.out.print("Select a faculty:");
            facIndex = input.nextInt();

            if (facIndex > presetFacultyList.getNumberOfEntries() || facIndex == 0) {
                System.out.println("Invalid faculty index");
            } else {
                Faculty selectedFaculty = presetFacultyList.getEntry(facIndex);
                String facCode = selectedFaculty.getFacCode();

                //filter the programme based on faculty
                progIndex = listProgrammeAtFaculty(facCode);

                if (progIndex == 0) {
                    System.out.println("Invalid programme index");
                } else {
                    Programme selectedProgramme = pList.getEntry(progIndex);

                    //programme without courseList
                    Programme programme = getProgramme(selectedProgramme);

                    if (selectedProgramme.getCourseList() != null) {
                        System.out.println("\n" + CourseController.courseHeader());
                        //show all the course in the selected programme
                        for (int i = 0; i < selectedProgramme.getCourseList().getNumberOfEntries(); i++) {
                            System.out.println((i + 1) + ". " + selectedProgramme.getCourseList().get(i).toStringByRow());
                            System.out.println(CourseController.printLine(127));
                        }
                        System.out.print("\nSelect a course: ");
                        courseIndex = input.nextInt();

                        if (courseIndex > selectedProgramme.getCourseList().getNumberOfEntries() || courseIndex == 0) {
                            System.out.println("Invalid course index");
                        } else {
                            Course removedCourse = selectedProgramme.getCourseList().getEntry(courseIndex);
                            String courseCode = removedCourse.getCourseCode();

                            System.out.print("Confirm to remove " + programme.getProgCode() + " from " + courseCode + "?(Yes=Y / No=N):");
                            yesNo = input.next().charAt(0);

                            if (Character.toUpperCase(yesNo) == 'Y') {
                                //remove course at presetProgrammeList
                                selectedProgramme.getCourseList().remove(removedCourse);
                                pList.replace(progIndex, selectedProgramme);
                                StudentController.updateStudentCourseListAfterDeleteCourse(removedCourse);
                                //remove programme at presetCourseList
                                for (int i = 0; i < cList.getNumberOfEntries(); i++) {
                                    if (courseCode.equals(cList.get(i).getCourseCode())) {
                                        Course selectedCourse = cList.get(i);
                                        for (int x = 0; x < selectedCourse.getProgrammeList().getNumberOfEntries(); x++) {
                                            if (selectedCourse.getProgrammeList().get(x).getProgCode().equals(programme.getProgCode())) {
                                                selectedCourse.getProgrammeList().remove(selectedCourse.getProgrammeList().get(x));
                                                cList.get(i).getProgrammeList().replace(x + 1, selectedProgramme);
                                                break;
                                            }
                                        }
                                    }
                                }
                                System.out.println("Remove " + programme.getProgCode() + " from " + courseCode + "successful");
                            }
                        }
                    } else {
                        System.out.println(programme.getProgCode() + " did not have any courses");
                    }

                }

            }

            System.out.print("\nContinue to remove programme from course?(Yes=Y / No=N):");
            yesNo = input.next().charAt(0);
        } while (Character.toUpperCase(yesNo) == 'Y');
    }//removeCourseFromProgramme(3)

    private void listAllProgramme() {
        System.out.println("\nList of all programme");
        System.out.println(programmeHeader());
        for (int i = 0; i < CourseController.presetProgrammeList.getNumberOfEntries(); i++) {
            System.out.println((i + 1) + ". " + CourseController.presetProgrammeList.get(i).toStringByRow());
            System.out.println(CourseController.printLine(105));
        }
    }
    //---------------------------------------------------------------------------------------

    public static String getProgCode(String progCode, String progName, String progSemester) {
        String[] words = progName.split("\\s+");

        int count = 0;
        StringBuilder progCodeBuilder = new StringBuilder(progCode);
        for (String word : words) {
            if (!word.isEmpty() && count != 2) { // Check if the word is not empty and  extract only the first character of the first two words
                char firstChar = word.charAt(0); // Get the first character
                progCodeBuilder.append(Character.toUpperCase(firstChar));
                count++;
            }
        }
        progCode = progCodeBuilder.toString();
        progCode += progSemester;
        return progCode;
    }

    public String semesterList() {
        Scanner input = new Scanner(System.in);
        int option;

        System.out.println("\nSemester List: ");
        System.out.println("1. Year 1 Sem 1");
        System.out.println("2. Year 1 Sem 2");
        System.out.println("3. Year 1 Sem 3");
        System.out.println("4. Year 2 Sem 1");
        System.out.println("5. Year 2 Sem 2");
        System.out.println("6. Year 2 Sem 3");
        System.out.println("7. Year 3 Sem 1");
        System.out.println("8. Year 3 Sem 2");
        System.out.println("9. Year 3 Sem 3");
        System.out.print("Select a semester : ");
        option = input.nextInt();

        return switch (option) {
            case 1 -> "Y1S1";
            case 2 -> "Y1S2";
            case 3 -> "Y1S3";
            case 4 -> "Y2S1";
            case 5 -> "Y2S2";
            case 6 -> "Y2S3";
            case 7 -> "Y3S1";
            case 8 -> "Y3S2";
            case 9 -> "Y3S3";
            default -> null;
        };
    }

    private Programme getProgramme(Programme selectedProgramme) {
        String progCode, progName, progSemester, progLevel, facultyCode;
        progCode = selectedProgramme.getProgCode();
        progName = selectedProgramme.getProgName();
        progSemester = selectedProgramme.getProgSemester();
        progLevel = selectedProgramme.getProgLevel();
        facultyCode = selectedProgramme.getFacultyCode();
        return new Programme(progCode, progName, progSemester, progLevel, facultyCode);
    }

    public Integer listProgrammeAtFaculty(String facCode) {
        Scanner input = new Scanner(System.in);
        int progIndex, num = 1;

        SortedLinkedListInterface<Programme> progList = new SortedDoublyLinkedList<>();

        System.out.println("\nList of All the Programme in " + facCode);
        System.out.println(programmeHeader());
        for (int i = 0; i < pList.getNumberOfEntries(); i++) {
            if (pList.get(i).getFacultyCode().equals(facCode)) {
                progList.add(pList.get(i));
                System.out.println((num) + ". " + pList.get(i).toStringByRow());
                System.out.println(CourseController.printLine(107));
                num++;
            }
        }
        System.out.print("Select a Programme : ");
        progIndex = input.nextInt();

        if (progIndex > progList.getNumberOfEntries() || progIndex == 0) {
            return 0;
        } else {
            Programme selectedProgramme = progList.getEntry(progIndex);
            String progCode = selectedProgramme.getProgCode();

            for (int i = 0; i < pList.getNumberOfEntries(); i++) {
                if (progCode.equals(pList.get(i).getProgCode())) {
                    progIndex = i + 1;
                    break;
                }
            }
            return progIndex;
        }

    }

    public String programmeHeader() {
        String header = String.format("%-6s%-15s%-40s%-14s%-16s%-15s",
                "No.", "Programme ", "Programme Name", "Semester", "Level", "Faculty");

        // Calculate the length of the header string
        int headerLength = header.length();

        // Create a dotted line string of the same length as the header
        String line = new String(new char[headerLength]).replace('\0', '-');

        // Concatenate the dotted line before and after the header
        return line + "\n" + header + "\n" + line;
    }


}
