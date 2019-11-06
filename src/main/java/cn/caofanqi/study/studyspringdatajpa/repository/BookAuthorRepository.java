package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  书籍作者关联实体持久层
 * @author 曹凡启
 * @date 2019/11/6 14:29
 * @version 1.0.0
 */
public interface BookAuthorRepository extends JpaRepository<BookAuthor,Long> {
}
