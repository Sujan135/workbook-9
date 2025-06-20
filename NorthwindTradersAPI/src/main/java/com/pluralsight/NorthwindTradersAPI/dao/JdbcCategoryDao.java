package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCategoryDao implements CategoryDao{
    private DataSource dataSource;

    public JdbcCategoryDao(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories  = new ArrayList<>();
        String sql = "SELECT CategoryId, CategoryName FROM Categories";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getInt("categoryId"),
                        resultSet.getString("categoryName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category getById(int id) {
        String sql = "SELECT CategoryId, CategoryName FROM Categories WHERE categoryId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Category(
                            resultSet.getInt("CategoryId"),
                            resultSet.getString("categoryName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
