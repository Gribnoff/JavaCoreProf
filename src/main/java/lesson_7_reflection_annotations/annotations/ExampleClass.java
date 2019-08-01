package lesson_7_reflection_annotations.annotations;

@SuppressWarnings("unused")
public class ExampleClass {

    @BeforeSuite
    public void before() {
        System.out.println("This must be executed first!");
    }

    @AfterSuite
    public void after() {
        System.out.println("This must be executed last!");
    }

    @Test
    public void someTest() {
        System.out.println("Some test");
    }

    @Test(priority = 8)
    public void normalTest() {
        System.out.println("Normal test");
    }

    @Test(priority = 3)
    public void anotherTest() {
        System.out.println("another test");
    }

    @Test(priority = 1)
    public void oneMoreTest() {
        System.out.println("One more test");
    }

    @Test(priority = 7)
    public void aTestForTest() {
        System.out.println("A test for test");
    }

    @Test(priority = 10)
    public void grandTest() {
        System.out.println("Grand test");
    }

    @Test(priority = 8)
    public void whateverTest() {
        System.out.println("Whatever test");
    }
}
