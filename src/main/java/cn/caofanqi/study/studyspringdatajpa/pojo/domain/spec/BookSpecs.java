package cn.caofanqi.study.studyspringdatajpa.pojo.domain.spec;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Book;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Book_;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Category_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.time.LocalDate;

/**
 * book Specification 条件
 *
 * @author caofanqi
 */
public class BookSpecs {


    /**
     *  书名模糊
     */
    public static Specification<Book> bookNameLike(String bookName){
        return (Specification<Book>) (root, query, builder) -> {
            //设置抓取策略，解决N+1条SQL问题
            root.fetch("category", JoinType.LEFT);
            return builder.like(root.get("bookName"),"%" + bookName + "%");
        };
    }


    /**
     * 大于前六个月的认为是新书
     */
    public static Specification<Book> isNewBook(){
        return (Specification<Book>) (root, query, builder) -> {
            LocalDate beforeSixMonth = LocalDate.now().minusMonths(6);
            root.fetch(Book_.category, JoinType.LEFT);
            return builder.greaterThan(root.get(Book_.publishDate),beforeSixMonth);
        };
    }


    /**
     *  门类名称模糊
     */
    public static Specification<Book> categoryNameLike(String categoryName){
        return (Specification<Book>) (root, query, builder) -> {
            root.fetch(Book_.category, JoinType.INNER);
            return builder.like(root.get(Book_.category).get(Category_.categoryName),"%" + categoryName + "%");
        };
    }

}
