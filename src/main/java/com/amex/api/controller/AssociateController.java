package com.amex.api.controller;

import com.amex.api.data.Associate;
import com.amex.api.service.AssociateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AssociateController {

    @Autowired
    private AssociateService associateService;

    @PostMapping("/associate")
    public Associate saveAssociate(@Valid Associate associate) {
        return associateService.saveAssociate(associate);
    }

    @GetMapping("/associates")
    public List<Associate> getAssociates() {
        return associateService.fetchAssociates();
    }

    @GetMapping("/associate/{id}")
    public Optional<Associate> getAssociatesById(@PathVariable("id") Long id) {
        return associateService.fetchAssociateById(id);
    }

    @PutMapping("/associate/{id}")
    public Associate updateAssociate(@RequestBody Associate associate, @PathVariable("id") Long id) {
        return associateService.updateAssociate(associate, id);
    }

    @DeleteMapping("/associate/{id}")
    public String deleteAssociate(@PathVariable("id") Long id) {
        associateService.deleteAssociateById(id);
        return "Deleted successfully";
    }
}
