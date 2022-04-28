package ru.geekbrains.march.market.core.tests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.march.market.core.repositories.CategoryRepository;

@SpringBootTest
class MarchMarketCoreApplicationTests {

	@Test
	void contextLoads() {
		CategoryRepositoryTest categoryRepositoryTest = new CategoryRepositoryTest();
		categoryRepositoryTest.findAllTest();
	}

}
