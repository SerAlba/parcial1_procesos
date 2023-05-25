package com.parcial1.products.services;

import com.parcial1.products.models.Product;
import com.parcial1.products.repository.ProductRepository;
import com.parcial1.products.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> createProduct() {
        RestTemplate restTemplate = new RestTemplate();
        Product[] incomingList = restTemplate.getForObject("https://fakestoreapi.com/products", Product[].class);
        List<Product> productsToInsert = new ArrayList<>(Arrays.asList(incomingList));

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (Product prod : productsToInsert) {
            entityManager.merge(prod);
        }

        entityManager.getTransaction().commit();

        return productsToInsert;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productBD = productRepository.findById(id).get();

        if (product.getTitle() != null) {
            productBD.setTitle(product.getTitle());
        }
        if (product.getPrice() != null) {
            productBD.setPrice(product.getPrice());
        }
        if (product.getDescription() != null) {
            productBD.setDescription(product.getDescription());
        }
        if (product.getCategory() != null) {
            productBD.setCategory(product.getCategory());
        }
        if (product.getImage() != null) {
            productBD.setImage(product.getImage());
        }

        return productRepository.save(productBD);
    }
}
