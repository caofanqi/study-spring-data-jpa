package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Book;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(false)
@Transactional
@SpringBootTest
class BookRepositoryTest {

    @Resource
    private BookRepository bookRepository;

    @Test
    void findBooksByBookNameContains() {
        System.out.println("bookRepository : " + bookRepository.getClass().getName());
        List<Book> books = bookRepository.findBooksByBookNameContains("Java");
        System.out.println(books);
    }

    @Test
    void testCrudRepository(){

        Book book1 = Book.builder().bookName("Spring实战").build();
        Book book2 = Book.builder().bookName("MySql数据库").build();

        ArrayList<Book> books = Lists.newArrayList(book1, book2);

        bookRepository.saveAll(books);

    }


    @Test
    void testPagingAndSortingRepository(){

        Sort.Order id = Sort.Order.by("id");
        Sort.Order bookName = Sort.Order.desc("bookName");
        Sort sort = Sort.by(id,bookName);

        Pageable pageable = PageRequest.of(2,2, sort);

        Page<Book> page = bookRepository.findAll(pageable);

        System.out.println("分页查询结果列表：" + page.getContent());
        System.out.println("当前分页结果列表中的元素个数：" + page.getNumberOfElements());
        System.out.println("当前条件下总条数：" + page.getTotalElements());
        System.out.println("总页数：" +page.getTotalPages());
        System.out.println("我们自己传的page：" +page.getNumber());
        System.out.println("我们自己传入的size：" +page.getSize());

    }

}