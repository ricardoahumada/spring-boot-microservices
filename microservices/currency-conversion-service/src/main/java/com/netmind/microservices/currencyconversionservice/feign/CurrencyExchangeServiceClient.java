package com.netmind.microservices.currencyconversionservice.feign;

import com.netmind.microservices.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name = "${microservices.currency-exchange-service.name}", url = "${microservices.currency-exchange-service.base-url}")
@FeignClient(name = "${microservices.currency-exchange-service.name}") //for eureka
public interface CurrencyExchangeServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "${microservices.currency-exchange-service.path}")
    public CurrencyConversionBean getConversion(
            @PathVariable String from,
            @PathVariable String to
    );

}
