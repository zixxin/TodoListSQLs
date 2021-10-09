package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int is_completed;

    public TodoItem(String title, String category, String desc, String due_date){
        this.title = title;
        this.category = category;
        this.desc = desc;
        this.due_date = due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        this.current_date = f.format(new Date());
    }
    
    public TodoItem(String title, String category, String desc, String due_date, String current_date){
        this.title = title;
        this.category = category;
        this.desc = desc;
        this.due_date = due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        this.current_date = f.format(new Date());
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public int getIsCompleted() {
		return is_completed;
	}
    
    public void setIsCompleted(int num) {
		this.is_completed = num;
	}
    
    public String toString() {
    	if(is_completed == 0) return "[" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date;
    	else return  "[V] [" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date;
    }
    
    public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
    }
}