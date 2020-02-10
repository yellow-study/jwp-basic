package next.controller.bo;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import org.h2.util.StringUtils;

import java.util.List;

public class QuestionBo {
    private QuestionDao questionDao;
    private AnswerDao answerDao;

    public QuestionBo(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    public boolean delete(Long questionId) {
        Question question = questionDao.findById(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        boolean removable = answers
                .stream()
                .allMatch(answer -> StringUtils.equals(answer.getWriter(), question.getWriter()));

        if (removable) {
            questionDao.delete(questionId);
        }

        return removable;
    }
}
