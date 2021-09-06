package com.kainos.jobnight.projections.band;

import java.util.List;
import java.util.Map;

public interface BandAndCompetency {
    String getName();
    Map<String, List<String>> getCompetenciesInfo();
}
