/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import entity.Tutor;

/**
 *
 * @author chink
 */
public class TutorDao {
    public static SortedLinkedListInterface<Tutor> tutorList(){
        SortedLinkedListInterface<Tutor> presetTutor = new SortedDoublyLinkedList<>();;

        presetTutor.add(new Tutor( "T1001","CK","Dean","Senior Lecturer","CK@gamil.com","MBA","Practical","FOCS",10,false));
        presetTutor.add(new Tutor( "T1002","MK","Deputy Dean","Assistant Professor","MK@gamil.com","MBA","Practical","FOAS",20,false));
        presetTutor.add(new Tutor( "T1003","KW","Deputy Dean","Principal Lecturer","KW@gamil.com","MBA","Practical","FOBE",12,false));
        presetTutor.add(new Tutor( "T1004","TING","Deputy Dean","Senior Lecturer","TING@gamil.com","MBA","Tutorial","FOET",23,false));
        presetTutor.add(new Tutor( "T1005","WONG","Associate Dean For Quality Assurance","Senior Lecturer","WONG@gamil.com","MBA","Tutorial","FCCI",2,false));
        presetTutor.add(new Tutor( "T1006","KX","Associate Dean","Senior Lecturer","KX@gamil.com","MBA","Tutorial","FSSH",3,false));
        presetTutor.add(new Tutor( "T1007","FP","Programme Leader","Senior Lecturer","FP@gamil.com","MBA","Tutorial","FAFB",24,false));
        presetTutor.add(new Tutor( "T1008","JOHN","Deputy Dean","Senior Lecturer","JOHN@gamil.com","MBA","Tutorial","FCCI",2,false));
        presetTutor.add(new Tutor( "T1009","ALICE","Deputy Dean","Senior Lecturer","ALICE@gamil.com","MBA","Tutorial","FSSH",3,false));
        presetTutor.add(new Tutor( "T10010","GEORGE","President","Senior Lecturer","GEORGE@gamil.com","MBA","Tutorial","FAFB",24,false));
        presetTutor.add(new Tutor( "T10011","KC","Associate Dean","Principal Lecturer","KC@gamil.com","MBA","Tutorial","FCCI",2,false));
        presetTutor.add(new Tutor( "T10012","ARROW","Associate Dean","Principal Lecturer","ARROW@gamil.com","MBA","Tutorial","FOCS",3,false));
        presetTutor.add(new Tutor( "T10013","JAME","Associate Dean","Principal Lecturer","JAME@gamil.com","MBA","Tutorial","FOCS",24,false));
        presetTutor.add(new Tutor( "T10014","AARON","Associate Dean","Principal Lecturer","AARON@gamil.com","MBA","Tutorial","FOCS",24,false));
        presetTutor.add(new Tutor( "T10015","JOE","Associate Dean","Principal Lecturer","JOE@gamil.com","MBA","Tutorial","FAFB",24,false));
        presetTutor.add(new Tutor( "T10016","JACKSON","Associate Dean","Principal Lecturer","JACKSON@gamil.com","MBA","Tutorial","FAFB",24,false));

        return presetTutor;

    }

    public static SortedLinkedListInterface<Tutor> presetFocsTutorList(){
        SortedLinkedListInterface<Tutor> presetFocsTutorList = new SortedDoublyLinkedList<>();;


        presetFocsTutorList.add(new Tutor( "T1001","CK","Dean","Senior Lecturer","CK@gamil.com","MBA","Practical","FOCS",10,false));
        presetFocsTutorList.add(new Tutor( "T10012","ARROW","Associate Dean","Principal Lecturer","ARROW@gamil.com","MBA","Tutorial","FOCS",3,false));
        return presetFocsTutorList;

    }

    public static SortedLinkedListInterface<Tutor> presetFocs2TutorList(){
        SortedLinkedListInterface<Tutor> presetFocs2TutorList = new SortedDoublyLinkedList<>();;

        presetFocs2TutorList.add(new Tutor( "T10013","JAME","Associate Dean","Principal Lecturer","JAME@gamil.com","MBA","Tutorial","FOCS",24,false));
        presetFocs2TutorList.add(new Tutor( "T10014","AARON","Associate Dean","Principal Lecturer","AARON@gamil.com","MBA","Tutorial","FOCS",24,false));
        return presetFocs2TutorList;

    }

    public static SortedLinkedListInterface<Tutor> presetFafbTutorList(){
        SortedLinkedListInterface<Tutor> presetFafbTutorList = new SortedDoublyLinkedList<>();;
        presetFafbTutorList.add(new Tutor( "T1007","FP","Programme Leader","Senior Lecturer","FP@gamil.com","MBA","Tutorial","FAFB",24,false));
        presetFafbTutorList.add(new Tutor( "T10010","GEORGE","President","Senior Lecturer","GEORGE@gamil.com","MBA","Tutorial","FAFB",24,false));
        presetFafbTutorList.add(new Tutor( "T10015","JOE","Associate Dean","Principal Lecturer","JOE@gamil.com","MBA","Tutorial","FAFB",24,false));
        presetFafbTutorList.add(new Tutor( "T10016","JACKSON","Associate Dean","Principal Lecturer","JACKSON@gamil.com","MBA","Tutorial","FAFB",24,false));


        return presetFafbTutorList;

    }

    public static SortedLinkedListInterface<Tutor> presetFcciTutorList(){
        SortedLinkedListInterface<Tutor> presetFcciTutorList = new SortedDoublyLinkedList<>();;
        presetFcciTutorList.add(new Tutor( "T1008","JOHN","Deputy Dean","Senior Lecturer","JOHN@gamil.com","MBA","Tutorial","FCCI",2,false));
        presetFcciTutorList.add(new Tutor( "T10011","KC","Associate Dean","Principal Lecturer","KC@gamil.com","MBA","Tutorial","FCCI",2,false));
        return presetFcciTutorList;

    }


//    public static SortedLinkedListInterface<Tutor> addTutor2(){
//        
//        Tutor  tutor2 = new Tutor("KW","President","Doctor","KW@hotmail.com","MBA",null,"FOCS",0);
//        tutorList.add(tutor2);
//        return tutorList;
//    }
//    public static SortedLinkedListInterface<Tutor> addTutor3(){
//        
//        Tutor  tutor3 = new Tutor("WONG","President","Doctor","WONG@hotmail.com","MBA",null,"FOCS",0);
//        tutorList.add(tutor3);
//        return tutorList;
//    }
//    public static SortedLinkedListInterface<Tutor> addTutor4(){
//       
//        Tutor  tutor4 = new Tutor("TING","President","Doctor","TING@hotmail.com","MBA",null,"FOCS",0);
//        tutorList.add(tutor4);
//        return tutorList;
//    }
}
