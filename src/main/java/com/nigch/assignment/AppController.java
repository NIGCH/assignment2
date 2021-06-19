package com.nigch.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AppController {
    private static final String FORM_URL = "/form";
    private static final String FORM_ATTRIBUTE = "formDto";
    private static final String SUCCESS_URL = "/success";

    @Autowired
    PrgHelper<FormDto> prgHelper;

    @GetMapping(FORM_URL)
    public String getForm(Model model) {
        if (!model.containsAttribute(FORM_ATTRIBUTE)) {
            model.addAttribute(new FormDto());
        }
        return "form";
    }

    @PostMapping(FORM_URL)
    public String postForm(@Valid @ModelAttribute FormDto formDto, BindingResult bindingResult) {
        return prgHelper.redirect(formDto, bindingResult, FORM_ATTRIBUTE, SUCCESS_URL);
    }

    @GetMapping(SUCCESS_URL)
    public String getSuccess(Model model) {
        model.addAttribute("message", "Submission successful");
        return "success";
    }
}
