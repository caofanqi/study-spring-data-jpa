package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Role;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.RoleNameAndAdminCountAndAgeAvg;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.RoleNameAndAdminCountAndAgeAvgDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author caofanqi
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * JPQL 使用投影
     */
    @Query(value = "select r.roleName as roleName,count(a) as adminCount , avg(a.age) as ageAvg from  Role r inner join Admin a on r = a.role group by r.roleName ")
    List<RoleNameAndAdminCountAndAgeAvg> findRoleNameAndAdminCountAndAgeAvgWithJPQL();

    /**
     * 原生SQL 使用投影
     */
    @Query(value = "SELECT r.role_name AS roleName,COUNT(*) AS adminCount,AVG(a.age) AS ageAvg FROM cfq_jpa_role r INNER JOIN cfq_jpa_admin a ON r.id = a.role_id GROUP BY r.role_name ", nativeQuery = true)
    List<RoleNameAndAdminCountAndAgeAvg> findRoleNameAndAdminCountAndAgeAvgWithSQL();

    /**
     * 使用DTO投影接收JPQL查询结果,如果不是实体本身的属性，要使用如下方式
     */
    @Query(value = "select new cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.RoleNameAndAdminCountAndAgeAvgDTO(r.roleName ,count(a), avg(a.age)) from  Role r inner join Admin a on r = a.role group by r.roleName")
    List<RoleNameAndAdminCountAndAgeAvgDTO> findRoleNameAndAdminCountAndAgeAvgWithDTO();

}
