package next.controller.bo;

import com.google.common.collect.Lists;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuestionBoTest {
    @Mock
    QuestionDao questionDao;
    @Mock
    AnswerDao answerDao;
    @InjectMocks
    QuestionBo questionBo;

    @Test
    public void delete_답변이_없으면_삭제가능() {
        // given
        long questionId = 123123;
        Question question = new Question("writer", "title", "contents");
        List<Answer> answers = Lists.newArrayList();

        // when
        when(questionDao.findById(questionId)).thenReturn(question);
        when(answerDao.findAllByQuestionId(questionId)).thenReturn(answers);
        questionBo.delete(questionId);

        // then
        verify(questionDao).findById(questionId);
        verify(answerDao).findAllByQuestionId(questionId);
        verify(questionDao).delete(questionId);
    }

    @Test
    public void delete_질문자와_답변자가_모두_같으면_삭제가능() {
        // given
        long questionId = 123123;
        Question question = new Question("writer", "title", "contents");
        List<Answer> answers = Lists.newArrayList(
                new Answer("writer", "contents", questionId),
                new Answer("writer", "contents2", questionId)
        );

        // when
        when(questionDao.findById(questionId)).thenReturn(question);
        when(answerDao.findAllByQuestionId(questionId)).thenReturn(answers);
        questionBo.delete(questionId);

        // then
        verify(questionDao).findById(questionId);
        verify(answerDao).findAllByQuestionId(questionId);
        verify(questionDao).delete(questionId);
    }

    @Test
    public void delete_질문자가_아닌_답변이_하나라도_있으면_삭제불가() {
        // given
        long questionId = 123123;
        Question question = new Question("writer", "title", "contents");
        List<Answer> answers = Lists.newArrayList(
                new Answer("writer", "contents", questionId),
                new Answer("writer", "contents2", questionId),
                new Answer("not writer", "contents3", questionId)
        );

        // when
        when(questionDao.findById(questionId)).thenReturn(question);
        when(answerDao.findAllByQuestionId(questionId)).thenReturn(answers);
        questionBo.delete(questionId);

        // then
        verify(questionDao).findById(questionId);
        verify(answerDao).findAllByQuestionId(questionId);
        verify(questionDao, never()).delete(questionId);
    }
}
