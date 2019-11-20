package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Category;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(false)
@Transactional
@SpringBootTest
class CategoryRepositoryTest {


    @Resource
    private CategoryRepository categoryRepository;


//    @Test
//    @BeforeEach
    void setup(){

        Category java = Category.builder().categoryName("Java").build();
        Category database = Category.builder().categoryName("数据库").build();
        Category dataStructure = Category.builder().categoryName("数据结构").build();

        ArrayList<Category> categories = Lists.newArrayList(java, database, dataStructure);

        Category category = Category.builder().categoryName("计算机科学图书").children(categories).build();
        categories.forEach(c -> c.setParent(category));

        Category longStory = Category.builder().categoryName("长篇小说").build();
        Category midStory = Category.builder().categoryName("中篇小说").build();
        Category shortStory = Category.builder().categoryName("短篇小说").build();

        ArrayList<Category> stories = Lists.newArrayList(longStory, midStory, shortStory);

        Category story = Category.builder().categoryName("小说类").children(stories).build();
        stories.forEach(s -> s.setParent(story));

        Category category2 = Category.builder().categoryName("文学图书").children(Lists.newArrayList(story)).build();
        story.setParent(category2);

        categoryRepository.saveAll(Lists.newArrayList(category,category2));

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


    @Test
    void findById(){
        Optional<Category> categoryOp = categoryRepository.findById(15L);
        if (categoryOp.isPresent()) {
            Category category = categoryOp.get();
            System.out.println(category);
        }
    }


    /**
     * 测试 一次查询树形结构
     */
    @Test
    void findAll(){
        List<Category> categories = categoryRepository.findAll();
        categories.stream().filter(c -> c.getParent() == null).forEach(c -> printName(c,null));
    }


    private void printName(Category category,String prefix){

        if (StringUtils.isEmpty(prefix)){
            prefix = "---";
        }

        System.out.println(prefix + category.getCategoryName());

        List<Category> children = category.getChildren();
        if (!CollectionUtils.isEmpty(children)){
            for (Category c : children){
                printName(c,prefix + "---");
            }
        }

    }


    @Test
    void findByParent(){
        List<Category> categories = categoryRepository.findByParent(null);
        categories.forEach(c -> printName(c,null));
    }



}