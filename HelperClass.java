package com.example.app;

public class HelperClass {
    String NAME,DEPT,YEAR,SECTION,ROLL,PHONE,EMAIL,PASSWORD,uid;




    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDEPT() {
        return DEPT;
    }

    public void setDEPT(String DEPT) {
        this.DEPT = DEPT;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getSECTION() {
        return SECTION;
    }

    public void setSECTION(String SECTION) {
        this.SECTION = SECTION;
    }

    public String getROLL() {
        return ROLL;
    }

    public void setROLL(String ROLL) {
        this.ROLL = ROLL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public HelperClass(String NAME, String DEPT, String YEAR, String SECTION, String ROLL, String PHONE, String EMAIL, String PASSWORD,String uid) {
        this.NAME = NAME;
        this.DEPT = DEPT;
        this.YEAR = YEAR;
        this.SECTION = SECTION;
        this.ROLL = ROLL;
        this.PHONE = PHONE;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.uid=uid;
    }


}
