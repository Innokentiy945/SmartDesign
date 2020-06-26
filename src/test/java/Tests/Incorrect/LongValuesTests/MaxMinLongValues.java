package Tests.Incorrect.LongValuesTests;

import URI.URI;
import Utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MaxMinLongValues {

    private URI uri;

    @BeforeClass
    public void initData() {
        uri = new URI();
    }


    @DataProvider
    public Object[][] maxLongValue() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/+92233703854775807");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "maxLongValue")
    public void getMaxLongValue(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.oneOperationEndpoint)
                .then();
        Assert.assertEquals(PropertiesReader.readProperties("maxLongValue"), "92233703854775807");
    }

    @DataProvider
    public Object[][] minLongValue() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/-92233703854775807");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "minLongValue")
    public void getMinLongValue(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.oneOperationEndpoint)
                .then();
        Assert.assertEquals(PropertiesReader.readProperties("minLongValue"), "92233703854775807");
    }


    @DataProvider
    public Object[][] borderOfIncorrectCounts() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/+10000000000");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "borderOfIncorrectCounts")
    public void getBorderOfCorrectCount(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.oneOperationEndpoint)
                .then();
        Assert.assertEquals(PropertiesReader.readProperties("borderOfCorrectCount"), "10000000000");
    }
}
