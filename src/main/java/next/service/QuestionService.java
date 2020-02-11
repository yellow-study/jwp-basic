package next.service;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import org.h2.util.StringUtils;

import java.util.List;

public class QuestionService {
    private QuestionDao questionDao = new QuestionDao();
    private AnswerDao answerDao = new AnswerDao();

    public boolean removeQuestion(long questionId) {
        if (!removableQuestion(questionId)) {
            return false;
        }

        questionDao.delete(questionId);
        answerDao.deleteAllByQuestionId(questionId);
        return true;
    }


    private boolean removableQuestion(long questionId) {
        Question question = questionDao.findById(questionId);

        if (question.getCountOfComment() <= 0) {
            return true;
        }

        List<Answer> answers = answerDao.findAllByQuestionId(questionId);
        return answers.stream()
                .allMatch(answer -> StringUtils.equals(answer.getUserId(), question.getUserId()));
    }
}
