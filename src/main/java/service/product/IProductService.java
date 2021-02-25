package service.product;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    boolean remove(int id);

    boolean add(Product e);

    boolean update(int id, Product e);

    List<Product> searchByName(String name);

    Product searchById(int id);
}
