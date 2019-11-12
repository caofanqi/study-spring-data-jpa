package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Book;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * 书籍持久层
 * @author caofanqi
 * 使用@RepositoryDefinition注解与继承Repository具有相同的效果
 */
//@RepositoryDefinition(domainClass = Book.class,idClass = Long.class)
//public interface BookRepository extends Repository<Book,Long>{
//public interface BookRepository extends CrudRepository<Book,Long> {
//public interface BookRepository extends PagingAndSortingRepository<Book,Long> , QueryByExampleExecutor<Book> {
public interface BookRepository extends JpaRepository<Book,Long> {

    /**
     * 根据书名查找书籍
     * @param bookName 书籍名称
     * @return 该书籍名称的书列表
     */
     List<Book> findBooksByBookNameContains(String bookName);

}
