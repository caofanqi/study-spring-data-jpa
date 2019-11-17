package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.User;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.Sex;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(false)
@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;


    /**
     * 数据库中没数据的话，先执行此测试用例，因为要测试delete打印sql语句，所以不使用 @BeforeEach了。
     */
//    @Test
//    @BeforeEach
    void setup(){
        User user1 = User.builder().username("tom").password("password123").age(23).sex(Sex.MAN).phone("13656785678").email("email_111@email.com").birthday(LocalDate.of(1996, 6, 6)).build();
        User user2 = User.builder().username("lucy").password("password1234").age(24).sex(Sex.WOMAN).phone("13612341234").email("emailc111222@email.com").birthday(LocalDate.of(1995, 4, 4)).build();
        User user3 = User.builder().username("jack").password("password12345").age(25).sex(Sex.MAN).phone("13523452345").email("jack111222333@email.com").birthday(LocalDate.of(1994, 5, 5)).build();
        User user4 = User.builder().username("bob").password("password123456").age(23).sex(Sex.MAN).phone("13734563456").email("bob111222333444@email.com").birthday(LocalDate.of(1996, 3, 6)).build();
        User user5 = User.builder().username("mack").password("password123").age(22).sex(Sex.MAN).phone("13667896789").email("mack111222333444555@email.com").birthday(LocalDate.of(1997, 6, 6)).build();

        ArrayList<User> users = Lists.newArrayList(user1, user2, user3, user4, user5);

        userRepository.saveAll(users);
    }


    @Test
    void findByUsernameAndPassword() {
        User tom = userRepository.findByUsernameAndPassword("tom", "password123");
        assertEquals("tom",tom.getUsername());
    }


    @Test
    void findByPhoneIsStartingWith(){
        List<User> users = userRepository.findByPhoneIsStartingWith("136");
        assertEquals(3,users.size());
    }

    @Test
    void findByPhone(){
        User tom = userRepository.findByPhone("13656785678");
        assertEquals("tom",tom.getUsername());
    }


    @Test
    void findBySexString(){
        Pageable pageable = PageRequest.of(1,2, Sort.by(Sort.Direction.DESC,"age"));
        Page<User> userPage = userRepository.findBySexString(Sex.MAN.toString(), pageable);

        assertEquals(2,userPage.getNumberOfElements());
        assertEquals(4,userPage.getTotalElements());
        assertEquals("mack",userPage.getContent().get(1).getUsername());
    }

    @Test
    void findBySex(){
        Pageable pageable = PageRequest.of(1,2, Sort.by(Sort.Direction.DESC,"age"));
        Page<User> userPage = userRepository.findBySex(Sex.MAN, pageable);

        assertEquals(2,userPage.getNumberOfElements());
        assertEquals(4,userPage.getTotalElements());
        assertEquals("mack",userPage.getContent().get(1).getUsername());
    }

    /**
     * Sort中不支持使用函数，可以使用别名或JpaSort替代
     */
    @Test
    void findByAsArrayAndSort(){

        //sort指向domain模型中有效的属性,jpa会自动为我们加上表的别名,不需要自己添加，添加就会报错哦。
        List<Object[]> result1 = userRepository.findByAsArrayAndSort(Sex.MAN, Sort.by("email"));

        //Sort不支持使用函数
        //Sort expression 'length(email): ASC' must only contain property references or aliases used in the select clause. If you really want to use something other than that for sorting, please use JpaSort.unsafe(…)!
        assertThrows(InvalidDataAccessApiUsageException.class,() -> userRepository.findByAsArrayAndSort(Sex.MAN, Sort.by("length(email)")));

        //使用JpaSort的unsafe来使用函数
        List<Object[]> result2 = userRepository.findByAsArrayAndSort(Sex.MAN, JpaSort.unsafe("length(email)"));

        //可以使用别名来进行排序
        List<Object[]> result3 = userRepository.findByAsArrayAndSort(Sex.MAN, Sort.by("em_len"));
    }


    @Test
    void findByPhoneOrUsername(){
        List<User> list = userRepository.findByPhoneOrUsername("xxx", "tom");
        assertEquals("tom",list.get(0).getUsername());
    }

    @Test
    void findByUsernameOrPhone(){
        List<User> list = userRepository.findByUsernameOrPhone("tom", "xxx");
        assertEquals("tom",list.get(0).getUsername());
    }


    @Test
    void findUserByUsernameWithSpelEntityName(){
        User tom = userRepository.findUserByUsernameWithSpelEntityName("tom");
        assertEquals("tom",tom.getUsername());
    }

    @Test
    void findUserByUsernameWithSpel(){
        User user = User.builder().username("tom").build();
        User tom = userRepository.findUserByUsernameWithSpel(user);
        assertEquals("tom",tom.getUsername());
    }

    @Test
    void findByEmailLikeWithSpel(){
        List<User> users = userRepository.findByEmailLikeWithSpel("email_");
        assertEquals(5,users.size());
    }

    @Test
    void findByEmailLikeWithNativeQuery(){
        List<User> users = userRepository.findByEmailLikeWithNativeQuery("email_");
        assertEquals(5,users.size());
    }

    @Test
    void findByEmailLikeWithEscaped(){
        List<User> users = userRepository.findByEmailLikeWithEscaped("email_");
        assertEquals(1,users.size());
    }


    @Test
    void updatePhoneByUsername(){

        User tom = userRepository.findByUsername("tom");

        int update = userRepository.updatePhoneByUsername("tom", "13688889999");

        assertEquals(1,update);

        //如果没有配置clearAutomatically=true，如果之前entityManager中有该实体，那么在同一个接口中再次查询时，是修改之前的对象。
        tom = userRepository.findByUsername("tom");

        System.out.println(tom.getPhone());

    }


    @Test
    void deleteByUsername(){
        int delete = userRepository.deleteByUsername("jack");
        System.out.println(delete);
    }


    @Test
    void deleteByUsernameWithQueryAndModifying(){
        int delete = userRepository.deleteByUsernameWithQueryAndModifying("bob");
        System.out.println(delete);
    }
}