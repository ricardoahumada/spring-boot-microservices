package com.microcompany.productsservice.assembler;

import com.microcompany.productsservice.controller.ProductServiceController;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.resource.ProductResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductResource> {
    public ProductModelAssembler() {
        super(ProductServiceController.class, ProductResource.class);
    }

    @Override
    public ProductResource toModel(Product entity) {
        ProductResource productModel = instantiateModel(entity);

        return productModel;
    }

    public Collection<ProductResource> toModel(Collection<Product> products) {
        if (products.isEmpty()) return Collections.emptyList();

        return null;
    }

    @Override
    public CollectionModel<ProductResource> toCollectionModel(Iterable<? extends Product> entities) {
        CollectionModel<ProductResource> productModels = super.toCollectionModel(entities);

        return productModels;
    }

}
