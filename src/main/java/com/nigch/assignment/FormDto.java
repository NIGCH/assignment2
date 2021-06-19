package com.nigch.assignment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class FormDto {

    @NotBlank(message = "Input cannot be blank")
    @Size(max = 10, message = "Enter a maximum of {max} characters")
    String input;
}
