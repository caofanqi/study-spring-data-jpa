package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Book;
//import cn.caofanqi.study.studyspringdatajpa.pojo.domain.spec.BookSpecs;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.spec.BookSpecs;
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


    /**
     * 对于fetch= FetchType.EAGER ,使用findById会执行关联查询。
     */
    @Test
    void testFindById(){
        Optional<Book> bookOptional = bookRepository.findById(1L);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            System.out.println(book.getCategory().getCategoryName());
        }
    }

    /**
     * 对于fetch= FetchType.EAGER ,使用我们自己定义的查询方法，则不生效，会使用懒加载的方式
     */
    @Test
    void findByBookName(){
        Optional<Book> bookOptional = bookRepository.findByBookName("java编程思想");
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            System.out.println(book.getCategory().getCategoryName());
        }
    }


    /**
     * 对于fetch= FetchType.EAGER ,使用我们自己定义的查询方法，则不生效，会使用懒加载的方式，执行 N+1条SQL。
     * 可以通过@EntityGraph来进行解决
     */
    @Test
    void findByPublishDate(){
        List<Book> books = bookRepository.findByPublishDate(LocalDate.of(2019,11,17));
        books.forEach(b -> System.out.println(b.getCategory().getCategoryName()));
    }

    /**
     * 对于fetch= FetchType.EAGER ,使用@Query，自己写查询语句，解决N+1条SQL问题。
     */
    @Test
    void findByPublishDateWithQuery(){
        List<Book> books = bookRepository.findByPublishDateWithQuery(LocalDate.of(2019, 11, 17));
        books.forEach(b -> System.out.println(b.getCategory().getCategoryName()));
    }


    /*
     * Spec
     */

    @Test
    void testSpec1(){
        List<Book> books = bookRepository.findAll(BookSpecs.bookNameLike("java"));
        books.forEach(b-> System.out.println(b.getBookName()));
    }

    @Test
    void testSpec2(){
        List<Book> books = bookRepository.findAll(BookSpecs.bookNameLike("java").and(BookSpecs.isNewBook()));
        books.forEach(b-> System.out.println(b.getBookName()));
    }

    /**
     * 排序和分页一样使用
     */
    @Test
    void testSpec3(){
        List<Book> books = bookRepository.findAll(BookSpecs.isNewBook(),Sort.by(Sort.Direction.DESC,"publishDate"));
        books.forEach(b-> System.out.println(b.getBookName()));
    }


    @Test
    void testSpec4(){
        List<Book> books = bookRepository.findAll(BookSpecs.categoryNameLike("数据库"));
        books.forEach(b-> System.out.println(b.getBookName()));
    }

}