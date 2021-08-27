package com.kainos.jobnight.projections;

import java.util.List;

public interface JobRoleWithBandandFamily {
    String getId();
    String getName();
    String getBandName();
    List<String> getJobFamilyName();
    String getCapability();
}
