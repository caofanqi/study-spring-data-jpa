package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.User;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.Sex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 用户持久层
 * @author caofanqi
 */
public interface UserRepository extends JpaRepository<User,Long> {


    /**
     * 根据用户名进行查询
     * @param username username
     * @return User
     */
    User findByUsername(String username);

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


    /**
     *  测试Order中不支持函数
     * @param sex sex
     * @param sort sort
     * @return list
     */
    @Query(value = "select u.id,length(u.email) as em_len from User u where u.sex = ?1 ")
    List<Object[]> findByAsArrayAndSort(Sex sex,Sort sort);


    /**
     * 使用@Param进行参数名称绑定后，参数位置无所谓
     * @param ppp ppp
     * @param uuu uuu
     * @return list
     */
    @Query(value = "select u from User u where u.username = :username or u.phone = :phone ")
    List<User> findByPhoneOrUsername(@Param("phone")String ppp,@Param("username")String uuu);


    /**
     * 参数名称一致时，可以省略@Param
     * @param username
     * @param phone
     * @return
     */
    @Query(value = "select u from User u where u.phone = :phone or u.username = :username ")
    List<User> findByUsernameOrPhone(String username,String phone);

    /**
     * 为了避免在查询的字符串声明中使用实体类名，可以使用#{#entityName}变量.
     * 根据用户名查询
     * @param username username
     * @return User
     */
    @Query(value = "select u from #{#entityName} u where u.username = ?1 ")
    User findUserByUsernameWithSpelEntityName(String username);

    /**
     * 使用spel表达式
     * 当 #{#entityName} 与SPEL 一起使用时，参数要使用 ?#{
     *
     * @param user user
     * @return user
     */
//    @Query(value = "select u from User u where u.username = :#{#user.username} ")
    @Query(value = "select u from #{#entityName} u where u.username = ?#{#user.username} ")
    User findUserByUsernameWithSpel(User user);


    /**
     * like查询可以在参数绑定或spel上追加%。
     * @param email email
     * @return list
     */
    @Query(value = "select u from User  u where u.email like %:#{[0]}% and u.email like %:email%")
    List<User> findByEmailLikeWithSpel(String email);

    /**
     * 原生sql也支持参数绑定和spel表达式
     * @param email email
     * @return list
     */
    @Query(value = "SELECT * FROM cfq_jpa_user WHERE email LIKE %:#{[0]}% AND email LIKE %:email% " ,nativeQuery = true)
    List<User> findByEmailLikeWithNativeQuery(String email);


    /**
     * like查询 escape() 定义转义字符
     * @param email email
     * @return list
     */
    @Query(value = "select u from User u where u.email like %?#{escape([0])}% escape ?#{escapeCharacter()} ")
    List<User> findByEmailLikeWithEscaped(String email);


    /**
     * 根据用户名修改手机号
     * @param username username
     * @param phone phone
     * @return int
     */
    @Modifying/*(clearAutomatically = true)*/
    @Query(value = "update User u set u.phone = :phone where u.username = :username ")
    int updatePhoneByUsername(String username,String phone);

    /**
     * 方法名派生删除，不需要添加@Modifying，该方法会执行两条sql语句，先执行select在执行delete
     * @param username username
     * @return int
     */
    int deleteByUsername(String username);


    /**
     * 使用@Query和@Modifying进行删除，一条sql语句，直接delete
     * @param username  username
     * @return int
     */
    @Modifying
    @Query(value = "delete from User u where u.username = :username")
    int deleteByUsernameWithQueryAndModifying(String username);


}
