package com.kainos.jobnight.controller;

import com.kainos.jobnight.entity.Band;
import com.kainos.jobnight.entity.Capability;
import com.kainos.jobnight.entity.JobFamily;
import com.kainos.jobnight.entity.JobRole;
import com.kainos.jobnight.helper_classes.Validator;
import com.kainos.jobnight.projections.capability.CapabilityAndJobFamilies;
import com.kainos.jobnight.projections.capability.CapabilityLeadInfo;
import com.kainos.jobnight.projections.capability.CapabilityWithRolesAndID;
import com.kainos.jobnight.repo.CapabilityRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kainos.jobnight.helper_classes.Util.safeGetJSONString;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/capability")
@RequiredArgsConstructor
public class CapabilityController {


    private final CapabilityRepository CapabilityRepository;

    @GetMapping("/")
	public List<Capability> getAllCapabilities() {
		return CapabilityRepository.findAll();
	}

    @GetMapping("getCapability/{name}")
    public List<CapabilityAndJobFamilies> getCapability(@PathVariable("name") String name){
        if(CapabilityRepository.getCapabilityFamiliesByName(name).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Capability does not exist");
        }else{
            return CapabilityRepository.getCapabilityFamiliesByName(name);
        }
    }

    @GetMapping("/leads")
    public List<CapabilityLeadInfo> getAllCapabilityLeads() {

        return CapabilityRepository.getCapabilityLeadInfo();
    }
  
    @GetMapping("/withRoles")
    public List<CapabilityWithRolesAndID> getAllCapabilitiesWithRoles() {
        return CapabilityRepository.getAllRolesWithCapability();
    }


    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Validator> addCapability(@RequestBody String obj) {
        if (obj == null || obj.equals("")) obj = "{}";
        JSONObject json = new JSONObject(obj);
        Validator validator = new Validator();

        Optional<String> cap_name = safeGetJSONString(json, "cap_name");
        Optional<String> lead_name = safeGetJSONString(json, "lead_name");
        Optional<String> url = safeGetJSONString(json, "url");
        Optional<String> message = safeGetJSONString(json, "message");

        // Check if capablity name is present, has characters and fits into 20 characters
        if (cap_name.isPresent()) {
            validator.validateStringNotEmpty("cap_name", cap_name.get());
            validator.validateStringMaxLength("cap_name", cap_name.get(), 20);
        } else {
            validator.setSource("cap_name", "Value must exist.");
        }

        // Check if lead name is present, has characters and fits into 100 characters
        if (lead_name.isPresent()) {
            validator.validateStringNotEmpty("lead_name", lead_name.get());
            validator.validateStringMaxLength("lead_name", lead_name.get(), 100);
        } else {
            validator.setSource("lead_name", "Value must exist.");
        }
        // Check if url is present, has characters and fits into 700 characters
        if (url.isPresent()) {
            validator.validateStringNotEmpty("url", url.get());
            validator.validateStringMaxLength("url", url.get(), 700);
        } else {
            validator.setSource("url", "Value must exist.");
        }

        // Check if lead message is present, has characters and fits into 100 characters
        if (message.isPresent()) {
            validator.validateStringNotEmpty("message", message.get());
            validator.validateStringMaxLength("message", message.get(), 1000);
        } else {
            validator.setSource("message", "Value must exist.");
        }



        if (validator.isOkay()) {
            // If ALL of the above validation passed
            // Assemble new capability object
            Capability cap = new Capability();

            // Unsafe Optional<>::get()s here should be okay since we know validation passed
            cap.setName(cap_name.get());

            cap.setLeadName(lead_name.get());

            cap.setLeadPhoto(url.get());

            cap.setLeadMessage(message.get());

            // Insert into database
            CapabilityRepository.save(cap);
        } else {
            // If ANY of the above validation failed,
            // return error 400 with what went wrong
            return new ResponseEntity<>(validator, HttpStatus.BAD_REQUEST);
        }

        //TODO: Validate administrator permissions somehow

        return new ResponseEntity<>(validator, (validator.isOkay()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
