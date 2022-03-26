import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreasureTest {

	private Treasure diamond;

	@BeforeEach
	public void setUp() throws Exception {
		diamond = new Treasure("diamond", 10, "a huge, glittering diamond");
	}

	@Test
	public void storesName() {
		assertEquals("diamond", diamond.getName());
	}

	@Test
	public void storesValue() {
		assertEquals(10, diamond.getValue());
	}

	@Test
	public void storesDescription() {
		assertEquals("a huge, glittering diamond", diamond.getDescription());
	}

}
