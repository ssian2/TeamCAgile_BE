package com.kainos.jobnight;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

@RestController
@RequestMapping("/api")
public class Index {

	private static Connection c;
	public Index(){}

	public static Connection getConnection() {
		String user;
		String password;
		String host;

		if (c != null) {
			return c;
		}

		try {
			FileInputStream propsStream =
					new FileInputStream("database.properties");

			Properties props = new Properties();
			props.load(propsStream);

			user            = props.getProperty("user");
			password        = props.getProperty("password");
			host            = props.getProperty("host");

			if (user == null || password == null || host == null)
				throw new IllegalArgumentException(
						"Properties file must exist and must contain "
								+ "user, password, and host properties.");

			c = DriverManager.getConnection(
					host+ "/TeamCWebApp?useSSL=false", user, password);
			return c;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/hello")
	public JSONObject index() {
		return new JSONObject("{'aa':'bb'}");
	}



	@GetMapping("/view_band_level")
	public static  ResultSet viewBandLevelForEachRole() {
		ResultSet BandForRole = null;
		try{
			Statement st = c.createStatement();
			BandForRole = st.executeQuery("SELECT role_name,band_name from employee_role join band on employee_role.band_id = band.band_id;");
		}catch(Exception e){
			System.out.println(e);
		}
		return  BandForRole;
	}


}