package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.User;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.Sex;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        User user1 = User.builder().username("tom").password("password123").age(23).sex(Sex.MAN).phone("13656785678").email("tom@email.com").birthday(LocalDate.of(1996, 6, 6)).build();
        User user2 = User.builder().username("lucy").password("password1234").age(24).sex(Sex.WOMAN).phone("13612341234").email("lucy@email.com").birthday(LocalDate.of(1995, 4, 4)).build();
        User user3 = User.builder().username("jack").password("password12345").age(25).sex(Sex.MAN).phone("13523452345").email("jack@email.com").birthday(LocalDate.of(1994, 5, 5)).build();
        User user4 = User.builder().username("bob").password("password123456").age(23).sex(Sex.MAN).phone("13734563456").email("bob@email.com").birthday(LocalDate.of(1996, 3, 6)).build();
        User user5 = User.builder().username("mack").password("password123").age(22).sex(Sex.MAN).phone("13667896789").email("mack@email.com").birthday(LocalDate.of(1997, 6, 6)).build();

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

}