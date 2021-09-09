package com.kainos.jobnight.helper_classes;
import lombok.AllArgsConstructor;

import javax.management.relation.Role;
import java.util.List;
import java.util.ArrayList;

@AllArgsConstructor
public class RoleResponsibility {
    public String role_name;

    //TODO: JobRole stores this as a set but here it's a list?
    public List<String> resps = new ArrayList<String>();
    
    public RoleResponsibility(String role_name){
        this.role_name = role_name;
    }
    public void AddResponsibility(String respName)
    {
        this.resps.add(respName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RoleResponsibility) {
            RoleResponsibility rr = (RoleResponsibility) obj;
            return rr.role_name.equals(this.role_name) &&
                rr.resps.containsAll(resps) &&
                resps.containsAll(rr.resps);
        }

        return false;
    }
}
