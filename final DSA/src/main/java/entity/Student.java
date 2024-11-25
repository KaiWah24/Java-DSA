/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import util.FormatUtil;
import util.GeneralUtil;

import java.time.LocalDateTime;

/**
 * @author FungPin
 */
public class Student implements Comparable<Student> {
    private String studentID;
    private String firstName;
    private String lastName;
    private Character gender;
    private String NRIC;
    private String mobileContactNo;
    private String address1;
    private String address2;
    private String address3;
    private String postalCode;
    private String state;
    private String city;
    private String email;
    private boolean valid;
    private LocalDateTime enrollmentDateTime;
    private Double totalFeePaid;
    private Double totalFeePending;
    public static int totalRegStudCounter = 0;
    private SortedLinkedListInterface<Course> courseList;
    private TutorialGroup tutorialGroup;
    private Programme programme;
    private SortedLinkedListInterface<Billing> billingList;


    private final int MAX_COURSE_SIZE = 6;


    public Student() {
    }


    //used in dao file
    public Student(String firstName, String lastName, Character gender, String NRIC, String mobileContactNo,
                   String address1, String address2, String address3, String postalCode, String state, String city,
                   Programme programme, SortedLinkedListInterface<Course> courseList, TutorialGroup tutorialGroup) {


        if (GeneralUtil.getLastTwoDigitsOfCurrentYear() != null) {
            String studentProgLevel = programme.getProgLevel().equalsIgnoreCase("diploma") ? "WMD" : "WMR";
            studentID = GeneralUtil.getLastTwoDigitsOfCurrentYear() + studentProgLevel + String.format("%05d", totalRegStudCounter);
        }
        email = lastName.toLowerCase() + GeneralUtil.extractStudentFirstName(firstName) + "-" + GeneralUtil.getLastTwoDigitsOfCurrentYear()
                + "@" + GeneralUtil.getCurrentEmailDomain();


        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.NRIC = NRIC;
        this.mobileContactNo = mobileContactNo;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.postalCode = postalCode;
        this.state = state;
        this.city = city;
        enrollmentDateTime = LocalDateTime.now().minusDays(10);


        if (courseList == null) {
            this.courseList = new SortedDoublyLinkedList<>();
        }
        valid = true;
        totalRegStudCounter++;
        this.programme = programme;
        this.courseList = courseList;
        this.tutorialGroup = tutorialGroup;
    }


    //used only when adding new student with console
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;


        if (GeneralUtil.getLastTwoDigitsOfCurrentYear() != null) {
            String studentProgLevel = "";
            if (programme != null) {
                studentProgLevel = programme.getProgLevel().equalsIgnoreCase("diploma") ? "WMD" : "WMR";
            }


            studentID = GeneralUtil.getLastTwoDigitsOfCurrentYear() + studentProgLevel + String.format("%05d", totalRegStudCounter);
        }
        email = lastName.toLowerCase() + GeneralUtil.extractStudentFirstName(firstName) + "-" + GeneralUtil.getLastTwoDigitsOfCurrentYear()
                + "@" + GeneralUtil.getCurrentEmailDomain();


