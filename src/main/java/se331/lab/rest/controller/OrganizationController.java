package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Organization;
import se331.lab.rest.service.OrganizationService;

@Controller
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @GetMapping("organizations")
    public ResponseEntity<?> ge(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page) {
        Page<Organization> pageOutput = organizationService.getOrganizations(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count",String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(),responseHeader,HttpStatus.OK);
    }

    @GetMapping("organizations/{id}")
    public ResponseEntity<?> getOrganization(@PathVariable("id") Long id) {

        Organization output = organizationService.getOrganization(id);
        if (output != null){
            return ResponseEntity.ok(output);
        }else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

    @PostMapping("/organizations")
    public ResponseEntity<?> addEvent(@RequestBody Organization organization){
        Organization output = organizationService.save(organization);
        return ResponseEntity.ok(organization);
    }
}
