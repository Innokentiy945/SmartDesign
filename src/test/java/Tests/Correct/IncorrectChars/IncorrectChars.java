package Tests.Correct.IncorrectChars;

import URI.URI;
import Utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import static io.restassured.RestAssured.given;

public class IncorrectChars {

    private URI uri;

    @BeforeClass
    public void initData() {
        uri = new URI();
    }

    @DataProvider
    public Object[][] incorrectChars() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/++");
        dataList.add("/+!");
        dataList.add("/*%$@");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "incorrectChars")
    public void incorrectCharsOperations(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .pathParam(PropertiesReader.readProperties("val2"), val)
                .pathParam(PropertiesReader.readProperties("val3"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.allOperationsEndpoint)
                .then()
                .statusCode(400);
    }


    @DataProvider
    public Object[][] incorrectCharsWithWords() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/apple");
        dataList.add("/dog");
        dataList.add("/cat");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "incorrectCharsWithWords")
    public void incorrectCharsOperationsWithWords(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .pathParam(PropertiesReader.readProperties("val2"), val)
                .pathParam(PropertiesReader.readProperties("val3"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.allOperationsEndpoint)
                .then();
        Assert.assertEquals(PropertiesReader.readProperties("incorrectCharMessageWithWords"), "unsupported operation");
    }
}
