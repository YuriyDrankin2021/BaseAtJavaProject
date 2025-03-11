package api;

import com.github.javafaker.Faker;
import data.dto.ToDoMockDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    Logger logger = LoggerFactory.getLogger(MockitoTest.class);
    SoftAssertions softAssertions = new SoftAssertions();
    Faker faker = new Faker();

    @Spy
    MockRestClient client;

    @ParameterizedTest(name = "Check mockito api result with list size = {0} ")
    @ValueSource(ints = {1, 5, 10})
    public void checkMockito(int listSize) {
        logger.debug("Stating test checkMockito");
        List<ToDoMockDTO> mockResult = generateResult(listSize);
        Mockito.doReturn(mockResult).when(client).getMockTodos();
        List<ToDoMockDTO> result = client.getMockTodos();

        softAssertions.assertThat(result.size()).as("Check mockito result size").isEqualTo(listSize);
        softAssertions.assertThat(result).as("Check mockito result equals mockResult").isEqualTo(mockResult);
        softAssertions.assertAll();

    }

    private List<ToDoMockDTO> generateResult(int size) {
        List<ToDoMockDTO> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ToDoMockDTO value = new ToDoMockDTO();
            value.setId(faker.number().randomDigit());
            value.setTitle(faker.animal().name());
            result.add(value);
        }
        return result;
    }

}
