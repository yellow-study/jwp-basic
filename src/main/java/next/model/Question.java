package next.model;

import java.util.Date;

public class Question {
	private long questionId;

	private String writer;

	private String title;

	private String contents;

	private Date createdDate;

	private String userId;

	private int countOfComment;

	public Question(String writer, String title, String contents, String userId) {
		this(0, writer, title, contents, new Date(), userId, 0);
	}

	public Question(long questionId, String writer, String title, String contents, Date createdDate, String userId, int countOfComment) {
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = createdDate;
		this.userId = userId;
		this.countOfComment = countOfComment;
	}

	public long getQuestionId() {
		return questionId;
	}

	public String getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public long getTimeFromCreateDate() {
		return this.createdDate.getTime();
	}

	public int getCountOfComment() {
		return countOfComment;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", writer=" + writer + ", title=" + title + ", contents="
			+ contents + ", createdDate=" + createdDate + ", countOfComment=" + countOfComment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)(questionId ^ (questionId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question)obj;
		if (questionId != other.questionId)
			return false;
		return true;
	}

	public String getUserId() {
		return userId;
	}
}
