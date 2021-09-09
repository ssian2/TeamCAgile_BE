package com.kainos.jobnight.projections.capability;

import java.util.List;

public interface CapabilityWithRolesAndID {
    String getID();
    String getName();
    List<List<String>> getJobRolesInCapability();
}
