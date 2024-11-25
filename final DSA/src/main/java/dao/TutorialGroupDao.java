/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import entity.Programme;
import entity.Tutor;
import entity.TutorialGroup;

/**
 *
 * @author KaiWah
 */
public class TutorialGroupDao {
    public static SortedLinkedListInterface<Programme> presetProgrammeList = CourseDao.presetProgrammeList();


    //DFA
    public static SortedLinkedListInterface<TutorialGroup> daoDFAY1S1() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        
        TutorialGroup tutorialGroup1 = new TutorialGroup("DFAY1S1", "DFAY1S1G1", CourseDao.presetProgramme().get(0));
        TutorialGroup tutorialGroup2 = new TutorialGroup("DFAY1S1", "DFAY1S1G2", CourseDao.presetProgramme().get(0));
        TutorialGroup tutorialGroup3 = new TutorialGroup("DFAY1S1", "DFAY1S1G3", CourseDao.presetProgramme().get(0));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }
    public static SortedLinkedListInterface<TutorialGroup> daoDFAY1S2() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("DFAY1S2", "DFAY1S2G1", CourseDao.presetProgramme().get(1));
        TutorialGroup tutorialGroup2 = new TutorialGroup("DFAY1S2", "DFAY1S2G2", CourseDao.presetProgramme().get(1));
        TutorialGroup tutorialGroup3 = new TutorialGroup("DFAY1S2", "DFAY1S2G3", CourseDao.presetProgramme().get(1));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }
    public static SortedLinkedListInterface<TutorialGroup> daoDFAY1S3() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("DFAY1S3", "DFAY1S3G1", CourseDao.presetProgramme().get(2));
        TutorialGroup tutorialGroup2 = new TutorialGroup("DFAY1S3", "DFAY1S3G2", CourseDao.presetProgramme().get(2));
        TutorialGroup tutorialGroup3 = new TutorialGroup("DFAY1S3", "DFAY1S3G3", CourseDao.presetProgramme().get(2));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }
    public static SortedLinkedListInterface<TutorialGroup> daoREIY2S1() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("REIY2S1", "REIY2S1G1", CourseDao.presetProgramme().get(3));
        TutorialGroup tutorialGroup2 = new TutorialGroup("REIY2S1", "REIY2S1G2", CourseDao.presetProgramme().get(3));
        TutorialGroup tutorialGroup3 = new TutorialGroup("REIY2S1", "REIY2S1G3", CourseDao.presetProgramme().get(3));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }

    public static SortedLinkedListInterface<TutorialGroup> daoREIY2S2() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("REIY2S2", "REIY2S2G1", CourseDao.presetProgramme().get(4));
        TutorialGroup tutorialGroup2 = new TutorialGroup("REIY2S2", "REIY2S2G2", CourseDao.presetProgramme().get(4));
        TutorialGroup tutorialGroup3 = new TutorialGroup("REIY2S2", "REIY2S2G3", CourseDao.presetProgramme().get(4));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }

    public static SortedLinkedListInterface<TutorialGroup> daoREIY2S3() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("REIY2S3", "REIY2S3G1", CourseDao.presetProgramme().get(5));
        TutorialGroup tutorialGroup2 = new TutorialGroup("REIY2S3", "REIY2S3G2", CourseDao.presetProgramme().get(5));
        TutorialGroup tutorialGroup3 = new TutorialGroup("REIY2S3", "REIY2S3G3", CourseDao.presetProgramme().get(5));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }

    public static SortedLinkedListInterface<TutorialGroup> daoDISY2S1() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("DISY2S1", "DISY2S1G1", CourseDao.presetProgramme().get(6));
        TutorialGroup tutorialGroup2 = new TutorialGroup("DISY2S1", "DISY2S1G2", CourseDao.presetProgramme().get(6));
        TutorialGroup tutorialGroup3 = new TutorialGroup("DISY2S1", "DISY2S1G3", CourseDao.presetProgramme().get(6));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }
    public static SortedLinkedListInterface<TutorialGroup> daoDISY2S2() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("DISY2S2", "DISY2S2G1", CourseDao.presetProgramme().get(7));
        TutorialGroup tutorialGroup2 = new TutorialGroup("DISY2S2", "DISY2S2G2", CourseDao.presetProgramme().get(7));
        TutorialGroup tutorialGroup3 = new TutorialGroup("DISY2S2", "DISY2S2G3", CourseDao.presetProgramme().get(7));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }
    public static SortedLinkedListInterface<TutorialGroup> daoDISY2S3() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("DISY2S3", "DISY2S3G1", CourseDao.presetProgramme().get(8));
        TutorialGroup tutorialGroup2 = new TutorialGroup("DISY2S3", "DISY2S3G2", CourseDao.presetProgramme().get(8));
        TutorialGroup tutorialGroup3 = new TutorialGroup("DISY2S3", "DISY2S3G3", CourseDao.presetProgramme().get(8));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }


    public static SortedLinkedListInterface<TutorialGroup> daoDFMY1S1() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("DFMY1S1", "DFMY1S1G1", CourseDao.presetProgramme().get(9));
        TutorialGroup tutorialGroup2 = new TutorialGroup("DFMY1S1", "DFMY1S1G2", CourseDao.presetProgramme().get(9));
        TutorialGroup tutorialGroup3 = new TutorialGroup("DFMY1S1", "DFMY1S1G3", CourseDao.presetProgramme().get(9));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }
    public static SortedLinkedListInterface<TutorialGroup> daoDFMY1S2() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("DFMY1S2", "DFMY1S2G1", CourseDao.presetProgramme().get(10));
        TutorialGroup tutorialGroup2 = new TutorialGroup("DFMY1S2", "DFMY1S2G2", CourseDao.presetProgramme().get(10));
        TutorialGroup tutorialGroup3 = new TutorialGroup("DFMY1S2", "DFMY1S2G3", CourseDao.presetProgramme().get(10));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }
    public static SortedLinkedListInterface<TutorialGroup> daoDFMY1S3() {
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("DFMY1S3", "DFMY1S3G1", CourseDao.presetProgramme().get(11));
        TutorialGroup tutorialGroup2 = new TutorialGroup("DFMY1S3", "DFMY1S3G2", CourseDao.presetProgramme().get(11));
        TutorialGroup tutorialGroup3 = new TutorialGroup("DFMY1S3", "DFMY1S3G3", CourseDao.presetProgramme().get(11));

        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);

        return tutorialGroupList;
    }

}


