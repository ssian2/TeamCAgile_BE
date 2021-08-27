package com.kainos.jobnight.projections;

import java.util.List;

public interface JobRoleWithBrandFamilyUrlAndSpec {
    String getName();
    String getBandName();
    String getJobFamilyName();
    String getCapability();
    String getUrl();
    String getSpecification();
    List<String> getResponsibilitiesList();
}
