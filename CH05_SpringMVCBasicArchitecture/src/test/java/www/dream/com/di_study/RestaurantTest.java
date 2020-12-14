package www.dream.com.di_study;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml")
public class RestaurantTest {
	@Autowired
	private Restaurant restaurant;
	
	@Autowired
	private Hotel hotel;
	
	@Autowired
	private Customer hong;
	
	@Test
	public void test() {
		assertNotNull(restaurant);
		assertNotNull(restaurant.getChef());
	}
	@Test
	public void testConstructorDI() {
		assertNotNull(hotel);
		assertNotNull(hotel.getChef());
	}
	
	@Test
	public void testReqConstructorDI() {
		assertNotNull(hotel.getRestaurant());
	}
}
