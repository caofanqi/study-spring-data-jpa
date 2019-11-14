package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.User;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.Sex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户持久层
 * @author caofanqi
 */
public interface UserRepository extends JpaRepository<User,Long> {


    /**
     *  通过用户名密码进行查询
     * @param username username
     * @param password password
     * @return User
     */
    @Query(value = "select u from User u where u.username = ?1 and u.password = ?2 ")
    User findByUsernameAndPassword(String username,String password);

    /**
     * 查询以...开头的phone用户
     * @param phone phone
     * @return list
     */
    @Query(value = "select u from User u where u.phone like ?1% ")
    List<User> findByPhoneIsStartingWith(String phone);

    /**
     * 用过phone进行查询
     * @param phone phone
     * @return User
     */
    @Query(value = "SELECT * FROM cfq_jpa_user WHERE phone = ?1 ",nativeQuery = true)
    User findByPhone(String phone);


    /**
     * 根据性别查询并分页，原生SQL，不能使用SEX枚举，要使用String
     * @param sex sex
     * @param pageable pageable
     * @return page
     */
    @Query(value = "SELECT * FROM cfq_jpa_user WHERE sex = ?1 ",
            countQuery = "SELECT count(*) FROM cfq_jpa_user WHERE sex = ?1 ",
            nativeQuery = true)
    Page<User> findBySexString(String sex, Pageable pageable);


    /**
     * 根据性别查询并分页，JPQL，能使用SEX枚举
     * @param sex sex
     * @param pageable pageable
     * @return page
     */
    @Query(value = "select u from User u where u.sex = ?1 ")
    Page<User> findBySex(Sex sex, Pageable pageable);
}
