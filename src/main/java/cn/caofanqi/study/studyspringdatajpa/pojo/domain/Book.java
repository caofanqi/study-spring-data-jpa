package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 书籍实体
 * @author caofanqi
 */
@Data
@Entity
@Builder
@Table(name = "jpa_book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String bookName;

    /**
     * 书和门类是多对一的关系
     */
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    /**
     * 书和作者是多对多的关系，我们让book为关联拥有放，添加@JoinTable注解。
     */
//    @ManyToMany
//    @JoinTable(name = "jpa_book_author",
//            joinColumns = @JoinColumn(name="book_id", referencedColumnName="id"),
//            inverseJoinColumns= @JoinColumn(name="author_id", referencedColumnName="id"))
//    private List<Author> authors;

    /**
     * 使用两个双向一对多/多对一来替代 多对多
     */
    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthors;


}
