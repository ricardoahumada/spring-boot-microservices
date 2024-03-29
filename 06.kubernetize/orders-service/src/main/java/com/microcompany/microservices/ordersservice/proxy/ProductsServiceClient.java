package com.microcompany.microservices.ordersservice.proxy;

import com.microcompany.microservices.ordersservice.model.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "products-service", url = "${products-service.base-url}")
public interface ProductsServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "${products-service.path}")
    public ProductBean getProduct(@PathVariable Long id);

}
