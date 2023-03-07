package com.apiDelivery.api.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiDelivery.api.domain.service.GrupoService;

@RestController
@RequestMapping(value = "/api/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoService grupoService;
	
}
