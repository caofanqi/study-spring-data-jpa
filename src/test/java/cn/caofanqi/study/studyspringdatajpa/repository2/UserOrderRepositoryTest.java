package cn.caofanqi.study.studyspringdatajpa.repository2;

import cn.caofanqi.study.studyspringdatajpa.MultiDataSourceApplication;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.User;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain2.UserOrder;
import cn.caofanqi.study.studyspringdatajpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

import java.time.LocalDate;

@Rollback(false)
@SpringBootTest(classes = MultiDataSourceApplication.class)
@ActiveProfiles("multi-datasource")
class UserOrderRepositoryTest {


    @Resource
    private UserRepository userRepository;

    @Resource
    private UserOrderRepository userOrderRepository;

    @Resource
    private PlatformTransactionManager transactionManagerOne;

    @Resource
    private PlatformTransactionManager transactionManagerTwo;

    @Test
    @Transactional
    void testSaveUser() {
        User user = new User();
        user.setUsername("张三");
        user.setAge(23);
        user.setPhone("13656785678");
        userRepository.save(user);
    }

    @Test
    @Transactional
    void testSaveUserOrder() {
        User user = userRepository.findByUsername("张三");

        UserOrder userOrder = new UserOrder();
        userOrder.setOrderName(user.getUsername() + "的订单");
        userOrder.setUserId(user.getId());
        userOrder.setCreateTime(LocalDate.now());

        userOrderRepository.save(userOrder);

    }


    @Test
    @Transactional
    void testSave() {

        TransactionStatus statusOne = transactionManagerOne.getTransaction(new DefaultTransactionDefinition());
        TransactionStatus statusTwo = transactionManagerTwo.getTransaction(new DefaultTransactionDefinition());

        try {
            User user = new User();
            user.setUsername("李四");
            user.setAge(23);
            user.setPhone("123456");
            Long userId = userRepository.save(user).getId();

//            int i = 1 / 0 ;

            UserOrder userOrder = new UserOrder();
            userOrder.setOrderName(user.getUsername() + "的订单");
            userOrder.setUserId(userId);
            userOrder.setCreateTime(LocalDate.now());

            userOrderRepository.save(userOrder);

            transactionManagerOne.commit(statusOne);
            transactionManagerTwo.commit(statusTwo);

        }catch (Exception e){
            transactionManagerOne.rollback(statusOne);
            transactionManagerTwo.rollback(statusTwo);
        }

    }

}