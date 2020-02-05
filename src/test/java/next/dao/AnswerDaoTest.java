package next.dao;

import core.web.filter.Controller;
import next.model.Answer;

import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;

public class AnswerDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void addAnswer() throws Exception {
        long questionId = 1L;
        Answer expected = new Answer("javajigi", "answer contents", questionId);
        AnswerDao dut = new AnswerDao();
        Answer answer = dut.insert(expected);
        System.out.println("Answer : " + answer);
    }


    @Test
    public void annotationTest() {
        //controller annotation 정보 가져오기
        Reflections ref = new Reflections();

        for (Class<?> cl : ref.getTypesAnnotatedWith(Controller.class)) {
            Controller controller = cl.getAnnotation(Controller.class);
            System.out.printf("Found class: %s, with meta name: %s%n",
                    cl.getSimpleName(), controller.value());
        }

    }
}
