package testsforpersons;

import com.rd.person.Man;
import com.rd.person.Woman;
import dataproviders.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestsWoman {
    private Woman woman;
    private Man man;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    void resetStreams() {
        outContent.reset();
        errContent.reset();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void isRetiredTrue(String firstName, String lastName, int age){
        woman = new Woman(firstName, lastName, age, null);
        Assert.assertTrue(woman.isRetired());
    }
    @Test(dataProvider = "isRetiredFalseWoman", dataProviderClass = TestDataProvider.class)
    public void isRetiredFalse(String firstName, String lastName, int age){
        woman = new Woman(firstName, lastName, age, null);
        Assert.assertFalse(woman.isRetired());
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void registerPartnership(String firstName, String lastName, int age){
        man = new Man("Peter", "Black", 60, null);
        woman = new Woman(firstName, lastName, age, null);
        woman.registerPartnership(man);
        Assert.assertEquals(woman.getPartner(), man);
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void deregisterPartnership(String firstName, String lastName, int age){
        Woman woman = new Woman(firstName, lastName, age, null);
        Man man = new Man("Peter", "Black", 65, null);
        woman.setPartner(man);
        woman.deregisterPartnership(true);
        Assert.assertNull(woman.getPartner());
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void statusPartnersNotMarriedAtAll(String firstName, String lastName, int age){
        resetStreams();
        Woman woman = new Woman(firstName, lastName, age, null);
        woman.statusPartners();
        Assert.assertEquals(outContent.toString(),"This partner have not been married at all\n");
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void statusPartnersMarried(String firstName, String lastName, int age){
        resetStreams();
        man = new Man("Peter", "Black", 60, null);
        woman = new Woman(firstName, lastName, age, null);
        woman.registerPartnership(man);
        woman.statusPartners();
        Assert.assertTrue(outContent.toString().startsWith("This partner is married\n"));
    }

    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void statusPartnersDivorced(String firstName, String lastName, int age){
        resetStreams();
        man = new Man("Peter", "Black", 60, null);
        woman = new Woman(firstName, lastName, age, null);
        woman.registerPartnership(man);
        woman.deregisterPartnership(true);
        woman.statusPartners();

        Assert.assertTrue(outContent.toString().startsWith("This partner has been divorced\n"));

    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void getFirstName(String firstName, String lastName, int age){
        woman = new Woman(firstName, lastName, age, null);
        Assert.assertEquals(woman.getFirstName(), firstName, "A first name for a woman is not equal");
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void getLastName(String firstName, String lastName, int age){
        woman = new Woman(firstName, lastName, age, null);
        Assert.assertEquals(woman.getLastName(), lastName, "A last name for a woman is not equal");
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void getAge(String firstName, String lastName, int age){
        woman = new Woman(firstName, lastName, age, null);
        Assert.assertEquals(woman.getAge(), age, "The age is not equal for woman");
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void getPartner(String firstName, String lastName, int age){
        woman = new Woman(firstName, lastName, age, null);
        Assert.assertNull(woman.getPartner(), "com.rd.person.Woman has partner");
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void setFirstName(String firstName, String lastName, int age){
        woman = new Woman(firstName, lastName, age, null);
        final String womanNewFirstName = "Clara";
        woman.setFirstName(womanNewFirstName);
        Assert.assertEquals(woman.getFirstName(), womanNewFirstName, "A first new name for a woman is not equal");
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void setLastName(String firstName, String lastName, int age){
        woman = new Woman(firstName, lastName, age, null);
        final String womanNewLastName = "Armstrong";
        woman.setLastName(womanNewLastName);
        Assert.assertEquals(woman.getLastName(), womanNewLastName, "A last new name for a woman is not equal");
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void setAge(String firstName, String lastName, int age){
        woman = new Woman(firstName, lastName, age, null);
        final int womanNewAge = 45;
        woman.setAge(womanNewAge);
        Assert.assertEquals(woman.getAge(), womanNewAge, "The new age is not equal for woman");
    }
    @Test(dataProvider = "personWoman", dataProviderClass = TestDataProvider.class)
    public void setPartner(String firstName, String lastName, int age){
        Woman woman = new Woman(firstName, lastName, age, null);
        Man man = new Man("Peter", "Black", 65, null);
        woman.setPartner(man);
        Assert.assertEquals(woman.getPartner(),man,"problem in partner test for woman");
    }
}