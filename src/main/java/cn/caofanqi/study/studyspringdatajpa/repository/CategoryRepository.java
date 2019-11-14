package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 门类持久层
 * @author caofanqi
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {


    /**
     * 使用@NamedQuery进行方法查询
     * @param name 分类名称
     * @return category
     */
    Category selectByName(String name);

    /**
     * 使用@NamedNativeQuery进行方法查询
     * @param name 分类名称
     * @return category
     */
    List<Category> selectByNameLike(String name);

}
