package next.model;

import java.util.Date;

public class Question {
    private long questionId;

    private String userId;

    private String writer;

    private String title;

    private String contents;

    private Date createdDate;

    private int countOfComment;

    public Question() {

    }

    public Question(String userId, String writer, String title, String contents) {
        this(0, userId, writer, title, contents, new Date(), 0);
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCountOfComment(int countOfComment) {
        this.countOfComment = countOfComment;
    }

    public Question(long questionId, String userId, String writer, String title, String contents, Date createdDate,
                    int countOfComment) {
        this.questionId = questionId;
        this.userId = userId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfComment = countOfComment;
    }

    public long getQuestionId() {
        return questionId;
    }

    public String getUserId() {
        return userId;
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
        return "Question [questionId=" + questionId + ", userId=" + userId + ", writer=" + writer + ", title=" + title + ", contents="
                + contents + ", createdDate=" + createdDate + ", countOfComment=" + countOfComment + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (questionId ^ (questionId >>> 32));
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
        Question other = (Question) obj;
        if (questionId != other.questionId)
            return false;
        return true;
    }
}
