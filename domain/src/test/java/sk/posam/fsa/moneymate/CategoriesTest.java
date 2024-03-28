package sk.posam.fsa.moneymate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.posam.fsa.moneymate.domain.Categories;
import sk.posam.fsa.moneymate.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoriesTest {


    private Categories categories;
    @BeforeEach
    void setUp() {
        categories = new Categories();
    }

    @Test
    void categoryExistsAfterAdding(){
        categories.add(new Category("Category 1"));
        assertTrue(categories.getCategories().iterator().hasNext());
    }

    @Test
    void categoryCannotBeAddedIfNameIsNotSpecified(){
        Category category = new Category("");
        assertThrows(IllegalArgumentException.class,
                () -> categories.add(category));
    }

    @Test
    void categoryDoesntExistAfterRemove(){
        Category category = new Category("Category 1");
        category.setId(1);
        categories.add(category);

        categories.remove(category.getId());

        assertNotEquals(1, categories.getCategories().size());
    }

    @Test
    void categoryCannotBeAddedIfAlreadyExists(){
        Category category = new Category("Category 1");
        categories.add(category);
        assertThrows(IllegalArgumentException.class, () -> categories.add(category));
    }

    @Test
    void categoryCannotBeAddedIfNull(){
        assertThrows(IllegalArgumentException.class, () -> categories.add(null));
    }

    @Test
    void categoryCannotBeRemovedIfNotExists(){
        assertThrows(IllegalArgumentException.class, () -> categories.remove(1));
    }

}