package com.kainos.jobnight.helper_classes;

import org.json.JSONObject;

import java.util.Optional;

public class Util {
	public static boolean JSONHasKeyValue(JSONObject json, String key) {
		return json.has(key) && !json.isNull(key);
	}

	public static Optional<String> safeGetJSONString(JSONObject json, String key) {
		if (JSONHasKeyValue(json, key)) {
			return Optional.of(json.getString(key));
		}
		return Optional.empty();
	}
}
