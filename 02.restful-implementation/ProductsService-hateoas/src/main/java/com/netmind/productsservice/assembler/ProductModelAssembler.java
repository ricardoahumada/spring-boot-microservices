package com.netmind.productsservice.assembler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.netmind.productsservice.controller.ProductServiceController;
import com.netmind.productsservice.entity.ProductEntity;
import com.netmind.productsservice.model.ProductModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<ProductEntity, ProductModel> {
    public ProductModelAssembler() {
        super(ProductServiceController.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(ProductEntity entity) {
        ProductModel productModel = instantiateModel(entity);

        productModel.add(linkTo(
                methodOn(ProductServiceController.class)
                        .getProduct(entity.getId()))
                .withSelfRel());

        productModel.setId(entity.getId());
        productModel.setName(entity.getName());
        return productModel;
    }

    @Override
    public CollectionModel<ProductModel> toCollectionModel(Iterable<? extends ProductEntity> entities) {
        CollectionModel<ProductModel> productModels = super.toCollectionModel(entities);

        productModels.add(linkTo(methodOn(ProductServiceController.class).getAllProducts()).withSelfRel());

        return productModels;
    }

    public List<ProductModel> toProductModel(List<ProductEntity> products) {
        if (products.isEmpty())
            return Collections.emptyList();

        return products.stream()
                .map(product -> ProductModel.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .build()
                        .add(linkTo(
                                methodOn(ProductServiceController.class)
                                        .getProduct(product.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }
}
