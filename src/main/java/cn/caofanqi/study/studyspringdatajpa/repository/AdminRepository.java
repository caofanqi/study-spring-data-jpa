package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Admin;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * admin持久层
 * @author caofanqi
 */
public interface AdminRepository extends JpaRepository<Admin,Long> {


    /**
     * 根据创建日期查询管理管
     * @param createTime createTime
     * @return list
     */
    //1、返回管理的实体
//    List<Admin> findByCreateTime(LocalDate createTime);
    //2、只想返回username
//    List<UsernameOnly> findByCreateTime(LocalDate createTime);
    //3、只想返回username属性和address属性中的city属性
//    List<AdminUsernameAndCity> findByCreateTime(LocalDate createTime);
    //4、全地址拼接投影
//    List<AdminUsernameAndFullAddress> findByCreateTime(LocalDate createTime);
    //5、使用java8默认接口方法全地址拼接投影
//    List<AdminUsernameAndFullAddressWithJava8> findByCreateTime(LocalDate createTime);
    //6、使用Spring Bean的方式返回全地址拼接投影
//    List<AdminUsernameAndFullAddressWithSpringBean> findByCreateTime(LocalDate createTime);
    //7、spel使用方法中的参数值
//    List<PrefixUsername> findByCreateTime(LocalDate createTime);
    //8、使用DTO的投影返回username
//    List<UsernameDTO> findByCreateTime(LocalDate createTime);
    //9、使用DTO返回username和address
    List<AdminUsernameAndAddressDTO> findByCreateTime(LocalDate createTime);

    /**
     *  动态返回投影，type可以是实体，接口投影，DTO投影
     */
    <T> List<T> findByCreateTime(LocalDate createTime, Class<T> type);

    /**
     * 返回单一投影
     * @param phone phone
     * @return UsernameOnly
     */
    UsernameOnly findByPhone(String phone);


    /**
     * 动态返回单一投影
     * @param phone phone
     * @param type type
     * @param <T> T
     * @return T
     */
    <T> T findByPhone(String phone,Class<T> type);

    /**
     * 支持分页
     * @param createTime
     * @param type
     * @param pageable
     * @param <T>
     * @return
     */
    <T> Page<T> findByCreateTime(LocalDate createTime, Class<T> type, Pageable pageable);





}
