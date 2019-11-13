package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Author;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.support.Authors;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.Sex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * 作者持久层
 *
 * @author caofanqi
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * 根据性别和生日进行查询
     *
     * @param sex      性别
     * @param birthday 生日
     * @return list
     */
    List<Author> findAuthorsBySexAndBirthday(Sex sex, LocalDate birthday);

    /**
     * 根据电话或姓名进行查询，并去重
     *
     * @param phone      电话
     * @param authorName 姓名
     * @return list
     */
    List<Author> findDistinctByPhoneOrAuthorName(String phone, String authorName);

    /**
     * 根据邮箱查询作者，忽略邮箱大小写
     *
     * @param email 邮箱
     * @return author
     */
    Author findByEmailIgnoreCase(String email);

    /**
     * 根据作者名，邮箱查询作者，忽略大小写
     *
     * @param authorName 作者名
     * @param email      邮箱
     * @return author
     */
    Author findByAuthorNameAndEmailAllIgnoreCase(String authorName, String email);

    /**
     * 根据性别查询作者，根据生日升序排序
     *
     * @param sex 性别
     * @return list
     */
    List<Author> findBySexOrderByBirthdayAsc(Sex sex);

    /**
     * 通过作者详细信息中的身份证号，查询
     *
     * @param IdCardNumber 身份证号
     * @return author
     */
    Author findByAuthorInfo_IdCardNumber(String IdCardNumber);

    /**
     * 通过生日分页查询,返回Page
     *
     * @param birthday 生日
     * @param pageable 分页对象
     * @return page
     */
    Page<Author> findByBirthday(LocalDate birthday, Pageable pageable);

    /**
     * 通过性别分页查询,返回Slice
     *
     * @param sex      性别
     * @param pageable 分页对象
     * @return slice
     */
    Slice<Author> findBySex(Sex sex, Pageable pageable);

    /**
     * 根据phone模糊查询
     *
     * @param phone phone
     * @param sort  排序对象
     * @return list
     */
    List<Author> findByPhoneContaining(String phone, Sort sort);

    /**
     * 根据生日和性别分页查询
     *
     * @param birthday 生日
     * @param sex      性别
     * @param pageable 分页对象
     * @return list
     */
    List<Author> findByBirthdayAndSex(LocalDate birthday, Sex sex, Pageable pageable);

    /**
     * 根据生日查询作者，并根据phone降序排列，取第一个
     *
     * @param birthday 生日
     * @return optional
     */
    Optional<Author> findTopByBirthdayOrderByPhoneDesc(LocalDate birthday);

    /**
     * 先分页在取top3，不建议分页和top同时使用
     *
     * @param sex      性别
     * @param pageable 分页对象
     * @return page
     */
    @Deprecated
    Page<Author> findTop3BySex(Sex sex, Pageable pageable);


    /**
     * 根据电话查找
     *
     * @param phone 电话
     * @return op
     */
    Optional<Author> findByPhone(String phone);


    /**
     * 根据性别查询返回Java8Stream
     *
     * @param sex 性别
     * @return stream
     */
    Stream<Author> findBySex(Sex sex);

    /**
     * 根据性别查询返回Streamable
     *
     * @param sex 性别
     * @return streamable
     */
    Streamable<Author> readBySex(Sex sex);

    /**
     * 根据性别查询，返回自定义Streamable
     *
     * @param birthday
     * @return authors
     */
    Authors readByBirthday(LocalDate birthday);

    /**
     * 异步返回结果
     *
     * @param email email
     * @return author
     */
    @Async
     Future<Author> findByEmail(String email);




}
