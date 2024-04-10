package com.example.Project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
class PatientController {

    private final ItemObjRepository repository;
    private final MyKafkaProducer myKafkaProducer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired(required = false)
    public PatientController(ItemObjRepository repository, MyKafkaProducer myKafkaProducer) {
        this.repository = repository;
        this.myKafkaProducer = myKafkaProducer;
    }

    @GetMapping
    public String getAllPatients(Model model) {
        model.addAttribute("patients", repository.findAll());
        return "patients";
    }

    @PostMapping
    public String addPatient(@ModelAttribute ItemObj patient) {
        repository.save(patient);
        if (myKafkaProducer != null) {
            String message = convertPatientToMessage(patient);
            myKafkaProducer.sendMessage("myTopic", String.valueOf(patient.getId()), message);
        }
        return "redirect:/patients";
    }

    @GetMapping("/{id}")
    public String getPatient(@PathVariable Long id, Model model) {
        ItemObj patient = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        model.addAttribute("patient", patient);
        return "patient";
    }

    @PostMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/patients";
    }

    private String convertPatientToMessage(ItemObj patient) {
        try {
            return objectMapper.writeValueAsString(patient);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при преобразовании пациента в сообщение", e);
        }
    }
}

