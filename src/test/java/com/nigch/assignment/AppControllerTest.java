package com.nigch.assignment;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldRenderFormPage() throws Exception {
		mockMvc.perform(get("/form"))
				.andExpect(status().isOk())
				.andExpect(view().name("form"))
				.andExpect(model().attributeExists("formDto"));
	}

	@Test
	void shouldRedirectToSuccessPageWhenInputIsValid() throws Exception {
		mockMvc.perform(post("/form")
				.param("input", "0123456789"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/success"));
	}

	@Test
	void shouldRedirectToFormPageWhenInputIsBlank() throws Exception {
		mockMvc.perform(post("/form")
				.param("input", ""))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/form"))
				.andExpect(flash().attributeExists(BindingResult.MODEL_KEY_PREFIX + "formDto"))
				.andExpect(flash().attributeExists("formDto"));
	}

	@Test
	void shouldRedirectToFormPageWhenInputExceedsMaximumLength() throws Exception {
		mockMvc.perform(post("/form")
				.param("input", "0123456789A"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/form"))
				.andExpect(flash().attributeExists(BindingResult.MODEL_KEY_PREFIX + "formDto"))
				.andExpect(flash().attributeExists("formDto"));
	}

	@Test
	void shouldRenderSuccessPage() throws Exception {
		mockMvc.perform(get("/success"))
				.andExpect(status().isOk())
				.andExpect(view().name("success"))
				.andExpect(model().attribute("message", "Submission successful"));
	}
}
