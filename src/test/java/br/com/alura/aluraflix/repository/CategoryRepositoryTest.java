package br.com.alura.aluraflix.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.alura.aluraflix.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void shouldGetACategoryByTitle(){
        String title = "DATA_SCIENCE";
        Category category = categoryRepository.findByTitle(title);

        assertNotNull(category);
    }
}
