package dataproviders;
import models.Person;

import org.testng.annotations.DataProvider;
import static utils.CsvReader.getListObjectsFromCsv;

public class TestDataProvider {
    private static final String PATH_MAN = "src/test/resources/dataMan.csv";
    private static final String PATH_WOMAN = "src/test/resources/dataWoman.csv";
    private static final String PATH_IS_RETIRED_FALSE_MAN = "src/test/resources/dataForIsRetiredFalseMan.csv";
    private static final String PATH_IS_RETIRED_FALSE_Woman = "src/test/resources/dataForIsRetiredFalseWoman.csv";
    @DataProvider(name = "personMan")
    public static Object[][] personsMan() {

        return getListObjectsFromCsv(PATH_MAN, Person.class).stream().map(number -> new Object[]{number.getFirstName(), number.getLastName(), number.getAge()})
                .toArray(Object[][]::new);
    }
    @DataProvider(name = "personWoman")
    public static Object[][] personWoman() {
        return getListObjectsFromCsv(PATH_WOMAN, Person.class).stream().map(number -> new Object[]{number.getFirstName(), number.getLastName(), number.getAge()})
                .toArray(Object[][]::new);
    }
    @DataProvider(name = "isRetiredFalseMan")
    public static Object[][] isRetiredFalseMan() {
        return getListObjectsFromCsv(PATH_IS_RETIRED_FALSE_MAN, Person.class).stream().map(number -> new Object[]{number.getFirstName(), number.getLastName(), number.getAge()})
                .toArray(Object[][]::new);
    }
    @DataProvider(name = "isRetiredFalseWoman")
    public static Object[][] isRetiredFalseWoman() {
        return getListObjectsFromCsv(PATH_IS_RETIRED_FALSE_Woman, Person.class).stream().map(number -> new Object[]{number.getFirstName(), number.getLastName(), number.getAge()})
                .toArray(Object[][]::new);
    }
}

