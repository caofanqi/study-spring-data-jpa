package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Category;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CategoryRepositoryTest {


    @Resource
    private CategoryRepository categoryRepository;


    @BeforeEach
    void setup(){
        Category category1 = Category.builder().categoryName("Java").build();
        Category category2 = Category.builder().categoryName("数据库").build();
        Category category3 = Category.builder().categoryName("数据结构").build();

        ArrayList<Category> categories = Lists.newArrayList(category1, category2, category3);

        categoryRepository.saveAll(categories);

    }

    @Test
    void selectByName() {

        Category category = categoryRepository.selectByName("Java");
        assertEquals("Java",category.getCategoryName());

    }

    @Test
    void selectByNameLike(){
        List<Category> categoryList = categoryRepository.selectByNameLike("%据%");
        assertEquals(2,categoryList.size());
    }
}