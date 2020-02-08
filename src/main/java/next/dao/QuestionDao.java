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
import next.factory.JdbcTemplateFactory;
import next.model.Question;

public class QuestionDao {
	public Question insert(Question question) {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
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

	public Question updateIncreaseCountOfAnswer(long questionId) {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		String sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer + 1 " +
			"WHERE questionId = ? ";
		PreparedStatementCreator psc = con -> {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, questionId);
			return pstmt;
		};

		KeyHolder keyHolder = new KeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		return findById(keyHolder.getId());
	}

	public Question updateDecreaseCountOfAnswer(long questionId) {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		String sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer - 1 " +
			"WHERE questionId = ? ";
		PreparedStatementCreator psc = con -> {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, questionId);
			return pstmt;
		};

		KeyHolder keyHolder = new KeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		return findById(keyHolder.getId());
	}

	public List<Question> findAll() {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		String sql = "SELECT questionId, writer, title, createdDate, userId, countOfAnswer FROM QUESTIONS "
			+ "order by questionId desc";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
					rs.getTimestamp("createdDate"), rs.getString("userId"), rs.getInt("countOfAnswer"));
			}

		};

		return jdbcTemplate.query(sql, rm);
	}

	public Question findById(long questionId) {
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		String sql = "SELECT questionId, writer, title, contents, createdDate, userId, countOfAnswer FROM QUESTIONS "
			+ "WHERE questionId = ?";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
					rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getString("userId"), rs.getInt("countOfAnswer"));
			}
		};

		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}
}
