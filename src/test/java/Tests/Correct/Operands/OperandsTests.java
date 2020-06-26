package Tests.Correct.Operands;

import URI.URI;
import Utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class OperandsTests {


    private URI uri;

    @BeforeClass
    public void initData() {
        uri = new URI();
    }




    @DataProvider
    public Object[][] dataProviderSmokeTest() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/-4");
        dataList.add("/+1");
        dataList.add("/*4");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "dataProviderSmokeTest")
    public void smokeTest(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .pathParam(PropertiesReader.readProperties("val2"), val)
                .pathParam(PropertiesReader.readProperties("val3"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.allOperationsEndpoint)
                .then();
        Assert.assertEquals(PropertiesReader.readProperties("smokeTestResult"), "12");
    }


    @DataProvider
    public Object[][] dataProviderOneValue() {
        List<String> dataList = new ArrayList<>();
        dataList.add("/-4");
        return dataList.stream()
                .map(data -> new Object[] {data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider= "dataProviderOneValue")
    public void oneValueTest(String val) {
        given()
                .pathParam(PropertiesReader.readProperties("val1"), val)
                .when()
                .get(PropertiesReader.readProperties("baseUri")+ URI.oneOperationEndpoint)
                .then();
        Assert.assertEquals(PropertiesReader.readProperties("oneValueTestResult"), "-4");
    }


}