package com.apiDelivery.api.api.exeptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(value = Include.NON_NULL )
public class Problem {
	
	private Integer status;
	private LocalDateTime timestamp;
	private String type;
	private String title;
	private String detail;
	private String userMassage;
	private List<Fild> fields;
	
	
	@Getter
	@Builder
	public static class Fild{
		
		private String name;
		private String userMessage;
		
	}
	

}
