package it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem;

import org.junit.*;

import static org.junit.Assert.*;

public class ProductTest {

    @BeforeClass
    public static void setUpClass(){
        System.out.println("Inizio");
    }

    @AfterClass
    public static void tearDownClass(){
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
    public void addInventoryProduct() {
        Product product = new Product();
        InventoryProduct inventoryProduct = new InventoryProduct();
        product.addInventoryProduct(inventoryProduct);
        assertSame(product.getInventoryProducts()[0], inventoryProduct);
    }
}