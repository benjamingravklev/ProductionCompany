package com.dev.productioncompany.model;

import java.io.Serializable;

public class ProjectModel implements Serializable {
    String id;
    String name, contract, list, datetime;

    public ProjectModel() {
    }

    public ProjectModel(String id, String name, String contract, String list, String datetime) {
        this.id = id;
        this.name = name;
        this.contract = contract;
        this.list = list;
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
