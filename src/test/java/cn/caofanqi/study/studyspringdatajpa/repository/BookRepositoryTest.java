package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Book;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Book book1 = Book.builder().bookName("Spring实战").publishDate(LocalDate.now()).build();
        Book book2 = Book.builder().bookName("MySql数据库").publishDate(LocalDate.now()).build();
        Book book3 = Book.builder().bookName("JAVA编程思想").publishDate(LocalDate.now()).build();
        Book book4 = Book.builder().bookName("java并发编程实战").publishDate(LocalDate.now()).build();
        Book book5 = Book.builder().bookName("数据结构与算法").publishDate(LocalDate.now()).build();

        ArrayList<Book> books = Lists.newArrayList(book1, book2,book3,book4,book5);

        bookRepository.saveAll(books);

    }


    @Test
    void testPagingAndSortingRepository(){

//        Sort.Order id = Sort.Order.by("id");
//        Sort.Order bookName = Sort.Order.desc("bookName");
//        Sort sort = Sort.by(id,bookName);

        //等价于上面三句代码
//        Sort sort = Sort.by("id").ascending().and(Sort.by("bookName").descending());

        //使用类型安全的排序
        Sort.TypedSort<Book> bookTypedSort = Sort.sort(Book.class);
        Sort sort = bookTypedSort.by(Book::getId).ascending()
                .and(bookTypedSort.by(Book::getBookName).descending());

        Pageable pageable = PageRequest.of(2,2, sort);

        Page<Book> page = bookRepository.findAll(pageable);

        System.out.println("分页查询结果列表：" + page.getContent());
        System.out.println("当前分页结果列表中的元素个数：" + page.getNumberOfElements());
        System.out.println("当前条件下总条数：" + page.getTotalElements());
        System.out.println("总页数：" +page.getTotalPages());
        System.out.println("我们自己传的page：" +page.getNumber());
        System.out.println("我们自己传入的size：" +page.getSize());

    }

    @Test
    void testQueryByExampleExecutor(){

        Book book = Book.builder().bookName("java").publishDate(LocalDate.of(2019,11,11)).id(1L).build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id") //忽略id属性，不管id有没有值，都不作为查询条件。
                .withIgnoreNullValues() //忽略属性为null的，不作为查询条件。
                .withMatcher("bookName",m -> m.startsWith().ignoreCase()) //设置bookName属性，前包含，忽略大小写。
                .withTransformer("publishDate",value -> Optional.of(LocalDate.of(2019,11,12))); //转换属性值

        Example<Book> example = Example.of(book,matcher);

        List<Book> books = bookRepository.findAll(example);

    }

    @Test
    void testJpaRepository(){

        List<Book> books = bookRepository.findAll();
        System.out.println(books.size());
    }

}