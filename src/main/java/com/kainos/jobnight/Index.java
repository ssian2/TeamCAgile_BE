package com.kainos.jobnight;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Index {

	@GetMapping("/hello")
	public JSONObject index() {
		return new JSONObject("{'aa':'bb'}");
	}

}