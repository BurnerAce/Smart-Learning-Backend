package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@RestController
public class DatabaseController {

    private final DataSource dataSource;

    public DatabaseController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/dbinfo")
    public String getDatabaseInfo() {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            return "Connected to: " + metaData.getURL();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
