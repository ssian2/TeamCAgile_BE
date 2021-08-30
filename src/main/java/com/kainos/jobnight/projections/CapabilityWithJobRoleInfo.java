package com.kainos.jobnight.projections;

import java.util.HashMap;
import java.util.List;

public interface CapabilityWithJobRoleInfo {
    public String getID();
    public String getName();
    HashMap<String, List<String>> getJobRolesWithFamilies();

}
