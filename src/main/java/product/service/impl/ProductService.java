package product.service.impl;

import product.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{
    private static Map<Integer, Product> listProduct;

    static {
        listProduct = new HashMap<>();
        listProduct.put(1,new Product(1,"Iphone 11 pro","Phone",3000));
        listProduct.put(2,new Product(2,"Samsung Note 10","Phone",2500));
        listProduct.put(3,new Product(3,"Kawasaki Z100","Motorcycle",8500));
        listProduct.put(4,new Product(4,"Yamaha R6","Motorcycle",9000));
        listProduct.put(5,new Product(5,"Sony LCD","Television",2800));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(listProduct.values());
    }

    @Override
    public void save(Product product) {
        listProduct.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return listProduct.get(id);
    }

    @Override
    public void update(int id, Product product) {
        listProduct.put(id,product);
    }

    @Override
    public void remove(int id) {
        listProduct.remove(id);
    }
}
