package Tests.Correct.NegativeNumbers;

import URI.URI;
import Utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;


public class NegativeNumbersMultiply {

    private URI uri;

    @BeforeClass
    public void initData() {
        uri = new URI();
    }


    @DataProvider
    public Object[][] dataProviderNegativeMultiplyValues() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/-4");
        dataList.add("/-1");
        dataList.add("/*5");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "dataProviderNegativeMultiplyValues")
    public void negativeMultiplyValues(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .pathParam(PropertiesReader.readProperties("val2"), val)
                .pathParam(PropertiesReader.readProperties("val3"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.allOperationsEndpoint)
                .then();
        Assert.assertEquals(PropertiesReader.readProperties("negativeMultiplyValues"), "-25");
    }

    @DataProvider
    public Object[][] dataProviderNegativeMultiplyOnNegativeValue() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/-4");
        dataList.add("/-1");
        dataList.add("/*-5");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "dataProviderNegativeMultiplyOnNegativeValue")
    public void negativeMultiplyOnNegativeValue(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .pathParam(PropertiesReader.readProperties("val2"), val)
                .pathParam(PropertiesReader.readProperties("val3"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.allOperationsEndpoint)
                .then();
        Assert.assertEquals(PropertiesReader.readProperties("negativeMultiplyValuesOnNegative"), "25");
    }

    @DataProvider
    public Object[][] dataProviderMultiplyOnZero() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/*-5");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "dataProviderMultiplyOnZero")
    public void multiplyOnZero(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .pathParam(PropertiesReader.readProperties("val2"), val)
                .pathParam(PropertiesReader.readProperties("val3"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.allOperationsEndpoint)
                .then();
        Assert.assertEquals(PropertiesReader.readProperties("multiplyOnZero"), "0");
    }





}
