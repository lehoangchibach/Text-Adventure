import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeaponTest {

	private Weapon sword;

	@BeforeEach
	public void setUp() throws Exception {
		sword = new Weapon("a sword", 3, "a trusty steel shortsword");
	}

	@Test
	public void storesName() {
		assertEquals("a sword", sword.getName());
	}

	@Test
	public void storesDamage() {
		assertEquals(3, sword.getDamage());
	}

	@Test
	public void storesDescription() {
		assertEquals("a trusty steel shortsword", sword.getDescription());
	}

}
