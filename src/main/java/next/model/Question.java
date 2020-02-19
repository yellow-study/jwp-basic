package next.model;

import java.util.Date;

public class Question {
    private long questionId;

    private String writer;

    private String title;

    private String contents;

    private Date createdDate;

    private int countOfComment;

    private String writerId;

    public Question(String writer, String title, String contents, String writerId) {
        this(0, writer, title, contents, new Date(), 0, writerId);
    }

    public Question(long questionId, String writer, String title, String contents, Date createdDate,
            int countOfComment, String writerId) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfComment = countOfComment;
        this.writerId = writerId;
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

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getWriterId() {
        return this.writerId;
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
