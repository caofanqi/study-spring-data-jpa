package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Book;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    /**
     * 根据书名进行查询书籍
     * @param bookName bookName
     * @return book
     */
     Optional<Book> findByBookName(String bookName);

    /**
     * 根据发布时间查询书籍
     * @param publishDate publishDate
     * @return list
     */
    @EntityGraph(attributePaths = {"category"})
    //    @EntityGraph(value = "Book.fetch.category")
     List<Book> findByPublishDate(LocalDate publishDate);


    /**
     * 使用@Query，JPQL中 声明要查询category属性，减少子查询。
     * @param publishDate publishDate
     * @return list
     */
     @Query(value = "select b,b.category from Book b where b.publishDate = :publishDate ")
//     @Query(value = "select b,c from Book b inner join Category c on b.category = c where b.publishDate = :publishDate ")
    List<Book> findByPublishDateWithQuery(LocalDate publishDate);


}
