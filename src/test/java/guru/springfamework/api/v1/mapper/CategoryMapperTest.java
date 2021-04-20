package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    protected static final String NAME = "name";
    protected static final Long ID = 1L;
    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);

        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());

    }
}