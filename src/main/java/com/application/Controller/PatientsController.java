package com.application.Controller;

import com.application.Entity.Patients;
import com.application.Models.PatientsObject;
import com.application.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientsController {
    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientsController(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    @PostMapping("/add")
    public PatientsObject addPatient(@Valid @RequestBody PatientsObject patientsObject) {
        Patients patients = new Patients(patientsObject);
        return new PatientsObject(patientsRepository.save(patients));
    }

    @GetMapping("/login/get")
    public PatientsObject getPatients(@RequestParam(name = "login") String login,
                                          @RequestParam(name = "password") String password) throws Exception {
        try {
            return new PatientsObject(patientsRepository.findByLoginAndPassword(login, password));
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient Not Found", e);
        }
    }

    @GetMapping("/get")
    public PatientsObject getPatientsByPersonalId(@RequestParam(name = "personal id") String personalId) throws Exception {
        try {
            return new PatientsObject(patientsRepository.findByPersonalIdentity(personalId));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient Not Found", e);
        }
    }

    @GetMapping("/get/all")
    public List<PatientsObject> getPatients() throws Exception {
        try {
            List<Patients> patients = patientsRepository.findAll();
            return patients.stream().map(PatientsObject::new).collect(Collectors.toList());
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patients Not Found", e);
        }
    }


}
