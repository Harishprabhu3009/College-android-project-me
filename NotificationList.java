package com.example.app;

public class NotificationList {
    private  String NotifiedTitle;
    private  String NotifiedContents;
    private  String NotifiedDate;

    public String getNotifiedTitle() {
        return NotifiedTitle;
    }

    public String getNotifiedContents() {
        return NotifiedContents;
    }

    public String getNotifiedDate() {
        return NotifiedDate;
    }

    public NotificationList(){}
    public NotificationList(String notifiedTitle, String notifiedContents, String notifiedDate) {
        NotifiedTitle = notifiedTitle;
        NotifiedContents = notifiedContents;
        NotifiedDate = notifiedDate;
    }


}

