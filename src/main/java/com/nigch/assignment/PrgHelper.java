package com.nigch.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class PrgHelper<T> {
    private static final String REDIRECT = "redirect:";

    @Autowired
    private HttpServletRequest request;

    public String redirect(T formDto, BindingResult bindingResult, String formAttribute, String successUrl) {

        if (bindingResult.hasErrors()) {
            FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
            flashMap.put(BindingResult.MODEL_KEY_PREFIX + formAttribute, bindingResult);
            flashMap.put(formAttribute, formDto);
            return REDIRECT + request.getRequestURI();
        }

        return REDIRECT + successUrl;
    }
}
