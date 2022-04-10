package com.clinic.manager.controllers;


import com.clinic.manager.request.MedicineRequest;
import com.clinic.manager.service.UnitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import com.clinic.manager.service.MedicineService;

import javax.servlet.http.HttpSession;


/**
 * Rest controller for  Medicine resource.
 */
@Slf4j
@Controller
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private UnitService unitService;

    private static final String ENTITY_NAME = "medicine";

    @RequestMapping(value = "/medicine", method = RequestMethod.GET)
    public String pageLogin(Model model, HttpSession session) {
        model.addAttribute("User", session.getAttribute("nameUser"));
        model.addAttribute("list", medicineService.findAll().getList());
        model.addAttribute("role", session.getAttribute("roleUser"));
        return ENTITY_NAME;
    }

    @RequestMapping(value = "/add-medicine", method = RequestMethod.GET)
    public String pageAdd(@RequestParam(value = "id", required = false) String id, Model model, HttpSession session) {
        model.addAttribute("User", session.getAttribute("nameUser"));
        model.addAttribute("units", unitService.findAll().getList());
        model.addAttribute("role", session.getAttribute("roleUser"));
        if(id == null) {
            model.addAttribute("userForm", new MedicineRequest());
            model.addAttribute("url", "/create_medicine");
            model.addAttribute("nameButton", "Create");
        } else {
            model.addAttribute("userForm", medicineService.findOne(id).getList());
            model.addAttribute("url", "/update_medicine?id=" + id);
            model.addAttribute("nameButton", "Update");
        }
        return "addMedicine";
    }

    @RequestMapping(value = "/create_medicine", method = RequestMethod.POST)
    public String postCreateEmployee(@ModelAttribute("userForm") MedicineRequest request, HttpSession session) {
        log.debug("REST request to save user : {}", request);
        medicineService.save(request);
        return "redirect:/medicine";
    }

    @RequestMapping(value = "/update_medicine", method = RequestMethod.POST)
    public String postUpdateEmployee(@RequestParam(value = "id", required = false) String id,
                                     @ModelAttribute("userForm") MedicineRequest request, HttpSession session) {
        log.debug("REST request to save user : {}", request);
        medicineService.partialUpdate(request, id);
        return "redirect:/medicine";
    }

    @RequestMapping(value = "/delete_medicine", method = RequestMethod.GET)
    public String postDeleteEmployee(@RequestParam(value = "id", required = false) String id, HttpSession session) {
        log.debug("REST request to delete user : {}", id);
        medicineService.delete(id);
        return "redirect:/medicine";
    }
}
