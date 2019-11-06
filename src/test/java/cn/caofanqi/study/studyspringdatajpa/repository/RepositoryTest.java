package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Author;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Book;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.BookAuthor;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Rollback(false)
@Transactional
@SpringBootTest
public class RepositoryTest {


    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private BookRepository bookRepository;

    @Resource
    private AuthorRepository authorRepository;

    @Resource
    private BookAuthorRepository bookAuthorRepository;

    /**
     * 多对多测试
     */
    @Test
    public void test() {

        //先有作者
//        Author author1 = Author.builder().authorName("张三").build();
//        Author author2 = Author.builder().authorName("李四").build();
//
//        List<Author> authors = Arrays.asList(author1, author2);
//
//        authorRepository.saveAll(authors);

        //先有分类
//        Category category = Category.builder().categoryName("计算机科学").build();
//        categoryRepository.save(category);

//        //最后有书
//        Book book1 = Book.builder().bookName("java编程思想").build();
//        Book book2 = Book.builder().bookName("数据库").build();
//
//        List<Book> books = Arrays.asList(book1, book2);
//
//        List<Author> authors = authorRepository.findAll();
//
//        Optional<Category> categoryOptional = categoryRepository.findById(1L);
//        Category category = categoryOptional.get();
//
//        //设置分类
//        book1.setCategory(category);
//        book2.setCategory(category);
//
//        //设置作者
//        book1.setAuthors(authors.subList(0,1));
//        book2.setAuthors(authors);
//
//        //保存书籍
//        bookRepository.saveAll(books);


//        Optional<Category> categoryOptional = categoryRepository.findById(1L);
//        if (categoryOptional.isPresent()) {
//            Category category = categoryOptional.get();
//
//            System.out.println(category.getCategoryName());
//
//            List<Book> books = category.getBooks();
//
//            books.forEach(b -> {
//                System.out.println(b.getBookName() + "===");
//                List<Author> authors = b.getAuthors();
//                authors.forEach(a -> System.out.println(a.getAuthorName()));
//            });
//
//        }

    }


    /**
     * 测试使用两个一对多/多对一 替代 多对多关系
     */
    @Test
    public void test2(){
        //先有作者
//        Author author1 = Author.builder().authorName("张三").build();
//        Author author2 = Author.builder().authorName("李四").build();
//
//        List<Author> authors = Arrays.asList(author1, author2);
//
//        authorRepository.saveAll(authors);

        //先有分类
//        Category category = Category.builder().categoryName("计算机科学").build();
//        categoryRepository.save(category);

//        //最后有书
//        Book book1 = Book.builder().bookName("java编程思想").build();
//        Book book2 = Book.builder().bookName("数据库").build();
//
//        List<Book> books = Arrays.asList(book1, book2);
//
//        List<Author> authors = authorRepository.findAll();
//
//        Optional<Category> categoryOptional = categoryRepository.findById(1L);
//        Category category = categoryOptional.get();
//
//        //设置分类
//        book1.setCategory(category);
//        book2.setCategory(category);
//
//        //设置书籍作者关联关系
//        BookAuthor bookAuthor1 = BookAuthor.builder().book(book1).author(authors.get(0)).build();
//        BookAuthor bookAuthor2 = BookAuthor.builder().book(book2).author(authors.get(0)).build();
//        BookAuthor bookAuthor3 = BookAuthor.builder().book(book2).author(authors.get(1)).build();
//
//        List<BookAuthor> bookAuthors = Arrays.asList(bookAuthor1, bookAuthor2, bookAuthor3);
//
//        //保存书籍
//        bookRepository.saveAll(books);
//        //保存关联关系
//        bookAuthorRepository.saveAll(bookAuthors);


        Optional<Category> categoryOptional = categoryRepository.findById(1L);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();

            System.out.println(category.getCategoryName());

            List<Book> books = category.getBooks();

            books.forEach(b -> {
                System.out.println(b.getBookName() + "===");
                List<BookAuthor> bookAuthors = b.getBookAuthors();
                bookAuthors.forEach(ba -> System.out.println(ba.getAuthor().getAuthorName()));
            });

        }

    }

}
