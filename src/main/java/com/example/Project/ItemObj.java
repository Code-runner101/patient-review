package com.example.Project;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class ItemObj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "pat_name")
    private String pat_name;
    @Column(name = "doc_name")
    private String doc_name;
    @Column(name = "time")
    private String time;
    @Column(name = "date")
    private String date;

    public ItemObj() {}

    public ItemObj(String pat_name, String doc_name, String time, String date) {
        this.pat_name = pat_name;
        this.doc_name = doc_name;
        this.time = time;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPat_name() {
        return pat_name;
    }

    public void setPat_name(String pat_name) {
        this.pat_name = pat_name;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Имя пациента: " + pat_name + ", Имя врача: " + doc_name + ", время приема: " + time;
    }
}

