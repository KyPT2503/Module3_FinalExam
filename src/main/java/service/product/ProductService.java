package service.product;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    public static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/productmanager";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "250399";

    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(CLASS_NAME);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from productmanager.product");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int amount = resultSet.getInt("amount");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                String category = null;
                preparedStatement = connection.prepareStatement("select name from productmanager.category where id=?");
                preparedStatement.setInt(1, categoryId);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                if (resultSet1.next()) {
                    category = resultSet1.getString("name");
                }
                System.out.println(category);
                products.add(new Product(id, name, price, amount, color, description, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return products;
    }

    @Override
    public boolean remove(int id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from productmanager.product where id=?");
            preparedStatement.setInt(1, id);
            boolean isRemoved = preparedStatement.executeUpdate() > 0;
            if (isRemoved) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean add(Product product) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            int categoryId = getCategoryIdByName(product.getCategory());
            preparedStatement = connection.prepareStatement("insert into productmanager.product (name, price, amount, color, description, category_id) value (?,?,?,?,?,?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getAmount());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, categoryId);
            boolean isAdded = preparedStatement.executeUpdate() > 0;
            if (isAdded) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    private int getCategoryIdByName(String category) {
        int id = -1;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select id from productmanager.category where name=?");
            preparedStatement.setString(1, category);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public boolean update(int id, Product product) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            int categoryId = getCategoryIdByName(product.getCategory());
            preparedStatement = connection.prepareStatement("update productmanager.product set name=?,price=?,amount=?,color=?,description=?,category_id=? where id=?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getAmount());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, categoryId);
            preparedStatement.setInt(7, id);
            boolean isUpdated = preparedStatement.executeUpdate() > 0;
            if (isUpdated) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> products = this.getAll();
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(name)) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public Product searchById(int id) {
        List<Product> products = this.getAll();
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
