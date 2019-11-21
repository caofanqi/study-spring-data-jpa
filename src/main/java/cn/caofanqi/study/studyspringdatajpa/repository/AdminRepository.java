package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Admin;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.UsernameDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
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
//    List<Admin> findByCreateTime(LocalDate createTime);
//    List<AdminProjections.UsernameOnly> findByCreateTime(LocalDate createTime);
//    List<AdminProjections.AdminSummary> findByCreateTime(LocalDate createTime);
//    List<AdminProjections.FullAddress> findByCreateTime(LocalDate createTime);
//    List<AdminProjections.FullAddressJava8> findByCreateTime(LocalDate createTime);
//    List<AdminProjections.FullAddressForSpringBean> findByCreateTime(LocalDate createTime);
//    List<AdminProjections.HelloUsername> findByCreateTime(LocalDate createTime);
    List<UsernameDTO> findByCreateTime(LocalDate createTime);


    /**
     *  动态返回投影
     */
    <T> List<T> findByCreateTime(LocalDate createTime, Class<T> type);


}
