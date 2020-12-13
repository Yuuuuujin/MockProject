package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.DeleteForm;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/list")
public class DeleteController {

	private EmployeeService empService;

	@Autowired
	public DeleteController(EmployeeService empService) {
		this.empService = empService;
	}

	@RequestMapping(value= "/empList", method= RequestMethod.POST)
	public String delete(@RequestBody DeleteForm deleteForm) {

		empService.delete(deleteForm.getEmpId());

		HashMap<String, String> deleteMsg = new HashMap<String, String>();

		deleteMsg.put("deleteMsg", "削除が成功しました");

        ObjectMapper objMapper = new ObjectMapper();
        String json = "";
		try {
			json = objMapper.writeValueAsString(deleteMsg);

		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		return json;
	}

}