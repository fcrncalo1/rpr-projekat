package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.OrderItemsManager;
import ba.unsa.etf.rpr.business.OrdersManager;
import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.dao.UsersDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.OrdersException;
import ba.unsa.etf.rpr.exceptions.ProductsException;
import ba.unsa.etf.rpr.exceptions.UsersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for project app
 */
public class AppTest {

    @Mock
    UsersDaoSQLImpl usersDaoSQL;
    @Mock
    ProductsManager pMan;
    @Mock
    UsersManager uMan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests duplicate User entry, database should support multiple users of the same name
     * @throws UsersException
     */
    @Test
    public void userDuplicateEntryTest() throws UsersException {
        Users user = new Users();
        user.setId(1);
        user.setFirstName("Name");
        user.setLastName("Name");
        user.setEmail("nname@gmail.com");
        user.setCity("Sarajevo");
        user.setAddress("Address 123");
        user.setMobileNumber("061111222");
        user.setUsername("nname");
        user.setPassword("ppass");
        UsersManager usersManager = new UsersManager();
        usersManager.add(user);
    }

    /**
     * Tests duplicate product entry, cannot insert a product that already exists
     */
    @Test
    public void productDuplicateEntryTest() {
        Products products = new Products();
        products.setName("Iphone 14 Pro Max");
        products.setPrice(3100.0);
        products.setQuantity(20);
        products.setId(1);
        ProductsManager productsManager = new ProductsManager();
        assertThrows(ProductsException.class, ()->productsManager.add(products));
    }

    /**
     * Tests behaviour when wanted user is not in the database
     */
    @Test
    public void nonExistentUserTest() {
        UsersManager usersManager = new UsersManager();
        assertThrows(UsersException.class,()->usersManager.getById(1234));
    }

    /**
     * Tests behaviour when wanted product is not in the database
     */
    @Test
    public void nonExistentProductTest() {
        ProductsManager productsManager = new ProductsManager();
        assertThrows(ProductsException.class,()->productsManager.getById(1234));
    }

    /**
     * Tests behaviour when wanted order is not in the database
     */
    @Test
    public void nonExistentOrderTest() {
        OrdersManager ordersManager = new OrdersManager();
        assertThrows(OrdersException.class,()->ordersManager.getById(1234));
    }

    /**
     * Tests behaviour when wanted order item is not in the database
     */
    @Test
    public void nonExistentOrderItemTest() {
        OrderItemsManager orderItemsManager = new OrderItemsManager();
        assertThrows(ProductsException.class,()->orderItemsManager.getById(1234));
    }

    /**
     * Tests product validation, quantity cannot be zero
     * @throws ProductsException
     */
    @Test
    public void productValidationTest() throws ProductsException {
        Products p = new Products();
        p.setName("Samsung Galaxy S8");
        p.setQuantity(0);
        p.setPrice(1500.0);
        doCallRealMethod().when(pMan).validate(p);
        assertThrows(ProductsException.class,()->pMan.validate(p));
    }

    /**
     * Tests behaviour when wanting to insert a duplicate email
     */
    @Test
    public void userDuplicateEmailTest() {
        Users user = new Users();
        user.setId(1);
        user.setFirstName("Korisnik");
        user.setLastName("Korisnik");
        user.setEmail("mmujic@gmail.com");
        user.setCity("Tuzla");
        user.setAddress("Tuzlanska 123");
        user.setMobileNumber("061092773");
        user.setUsername("kkorisnik");
        user.setPassword("ksifra1");
        UsersManager usersManager = new UsersManager();
        assertThrows(UsersException.class,()->usersManager.add(user));
    }

    /**
     * Tests name validation
     * @throws UsersException
     */
    @Test
    public void nameValidationTest() throws UsersException {
        Users u = new Users();
        u.setFirstName("       ");
        doCallRealMethod().when(uMan).validate(u);
        Answer<Users> answer = new Answer<Users>() {
            @Override
            public Users answer(InvocationOnMock invocationOnMock) throws Throwable {
                uMan.validate(u);
                return u;
            }
        };
        when(uMan.add(u)).thenAnswer(answer);
        assertThrows(UsersException.class,()->uMan.add(u));
    }

    /**
     * Verifies invocation of mocked DAO class
     * @throws SQLException
     */
    @Test
    public void mockInvocationTest() throws SQLException {
        Users u = new Users();
        u.setFirstName("Mock");
        when(usersDaoSQL.getById(1)).thenReturn(u);
        Users u1 = usersDaoSQL.getById(1);
        verify(usersDaoSQL).getById(1);
    }
}
