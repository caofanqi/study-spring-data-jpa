package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import cn.caofanqi.study.studyspringdatajpa.pojo.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String email;

    private String phone;

    /**
     * &#064;OneToOne 指定一对一关联关系
     * &#064;JoinColumn name 本表中外键列的列名，referencedColumnName ，关联表中与name进行对应的列名
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_info_id",referencedColumnName = "id")
    private AuthorInfo authorInfo;

    /**
     * 作者和书籍是多对多关系，我们让作者作为非拥有方，使用mappedBy指向拥有放属性
     */
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    /**
     * 使用两个双向一对多/多对一来替代 多对多
     */
//    @OneToMany(mappedBy = "author")
//    private List<BookAuthor> bookAuthors;

    /**
     * &#064;Embedded 指定该字段是一个嵌入对象，并可以通过 &#064;AttributeOverrides和&#064;AttributeOverride来修改
     * 嵌入对象在本实体表中的映射字段名称，修改后原有可嵌入对象中的字段设置失效。
      */
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "detailedAddress",column = @Column(name = "addr_detailed")),
//            @AttributeOverride(name = "zipCode",column = @Column(name = "addr_zip"))
//    })
//    private Address address;


    /**
     *  &#064;ElementCollection 映射基本类型和可嵌入类型集合，可以使用&#064;CollectionTable 指定额外产生表的名称，外键名称等。
     */
//    @ElementCollection
//    @CollectionTable(name = "jpa_author_nick",joinColumns = {@JoinColumn(name = "a_id",referencedColumnName = "id")})
//    private Set<String> nickName;


    /**
     * 一个作者有多个地址
     */
    @ElementCollection
    @MapKeyColumn(name = "addr_key")
    @CollectionTable(name = "jpa_author_address")
    @AttributeOverrides({
            @AttributeOverride(name = "value.detailedAddress",column = @Column(name = "addr_detailed")),
            @AttributeOverride(name = "value.zipCode",column = @Column(name = "addr_zip"))
    })

    private Map<String,Address> addresses;

}
