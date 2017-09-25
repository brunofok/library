package com.bruno.ac.product;

import com.bruno.ac.product.data.entity.Product;
import com.bruno.ac.product.data.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Path("/products")
public class ProductController {
    private IProductRepository productRepository;

    public ProductController(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GET
    @Produces("application/json")
    public Collection<Product> findAll(@RequestParam(required = false) String name){
        List<Product> productList = new ArrayList<>();

        return productList;
    }
}
