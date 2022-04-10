package com.clinic.manager.controllers;

import com.clinic.manager.dto.UserDto;
import com.clinic.manager.request.PatientRequest;
import com.clinic.manager.response.PageAndDataResponse;
import com.clinic.manager.service.PatientService;
import com.clinic.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import com.clinic.manager.service.UserService;
import com.clinic.manager.request.UserRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Rest controller for  User resource.
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String pageLogin(@RequestParam(value = "mess", required = false) String mess, Model model) {
        model.addAttribute("userForm", new UserRequest());
        model.addAttribute("mess", mess);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute("userForm") UserRequest request) {
        log.debug("REST request to save user : {}", request);

        PageAndDataResponse<List<UserDto>> result = userService.findAll();
        String mess = "";
        for (UserDto item: result.getList()
             ) {
            if (item.getEmail().equals(request.getEmail())) {
                mess = "Email already exists!";
            }
        }
        if (!mess.equals("")) {

            return "redirect:/register?mess=" + mess;
        } else {
            if (request.getIdRole().equals(4L)) {
                PatientRequest patientRequest = new PatientRequest();
                patientRequest.setName(request.getName());
                patientService.save(patientRequest, null);
            }
            userService.save(request, null);
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@ModelAttribute("userForm") UserRequest request, HttpSession session) {
        log.debug("REST request to save user : {}", request);
        PageAndDataResponse<UserDto> dto = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (dto.getList() != null) {
            String code = "";
            if (dto.getList().getIdRole() == 1) {
                code = "DOCTOR";
            } else if(dto.getList().getIdRole() == 2) {
                code = "NURSE";
            } else if(dto.getList().getIdRole() == 4) {
                code = "USER";
            } else {
                code = "ADMIN";
            }
            session.setAttribute("nameUser", dto.getList().getName());
            session.setAttribute("id", dto.getList().getId());
            session.setAttribute("roleUser", code);
            if(code.equals("USER")) {
                return "redirect:/list";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/login?mess=Wrong account or password please check again!!";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String pageRegister(@RequestParam(value = "mess", required = false) String mess, Model model) {

        model.addAttribute("userForm", new UserRequest());
        model.addAttribute("roles", roleService.findAll().getList());
        model.addAttribute("mess", mess);
        return "register";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String uploadOneFileHandler(@RequestParam(value = "mess", required = false) String mess, Model model, HttpSession session) {
        model.addAttribute("mess", mess);
        model.addAttribute("listUser", userService.findAll().getList());
        model.addAttribute("User", session.getAttribute("nameUser"));
        model.addAttribute("role", session.getAttribute("roleUser"));
        return "index";
    }

    @RequestMapping(value = "/add-employee", method = RequestMethod.GET)
    public String pageAdd(@RequestParam(value = "mess", required = false) String mess, @RequestParam(value = "id", required = false) Long id, Model model, HttpSession session) {
        model.addAttribute("roles", roleService.findAll().getList());
        model.addAttribute("User", session.getAttribute("nameUser"));
        model.addAttribute("role", session.getAttribute("roleUser"));
        model.addAttribute("mess", mess);
        if(id == null) {
            model.addAttribute("userForm", new UserRequest());
            model.addAttribute("url", "/create_employee");
            model.addAttribute("nameButton", "Create");
        } else {
            model.addAttribute("userForm", userService.findOne(id).getList());
            model.addAttribute("url", "/update_employee?id=" + id);
            model.addAttribute("nameButton", "Update");
        }
        return "addEmployee";
    }

    @RequestMapping(value = "/create_employee", method = RequestMethod.POST)
    public String postCreateEmployee(@ModelAttribute("userForm") UserRequest request) {
        log.debug("REST request to save user : {}", request);
        PageAndDataResponse<List<UserDto>> result = userService.findAll();
        String mess = "";
        for (UserDto item: result.getList()
        ) {
            if (item.getEmail().equals(request.getEmail())) {
                mess = "Email already exists!";
            }
        }
        if (!mess.equals("")) {
            return "redirect:/add-employee?mess=" + mess;
        } else {
            if (request.getIdRole().equals(4L)) {
                PatientRequest patientRequest = new PatientRequest();
                patientRequest.setName(request.getName());
                patientService.save(patientRequest, null);
            }
            request.setPassword("123");
            userService.save(request, null);
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/update_employee", method = RequestMethod.POST)
    public String postUpdateEmployee(@RequestParam(value = "id", required = false) Long id,
                                     @ModelAttribute("userForm") UserRequest request, HttpSession session) {
        log.debug("REST request to save user : {}", request);
        PageAndDataResponse<List<UserDto>> result = userService.findAll();
        String mess = "";
        for (UserDto item: result.getList()
        ) {
            if (item.getEmail().equals(request.getEmail()) && !item.getId().equals(id)) {
                mess = "Email already exists!";
            }
        }
        if (!mess.equals("")) {
            return "redirect:/add-employee?id="+ id +"&&mess=" + mess;
        } else {
            userService.partialUpdate(request, id);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/delete_employee", method = RequestMethod.GET)
    public String postDeleteEmployee(@RequestParam(value = "id", required = false) Long id, HttpSession session) {
        log.debug("REST request to delete user : {}", id);
        userService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String pageList(Model model, HttpSession session) {
        List<UserDto> result = userService.findAll().getList().stream().filter(item -> item.getIdRole() == 1).collect(Collectors.toList());
        model.addAttribute("listUser", result);
        model.addAttribute("User", session.getAttribute("nameUser"));

        model.addAttribute("role", session.getAttribute("roleUser"));
        return "list";
    }

}
