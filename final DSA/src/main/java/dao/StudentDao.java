/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 * @author FungPin
 */

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import entity.Billing;
import entity.Student;

import java.time.LocalDateTime;

import static util.GeneralUtil.getProgramme;

public class StudentDao {
    public static SortedLinkedListInterface<Student> initStudents() {
        SortedLinkedListInterface<Student> studentList = new SortedDoublyLinkedList<>();
        String[] firstNames = {
                "Emily", "Jacob", "Mia", "Ethan", "Sophia",
                "Alexander", "Olivia", "Noah", "Ava", "Liam",
                "Isabella", "Benjamin", "Madison", "Charlotte", "Daniel",
                "Abigail", "Mason", "Emma", "Lucas", "Chloe",
                "Aiden", "Lily", "Gabriel", "Zoe", "James",
                "Ella", "Avery", "Jack", "Harper", "Elijah",
                "Amelia", "Logan", "Riley", "Carter", "Grace",
                "Owen", "Scarlett", "Nathan", "Layla", "Matthew",
                "Piper", "Ryan", "Isla", "Sam", "Luna",
                "Isaac", "Nora", "Caleb", "Hazel", "Adrian"
        };
        char[] genders = new char[50];

        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                genders[i] = 'M';  // Male
            } else {
                genders[i] = 'F';  // Female
            }
        }

        for (int i = 0; i < 50; i++) {

            if (i == 0) {
                String firstName = "Fung pin";
                char gender = genders[i];
                String NRIC = "A012345" + (66 + i) + "B";
                String mobile = "01234567" + String.format("%03d", i + 10);
                Student student = new Student(firstName, "Soong", gender, NRIC, mobile,
                        "987 Maple Avenue", "Unit 5", "Urban Area", "23456",
                        "Kuala Lumpur", "Malaysia", getProgramme("REIY2S3") == null ? null : getProgramme("REIY2S3"), CourseDao.presetCourseTestStudent(), getProgramme("REIY2S3").getTutorialGroupList().get(0));

                SortedLinkedListInterface<Billing> billingList = new SortedDoublyLinkedList<>();
                billingList.add(new Billing(CourseDao.presetCourseTestStudent()));
                student.setBillingList(billingList);
                student.setTotalFeePending(billingList.getLast().getAmountDue());
                student.setStudentID("23WMR09186");
                studentList.add(student);
                getProgramme("REIY2S3").getTutorialGroupList().get(0).addStudentToTutorialGroup(student);
            }


            if (i % 2 == 0) {
                //FCCI
                String firstName = firstNames[i];
                char gender = genders[i];
                String NRIC = "A012345" + (66 + i) + "B";
                String mobile = "01234567" + String.format("%03d", i + 10);
                Student student = new Student(firstName, "Test", gender, NRIC, mobile,
                        "987 Maple Avenue", "Unit 5", "Urban Area", "23456",
                        "Kuala Lumpur", "Malaysia", getProgramme("DFMY1S3"), getProgramme("DFMY1S3").getCourseList(), getProgramme("DFMY1S3").getTutorialGroupList().get(1));


                SortedLinkedListInterface<Billing> billingList = new SortedDoublyLinkedList<>();
                billingList.add(new Billing(CourseDao.presetCourseFCCI()));
                student.setBillingList(billingList);
                student.setEnrollmentDateTime(LocalDateTime.of(2024, 2, 10, 12, 30));
                studentList.add(student);
            } else {
                //fafb
                String firstName = firstNames[i];
                char gender = genders[i];
                String NRIC = "A012345" + (66 + i) + "B";
                String mobile = "01234567" + String.format("%03d", i + 10);
                Student student = new Student(firstName, "Test", gender, NRIC, mobile,
                        "987 Maple Avenue", "Unit 5", "Urban Area", "23456",
                        "Kuala Lumpur", "Malaysia", getProgramme("DFAY1S1"), getProgramme("DFAY1S1").getCourseList(), getProgramme("DFAY1S1").getTutorialGroupList().get(0));


                SortedLinkedListInterface<Billing> billingList = new SortedDoublyLinkedList<>();
                billingList.add(new Billing(CourseDao.presetCourseFAFB()));
                student.setBillingList(billingList);
                studentList.add(student);
                getProgramme("DFAY1S1").getTutorialGroupList().get(0).addStudentToTutorialGroup(student);
            }
        }
        return studentList;
    }
}




