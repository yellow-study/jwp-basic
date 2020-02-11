package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.RowMapper;
import next.model.Answer;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class AnswerDao {
    JdbcTemplate jdbcTemplate = JdbcTemplate.newInstance();

    public Answer insert(Answer answer) {
        String sql = "INSERT INTO ANSWERS (userId, writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, answer.getUserId());
            pstmt.setString(2, answer.getWriter());
            pstmt.setString(3, answer.getContents());
            pstmt.setTimestamp(4, new Timestamp(answer.getTimeFromCreateDate()));
            pstmt.setLong(5, answer.getQuestionId());
            return pstmt;
        };

        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return findById(keyHolder.getId());
    }

    public Answer findById(long answerId) {
        String sql = "SELECT answerId, userId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId = ?";

        RowMapper<Answer> rm = rs -> new Answer(rs.getLong("answerId"), rs.getString("userId"), rs.getString("writer"), rs.getString("contents"),
                rs.getTimestamp("createdDate"), rs.getLong("questionId"));

        return jdbcTemplate.queryForObject(sql, rm, answerId);
    }

    public List<Answer> findAllByQuestionId(long questionId) {
        String sql = "SELECT answerId, userId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? "
                + "order by answerId desc";

        RowMapper<Answer> rm = rs -> new Answer(rs.getLong("answerId"), rs.getString("userId"), rs.getString("writer"), rs.getString("contents"),
                rs.getTimestamp("createdDate"), questionId);

        return jdbcTemplate.query(sql, rm, questionId);
    }

    public void delete(Long answerId) {
        String sql = "DELETE FROM ANSWERS WHERE answerId = ?";
        jdbcTemplate.update(sql, answerId);
    }

    public void deleteAllByQuestionId(Long questionId) {
        String sql = "DELETE FROM ANSWERS WHERE questionId = ?";
        jdbcTemplate.update(sql, questionId);
    }
}
