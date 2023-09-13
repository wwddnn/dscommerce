package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired /*injeta o componente productrepository*/
    private ProductService service;

    /* requisicao que busca o produto por id*/
    @GetMapping(value = "/{id}")/*casa esse id com o que esta embaixo, usamos entao o @PathVariable*/
    public ProductDTO findById(@PathVariable long id) {
        return service.findById(id);
    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {/*Pageable poe a lista paginada qd exibe */
        return service.findAll(pageable);
    }
}