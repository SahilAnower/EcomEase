package com.sahil.products.controller;

import com.sahil.products.constants.ProductsConstants;
import com.sahil.products.dto.ProductDto;
import com.sahil.products.dto.ProductsContactInfoDto;
import com.sahil.products.dto.ResponseDto;
import com.sahil.products.service.IProductsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/products", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ProductsController {

    private final IProductsService iProductsService;

    public ProductsController(IProductsService iProductsService) {
        this.iProductsService = iProductsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private ProductsContactInfoDto productsContactInfoDto;

    @Autowired
    private Environment environment;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        iProductsService.createProduct(productDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductsConstants.STATUS_201, ProductsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<ProductDto> getProductByIdAndQty(@RequestParam Long id, @RequestParam Integer quantity) {
        ProductDto productDto = iProductsService.getProduct(id, quantity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productDto);
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<List<ProductDto>> getAllProducts () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iProductsService.getAllProducts());
    }

    @GetMapping("/fetch-by-category")
    public ResponseEntity<List<ProductDto>> getProductsByCategory (@RequestParam Long categoryId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iProductsService.getProductsByCategory(categoryId));
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<ProductsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productsContactInfoDto);
    }

    @PostMapping("/get-product-availability")
    public ResponseEntity<Map<Long, Double>> getProductAvailability (@RequestBody Map<Long, Integer> productQuantityMap) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iProductsService.getProductPriceMap(productQuantityMap));
    }
}
