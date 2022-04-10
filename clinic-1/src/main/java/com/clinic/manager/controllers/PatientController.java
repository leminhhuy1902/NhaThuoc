package com.clinic.manager.controllers;


import com.clinic.manager.request.UserRequest;
import com.clinic.manager.response.PageAndDataResponse;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tech.jhipster.web.util.HeaderUtil;

import lombok.extern.slf4j.Slf4j;
import com.clinic.manager.service.PatientService;
import com.clinic.manager.request.PatientRequest;
import com.clinic.manager.dto.PatientDto;

import javax.servlet.http.HttpSession;


/**
 * Rest controller for  Patient resource.
 */
@Slf4j
@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    private static final String ENTITY_NAME = "patient";

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public String pageLogin(Model model, HttpSession session) {
        model.addAttribute("listUser", patientService.findAll().getList());
        model.addAttribute("User", session.getAttribute("nameUser"));
        model.addAttribute("role", session.getAttribute("roleUser"));
        return ENTITY_NAME;
    }

    @RequestMapping(value = "/add-patient", method = RequestMethod.GET)
    public String pageAdd(@RequestParam(value = "id", required = false) Long id, Model model, HttpSession session) {
        model.addAttribute("User", session.getAttribute("nameUser"));
        model.addAttribute("role", session.getAttribute("roleUser"));
        if(id == null) {
            model.addAttribute("userForm", new UserRequest());
            model.addAttribute("url", "/create_patient");
            model.addAttribute("nameButton", "Create");
        } else {
            model.addAttribute("userForm", patientService.findOne(id).getList());
            model.addAttribute("url", "/update_patient?id=" + id);
            model.addAttribute("nameButton", "Update");
        }
        return "addPatient";
    }

    @RequestMapping(value = "/create_patient", method = RequestMethod.POST)
    public String postCreateEmployee(@ModelAttribute("userForm") PatientRequest request, HttpSession session) {
        log.debug("REST request to save user : {}", request);
        patientService.save(request, null);
        return "redirect:/patient";
    }

    @RequestMapping(value = "/update_patient", method = RequestMethod.POST)
    public String postUpdateEmployee(@RequestParam(value = "id", required = false) Long id,
                                     @ModelAttribute("userForm") PatientRequest request, HttpSession session) {
        log.debug("REST request to save user : {}", request);
        patientService.partialUpdate(request, id);
        return "redirect:/patient";
    }

    @RequestMapping(value = "/delete_patient", method = RequestMethod.GET)
    public String postDeleteEmployee(@RequestParam(value = "id", required = false) Long id, HttpSession session) {
        log.debug("REST request to delete user : {}", id);
        patientService.delete(id);
        return "redirect:/patient";
    }
}
