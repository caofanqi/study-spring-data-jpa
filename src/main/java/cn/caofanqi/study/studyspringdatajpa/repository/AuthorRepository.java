package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 作者持久层
 *
 * @author caofanqi
 */
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
