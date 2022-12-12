package com.dh.catalog.controller;

import com.dh.catalog.dto.CatalogDto;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalogs")
public class CatalogController {

	private final CatalogService service;

	public CatalogController(CatalogService service) {
		this.service = service;
	}

	@GetMapping
	ResponseEntity<CatalogDto> getAll(){
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("online/{genre}")
	ResponseEntity<CatalogDto> getCatalogOnline(@PathVariable String genre) throws Exception {
		return ResponseEntity.ok(service.getCatalogOnline(genre));
	}

	@GetMapping("offline/{genre}")
	ResponseEntity<CatalogDto> getCatalogOffline(@PathVariable String genre) {
		return ResponseEntity.ok(service.getCatalogOffline(genre));
	}

}
