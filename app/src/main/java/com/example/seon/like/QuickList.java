package com.example.seon.like;

/**
 * Created by Seon on 2015-08-12.
 */
public class QuickList {
    private String title;
    private String detail;
    private String time;
    public QuickList(){}
    public QuickList(String title, String detail, String time){
        this.title = title;
        this.detail = detail;
        this.time = time;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDetail(){
        return detail;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }
}
