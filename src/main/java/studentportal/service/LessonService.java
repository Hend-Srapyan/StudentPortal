package studentportal.service;

import studentportal.db.DBConnectionProvider;
import studentportal.model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonService {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Lesson lesson) {
        String sql = "INSERT INTO lesson(name,lecturer_name,price) VALUES(?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, lesson.getLecturerName());
            preparedStatement.setDouble(3, lesson.getPrice());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                lesson.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getAllLessons() {
        String sql = "SELECT * FROM lesson";
        List<Lesson> lessons = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .build());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lessons;
    }

    public Lesson getLessonById(int id) {
        String sql = "SELECT * FROM lesson WHERE id = " + id;


        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Lesson lesson = Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .build();
                return lesson;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
