package com.kainos.jobnight.projections.band;
import com.kainos.jobnight.entity.Training;

import java.util.Set;

public interface BandAndTrainings {
    String getName();
    Set<Training> getTrainings();
}
