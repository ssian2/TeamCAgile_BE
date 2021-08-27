package com.kainos.jobnight.projections;

import java.util.List;

public interface CapabilityAndJobFamilies {
    String getID();
    String getName();
    List<String> getJobFamiliesNames();
}
