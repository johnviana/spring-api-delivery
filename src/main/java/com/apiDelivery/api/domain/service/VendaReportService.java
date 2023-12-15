package com.apiDelivery.api.domain.service;

import org.springframework.stereotype.Repository;

import com.apiDelivery.api.domain.filter.VendaDiariaFilter;

@Repository
public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}