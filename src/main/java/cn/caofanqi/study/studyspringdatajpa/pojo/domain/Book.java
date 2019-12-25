package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * 书籍实体
 * @author caofanqi
 */
@Data
@Entity
@Builder
@Table(name = "jpa_book")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs({
    @NamedEntityGraph(name = "Book.fetch.category",attributeNodes = {@NamedAttributeNode("category")})
})
public class Book {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "sequenceGenerator") //指定生成器
    @GenericGenerator(name = "sequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",//为生成器指定策略（hibernate默认提供的）
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "id_sequence"),//指定要用的序列或表的名称
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1000"),//sequence从几开始
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),//增量步长
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "pooled"),//指定优化器，用于提高性能
            }
    )
    private Long id;

    private  String bookName;

    private LocalDate publishDate;

    /**
     * 书和门类是多对一的关系
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    /**
     * 书和作者是多对多的关系，我们让book为关联拥有放，添加@JoinTable注解。
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "jpa_book_author",
            joinColumns = @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="author_id", referencedColumnName="id"))
    private List<Author> authors;

    /**
     * 使用两个双向一对多/多对一来替代 多对多
     */
//    @OneToMany(mappedBy = "book")
//    private List<BookAuthor> bookAuthors;


}
