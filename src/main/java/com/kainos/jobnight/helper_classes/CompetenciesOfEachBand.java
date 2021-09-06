package com.kainos.jobnight.helper_classes;
import java.util.*;

public class CompetenciesOfEachBand {
    public String name;
    public List<List<String>> competency_info;


    public CompetenciesOfEachBand(String name){
        this.name = name;
        this.competency_info =new ArrayList<List<String>>();
    }

    public void addCompetencyInfo(String name, String desc){
        var data = new ArrayList<String>();
        data.add(name);
        data.add(desc);
        this.competency_info.add(data);
    }
    public String getName(){
        return this.name;
    }
}
