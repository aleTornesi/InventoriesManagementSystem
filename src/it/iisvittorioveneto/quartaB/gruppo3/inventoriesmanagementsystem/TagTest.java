package it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem;

import org.junit.*;

import static org.junit.Assert.*;

public class TagTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Inizio");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Fine");
    }

    @Before
    public void setUp() {
        System.out.println("Inizio test");
    }

    @After
    public void tearDown() {
        System.out.println("Fine test");
    }

    @Test
    public void addProduct() {
        Tag tag = new Tag();
        Product product = new Product();
        tag.addProduct(product);
        assertSame(tag.getProducts()[0], product);
    }

    @Test
    public void removeProduct() {
        Tag tag = new Tag();
        Product product = new Product();
        tag.addProduct(product);
        tag.removeProduct(product);
        assertEquals(0, tag.getProducts().length);
    }
}