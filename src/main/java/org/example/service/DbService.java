package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbService {
    // Połączenie z bazą danych
    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/kryptowaluty";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }


    // Metoda do inserty do tabeli "kursy_fiat" za pomocą pstmt
    public void insertFiatRate(String date, String time, String code, Double rate) throws SQLException {
        String sql = "INSERT INTO kursy_fiat (data, czas, code, rate) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setString(2, time);
            pstmt.setString(3, code);
            pstmt.setDouble(4, rate);
            pstmt.executeUpdate();
        }
    }

    // Metoda do inserty do tabeli "kursy_krypto" za pomocą pstmt
    public void insertCryptoRate(String date, String time, String code, Double rate) throws SQLException {
        String sql = "INSERT INTO kursy_krypto (data, czas, code, rate) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setString(2, time);
            pstmt.setString(3, code);
            pstmt.setDouble(4, rate);
            pstmt.executeUpdate();
        }
    }
}
