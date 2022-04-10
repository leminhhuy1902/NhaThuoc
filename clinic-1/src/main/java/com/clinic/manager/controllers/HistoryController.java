package com.clinic.manager.controllers;

import com.clinic.manager.dto.BillServiceDto;
import com.clinic.manager.dto.MedicineDto;
import com.clinic.manager.dto.UserDto;

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
public class HistoryController {

    @Autowired
    private BillServiceService billServiceService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicineService medicineService;

    private static final String ENTITY_NAME = "history";

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String pageLogin(Model model, HttpSession session) {
        Long id = Long.parseLong(session.getAttribute("id").toString());
        String role = session.getAttribute("roleUser").toString();
        PageAndDataResponse<List<BillServiceDto>> resultDB;
            resultDB = billServiceService.findByDoctor(id);
        List<BillServiceDto> result = resultDB.getList().stream().filter(item -> {
            return item.getStatus() == 2;
        }).collect(Collectors.toList());
        model.addAttribute("list", result);
        model.addAttribute("role", role);
        model.addAttribute("User", session.getAttribute("nameUser"));
        return ENTITY_NAME;
    }


    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String pay(Model model, HttpSession session) {
        PageAndDataResponse<List<BillServiceDto>> resultDB = billServiceService.findAll();
        List<BillServiceDto> result = resultDB.getList().stream().filter(item -> {
            return item.getStatus() == 2;
        }).collect(Collectors.toList());
        model.addAttribute("list", result);
        model.addAttribute("role", session.getAttribute("roleUser"));
        model.addAttribute("User", session.getAttribute("nameUser"));
        return "pay";
    }

    @RequestMapping(value = "/add-pay", method = RequestMethod.GET)
    public String pageAdd(@RequestParam(value = "id", required = false) Long id, Model model, HttpSession session) {
        List<UserDto> resultUser = userService.findAll().getList().stream().filter(item -> item.getIdRole() == 1).collect(Collectors.toList());
        List<MedicineDto> resultMedicine = medicineService.findAll().getList();
        model.addAttribute("listUser", resultUser);
        model.addAttribute("listPatient", patientService.findAll().getList());
        model.addAttribute("User", session.getAttribute("nameUser"));
        model.addAttribute("listMedicine", resultMedicine);
        model.addAttribute("role", session.getAttribute("roleUser"));
        BillServiceDto resultBillServiceDTO = billServiceService.findOne(id).getList();
        resultBillServiceDTO.setMedicine(resultBillServiceDTO.getIdMedicine());
        Integer priceMedicine = 0;
        for (MedicineDto item: resultMedicine
             ) {
            if (resultBillServiceDTO.getIdMedicine().contains(item.getId())) {
                priceMedicine += item.getPrice();
            }
        }
        resultBillServiceDTO.setPrice(priceMedicine + 150000);

        model.addAttribute("userForm", resultBillServiceDTO);
        model.addAttribute("url", "/pay?id=" + id);
        model.addAttribute("nameButton", "Thanh toán");

        return "addPay";
    }


    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String postUpdateEmployee(@RequestParam(value = "id", required = false) Long id,
                                     @ModelAttribute("userForm") BillServiceRequest request, HttpSession session) {
        log.debug("REST request to save user : {}", request);
        request.setStatusPay(2);
        billServiceService.partialUpdate(request, id);
        return "redirect:/pay";
    }
}
