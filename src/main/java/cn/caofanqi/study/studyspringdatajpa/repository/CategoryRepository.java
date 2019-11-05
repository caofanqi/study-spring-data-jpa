package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 门类持久层
 * @author caofanqi
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
