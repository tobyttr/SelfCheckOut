package com.vaadin.tutorial.crm.backend;

import com.vaadin.flow.component.crud.Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLConnectionService {

    String connectionURL = "jdbc:postgresql://localhost:5432/tobiastajetreitan";

    String userName = "java";
    String password = "jw8s0F4"

    public SQLConnectionService() {}

    public String getProductName(String plu) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "SELECT name FROM Products WHERE plu LIKE ?;";
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setString(1, plu);
            ResultSet rows = statement.executeQuery();

            if (!rows.next()) {
                return "ERROR: There is no product with that PLU or EAN";
            }
            return rows.getString(1);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("getProductName Error encountered: " + ex.getMessage());
        }
        return null;
    }

    public double getProductPrice(String plu) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "SELECT price FROM Products WHERE plu LIKE ?;";
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setString(1, plu);
            ResultSet rows = statement.executeQuery();

            if (!rows.next()) {
                return 0;
            }
            return rows.getDouble(1);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("getProductPrice Error encountered: " + ex.getMessage());
        }
        return 0;
    }

    public int getProductCid(String plu) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "SELECT cid FROM Products WHERE plu LIKE ?;";
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setString(1, plu);
            ResultSet rows = statement.executeQuery();

            if (!rows.next()) {
                return 0;
            }
            return rows.getInt(1);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("getProductName Error encountered: " + ex.getMessage());
        }
        return 0;
    }

    public boolean getProductAgeRestricted(String plu) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "SELECT agerestricted FROM Products WHERE plu LIKE ?;";
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setString(1, plu);
            ResultSet rows = statement.executeQuery();

            if (!rows.next()) {
                return true;
            }
            return rows.getBoolean(1);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("getProductName Error encountered: " + ex.getMessage());
        }
        return true;
    }

    public void sqlAddProduct(String plu, String name, Double price, String cid, Boolean ageRestricted) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "INSERT INTO Products (plu, name, price, cid, agerestricted) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.setLong(1, Long.parseLong(plu));
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setInt(4, Integer.parseInt(cid));
            statement.setBoolean(5, ageRestricted);

            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("sqlAddProduct Error encountered: " + ex.getMessage());
        }
    }

    public void makeNewOrder(String cosId) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "INSERT INTO Orders VALUES (nextval('orders_oid_seq'), ?, 0.0);";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.setInt(1, Integer.parseInt(cosId));

            statement.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("makeNewOrder Error encountered: " + ex.getMessage());
        }
    }

    public int getLatestOrderId() {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "SELECT oid FROM Orders WHERE oid = (SELECT MAX(oid) FROM Orders);";
            PreparedStatement statement = connection.prepareStatement(q);
            ResultSet rows = statement.executeQuery();

            if (!rows.next()) {
                return 0;
            }
            //rows.next();
            return rows.getInt(1);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("getLatestOrderId Error encountered: " + ex.getMessage());
        }
        return 0;
    }

    public int getLatestCosId() {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "SELECT cosId FROM Costumers WHERE cosId = (SELECT MAX(cosId) FROM Costumers);";
            PreparedStatement statement = connection.prepareStatement(q);
            ResultSet rows = statement.executeQuery();

            if (!rows.next()) {
                return 0;
            }
            //rows.next();
            return rows.getInt(1);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("getLatestOrderId Error encountered: " + ex.getMessage());
        }
        return 0;
    }

    public void makeNewOrderLine(int oId, String prodName, double prodPrice, double prodQuant, double totalPrice) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "INSERT INTO OrderLines VALUES (nextval('orderlines_olineid_seq'), ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.setInt(1, oId);
            statement.setString(2, prodName);
            statement.setDouble(3, prodPrice);
            statement.setDouble(4, prodQuant);
            statement.setDouble(5, totalPrice);

            statement.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("makeNewOrderLines Error encountered: " + ex.getMessage());
        }
    }

    public ResultSet getAllLinesFromOid(int oId) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "SELECT prodname, prodprice, prodquant, totalprice FROM OrderLines WHERE oid = ? ORDER BY oLineId DESC;";
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setInt(1, oId);
            ResultSet rows = statement.executeQuery();


            return rows;
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("getLatestOrderId Error encountered: " + ex.getMessage());
        }
        return null;
    }

    public ResultSet getAllProducts() {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "SELECT * FROM Products;";
            PreparedStatement statement = connection.prepareStatement(q);
            ResultSet rows = statement.executeQuery();

            return rows;
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("getLatestOrderId Error encountered: " + ex.getMessage());
        }
        return null;
    }

    public int makeNewCustomer(String fName, String lName, String eMail, String phone) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "INSERT INTO Costumers VALUES (nextval('costumers_cosid_seq'), ?, ?, ?, ?); SELECT currval(pg_get_serial_sequence('Costumers', 'cosid'));";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.setString(1, fName);
            statement.setString(2, lName);
            statement.setString(3, eMail);
            statement.setString(4, phone);

            statement.executeQuery();
            ResultSet rows = statement.executeQuery();

            if (!rows.next()) {
                return 0;
            }
            return rows.getInt(1);

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("makeNewCustomer Error encountered: " + ex.getMessage());
        }
        return 0;
    }

    public void updateProduct(String ogPlu, String plu, String name, Double price, String cid, Boolean ageRestricted) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "DELETE FROM Products WHERE plu = ?;";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.setString(1, ogPlu);

            statement.execute();

            sqlAddProduct(plu, name, price, cid, ageRestricted);

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("updateProduct Error encountered: " + ex.getMessage());
        }
    }

    public void saveCurrentEditingProduct(String plu) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "INSERT INTO EditingProduct VALUES (?);";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.setString(1, plu);

            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("saveCurrentEditingProduct Error encountered: " + ex.getMessage());
        }
    }

    public String getCurrentEditingProduct() {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "SELECT * FROM EditingProduct;";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.executeQuery();
            ResultSet rows = statement.executeQuery();

            if (!rows.next()) {
                return "No products in editing";
            }
            return rows.getString(1);

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("getCurrentEditingProduct Error encountered: " + ex.getMessage());
        }
        return "Unknown Error";
    }

    public void updateEditedProduct(String ogPlu, String plu, String name, Double price, String cid, Boolean ageRestricted) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "DELETE FROM EditingProduct WHERE plu = ?;";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.setString(1, ogPlu);

            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("saveCurrentEditingProduct Error encountered: " + ex.getMessage());
        }

        deleteProduct(ogPlu);
        sqlAddProduct(plu, name, price, cid, ageRestricted);
    }

    public void deleteProduct(String plu) {
        try {
            // Load driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Create a connection to the database
            Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            String q = "DELETE FROM Products WHERE plu = ?;";
            PreparedStatement statement = connection.prepareStatement(q);

            statement.setString(1, plu);

            statement.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("deleteProduct Error encountered: " + ex.getMessage());
        }
    }

}