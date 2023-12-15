package com.apiDelivery.api.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiDelivery.api.domain.filter.VendaDiariaFilter;
import com.apiDelivery.api.domain.service.VendaQueryService;
import com.apiDelivery.api.domain.service.VendaReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;

@Service
public class PdfVendasReportService implements VendaReportService{

	@Autowired
	private VendaQueryService vendaQueryService;
	
	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		try {
			var inputStream = this.getClass().getResourceAsStream(
					"/relatorios/vendas-diarias.jasper");
			
			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
			var dataSource = new JRBeanCollectionDataSource(vendasDiarias);
			System.out.println("Apraceu aqui sera");
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
	        JRPdfExporter exporter = new JRPdfExporter();
	        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));


			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
		}
	}
//		
		
//	}
//	
//		<textField>
//		    <reportElement x="0" y="0" width="100" height="20" />
//		    <textElement textAlignment="Left">
//		        <font size="10" isBold="false"/>
//		    </textElement>
//		    <textFieldExpression><![CDATA[$R{hello}]]></textFieldExpression>
//	    </textField>
	
}