        totalRegStudCounter++;
        valid = true;
        courseList = new SortedDoublyLinkedList<>();
        enrollmentDateTime = LocalDateTime.now();
        totalFeePaid = 0.00;
        totalFeePending = 0.00;
        //prefix for testing purpose
        programme = null;
        this.courseList = new SortedDoublyLinkedList<>();
        billingList = new SortedDoublyLinkedList<>();
    }


    public Student(String studentID) {
        this.studentID = studentID;
    }


    public String getStudentID() {
        return studentID;
    }


    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Character getGender() {
        return gender;
    }


    public void setGender(Character gender) {
        this.gender = gender;
    }


    public String getNRIC() {
        return NRIC;
    }


    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }


    public String getMobileContactNo() {
        return mobileContactNo;
    }


    public void setMobileContactNo(String mobileContactNo) {
        this.mobileContactNo = mobileContactNo;
    }


    public String getAddress1() {
        return address1;
    }


    public void setAddress1(String address1) {
        this.address1 = address1;
    }


    public String getAddress2() {
        return address2;
    }


    public void setAddress2(String address2) {
        this.address2 = address2;
    }


    public String getAddress3() {
        return address3;
    }


    public void setAddress3(String address3) {
        this.address3 = address3;
    }


    public String getPostalCode() {
        return postalCode;
    }


    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isValid() {
        return valid;
    }


    public void setValid(boolean valid) {
        this.valid = valid;
    }


    public SortedLinkedListInterface<Course> getCourseList() {
        if (courseList == null) {
            courseList = new SortedDoublyLinkedList<>();
        }
        return courseList;
    }


    public void setCourseList(SortedLinkedListInterface<Course> courseList) {
        this.courseList = courseList;
    }


    public LocalDateTime getEnrollmentDateTime() {
        return enrollmentDateTime;
    }

    public void setEnrollmentDateTime(LocalDateTime enrollmentDateTime) {
        this.enrollmentDateTime = enrollmentDateTime;
    }

    public Double getTotalFeePaid() {
        return totalFeePaid;
    }

    public void setTotalFeePaid(Double totalFeePaid) {
        this.totalFeePaid = totalFeePaid;
    }

    public Double getTotalFeePending() {
        return totalFeePending;
    }

    public void setTotalFeePending(Double totalFeePending) {
        this.totalFeePending = totalFeePending;
    }


    public TutorialGroup getTutorialGroup() {
        if (tutorialGroup == null) {
            SortedLinkedListInterface<TutorialGroup> tutorialGroup = new SortedDoublyLinkedList<>();
        }
        return tutorialGroup;
    }


    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }


    public Programme getProgramme() {
        return programme;
    }


    public void setProgramme(Programme programme) {
        this.programme = programme;
    }


    public int getMAX_COURSE_SIZE() {
        return MAX_COURSE_SIZE;
    }


    public SortedLinkedListInterface<Billing> getBillingList() {
        if (billingList == null) {
            billingList = new SortedDoublyLinkedList<>();
        }
        return billingList;
    }


    public void setBillingList(SortedLinkedListInterface<Billing> billingList) {
        this.billingList = billingList;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!(this.studentID.equals(other.studentID))) {
            return false;
        }
        return true;
    }


    @Override
    public int compareTo(Student s) {
        return studentID.compareTo(s.studentID);
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("-".repeat(266));
        result.append("\n");


        result.append(String.format(
                "%-10s |%-25s |%-15s |%-8s |%-25s |%-12s |%-50s |%-25s |%-30s |%-30s |%-30s",
                studentID, firstName, lastName, gender, NRIC, mobileContactNo,
                email, enrollmentDateTime.format(FormatUtil.dateFormatter), programme == null ? null : programme.getFacultyCode(), programme == null ? null : programme.getProgCode(), tutorialGroup == null ? null : tutorialGroup.getTutorialGroupName()));
        return result.toString();
    }


    public String toStringWithCourse() {
        StringBuilder result = new StringBuilder();
        result.append("-".repeat(266));
        result.append("\n");


        result.append(String.format(
                "%-10s |%-25s |%-15s |%-8s |%-25s |%-12s |%-50s |%-25s |%-30s |%-30s |%-30s\n",
                studentID, firstName, lastName, gender, NRIC, mobileContactNo,
                email, enrollmentDateTime.format(FormatUtil.dateFormatter), programme == null ? null : programme.getFacultyCode(), programme == null ? null : programme.getProgCode(), tutorialGroup == null ? null : tutorialGroup.getTutorialGroupName()));
        result.append("-".repeat(266));
        result.append("\n\nCourse Taken for student: ").append(firstName).append(" ").append(lastName).append("\n");
//       result.append(Course.courseHeader()).append("\n");
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            result.append(courseList.get(i).toStringByColumn2()).append("\n");
        }
        result.append("-".repeat(122));


        return result.toString();
    }
}






