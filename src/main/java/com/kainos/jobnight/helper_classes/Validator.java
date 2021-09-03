package com.kainos.jobnight.helper_classes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import static java.util.Optional.empty;

public class Validator {
	// Contains list of validation failures
	// Stores what failed and a short message explaining why
	// Messages should be in present tense
	Map<String, String> sources;

	public Map<String, String> getSources() { return sources; }

	public Validator() {
		sources = new HashMap<String, String>();
	}

	public void setSource(String source, String message) {
		sources.putIfAbsent(source, message);
	}

	public boolean isOkay() { return sources.isEmpty(); }

	public Optional<Integer> validateIsNumber(String source, String value) {
		try {
			int i = Integer.parseInt(value);
			return Optional.of(i);
		} catch(NumberFormatException ex) {
			setSource(source, "Value is not a number.");
			return Optional.empty();
		}
	}

	public boolean validateNumberEquals(String source, int value, int expected) {
		if (value != expected) {
			setSource(source, "Value does not match the expected value.");
			return false;
		}

		return true;
	}

	public boolean validateNumberIn(String source, int value, List<Integer> expected) {
		if (!expected.contains(value)) {
			setSource(source, "Value is not allowed.");
			return false;
		}

		return true;
	}

	// Inclusive range
	public boolean validateNumberRange(String source, int value, int min, int max) {
		if (value < min || value > max) {
			setSource(source, "Value is outside allowed range.");
			return false;
		}

		return true;
	}

	public boolean validateStringNotEmpty(String source, String value) {
		if (value.length() == 0) {
			setSource(source, "Value must not be empty.");
			return false;
		}

		return true;
	}

	public boolean validateStringLengthEquals(String source, String value, int length) {
		if (value.length() != length ) {
			setSource(source, "Value is not the allowed length.");
			return false;
		}

		return true;
	}

	public boolean validateStringMaxLength(String source, String value, int max) {
		if (value.length() > max) {
			setSource(source, "Value is too long.");
			return false;
		}

		return true;
	}

	public boolean validateStringRegex(String source, String value, String regex) {
		if (!Pattern.compile(regex).matcher(value).find()) {
			setSource(source, "Value does not meet the expected format.");
			return false;
		}

		return true;
	}
}
