package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.RowMapper;
import next.model.Question;

public class QuestionDao {
    private JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();

    public Question insert(Question question) {
        String sql = "INSERT INTO QUESTIONS " + 
                "(writer, title, contents, createdDate) " + 
                " VALUES (?, ?, ?, ?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, question.getWriter());
                pstmt.setString(2, question.getTitle());
                pstmt.setString(3, question.getContents());
                pstmt.setTimestamp(4, new Timestamp(question.getTimeFromCreateDate()));
                return pstmt;
            }
        };

        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return findById(keyHolder.getId());
    }

	public Question modify(Question question) {
		String sql = "UPDATE questions "
			+ "SET title = ?,"
			+ "contents = ?)";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, question.getTitle());
				pstmt.setString(2, question.getContents());
				return pstmt;
			}
		};

		KeyHolder keyHolder = new KeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		return findById(keyHolder.getId());
	}
    
    public List<Question> findAll() {
        String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer, writerId FROM QUESTIONS "
                + "order by questionId desc";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
                        rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"), rs.getString("writerId"));
            }

        };

        return jdbcTemplate.query(sql, rm);
    }

    public Question findById(long questionId) {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer, writerId FROM QUESTIONS "
                + "WHERE questionId = ?";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                        rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"), rs.getString("writerId"));
            }
        };

        return jdbcTemplate.queryForObject(sql, rm, questionId);
    }

    public void updateCountOfAnswer(boolean isAddedAnswer, long questionId) {
        String sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer ";
        if(isAddedAnswer){
            sql += "+1";
        } else {
            sql += "-1";
        }
        sql += "WHERE questionId = ?";

        jdbcTemplate.update(sql, questionId);
    }
}
