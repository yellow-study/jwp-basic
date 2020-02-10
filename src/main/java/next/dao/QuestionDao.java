package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.RowMapper;
import next.model.Question;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
    private static JdbcTemplate jdbcTemplate = JdbcTemplate.newInstance();

    public void updateCountOfComment(long questionId, int value) {
        String sql = "UPDATE QUESTIONS " +
                "SET countOfAnswer = countOfAnswer + ?" +
                " WHERE questionId = ?";

        jdbcTemplate.update(sql, value, questionId);
    }

    public Question insert(Question question) {
        String sql = "INSERT INTO QUESTIONS " +
                "(userId, writer, title, contents, createdDate) " +
                " VALUES (?, ?, ?, ?, ?)";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, question.getUserId());
            pstmt.setString(2, question.getWriter());
            pstmt.setString(3, question.getTitle());
            pstmt.setString(4, question.getContents());
            pstmt.setTimestamp(5, new Timestamp(question.getTimeFromCreateDate()));
            return pstmt;
        };

        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return findById(keyHolder.getId());
    }

    public List<Question> findAll() {
        String sql = "SELECT questionId, userId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
                + "order by questionId desc";

        RowMapper<Question> rm = rs -> new Question(rs.getLong("questionId"), rs.getString("userId"), rs.getString("writer"), rs.getString("title"), null,
                rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));

        return jdbcTemplate.query(sql, rm);
    }

    public Question findById(long questionId) {
        String sql = "SELECT questionId, userId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
                + "WHERE questionId = ?";

        RowMapper<Question> rm = rs -> new Question(rs.getLong("questionId"), rs.getString("userId"), rs.getString("writer"), rs.getString("title"),
                rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, rm, questionId);
    }

    public void update(Question question) {
        String sql = "UPDATE QUESTIONS " +
                "SET title = ?, " +
                "contents = ? " +
                "WHERE questionId = ?";

        jdbcTemplate.update(sql, question.getTitle(), question.getContents(), question.getQuestionId());
    }
}
