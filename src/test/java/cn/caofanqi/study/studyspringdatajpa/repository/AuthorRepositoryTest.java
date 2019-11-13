package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Author;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AuthorInfo;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.support.Authors;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.Sex;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class AuthorRepositoryTest {

    @Resource
    private  AuthorRepository authorRepository;

    @BeforeEach
    void setup(){
        Author author1 = Author.builder().authorName("张三").sex(Sex.MAN).email("zs@zs.com").phone("123451").birthday(LocalDate.of(2019, 1, 1)).authorInfo(new AuthorInfo("111")).build();
        Author author2 = Author.builder().authorName("李四").sex(Sex.MAN).email("ls@ls.com").phone("123452").birthday(LocalDate.of(2019, 2, 1)).authorInfo(new AuthorInfo("222")).build();
        Author author3 = Author.builder().authorName("王五").sex(Sex.MAN).email("ww@ww.com").phone("123453").birthday(LocalDate.of(2019, 1, 1)).authorInfo(new AuthorInfo("333")).build();
        Author author4 = Author.builder().authorName("赵六").sex(Sex.MAN).email("zl@zl.com").phone("123454").birthday(LocalDate.of(2019, 5, 1)).authorInfo(new AuthorInfo("444")).build();
        Author author5 = Author.builder().authorName("tom").sex(Sex.MAN).email("tom@tom.com").phone("123455").birthday(LocalDate.of(2019, 1, 1)).authorInfo(new AuthorInfo("555")).build();
        Author author6 = Author.builder().authorName("lucy").sex(Sex.WOMAN).email("lucy@lucy.com").phone("123456").birthday(LocalDate.of(2019, 1, 1)).authorInfo(new AuthorInfo("666")).build();
        Author author7 = Author.builder().authorName("mack").sex(Sex.MAN).email("mack@mack.com").phone("123457").birthday(LocalDate.of(2019, 10, 1)).authorInfo(new AuthorInfo("777")).build();
        Author author8 = Author.builder().authorName("holo").sex(Sex.WOMAN).email("holo@holo.com").phone("123458").birthday(LocalDate.of(2019, 1, 1)).authorInfo(new AuthorInfo("888")).build();

        ArrayList<Author> authors = Lists.newArrayList(author1, author2, author3, author4, author5, author6, author7, author8);

        authorRepository.saveAll(authors);
    }

    @Test
    void findAuthorsBySexAndBirthday() {
        List<Author> authorsBySexAndBirthday = authorRepository.findAuthorsBySexAndBirthday(Sex.MAN, LocalDate.of(2019, 1, 1));
        assertEquals(3,authorsBySexAndBirthday.size());
    }

    @Test
    void findDistinctByPhoneOrAuthorName() {
        List<Author> authors = authorRepository.findDistinctByPhoneOrAuthorName("123451", "张三");
        assertEquals(1,authors.size());
    }

    @Test
    void findByEmailIgnoreCase() {
        Author author = authorRepository.findByEmailIgnoreCase("ZS@ZS.COM");
        assertEquals("zs@zs.com",author.getEmail());
    }

    @Test
    void findByAuthorNameAndEmailAllIgnoreCase() {
        Author author = authorRepository.findByAuthorNameAndEmailAllIgnoreCase("TOM", "TOM@TOM.COM");
        assertEquals("tom",author.getAuthorName());
    }

    @Test
    void findBySexOrderByBirthdayAsc() {
        List<Author> authors = authorRepository.findBySexOrderByBirthdayAsc(Sex.MAN);
        assertEquals(LocalDate.of(2019, 1, 1),authors.get(0).getBirthday());
    }

    @Test
    void findByAuthorInfo_IdCardNumber(){
        Author author = authorRepository.findByAuthorInfo_IdCardNumber("111");
        assertEquals("张三",author.getAuthorName());
    }


    @Test
    void findByBirthday(){
        Pageable pageable = PageRequest.of(1, 2, Sort.Direction.DESC, "phone");
        Page<Author> page = authorRepository.findByBirthday(LocalDate.of(2019, 1, 1), pageable);

        assertEquals(5,page.getTotalElements());

    }

    @Test
    void findBySex(){
        Pageable pageable = PageRequest.of(2, 2);
        Slice<Author> slice = authorRepository.findBySex(Sex.MAN, pageable);

        System.out.println("当前返回的slice是否有内容：" + slice.hasContent());
        System.out.println("是否有下一页：" + slice.hasNext());
        System.out.println("是否有上一页：" + slice.hasPrevious());
        System.out.println("是否是第一页：" + slice.isFirst());
        System.out.println("是否是最后一页：" + slice.isLast());
        System.out.println("下一页pageable：" + slice.nextPageable());
        System.out.println("下一页或最后一页pageable：" + slice.nextOrLastPageable());
        System.out.println("上一页pageable：" + slice.previousPageable());
        System.out.println("上一页或第一页pageable：" + slice.previousOrFirstPageable());

    }


    @Test
    void findByBirthdayAndSex(){
        Pageable pageable = PageRequest.of(1, 2, Sort.Direction.DESC, "phone");
        List<Author> authors = authorRepository.findByBirthdayAndSex(LocalDate.of(2019, 1, 1), Sex.MAN, pageable);
        System.out.println(authors.size());
    }

    @Test
    void findTopByBirthdayOrderByPhoneDesc(){
        Optional<Author> op = authorRepository.findTopByBirthdayOrderByPhoneDesc(LocalDate.of(2019, 1, 1));
        if (op.isPresent()){
            Author author = op.get();
            assertEquals("holo",author.getAuthorName());
        }
    }


    @Test
    void findTop3BySex(){
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.ASC, "phone");
        Page<Author> authors = authorRepository.findTop3BySex(Sex.MAN, pageable);
        System.out.println(authors.getContent().size());
    }


    @Test
    void findByPhone(){
        Optional<Author> authorOptional = authorRepository.findByPhone("123451");
        assertTrue(authorOptional.isPresent());

        Optional<Author> authorOptional2 = authorRepository.findByPhone("1234512323");
        assertFalse(authorOptional2.isPresent());
    }

    @Test
    void findBySexStream(){
        try(Stream<Author> authors =  authorRepository.findBySex(Sex.MAN)) {
           assertEquals(6,authors.count());
        }
    }

    @Test
    void readBySex(){
        Streamable<Author> authors = authorRepository.readBySex(Sex.MAN).and(authorRepository.readBySex(Sex.WOMAN));
        assertEquals(8,authors.stream().count());
    }

    @Test
    void readByBirthday(){
        Authors authors = authorRepository.readByBirthday(LocalDate.of(2019, 1, 1));
        assertEquals(5,authors.stream().count());
    }


    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void findByEmail(){
        Future<Author> authorFuture = authorRepository.findByEmail("zs@zs.com");

        while (!authorFuture.isDone()){
            System.out.println("稍等...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Author author = authorFuture.get();
            assertEquals("zs@zs.com",author.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

        authorRepository.deleteAll();
    }




}