package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Book;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 书籍持久层
 * @author caofanqi
 */
public interface BookRepository extends JpaRepository<Book,Long> {
}
