package com.kainos.jobnight.helper_classes;
import java.util.List;
import java.util.ArrayList;

public class RoleResponsibility {
    public String role_name;
    public List<String> resps = new ArrayList<String>();
    
    public RoleResponsibility(String role_name){
        this.role_name = role_name;
    }
    public void AddResponsibility(String respName)
    {
        this.resps.add(respName);
    }
}
