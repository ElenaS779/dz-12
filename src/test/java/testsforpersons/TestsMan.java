package testsforpersons;
import com.rd.person.Man;
import com.rd.person.Woman;
import dataproviders.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestsMan {
    private Man man;
    private Woman woman;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    void resetStreams() {
        outContent.reset();
        errContent.reset();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void isRetiredTrue(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        Assert.assertTrue(man.isRetired());
    }
    @Test(dataProvider = "isRetiredFalseMan", dataProviderClass = TestDataProvider.class)
    public void isRetiredFalse(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        Assert.assertFalse(man.isRetired());
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void registerPartnership(String firstName, String lastName, int age){
        woman = new Woman("Maria", "Smith", 59, null);
        man = new Man(firstName, lastName, age, null);
        man.registerPartnership(woman);
        Assert.assertEquals(man.getPartner(), woman);
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void deregisterPartnership(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        woman = new Woman("Maria", "Smith", 59, null);
        man.setPartner(woman);
        man.deregisterPartnership(true);
        Assert.assertNull(man.getPartner());
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void statusPartnersNotMarriedAtAll(String firstName, String lastName, int age){
        resetStreams();
        man = new Man(firstName, lastName, age, null);
        man.statusPartners();
        Assert.assertEquals(outContent.toString(),"This partner have not been married at all\n");
    }

    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void statusPartnersMarried(String firstName, String lastName, int age){
        resetStreams();
        man = new Man(firstName, lastName, age, null);
        woman = new Woman("Maria", "Smith", 59, null);
        man.registerPartnership(woman);
        man.statusPartners();
        Assert.assertTrue(outContent.toString().startsWith("This partner is married\n"));
    }

    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void statusPartnersDivorced(String firstName, String lastName, int age){
        resetStreams();
        man = new Man(firstName, lastName, age, null);
        woman = new Woman("Maria", "Smith", 59, null);
        man.registerPartnership(woman);
        man.deregisterPartnership(true);
        man.statusPartners();
        Assert.assertTrue(outContent.toString().startsWith("This partner has been divorced\n"));
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void getFirstName(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        Assert.assertEquals(man.getFirstName(), firstName, "A first name for a man is not equal");
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void getLastName(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        Assert.assertEquals(man.getLastName(), lastName, "A last name for a man is not equal");
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void getAge(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        Assert.assertEquals(man.getAge(), age, "The age is not equal for man");
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void getPartner(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        Assert.assertNull(man.getPartner(), "com.rd.person.Man has partner");
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void setFirstName(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        final String manFirstName = "Rob";
        man.setFirstName(manFirstName);
        Assert.assertEquals(man.getFirstName(), manFirstName, "A first new name for a man is not equal");
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void setLastName(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        final String manNewLastName = "Eilish";
        man.setLastName(manNewLastName);
        Assert.assertEquals(man.getLastName(), manNewLastName, "A last new name for a man is not equal");
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void setAge(String firstName, String lastName, int age){
        man = new Man(firstName, lastName, age, null);
        final int manNewAge = 53;
        man.setAge(manNewAge);
        Assert.assertEquals(man.getAge(), manNewAge, "The new age is not equal for man");
    }
    @Test(dataProvider = "personMan", dataProviderClass = TestDataProvider.class)
    public void setPartner(String firstName, String lastName, int age){
        Woman woman = new Woman("Maria", "Smith", 59, null);
        man = new Man(firstName, lastName, age, null);
        man.setPartner(woman);
        Assert.assertEquals(man.getPartner(),woman,"problem in partner test for man");
    }
}
