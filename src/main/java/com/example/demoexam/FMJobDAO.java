package com.example.demoexam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FMJobDAO {
    private DBConnection dbConnection = new DBConnection();

    //  Создание члена семьи
    public void create(FMJob newMember) throws SQLException {

        String sql = "INSERT INTO family_members_job (memjob_id, member_id, fio, curr_position," +
                " org_name, salary, start_date)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newMember.getFio());
            stmt.setString(2, newMember.getPosition());
            stmt.setString(3, newMember.getOrg_name());
            stmt.setInt(4, newMember.getSalary());
            stmt.setDate(5, newMember.getDateOfBirth() != null ? newMember.getDateOfBirth() : null);
            stmt.executeUpdate();
            System.out.println("Новый член семьи успешно добавлен!");
        } catch (SQLException e) {
            throw new SQLException(" !!! При создании члена семьи произошла ошибка: " + e.getMessage() + " !!! ");
        }
    }

    //    Изменение члена семьи
    public void update(int memjob_id , int member_id, String fio,
                       String curr_position, String org_name,
                       int salary, Date start_date) throws SQLException {

        String sql = "UPDATE family_members_job SET fio = ?," +
                " curr_position = ?, org_name = ?, salary = ?, start_date = ? WHERE memjob_id = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fio);
            stmt.setString(2, curr_position);
            stmt.setString(3, org_name);
            stmt.setInt(4, salary);
            stmt.setDate(5, start_date);
            stmt.setInt(6, memjob_id);
            stmt.executeUpdate();
            System.out.println("Изменения полей члена семьи было успешным!");
        } catch (SQLException e) {
            throw new SQLException(" !!! При изменении полей члена семьи произошла ошибка: " + e.getMessage() + " !!! ");
        }
    }

    //    Получение списка всех членов семьи
    public List<FMJob> getAllMembers() throws SQLException {
        List<FMJob> members = new ArrayList<>();
        String sql = "SELECT * FROM family_members_job";
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                FMJob member = new FMJob(
                        rs.getInt("memjob_id"),
                        rs.getInt("memberId"),
                        rs.getString("fio"),
                        rs.getString("position"),
                        rs.getString("org_name"),
                        rs.getInt("salary"),
                        rs.getDate("dateOfBirth")
                );
                members.add(member);
            }
        } catch (SQLException e) {
            throw new SQLException(" !!! При получении списка членов семьи произошла ошибка: " + e.getMessage() + " !!! ");
        }

        return members;
    }

    //     Поиск Члена семьи по id
    public FMJob getMember(int memberJob_id) throws SQLException {
        String sql = "SELECT * FROM family_members_job WHERE memjob_id = ?";
        FMJob member = null;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberJob_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                member = new FMJob(
                        rs.getInt("memjob_id"),
                        rs.getInt("memberId"),
                        rs.getString("fio"),
                        rs.getString("position"),
                        rs.getString("org_name"),
                        rs.getInt("salary"),
                        rs.getDate("dateOfBirth")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Ошибка при получении члена семьи: " + e.getMessage());
        }

        return member;
    }

}