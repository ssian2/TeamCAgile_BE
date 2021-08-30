package com.kainos.jobnight.projections.jobFamily;

import java.util.HashMap;
import java.util.List;

public interface JobFamilyNamesWithRolesAndBands {
    public String getName();
    List<HashMap<String, String>>  getJobRoleNamesAndBands();
}
