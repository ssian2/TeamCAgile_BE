package com.kainos.jobnight.projections;
import com.kainos.jobnight.entity.Training;

import java.util.Set;

public interface BandAndTrainings {
    String getName();
    Set<Training> getTrainings();
}
