package api;

import data.dto.ToDoMockDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

public class MockTest {
    Logger logger = LoggerFactory.getLogger(MockTest.class);
    MockRestClient client = new MockRestClient();
    SoftAssertions softAssertions = new SoftAssertions();

    @Test
    public void getMockTest(){
        logger.debug("Stating test getMockTest");
        List<ToDoMockDTO> result = client.getMockTodos();
        softAssertions.assertThat(result.size()).as("Check result size").isEqualTo(5);
        softAssertions.assertAll();
    }
}
