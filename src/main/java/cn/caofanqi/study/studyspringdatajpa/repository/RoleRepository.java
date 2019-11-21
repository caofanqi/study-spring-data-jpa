package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author caofanqi
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
}
