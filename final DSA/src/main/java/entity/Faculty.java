/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package entity;

/**
 *
 * @author MingKing
 */
public class Faculty implements Comparable<Faculty> {
    private String facCode;
    private String facName;

    public Faculty(String facCode, String facName) {
        this.facCode = facCode;
        this.facName = facName;
    }


    public String getFacCode() {
        return facCode;
    }

    public void setFacCode(String facCode) {
        this.facCode = facCode;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }


    @Override
    public int compareTo(Faculty faculty) {
        return facCode.compareTo(faculty.facCode);
    }

    public String toStringByRow() {
        return String.format("%3s%-15s%-40s"," ",facCode,facName);

    }

}

