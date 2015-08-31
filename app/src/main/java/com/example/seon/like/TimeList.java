package com.example.seon.like;

/**
 * Created by Seon on 2015-08-18.
 */
public class TimeList {
    private String title;
    private String detail;
    private String time;
    private int _id;
    public TimeList(int _id, String _title, String detail, String times) {
        this._id = _id;
        this.title = _title;
        this.detail = detail;
        this.time = times;
    }
    public int getId(){
        return _id;
    }
    public void setId(int _id){
        this._id = _id;
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
