package com.apiDelivery.api.domain.service;

import java.util.List;

import com.apiDelivery.api.domain.filter.VendaDiariaFilter;
import com.apiDelivery.api.domain.model.dto.VendaDiaria;


public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}