package com.apiDelivery.api.domain.exception.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {
    private Integer status;
    private String type;
    private String title;
    private String detail;

}