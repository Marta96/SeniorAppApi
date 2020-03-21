package com.application.Controller;

import com.application.Entity.Caregivers;
import com.application.Models.CaregiversObject;
import com.application.Repository.CaregiversRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/caregivers")
public class CaregiversController {
    private final CaregiversRepository caregiversRepository;

    @Autowired
    public CaregiversController(CaregiversRepository caregiversRepository) {
        this.caregiversRepository = caregiversRepository;
    }

    @PostMapping("/add")
    public CaregiversObject createCaregivers(@Valid @RequestBody CaregiversObject caregiversObject) {
        Caregivers caregivers = new Caregivers(caregiversObject);
        return new CaregiversObject(caregiversRepository.save(caregivers));
    }

    @GetMapping("/get")
    public CaregiversObject getCaregivers(@RequestParam(name = "login") String login,
                                          @RequestParam(name = "password") String password) throws Exception {
        try {
            return new CaregiversObject(caregiversRepository.findByLoginAndPassword(login, password));
        } catch(Exception e) {
            throw new NotFoundException("Not found", e);
        }
    }
}
