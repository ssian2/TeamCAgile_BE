package com.kainos.jobnight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.kainos.jobnight.Index;

import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class JobnightApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(JobnightApplication.class, args);
		Index.getConnection();
		ResultSet test = Index.viewBandLevelForEachRole();
		while(test.next()){
			System.out.println("Hello");
			String out = String.format("%s is in %s.",
					test.getString("role_name"), test.getString("band_name"));
			System.out.println(out);
		}
	}

}
