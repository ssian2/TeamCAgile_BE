package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.entity.JobFamily;
import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.entity.Responsibility;
import com.kainos.jobnight.helper_classes.RoleResponsibility;
import com.kainos.jobnight.helper_classes.Validator;
import com.kainos.jobnight.projections.JobRole.JobRoleWithBandandFamily;
import com.kainos.jobnight.projections.JobRole.JobRoleWithBrandFamilyUrlAndSpec;
import com.kainos.jobnight.repo.BandRepository;
import com.kainos.jobnight.repo.JobFamilyRepository;
import com.kainos.jobnight.repo.JobRoleRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kainos.jobnight.helper_classes.Util.safeGetJSONString;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/job-role")
@RequiredArgsConstructor
public class JobRoleController {

	// Don't use @Autowired on controllers so we can test them better

	private final JobRoleRepository roleRepo;
	private final BandRepository bandRepo;
	private final JobFamilyRepository familyRepo;

	@GetMapping("/all")
	public List<JobRole> getAllJobRoles() {
		return roleRepo.findAll();
	}
	
	@GetMapping("/view-job-spec/{id}")
	public List<JobRoleWithBrandFamilyUrlAndSpec> viewJobSpecById(@PathVariable("id") Short id) {
		if (roleRepo.findById(id).isPresent()) {
			return roleRepo.getJobRoleDetailsById(id);
		}
		throw new ResponseStatusException(NOT_FOUND, "Job role does not exist");
	}

	@GetMapping("/view-job-spec")
	public List<JobRole> viewJobSpec(){
		return roleRepo.findAll();
	}

	@GetMapping("/view-band-level")
	public List<JobRole> viewBandLevel() {
		return roleRepo.findAll();
	}

	@GetMapping("/view-responsibilities-per-role")
	public List<RoleResponsibility> getResponsibilitiesPerRole() {
		List<JobRole> roles =  roleRepo.jobRoleResponsibilities();
		List<RoleResponsibility> joinedData= new ArrayList<RoleResponsibility>();

		for (JobRole j: roles){
			Set<Responsibility> responsibilities = j.getResponsibilities();
			RoleResponsibility data = new RoleResponsibility(j.getName());

			for (Responsibility r: responsibilities){
				data.AddResponsibility(r.getName());
			}

			if (!data.resps.isEmpty()){
				joinedData.add(data);
			}
		}
		return joinedData;
	}

	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Validator> addJobRole(@RequestBody String obj) {
		if (obj == null || obj.equals("")) obj = "{}";
		JSONObject json = new JSONObject(obj);
		Validator validator = new Validator();

		Optional<String> name = safeGetJSONString(json, "name");
		Optional<String> summary = safeGetJSONString(json, "summary");
		Optional<String> band = safeGetJSONString(json, "band");
		Optional<String> family = safeGetJSONString(json, "job_family");

		// Check if name is present, has characters and fits into 300 characters
		if (name.isPresent()) {
			validator.validateStringNotEmpty("name", name.get());
			validator.validateStringMaxLength("name", name.get(), 40);
		} else {
			validator.setSource("name", "Value must exist.");
		}

		// Check if summary is present, has characters and fits into 300 characters
		if (summary.isPresent()) {
			validator.validateStringNotEmpty("summary", summary.get());
			validator.validateStringMaxLength("summary", summary.get(), 300);
		} else {
			validator.setSource("summary", "Value must exist.");
		}

		List<Band> bands = bandRepo.findAll();

		// Validate if band exists, SQL will do this for us,
		// but this will generate a nicer error message
		if (band.isPresent()) {
			Optional<Integer> bandId = validator.validateIsNumber("band", band.get());

			if (bandId.isPresent()) {
				validator.validateNumberIn(
					"band",
					bandId.get(),
					bands.stream()
						.map(Band::getId)
						.map(Short::intValue)
						.collect(Collectors.toList()));
			}
		} else {
			validator.setSource("band", "Value must exist.");
		}

		List<JobFamily> families = familyRepo.findAll();

		// Validate if capability exists, SQL will do this for us,
		// but this will generate a nicer error message
		if (family.isPresent()) {
			Optional<Integer> jobFamilyId = validator.validateIsNumber("job_family", family.get());

			if (jobFamilyId.isPresent()) {
				validator.validateNumberIn(
					"job_family",
					jobFamilyId.get(),
					families.stream()
						.map(JobFamily::getID)
						.map(Long::intValue)
						.collect(Collectors.toList()));
			}
		} else {
			validator.setSource("job_family", "Value must exist.");
		}

		if (validator.isOkay()) {
			// If ALL of the above validation passed
			// Assemble new jobRole object
			JobRole jobRole = new JobRole();

			// Unsafe Optional<>::get()s here should be okay since we know validation passed
			jobRole.setName(name.get());

			// Accidentally called this 'summary', whoops
			// Still ain't changing it, tho.
			jobRole.setSpecification(summary.get());

			// Find band with matching Id, we know one exists
			jobRole.setBand(bands.stream()
				.filter(b -> b.getId() == Short.parseShort(band.get()))
				.findFirst()
				.orElse(null));

			// Find capability with matching Id, we know one exists
			jobRole.setJobFamily(families.stream()
				.filter(c -> c.getID() == Short.parseShort(family.get()))
				.findFirst()
				.orElse(null));

			// Insert into database
			roleRepo.save(jobRole);
		} else {
			// If ANY of the above validation failed,
			// return error 400 with what went wrong
			return new ResponseEntity<>(validator, HttpStatus.BAD_REQUEST);
		}

		//TODO: Validate administrator permissions somehow

		return new ResponseEntity<>(validator, (validator.isOkay()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}


	@GetMapping("/job-roles-with-band-and-family")
	public List<JobRoleWithBandandFamily> jobRoleBandAndFamily(){
		return roleRepo.jobRoleWithBandAndFamily();
	}


	@GetMapping("/by-capability/{name}")
	public List<JobRoleWithBandandFamily> jobRoleBandAndFamilyByCapability(@PathVariable("name") String name) {
		if (roleRepo.getJobRoleDetailsByCapabilityName(name).isEmpty()) {
			throw new ResponseStatusException(NOT_FOUND, "No Job roles in this capability");
		} else {
			return roleRepo.getJobRoleDetailsByCapabilityName(name);

		}
	}

	@GetMapping("/view-responsibilities-per-role/{id}")
	public RoleResponsibility getRespsPerRoleByID(@PathVariable("id") Short ID)
	{
		if(roleRepo.findById(ID).isPresent()){
			var result = roleRepo.getRoleWithRespById(ID);
			var resps = result.getResponsibilities();
			RoleResponsibility data = new RoleResponsibility(result.getName());
			for (Responsibility r: resps){
				data.AddResponsibility(r.getName());
			}
			return data;
		}else{
			return null;

		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public String deleteJobRoleByObject(@PathVariable("id") Short ID)
	{
		if(roleRepo.findById(ID).isPresent()) {
			//roleRepo.deleteById(ID);
			roleRepo.delete(ID);
			return "Deleted";
		}
		return "Not deleted";

	}
}