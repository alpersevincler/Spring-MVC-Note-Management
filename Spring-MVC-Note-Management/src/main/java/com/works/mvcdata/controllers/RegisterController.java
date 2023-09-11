package com.works.mvcdata.controllers;

import com.works.mvcdata.entities.Customer;
import com.works.mvcdata.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    final CustomerService customerService;
    final HttpServletRequest req;
    String error = "";
    String success = "";
    int status = -1;

    @GetMapping("/register")
    private String register(Model model) {
        req.getSession().setAttribute("cid", 2l);
        model.addAttribute("status", status);
        model.addAttribute("error", error);
        model.addAttribute("success", success);


        Customer c = customerService.login("ali@gggmöail", "1234");
        System.out.println(c);
        return "customerRegister";
    }

    @PostMapping("/customerRegister")
    private String customerRegister(Customer customer) {
        Customer c = customerService.save(customer);
        if (c != null && c.getCid() > 0) {
            error = customer.getEmail() + "Bu mail edresi daha önce kayıtlı!";

        } else if (c != null && c.getCid() > 0) {
            success = customer.getEmail() + "Kayıt işlemi başarılı!";

        }
        return "redirect:/";
    }
}
