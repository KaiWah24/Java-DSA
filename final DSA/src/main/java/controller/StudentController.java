/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 * @author FungPin
 */


import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import entity.Billing;
import entity.Course;
import entity.Programme;
import entity.Student;
import util.FormatUtil;
import util.GeneralUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class StudentController {
    public static SortedLinkedListInterface<Student> studentList;
    public void studentMenu() {
        Scanner input = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n-------Student Registration Management Subsystem-------");
            System.out.println("1. Add New Student");
            System.out.println("2. Remove a Student");
            System.out.println("3. Amend Student Details");
            System.out.println("4. Search Students for Registered Courses");
            System.out.println("5. Add Student to Courses");
            System.out.println("6. Remove a Student from a Course");
            System.out.println("7. Calculate Fee Paid for Registered Courses");
            System.out.println("8. Filter Students for Courses Based on Criteria");
            System.out.println("9. Generate Summary Reports");
            System.out.println("10. Search for student");
            System.out.println("11. Descending sort based on student id");
            System.out.println("12. Ascending sort based on student id");
            System.out.println("99. Print all student list");
            System.out.println("0. Exit");
            System.out.println("-------------------------------------------------------\n");
            System.out.print("Enter an option: ");


            choice = input.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    updateStudentDetails();
                    break;
                case 4:
                    searchStudentsForRegisteredCourse();
                    break;
                case 5:
                    addStudentToCourse();
                    break;
                case 6:
                    removeStudentFromCourse();
                    break;
                case 7:
                    calculateCourseFee();
                    break;
                case 8:
                    filterStudentsForCourses();
                    break;
                case 9:
                    reportMenu();
                    break;
                case 10:
                    searchForStudent();
                    break;
                case 11:
                    System.out.println("Before descending sorting");
                    System.out.println(studentList.toString());
                    System.out.println("After descending sorting");
                    studentList.reverseSort();
                    System.out.println(studentList.toString());
                    break;

                case 12:
                    System.out.println("Before ascending sorting");
                    System.out.println(studentList.toString());
                    System.out.println("After ascending sorting");
                    studentList.sort();
                    System.out.println(studentList.toString());
                    break;

                case 99:
                    System.out.println(FormatUtil.printStudentHeader());
                    System.out.println(studentList);
                    break;
                case 0:
                    System.out.println("Exiting to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 0 and 12.");
            }
        } while (choice != 0);
    }

    private void addStudent() {
        Scanner input = new Scanner(System.in);
        String NRIC;
        String mobileNumber;
        char gender;
        System.out.print("Enter first name eg. Fung Pin: ");
        String firstName = input.nextLine();


        while (firstName == null || firstName.isEmpty()) {
            System.out.println("First name cannot be empty. Please enter first name:");
            firstName = input.nextLine();
        }


        System.out.print("Enter last name: ");
        String lastName = input.nextLine();


        while (lastName == null || lastName.isEmpty()) {
            System.out.println("Last name cannot be empty. Please enter first name:");
            lastName = (input.nextLine());
        }

        Student tempStudent = new Student(firstName, lastName);
        do {
            System.out.print("Enter gender (M/F):");
            gender = input.next().charAt(0);


            switch (Character.toUpperCase(gender)) {
                case 'M':
                case 'F':
                    tempStudent.setGender(Character.toUpperCase(gender));
                    break;
                default:
                    System.out.println("Invalid gender entered");
                    break;
            }
        } while (Character.toUpperCase(gender) != 'M' && Character.toUpperCase(gender) != 'F');
        input.nextLine();
        do {
            System.out.print("Enter NRIC eg 123456121234: ");
            NRIC = input.nextLine();


            if (GeneralUtil.isValidNRIC(NRIC, studentList)) {
                tempStudent.setNRIC(NRIC);
            } else {
                System.out.println("Invalid NRIC entered ");
            }
        } while (!GeneralUtil.isValidNRIC(NRIC, studentList));


        do {
            System.out.print("Enter mobile contact number: ");
            mobileNumber = input.nextLine();
            if (GeneralUtil.isValidMobileContact(mobileNumber)) {
                tempStudent.setMobileContactNo(mobileNumber);
            } else {
                System.out.println("Invalid phone number");
            }
        } while (!GeneralUtil.isValidMobileContact(mobileNumber));


        System.out.println("Enter address line 1:");
        tempStudent.setAddress1(input.nextLine());


        System.out.println("Enter address line 2:");
        tempStudent.setAddress2(input.nextLine());


        System.out.println("Enter address line 3:");
        tempStudent.setAddress3(input.nextLine());


        System.out.println("Enter postal code:");
        tempStudent.setPostalCode(input.nextLine());


        System.out.println("Enter state:");
        tempStudent.setState(input.nextLine());


        System.out.println("Enter city:");
        tempStudent.setCity(input.nextLine());


        studentList.add(tempStudent);
        System.out.println("Student added");
        System.out.println(FormatUtil.printStudentHeader());
        System.out.println(tempStudent);


    }


    private void removeStudent() {
        Scanner input = new Scanner(System.in);


        System.out.println("Please enter the student id that you want to remove: ");
        String inputStudentID = input.nextLine();


        Student currentStudent = searchStudentWithStudentId(inputStudentID);


        if (currentStudent != null) {
            System.out.println("Are you sure you want to remove the below student");
            System.out.println(currentStudent);
            System.out.print("Y/N: ");
            char yesOrNo = input.next().charAt(0);


            if (Character.toUpperCase(yesOrNo) == 'Y') {
                //better implementation
                currentStudent.setValid(false);
                //remove logic
                studentList.remove(currentStudent);
            } else {
                System.out.println("Press <ENTER> key to return to the main menu...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
            }
        }
    }

    private boolean updateStudentDetails() {
        String studentId;
        int selection;
        boolean changesMade = false;
        Scanner input = new Scanner(System.in);


        do {
            System.out.print("Please enter student's id: ");
            studentId = input.nextLine();


            if (studentId == null) {
                System.out.println("Please enter valid student ID");
            }


        } while (studentId == null);

        Student selectedStudent = searchStudentWithStudentId(studentId);

        if (selectedStudent == null) {
            System.out.println("No student found, returning to main menu");
            return false;
        }
        do {
            System.out.println("Please select the items to be changed");
            System.out.println("1. Student's name");
            System.out.println("2. Student's mobile contact");
            System.out.println("3. Student's address");
            System.out.println("0. Exit to main menu");


            selection = input.nextInt();
            switch (selection) {
                case 1:
                    changesMade = updateStudentName(selectedStudent);
                    break;
                case 2:
                    changesMade = updateStudentMobileContact(selectedStudent);
                    break;
                case 3:
                    changesMade = updateStudentAddress(selectedStudent);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 0 and 4.");
                    break;
            }
        } while (selection != 0);
        return changesMade;
    }


    private boolean updateStudentMobileContact(Student student) {
        Scanner input = new Scanner(System.in);


        boolean changesMade = false;
        System.out.println("Current student");
        System.out.println(FormatUtil.printStudentHeader());
        System.out.println(student.toString());
        System.out.println("Student's contact: " + student.getMobileContactNo());
        String newMobileContact;
        do {
            System.out.print("Please enter the new student contact: ");
            newMobileContact = input.nextLine();


            System.out.println("Are you sure you want to change the below student's contact number as: " + newMobileContact);
            System.out.print("Y to confirm : ");
            char yesOrNo = input.next().charAt(0);
            if (Character.toUpperCase(yesOrNo) == 'Y') {
                if (GeneralUtil.isValidMobileContact(newMobileContact)) {
                    student.setMobileContactNo(newMobileContact);
                    System.out.println("Mobile contact updated successfully");
                }
            }
            if (!GeneralUtil.isValidMobileContact(newMobileContact)) {
                System.out.println("Invalid format, please enter only Malaysian mobile contact format");
                System.out.println("eg. 01112345678");
            }


        } while (!GeneralUtil.isValidMobileContact(newMobileContact));


        System.out.println("Press <ENTER> key to return to the main menu...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();


        return changesMade;
    }


    private boolean updateStudentName(Student student) {
        boolean valid = false;
        String firstName;
        String lastName;
        Scanner input = new Scanner(System.in);
        System.out.println("Current name: " + student.getFirstName() + " " + student.getLastName());


        do {
            System.out.println("Type 999 to exit at anytime");
            System.out.print("Please enter new first name : ");
            firstName = input.nextLine();
            System.out.print("Please enter new last name : ");
            lastName = input.nextLine();


            if (firstName.equalsIgnoreCase(student.getFirstName()) || lastName.equalsIgnoreCase(student.getLastName())) {
                System.out.println("Name cannot be identical as previous, please try again.");
            }


            if (GeneralUtil.isAlphanumeric(firstName) && GeneralUtil.isAlphanumeric(lastName)) {
                student.setFirstName(firstName);
                student.setLastName(lastName);


                //ensure email in sync
                updateStudentEmail(student);
                System.out.println("Changes successfully");
                System.out.println("Press <ENTER> key to return to the main menu...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                valid = true;
            } else {
                System.out.println("No special character is allowed, please try again");
            }


            if (firstName.equalsIgnoreCase("999") || lastName.equalsIgnoreCase("999")) {
                break;
            }


        } while (!valid);


        return valid;
    }

    private void updateStudentEmail(Student student) {
        if (student.getFirstName() != null && student.getLastName() != null) {
            String newEmail = student.getLastName() +
                    GeneralUtil.extractStudentFirstName(student.getFirstName()).toLowerCase() + "-" +
                    GeneralUtil.extractLastTwoDigitsOfGivenYear(student.getEnrollmentDateTime().getYear()) + GeneralUtil.getCurrentEmailDomain();
            student.setEmail(newEmail);
        }
    }


    private boolean updateStudentAddress(Student selectedStudent) {
        Scanner input = new Scanner(System.in);


        System.out.println("Current student Address 1: " + selectedStudent.getAddress1());
        String newAddress1 = "";
        while (newAddress1.isEmpty()) {
            System.out.print("Enter new Address 1 (cannot be empty): ");
            newAddress1 = input.nextLine().trim();
        }


        System.out.println("Current student Address 2: " + selectedStudent.getAddress2());
        String newAddress2 = "";
        while (newAddress2.isEmpty()) {
            System.out.print("Enter new Address 2 (cannot be empty): ");
            newAddress2 = input.nextLine().trim();
        }


        System.out.println("Current student Address 3: " + selectedStudent.getAddress3());
        String newAddress3 = "";
        while (newAddress3.isEmpty()) {
            System.out.print("Enter new Address 3 (cannot be empty): ");
            newAddress3 = input.nextLine().trim();
        }


        System.out.println("Postal code: " + selectedStudent.getPostalCode());
        String newPostalCode = "";
        while (newPostalCode.isEmpty()) {
            System.out.print("Enter new Postal Code (cannot be empty): ");
            newPostalCode = input.nextLine().trim();
        }


        System.out.println("City: " + selectedStudent.getCity());
        String newCity = "";
        while (newCity.isEmpty()) {
            System.out.print("Enter new City (cannot be empty): ");
            newCity = input.nextLine().trim();
        }


        System.out.println("New student Address 1: " + newAddress1);
        System.out.println("New student Address 2: " + newAddress2);
        System.out.println("New student Address 3: " + newAddress3);
        System.out.println("New postal code: " + newPostalCode);
        System.out.println("New city: " + newCity);
        System.out.println("Confirm changes? Y/N: ");
        char yesOrNo = input.next().charAt(0);


        if (Character.toUpperCase(yesOrNo) == 'Y') {
            selectedStudent.setAddress1(newAddress1);
            selectedStudent.setAddress2(newAddress2);
            selectedStudent.setAddress3(newAddress3);
            selectedStudent.setPostalCode(newPostalCode);
            selectedStudent.setCity(newCity);


            System.out.println("Changes successfully");
        } else {
            System.out.println("No updates done...");
            return false;
        }


        System.out.println("Press <ENTER> key to return to the main menu...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        return true;
    }


    //Menu 4
    private void searchStudentsForRegisteredCourse() {
        Scanner input = new Scanner(System.in);
        char yesOrNo;
        SortedLinkedListInterface<Student> tempStudentList = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<String> courseIDList = new SortedDoublyLinkedList<>();
        do {
            System.out.println("Please enter the course ID to get students ");
            System.out.print("Input eg.BACS2063: ");
            String courseCode = input.nextLine().trim();

            if (!courseCode.isEmpty()) {
                courseIDList.add(courseCode);
            }

            System.out.println("Current course ID entered");
            System.out.println(courseIDList);

            do {
                System.out.println("Do you want to continue entering course ID?");
                System.out.print("Y/N : ");
                yesOrNo = input.next().charAt(0);
                input.nextLine();
                if (Character.toUpperCase(yesOrNo) != 'Y' && Character.toUpperCase(yesOrNo) != 'N') {
                    System.out.println("Invalid character entered");
                }
            } while (Character.toUpperCase(yesOrNo) != 'Y' && Character.toUpperCase(yesOrNo) != 'N');


        } while (Character.toUpperCase(yesOrNo) == 'Y');


        for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
            boolean studentAdded = false;
            Student currentStudent = studentList.get(i);
            SortedLinkedListInterface<Course> currentStudentCourseList = currentStudent.getCourseList();
            for (int j = 0; j < currentStudentCourseList.getNumberOfEntries(); j++) {
                Course currentStudentCourse = currentStudentCourseList.get(j);
                for (int k = 0; k < courseIDList.getNumberOfEntries(); k++) {
                    if (currentStudentCourse.getCourseCode().contains(courseIDList.get(k)) && !studentAdded) {
                        tempStudentList.add(currentStudent);
                        studentAdded = true;
                        break;
                    }
                }
            }
        }
        if (!tempStudentList.isEmpty()) {
            System.out.println("Student found");
            for (int i = 0; i < tempStudentList.getNumberOfEntries(); i++) {
                System.out.println(FormatUtil.printStudentHeader());
                System.out.println(tempStudentList.get(i).toStringWithCourse());
            }
        } else {
            System.out.println("No student found...");
            System.out.println("Press <ENTER> key to return to the main menu...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }


    //Menu 5
    private void addStudentToCourse() {
        SortedLinkedListInterface<Course> existingStudentCourseList;
        SortedLinkedListInterface<Course> tempCourseList = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Course> programmeOfferedCourseList;


        String studentId = promptForStudentID();
        Student selectedStudent = searchStudentWithStudentId(studentId);


        boolean duplicated;
        boolean validCourseType = false;


        if (selectedStudent != null) {


            //if max number of course, do early return
            if (selectedStudent.getCourseList().getNumberOfEntries() >= selectedStudent.getMAX_COURSE_SIZE()) {
                System.out.println("This student has reached the maximum number of registered courses and cannot add any more.");
                System.out.println("Press <ENTER> key to return to the main menu...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                return;
            }
            if (!selectedStudent.isValid()) {
                System.out.println("The student status is invalid, please check and try again");
                System.out.println("Press <ENTER> key to return to the main menu...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                return;
            }


            //check selected student's programme offered course list
            if (selectedStudent.getProgramme() != null) {
                if (!selectedStudent.getProgramme().getCourseList().isEmpty()) {


                    existingStudentCourseList = selectedStudent.getCourseList();
                    programmeOfferedCourseList = selectedStudent.getProgramme().getCourseList();


                    for (int i = 0; i < programmeOfferedCourseList.getNumberOfEntries(); i++) {
                        boolean isEnrolled = false;
                        for (int j = 0; j < existingStudentCourseList.getNumberOfEntries(); j++) {
                            if (programmeOfferedCourseList.get(i).compareTo(existingStudentCourseList.get(j)) == 0) {
                                isEnrolled = true;
                                break;
                            }
                        }
                        if (!isEnrolled) {
                            tempCourseList.add(programmeOfferedCourseList.get(i));
                        }
                    }


                    boolean valid = false;
                    Scanner input = new Scanner(System.in);
                    if (tempCourseList.getNumberOfEntries() <= 0) {
                        System.out.println("No course available for the student right now");
                    } else {
                        do {
                            System.out.println("Available course list");
                            duplicated = false;
                            Course.courseHeader();
                            for (int i = 0; i < tempCourseList.getNumberOfEntries(); i++) {
                                System.out.print(i + 1 + ". ");
                                System.out.println(tempCourseList.get(i).toStringByRow());
                            }
                            System.out.print("Please select the course to add student to: ");
                            int selectedCourseNumber = input.nextInt();

                            //base case
                            if (selectedCourseNumber == 999) {
                                return;
                            }

                            if (selectedCourseNumber > 0 && selectedCourseNumber <= tempCourseList.getNumberOfEntries()) {
                                Course choosenCourse = tempCourseList.get(selectedCourseNumber - 1);
                                String defaultCourseCategory = choosenCourse.getCourseCategory();

                                do {
                                    input.nextLine();
                                    System.out.print("Please enter the course type eg. Compulsory, Elective, Resit, Repeat: ");
                                    String courseType = input.nextLine().toUpperCase();

                                    switch (courseType) {
                                        case "COMPULSORY":
                                        case "ELECTIVE":
                                        case "RESIT":
                                        case "REPEAT":
                                            choosenCourse.setCourseType(courseType);
                                            validCourseType = true;
                                            break;
                                        default:
                                            System.out.println("Invalid course status, please only enter: Compulsory, Elective, Resit, Repeat");
                                    }


                                    //If repeat or resit, calculation is the base course fee only, not per credit hour
                                    if (courseType.equalsIgnoreCase("REPEAT") || courseType.equalsIgnoreCase("RESIT")) {
                                        double adjustedCourseFee;
                                        if (defaultCourseCategory.equalsIgnoreCase("Compulsory")) {
                                            adjustedCourseFee = GeneralUtil.getMainCourseFee();
                                        } else {
                                            adjustedCourseFee = GeneralUtil.getMPUCourseFee();
                                        }
                                        choosenCourse.setCourseFee(adjustedCourseFee);
                                    }
                                } while (!validCourseType);


                                System.out.println("Are you sure to add this student");
                                System.out.println(FormatUtil.printStudentHeader());
                                System.out.println(selectedStudent);
                                System.out.println(FormatUtil.printDottedLine(266));


                                System.out.println("to this course?");
                                System.out.println(Course.courseHeader());
                                System.out.println("1. " + choosenCourse.toStringByRow());
                                System.out.println(FormatUtil.printDottedLine(122));
                                System.out.print("Y/N: ");
                                char yesOrNo = input.next().charAt(0);


                                if (Character.toUpperCase(yesOrNo) == 'Y') {
                                    for (int i = 0; i < selectedStudent.getCourseList().getNumberOfEntries(); i++) {
                                        if (selectedStudent.getCourseList().get(i).compareTo(choosenCourse) == 0) {
                                            duplicated = true;
                                        }
                                    }


                                    if (duplicated) {
                                        System.out.println("Student already registered the course!");
                                        System.out.println("Please select another course!");
                                    } else {
                                        input.nextLine(); // consume newline


                                        System.out.println("Course registered successfully.");


                                        //update student entry
                                        selectedStudent.getCourseList().add(choosenCourse);


                                        //to avoid nested programme list in chosenCourse
                                        choosenCourse.setProgrammeList(null);


                                        //update student courseFee
                                        Double studentFeePending = calculateStudentCoursesFee(selectedStudent, selectedStudent.getCourseList().getNumberOfEntries() - 1);
                                        selectedStudent.setTotalFeePending(studentFeePending);


                                        valid = true;


                                        SortedLinkedListInterface<Billing> existingStudentBillingList = selectedStudent.getBillingList();


                                        if (!existingStudentBillingList.isEmpty()) {
                                            //get latest billing
                                            Billing currentBilling = existingStudentBillingList.getLast();
                                            double amountDue = currentBilling.getAmountDue();


                                            //increase amountDue based on newCourse and previous entry
                                            currentBilling.setAmountDue(amountDue + choosenCourse.getCourseFee());


                                            selectedStudent.setTotalFeePending(currentBilling.getAmountDue());
                                            //update that entry
                                            currentBilling.getCourseList().add(choosenCourse);
                                        }


                                        System.out.println("Press <ENTER> key to return to the main menu...");
                                        Scanner scanner = new Scanner(System.in);
                                        scanner.nextLine();
                                    }
                                } else if (Character.toUpperCase(yesOrNo) == 'N') {
                                    System.out.println("Exiting..");
                                    break;
                                } else {
                                    System.out.println("Invalid character entered");
                                }
                            } else {
                                System.out.println("Invalid course selected, please try again, or type 999 to exit");
                            }
                        } while (!valid);
                    }


                } else {
                    System.out.println("No course available for the student right now");
                    System.out.println("Press <ENTER> key to return to the main menu...");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                }
            } else {
                System.out.println("Student does not have a programme, please assign a programme to continue");
                System.out.println("Press <ENTER> key to return to the main menu...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
            }
        }
    }


    //Menu 6
    private boolean removeStudentFromCourse() {


        String studentID = promptForStudentID();
        Scanner input = new Scanner(System.in);
        int choice;
        SortedLinkedListInterface<Course> existingCourseList = new SortedDoublyLinkedList<>();


        if (studentID != null && !studentID.isEmpty()) {
            Student choosenStudent = searchStudentWithStudentId(studentID);


            if (choosenStudent != null) {
                //only allow COMPULSORY and ELECTIVE courseType to be removed
                String[] statuses = {"COMPULSORY", "ELECTIVE"};
                for (int i = 0; i < choosenStudent.getCourseList().getNumberOfEntries(); i++) {
                    Course currentCourse = choosenStudent.getCourseList().get(i);
                    for (String status : statuses) {
                        if (currentCourse.getCourseType().toUpperCase().contains(status)) {
                            existingCourseList.add(currentCourse);
                        }
                    }
                }


                if (!existingCourseList.isEmpty()) {
                    do {
                        System.out.println(FormatUtil.printStudentHeader());
                        System.out.println(choosenStudent);
                        System.out.println("Choose course to remove student from");
                        for (int i = 0; i < existingCourseList.getNumberOfEntries(); i++) {
                            System.out.println(i + 1 + ". " + existingCourseList.get(i).toStringByRow());
                        }
                        System.out.println("Please enter a selection: ");


                        choice = input.nextInt();


                        if (choice >= 1 && choice <= choosenStudent.getCourseList().getNumberOfEntries()) {
                            Course selectedCourse = existingCourseList.get(choice - 1);
                            System.out.println("Selected Course");
                            System.out.println(selectedCourse.toStringByRow());


                            //if student already paid for course
                            if (choosenStudent.getBillingList() != null && choosenStudent.getBillingList().getLast().getAmountPaid() > 0) {
                                System.out.println("The student has already paid for the course");
                            }
                            System.out.println("Are you sure to remove the selected course?");
                            System.out.print("Y/N : ");
                            char yesOrNo = input.next().charAt(0);


                            if (Character.toUpperCase(yesOrNo) == 'Y') {


                                if (!choosenStudent.getBillingList().isEmpty()) {
                                    for (int i = 0; i < choosenStudent.getBillingList().getLast().getCourseList().getNumberOfEntries(); i++) {
                                        Billing currentBilling = choosenStudent.getBillingList().getLast();
                                        Course currentCourse = currentBilling.getCourseList().get(i);


                                        if (currentCourse.compareTo(selectedCourse) == 0 && currentBilling.getPaidAt() == null) {
                                            //course not paid, and to be removed from currentBilling
                                            currentBilling.getCourseList().remove(selectedCourse);
                                            currentBilling.setAmountDue(currentBilling.getAmountDue() - currentCourse.getCourseFee());
                                            choosenStudent.setTotalFeePending(choosenStudent.getTotalFeePending() - currentCourse.getCourseFee());
                                        }
                                    }
                                }
                                // remove course from student's entity's courseList
                                choosenStudent.getCourseList().remove(selectedCourse);


                                System.out.println("Course removed successfully");
                                return true;
                            }


                        } else {
                            System.out.println("Invalid choice, please enter again");
                        }
                    } while (choice < 1 || choice > choosenStudent.getCourseList().getNumberOfEntries());
                } else {
                    System.out.println("No course can be removed...");
                    System.out.println("Press <ENTER> key to return to the main menu...");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                }
                return false;
            }
        }
        return false;
    }


    //Menu 7
    private void calculateCourseFee() {
        Scanner input = new Scanner(System.in);
        double totalFeePending;
        double totalFeePaid;
        System.out.println("Please enter the studentID: ");
        String studentID = input.nextLine();


        Student selectedStudent = searchStudentWithStudentId(studentID);


        if (selectedStudent != null) {
            //use student fee pending directly since updated when adding course
            totalFeePending = selectedStudent.getTotalFeePending() == null ? 0 : selectedStudent.getTotalFeePending();
            totalFeePaid = selectedStudent.getTotalFeePaid() == null ? 0 : selectedStudent.getTotalFeePaid();


            System.out.println(FormatUtil.printStudentHeader());
            System.out.println(selectedStudent);
            System.out.println(FormatUtil.printDottedLine(266));


            if (!selectedStudent.getBillingList().isEmpty()) {
                SortedLinkedListInterface<Course> duePaymentCourseList = selectedStudent.getBillingList().getLast().getCourseList();


                System.out.println(Course.courseHeader());
                for (int i = 0; i < duePaymentCourseList.getNumberOfEntries(); i++) {
                    System.out.println(i + 1 + ". " + duePaymentCourseList.get(i).toStringByRow());
                }
                System.out.println(FormatUtil.printDottedLine(122));


                System.out.println("\n\nTotal fee pending: RM" + totalFeePending);
                System.out.println("Total fee paid: " + "RM " + totalFeePaid);


                if (!selectedStudent.getBillingList().isEmpty()) {
                    System.out.print("Would you like to view all billing information? Y/N: ");
                    char yesOrNo = input.next().charAt(0);


                    if (Character.toUpperCase(yesOrNo) == 'Y') {
                        System.out.println("Billing Information");
                        System.out.println(FormatUtil.printBillingHeader());
                        for (int i = 0; i < selectedStudent.getBillingList().getNumberOfEntries(); i++) {
                            System.out.println(i + 1 + "." + selectedStudent.getBillingList().get(i).toStringWithoutCourse());
                        }
                        int choice;


                        do {
                            System.out.print("Please select your choice: ");
                            choice = input.nextInt();


                            if (choice > 0 && choice <= selectedStudent.getBillingList().getNumberOfEntries()) {
                                System.out.println(FormatUtil.printBillingHeaderWithoutNumber());
                                System.out.println(selectedStudent.getBillingList().get(choice - 1).toString());
                                System.out.println("END OF BILLING INFORMATION");
                                System.out.println(FormatUtil.printDottedLine(145));
                            } else {
                                System.out.println("Invalid Choice, please try again");
                            }
                        } while (choice <= 0 || choice > selectedStudent.getBillingList().getNumberOfEntries());
                    } else {
                        System.out.println("Invalid input");
                    }
                }

                if (totalFeePending > 0) {
                    payAllCourseFee(selectedStudent);
                }
            } else {
                System.out.println("No pending payment for all courses for the current student");
                if (!selectedStudent.getBillingList().isEmpty()) {
                    System.out.print("Would you like to view all billing information? Y/N: ");
                    char yesOrNo = input.next().charAt(0);


                    if (Character.toUpperCase(yesOrNo) == 'Y') {
                        System.out.println("Billing Information");
                        System.out.println(FormatUtil.printBillingHeader());
                        for (int i = 0; i < selectedStudent.getBillingList().getNumberOfEntries(); i++) {
                            System.out.println(i + 1 + ". " + selectedStudent.getBillingList().get(i).toStringWithoutCourse());
                        }
                        int choice;


                        do {
                            System.out.print("Please select your choice: ");
                            choice = input.nextInt();


                            if (choice > 0 && choice <= selectedStudent.getBillingList().getNumberOfEntries()) {
                                System.out.println(FormatUtil.printBillingHeaderWithoutNumber());
                                System.out.println(selectedStudent.getBillingList().get(choice - 1).toString());
                                System.out.println("END OF BILLING INFORMATION");
                                System.out.println(FormatUtil.printDottedLine(145));
                            } else {
                                System.out.println("Invalid Choice, please try again");
                            }
                        } while (choice <= 0 || choice > selectedStudent.getBillingList().getNumberOfEntries());
                    }
                } else {
                    System.out.println("Student has no pending payment course");
                    System.out.println("Press <ENTER> key to return to the main menu...");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                }
            }
        }
    }


    //Menu 8
    private void filterStudentsForCourses() {
        Scanner input = new Scanner(System.in);


        System.out.println("Sample input");
        System.out.println("-> Course code: BAMS3012");
        System.out.println("-> Course name: Data Structure And Algorithm");
        System.out.println("-> Course level: Advance/Introductory/Intermediate");
        System.out.println("-> Course Category: Main/MPU");
        System.out.println("-> Course Type: Compulsory/Resit/Repeat");


        System.out.print("Please enter any course INFORMATION to search for students:  ");
        String searchTerm = input.nextLine().trim();


        if (!getFilteredStudentListWithCourseCriteria(searchTerm)) {
            System.out.println("No course found with the term: " + searchTerm);
        }
    }


    //Menu 8
    private boolean getFilteredStudentListWithCourseCriteria(String searchTerm) {
        SortedLinkedListInterface<Student> tempStudentList = new SortedDoublyLinkedList<>();
        boolean isNumeric = false;
        int searchInt = 0;


        try {
            searchInt = Integer.parseInt(searchTerm);
            isNumeric = true;
        } catch (NumberFormatException e) {
            // If not a number, proceed with string comparison
        }
        for (int i = 0; i < studentList.getNumberOfEntries() && studentList.get(i).isValid(); i++) {
            Student currentStudent = studentList.get(i);
            if (currentStudent != null && !currentStudent.getCourseList().isEmpty()) {
                for (int j = 0; j < currentStudent.getCourseList().getNumberOfEntries(); j++) {
                    Course currentStudentCourse = currentStudent.getCourseList().get(j);
                    if (isCourseMatch(currentStudentCourse, searchTerm, isNumeric, searchInt)) {
                        tempStudentList.add(currentStudent);
                        break; // Add student once and avoid duplicates
                    }
                }
            }
        }
        if (!tempStudentList.isEmpty()) {
            for (int i = 0; i < tempStudentList.getNumberOfEntries(); i++) {
                System.out.println(FormatUtil.printStudentHeader());
                System.out.println(tempStudentList.get(i).toStringWithCourse());
            }
            System.out.println("Criteria given : " + searchTerm);
            System.out.println("Total students found : " + tempStudentList.getNumberOfEntries());
            return true;
        }
        return false;
    }


    //Menu 9
    private void reportMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter filters for report generation");
        System.out.println("1. Date");
        System.out.println("2. Faculty/Programme");
        System.out.println("3. All students");
        System.out.println("4. Analytics report");


        int choice = input.nextInt();


        switch (choice) {
            case 1:
                getEnrollmentDateStudentList();
                break;
            case 2:
                generateReportBasedOnFacultyOrProgramme();
                break;
            case 3:
                generateStudentSummaryReport(studentList);
                break;
            case 4:
                generateStudentAnalyticReport(studentList);
                break;
        }
    }


    private void generateReportBasedOnFacultyOrProgramme() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter search criteria to generate report for students in specific faculty or programme");
        System.out.println("Faculty eg. FOCS,FAFB,FCCI etc.");
        System.out.println("Programme eg. REIY2S1,RDSY3S1,RSDY1S1,RSWY2S2 etc.");
        System.out.print("Input : ");
        String metrics = input.nextLine();


        SortedLinkedListInterface<Student> tempStudentList = new SortedDoublyLinkedList<>();
        // Populate tempStudentList based on metric
        for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
            Student student = studentList.get(i);
            if (student.getProgramme() != null) {
                if (student.getProgramme().getFacultyCode().equalsIgnoreCase(metrics) ||
                        student.getProgramme().getProgCode().equalsIgnoreCase(metrics)) {
                    tempStudentList.add(student);
                }
            }
        }
        if (!tempStudentList.isEmpty()) {
            generateStudentSummaryReport(tempStudentList);
        } else {
            System.out.println("No students found for the given metric: " + metrics);
        }
    }


    private void getEnrollmentDateStudentList() {
        Scanner input = new Scanner(System.in);
        LocalDate filterDate;
        String choice;
        do {
            System.out.println("Please enter the date to filter by enrolment (format: YYYY-MM-DD):");
            System.out.println("Type 'before' to list students who enrolled before the date.");
            System.out.println("Type 'after' to list students who enrolled after the date.");
            System.out.print("Enter choice (before/after): ");
            choice = input.nextLine().trim();


            if (!"before".equalsIgnoreCase(choice) && !"after".equalsIgnoreCase(choice)) {
                System.out.println("Invalid choice. Please enter 'before' or 'after'.");
                continue;
            }


            System.out.print("Enter date (YYYY-MM-DD): ");
            String dateString = input.nextLine().trim();


            try {
                filterDate = LocalDate.parse(dateString);
                break;


            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
            }
        } while (true);


        SortedLinkedListInterface<Student> filteredStudents = new SortedDoublyLinkedList<>();


        for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
            Student student = studentList.get(i);
            LocalDate enrollmentDate = student.getEnrollmentDateTime().toLocalDate();


            if ("before".equalsIgnoreCase(choice) && enrollmentDate.isBefore(filterDate) && student.isValid() && student.getProgramme() != null) {
                filteredStudents.add(student);
            } else if ("after".equalsIgnoreCase(choice) && enrollmentDate.isAfter(filterDate) && student.isValid() && student.getProgramme() != null) {
                filteredStudents.add(student);
            }
        }
        generateStudentSummaryReport(filteredStudents);
    }


    private void generateStudentSummaryReport(SortedLinkedListInterface<Student> studentList) {
        System.out.println("=============================================================================================================================================================================================================================================================================");
        System.out.printf("%145s\n", "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.printf("%130s\n\n", "STUDENT MANAGEMENT SUBSYSTEM");
        System.out.printf("%127s\n", "STUDENT SUMMARY REPORT");
        System.out.printf("%127s\n", "----------------------");


        System.out.println("Generated at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, hh:mm a")));
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");


        System.out.printf("%-10s %-30s %-50s %-20s %-90s %-20s %-25s %-15s",
                "ID",
                "STUDENT NAME",
                "PROGRAMME",
                "STATUS",
                "COURSES REGISTERED",
                "ENROLMENT DATE",
                "CREDITS ENROLLED",
                "CREDITS COMPLETED\n");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");


        int tempMaxCourseCount = 0;
        double totalAmountDue = 0;
        double totalAmountPaid = 0;


        int countFOCS = 0, countFAFB = 0, countFCCI = 0;


        for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
            if (studentList.get(i).getProgramme() != null) {
                String facultyCode = studentList.get(i).getProgramme().getFacultyCode();
                switch (facultyCode.toUpperCase()) {
                    case "FOCS":
                        countFOCS++;
                        break;
                    case "FAFB":
                        countFAFB++;
                        break;
                    case "FCCI":
                        countFCCI++;
                        break;
                }
            }
        }

        int maxCreditHourCompleted = 0;
        for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
            if (studentList.get(i).getProgramme() != null) {
                Student student = studentList.get(i);
                String studentId = student.getStudentID();
                String studentName = student.getFirstName() + " " + student.getLastName();
                String programme = student.getProgramme().getProgName() + " " + student.getProgramme().getProgCode();
                String status = student.isValid() ? "Active" : "Inactive";
                String coursesRegistered = getCoursesRegistered(student);
                int currentCourseCount = student.getCourseList().getNumberOfEntries();
                int currentStudentCreditHourCompleted = 0;

                for (int j = 0; j < student.getCourseList().getNumberOfEntries(); j++) {
                    currentStudentCreditHourCompleted += student.getCourseList().get(j).getCreditHours();
                }

                if (currentStudentCreditHourCompleted > maxCreditHourCompleted) {
                    maxCreditHourCompleted = currentStudentCreditHourCompleted;
                }

                if (currentCourseCount > tempMaxCourseCount) {
                    tempMaxCourseCount = currentCourseCount;
                }


                int totalCreditsEnrolled = getTotalCreditsEnrolled(student);
                int totalCreditsCompleted = getTotalCreditsCompleted(student);


                for (int j = 0; j < student.getBillingList().getNumberOfEntries(); j++) {
                    if (student.getBillingList().get(j) != null) {
                        totalAmountDue += student.getBillingList().get(j).getAmountDue();
                        totalAmountPaid += student.getBillingList().get(j).getAmountPaid();
                    }
                }


                System.out.printf("%-10s %-30s %-50s %-20s %-90s %-20s %-25d %-15d%n",
                        studentId,
                        studentName,
                        programme,
                        status,
                        coursesRegistered,
                        student.getEnrollmentDateTime().format(FormatUtil.dateFormatter),
                        totalCreditsEnrolled,
                        totalCreditsCompleted);
            }

        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Total Students: " + studentList.getNumberOfEntries());
        System.out.println("Faculty Distribution:");
        if (countFOCS > 0) {
            System.out.printf(" - FOCS: %d students (%.2f%%)\n", countFOCS, 100.0 * countFOCS / studentList.getNumberOfEntries());
        }
        if (countFAFB > 0) {
            System.out.printf(" - FAFB: %d students (%.2f%%)\n", countFAFB, 100.0 * countFAFB / studentList.getNumberOfEntries());
        }
        if (countFCCI > 0) {
            System.out.printf(" - FCCI: %d students (%.2f%%)\n", countFCCI, 100.0 * countFCCI / studentList.getNumberOfEntries());
        }


        System.out.println("Financial Summary:");
        System.out.printf("- Total Amount Due: RM %.2f\n", totalAmountDue);
        System.out.printf("- Total Amount Paid: RM %.2f\n", totalAmountPaid);

        System.out.println("Most number of course taken by student: " + tempMaxCourseCount);
        System.out.println("Highest credits hour completed: " + maxCreditHourCompleted);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%125s\n", "END OF THE STUDENT SUMMARY REPORT");
        System.out.println("=============================================================================================================================================================================================================================================================================");
        System.out.println("Press <ENTER> key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


    private void generateStudentAnalyticReport(SortedLinkedListInterface<Student> studentList) {
        System.out.println("=========================================================================================================================================================================================================================================================");
        System.out.printf("%145s\n", "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.printf("%130s\n\n", "STUDENT MANAGEMENT SUBSYSTEM");
        System.out.printf("%127s\n", "STUDENT ANALYTIC REPORT");
        System.out.printf("%127s\n", "----------------------");


        System.out.println("Generated at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, hh:mm a")));
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        int totalStudentsCount = studentList.getNumberOfEntries();


        int maleCount = 0;
        int femaleCount = 0;
        double totalFeePaid = 0;
        double totalFeePending = 0;
        int totalCourses = 0;


        for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
            Student student = studentList.get(i);
            totalFeePaid += student.getTotalFeePaid() == null ? 0 : student.getTotalFeePaid();
            totalFeePending += student.getTotalFeePending() == null ? 0 : student.getTotalFeePending();


            if (student.getGender() == 'M') {
                maleCount++;
            } else if (student.getGender() == 'F') {
                femaleCount++;
            }
            totalCourses += student.getCourseList().getNumberOfEntries();
        }


        double totalMaleStudentPercentage = (double) maleCount / studentList.getNumberOfEntries() * 100;
        double totalFemaleStudentPercentage = (double) femaleCount / studentList.getNumberOfEntries() * 100;


        double averageFeePaid = totalStudentsCount > 0 ? totalFeePaid / totalStudentsCount : 0;
        int averageCoursesPerStudent = totalStudentsCount > 0 ? Math.round((float) totalCourses / totalStudentsCount) : 0;

        System.out.println("Student Analytic Report");
        System.out.println("Total Number of Students: " + totalStudentsCount);
        System.out.println("Gender Distribution - Male: " + maleCount + ", Female: " + femaleCount);
        System.out.printf("Percentage of male count: %.2f%%, female count: %.2f%%%n", totalMaleStudentPercentage, totalFemaleStudentPercentage);


        System.out.println("Total Fee Paid: RM" + totalFeePaid);
        System.out.println("Total Fee Pending: RM" + totalFeePending);
        System.out.printf("Average Fee Paid per Student: RM %.2f\n", averageFeePaid);

        System.out.print("Average Number of Courses per Student: ");
        System.out.printf("%d\n", averageCoursesPerStudent);


        System.out.printf("%125s\n", "END OF THE STUDENT SUMMARY REPORT");
        System.out.println("=========================================================================================================================================================================================================================================================");
        System.out.println("Press <ENTER> key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


    private String getCoursesRegistered(Student student) {
        StringBuilder courses = new StringBuilder();
        for (int i = 0; i < student.getCourseList().getNumberOfEntries(); i++) {
            Course course = student.getCourseList().get(i);
            if (!courses.isEmpty()) {
                courses.append(", ");
            }
            courses.append(course.getCourseCode());
        }
        return courses.toString();
    }

    private int getTotalCreditsEnrolled(Student student) {
        int totalCreditsHour = 0;
        for (int i = 0; i < student.getCourseList().getNumberOfEntries(); i++) {
            Course course = student.getCourseList().get(i);
            totalCreditsHour += course.getCreditHours();
        }
        return totalCreditsHour;
    }


    private int getTotalCreditsCompleted(Student student) {
        int totalCreditHourCompleted = 0;
        for (int i = 0; i < student.getBillingList().getNumberOfEntries(); i++) {
            Billing currentBilling = student.getBillingList().get(i);


            for (int j = 0; j < currentBilling.getCourseList().getNumberOfEntries(); j++) {
                totalCreditHourCompleted += currentBilling.getCourseList().get(j).getCreditHours();
            }
        }
        return totalCreditHourCompleted;
    }


    //Menu 10
    private void searchForStudent() {
        String studentID = promptForStudentID();


        if (!studentID.isEmpty()) {
            if (studentList.search(new Student(studentID.toUpperCase())) != null) {
                Student currentStudent = studentList.search(new Student(studentID.toUpperCase()));
                System.out.println("Student found");
                System.out.println(FormatUtil.printStudentHeader());
                if (currentStudent.getCourseList().getNumberOfEntries() <= 0) {
                    System.out.println(currentStudent);
                } else {
                    System.out.println(currentStudent.toStringWithCourse());
                }
            } else {
                System.out.println("No student found");
            }
        } else {
            System.out.println("No student found");
        }
        System.out.println("Press <ENTER> key to return to the main menu...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


    //Additional functions
    // To pay all pending course fee
    private boolean payAllCourseFee(Student student) {
        Scanner input = new Scanner(System.in);
        boolean paid = false;
        if (student.getBillingList().getLast().getAmountDue() > 0) {


            System.out.print("Total amount due for student " + student.getFirstName() + " :");
            System.out.println("RM" + student.getBillingList().getLast().getAmountDue());


            System.out.print("Would you like to pay now? Y/N: ");
            char yesOrNo = input.next().charAt(0);


            if (Character.toUpperCase(yesOrNo) == 'Y') {
                double previousAmountPaid = 0;
                if (student.getBillingList().getLast().getAmountPaid() != 0) {
                    previousAmountPaid = student.getBillingList().getLast().getAmountPaid();
                }


                double amountDue = student.getBillingList().getLast().getAmountDue();
                //update total paid
                double totalPaid = student.getTotalFeePaid() == null ? 0 : student.getTotalFeePaid();
                totalPaid += student.getBillingList().getLast().getAmountDue();

                student.setTotalFeePaid(totalPaid);

                //paid for amount due
                student.getBillingList().getLast().setAmountPaid(amountDue + previousAmountPaid);
                //update total due
                student.setTotalFeePending(0.00);
                //set amount due = 0
                student.getBillingList().getLast().setAmountDue(0.00);
                student.getBillingList().getLast().setPaidAt(LocalDate.now());


                System.out.println("Payment successful, printing billing receipt..");
                System.out.println(FormatUtil.printBillingHeader());
                System.out.println("1. " + student.getBillingList().getLast().toStringWithoutCourse());
                paid = true;
            } else {
                System.out.println("Returning to main menu");
                System.out.println("Press <ENTER> key to return to the main menu...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
            }


        }
        return paid;
    }


    //To calculate student pending course fee
//Recursively sums courseFee until index < 0
    private Double calculateStudentCoursesFee(Student student, int index) {
        // Base case: when index reaches 0, stop the recursion
        if (index < 0 || student.getCourseList().isEmpty()) {
            return 0.0;
        }
        double courseFee = student.getCourseList().get(index).getCourseFee();
        //course fee = current method course fee + with the next calculateCourseFee return value
        return courseFee + calculateStudentCoursesFee(student, index - 1);
    }


    private String promptForStudentID() {
        String studentID;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Please enter student's id: ");
            studentID = input.nextLine();


            if (studentID == null) {
                System.out.println("Invalid input, please enter the student ID");
            }


        } while (studentID == null);
        return studentID;
    }


    private Student searchStudentWithStudentId(String studentID) {
        Student selectedStudent = null;
        if (!studentID.isEmpty()) {
            if (studentList.search(new Student(studentID)) != null) {
                selectedStudent = studentList.search(new Student(studentID));
            } else {
                System.out.println("No student found");
                System.out.println("Press <ENTER> key to return to the main menu...");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
            }
        }
        return selectedStudent;
    }


    private boolean isCourseMatch(Course course, String searchTerm, boolean isNumeric, int searchInt) {
        return course.getCourseType().equalsIgnoreCase(searchTerm) ||
                course.getCourseLevel().equalsIgnoreCase(searchTerm) ||
                course.getCourseName().equalsIgnoreCase(searchTerm) ||
                course.getCourseCategory().equalsIgnoreCase(searchTerm) ||
                course.getCourseCode().equalsIgnoreCase(searchTerm) ||
                (isNumeric && course.getCreditHours() == searchInt);
    }


    //for linkage purpose
    public static boolean updateStudentCourseListAfterDeleteCourse(Course deletedCourse) {
        boolean updated = false;
        for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
            Student currentStudent = studentList.get(i);
            if (currentStudent != null) {
                for (int j = 0; j < currentStudent.getCourseList().getNumberOfEntries(); j++) {
                    if (currentStudent.getCourseList().get(j).compareTo(deletedCourse) == 0) {
                        currentStudent.getCourseList().remove(deletedCourse);
                        updated = true;
                    }
                }
            }
        }
        return updated;
    }

    public static boolean updateStudentCourseList(Course amendedCourse) {
        boolean updated = false;
        for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
            Student currentStudent = studentList.get(i);
            if (currentStudent != null) {
                for (int j = 0; j < currentStudent.getCourseList().getNumberOfEntries(); j++) {
                    if (currentStudent.getCourseList().get(j).compareTo(amendedCourse) == 0) {
                        currentStudent.getCourseList().replace(j, amendedCourse);
                        updated = true;
                    }
                }
            }
        }
        return updated;
    }

    public static boolean updateStudentProgramme(Programme programme) {
        boolean updated = false;
        for (int i = 0; i < studentList.getNumberOfEntries(); i++) {
            Student currentStudent = studentList.get(i);
            if (currentStudent != null) {
                if (currentStudent.getProgramme().compareTo(programme) == 0) {
                    currentStudent.setProgramme(null);
                    currentStudent.setTutorialGroup(null);
                    currentStudent.setCourseList(null);
                }
            }
        }
        return updated;
    }
}



