package com.application.Controller;

import com.application.Entity.TestMmse;
import com.application.Models.TestMmseObject;
import com.application.Repository.TestMmseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/testMmse")
public class TestMmseController {
    private final TestMmseRepository testMmseRepository;

    @Autowired
    public TestMmseController(TestMmseRepository testMmseRepository) {
        this.testMmseRepository = testMmseRepository;
    }

    @PostMapping("/add")
    public TestMmseObject addTestMmse(@Valid @RequestBody TestMmseObject testMmseObject) {
        TestMmse testMmse = new TestMmse(testMmseObject);
        return new TestMmseObject(testMmseRepository.save(testMmse));
    }

    @GetMapping("/get/all")
    public List<TestMmseObject> getAllTestMmseByPersonalId(@RequestParam(name = "patient id") Integer id) throws Exception {
        try {
            List<TestMmse> testMmses = testMmseRepository.findAllByIdPatientsOrderByDateDesc(id);
            return testMmses.stream().map(TestMmseObject::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient Not Found", e);
        }
    }

    @GetMapping("/get/last")
    public TestMmseObject getLastTestMmseByPersonalId(@RequestParam(name = "patient id") Integer id) throws Exception {
        try {
            return new TestMmseObject(testMmseRepository.findFirstByIdPatientsOrderByDateDesc(id));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient Not Found", e);
        }
    }
}
