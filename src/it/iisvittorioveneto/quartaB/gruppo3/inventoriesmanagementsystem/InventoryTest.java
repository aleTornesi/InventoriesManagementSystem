package it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem;

import org.junit.*;

import static org.junit.Assert.*;

public class InventoryTest {

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
        Inventory inventory = new Inventory();
        Product product = new Product();
        inventory.addProduct(product);
        assertTrue(inventory.getProducts()[0]==product);
    }
}