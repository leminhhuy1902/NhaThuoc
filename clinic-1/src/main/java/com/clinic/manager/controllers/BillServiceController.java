package com.clinic.manager.controllers;


import com.clinic.manager.dto.BillServiceDto;
import com.clinic.manager.dto.PatientDto;
import com.clinic.manager.dto.UserDto;
import com.clinic.manager.request.MedicineRequest;

import com.clinic.manager.response.PageAndDataResponse;
import com.clinic.manager.service.MedicineService;
import com.clinic.manager.service.PatientService;
import com.clinic.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import com.clinic.manager.service.BillServiceService;
import com.clinic.manager.request.BillServiceRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Rest controller for  BillService resource.
 */
@Slf4j
@Controller
public class BillServiceController {

    @Autowired
    private BillServiceService billServiceService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicineService medicineService;

    private static final String ENTITY_NAME = "billService";

    @RequestMapping(value = "/bill_service", method = RequestMethod.GET)
    public String pageLogin(Model model, HttpSession session) {
        Long id = Long.parseLong(session.getAttribute("id").toString());
        PageAndDataResponse<List<BillServiceDto>> resultDB;

        resultDB = billServiceService.findByDoctor(id);
        List<BillServiceDto> result = resultDB.getList().stream().filter(item -> {
            return item.getStatus() == 1;
        }).collect(Collectors.toList());
        model.addAttribute("list", result);
        model.addAttribute("role", session.getAttribute("roleUser"));
        model.addAttribute("User", session.getAttribute("nameUser"));
        return ENTITY_NAME;
    }

    @RequestMapping(value = "/add-bill_service", method = RequestMethod.GET)
    public String pageAdd(@RequestParam(value = "id", required = false) Long id, Model model, HttpSession session) {
        List<UserDto> resultUser = userService.findAll().getList().stream().filter(item -> item.getIdRole() == 1).collect(Collectors.toList());

        model.addAttribute("listUser", resultUser);
        model.addAttribute("role", session.getAttribute("roleUser"));
        model.addAttribute("listPatient", patientService.findAll().getList());
        model.addAttribute("User", session.getAttribute("nameUser"));
        model.addAttribute("listMedicine", medicineService.findAll().getList());

        if(id == null) {
            model.addAttribute("userForm", new BillServiceRequest());
            model.addAttribute("url", "/create_bill_service");
            model.addAttribute("nameButton", "Create");
        } else {
            BillServiceDto resultBillServiceDTO = billServiceService.findOne(id).getList();
            resultBillServiceDTO.setMedicine(resultBillServiceDTO.getIdMedicine());
            model.addAttribute("userForm", resultBillServiceDTO);
            model.addAttribute("url", "/update_bill_service?id=" + id);
            model.addAttribute("nameButton", "Update");
        }
        return "addBillService";
    }

    @RequestMapping(value = "/create_bill_service", method = RequestMethod.POST)
    public String postCreateEmployee(@ModelAttribute("userForm") BillServiceRequest request, HttpSession session) {
        log.debug("REST request to save user : {}", request);
        billServiceService.save(request, null);
        return "redirect:/bill_service";
    }

    @RequestMapping(value = "/update_bill_service", method = RequestMethod.POST)
    public String postUpdateEmployee(@RequestParam(value = "id", required = false) Long id,
                                     @ModelAttribute("userForm") BillServiceRequest request, HttpSession session) {
        log.debug("REST request to save user : {}", request);
        billServiceService.partialUpdate(request, id);
        return "redirect:/bill_service";
    }

    @RequestMapping(value = "/delete_bill_service", method = RequestMethod.GET)
    public String postDeleteEmployee(@RequestParam(value = "id", required = false) Long id, HttpSession session) {
        log.debug("REST request to delete user : {}", id);
        billServiceService.delete(id);
        return "redirect:/bill_service";
    }
}
