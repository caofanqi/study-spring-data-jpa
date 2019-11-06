package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 作者实体
 *
 * @author caofanqi
 */
@Data
@Entity
@Builder
@Table(name = "jpa_author")
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorName;

    /**
     * &#064;OneToOne 指定一对一关联关系
     * &#064;JoinColumn name 本表中外键列的列名，referencedColumnName ，关联表中与name进行对应的列名
     */
    @OneToOne
    @JoinColumn(name = "author_info_id",referencedColumnName = "id")
    private AuthorInfo authorInfo;

    /**
     * 作者和书籍是多对多关系，我们让作者作为非拥有方，使用mappedBy指向拥有放属性
     */
//    @ManyToMany(mappedBy = "authors")
//    private List<Book> books;

    /**
     * 使用两个双向一对多/多对一来替代 多对多
     */
    @OneToMany(mappedBy = "author")
    private List<BookAuthor> bookAuthors;

}
