package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * 类别
 * @author caofanqi
 */
@Data
@Entity
@Builder
@Table(name = "jpa_category")
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Category.selectByName",query = "select c from Category c where c.categoryName = ?1 ")
@NamedNativeQuery(name = "Category.selectByNameLike",query = "SELECT * FROM cfq_jpa_category WHERE category_name LIKE ?1 ",resultClass = Category.class)
//@NamedEntityGraph(name = "Category.findByParent",
//                attributeNodes = {@NamedAttributeNode(value = "children", subgraph = "son")}, //第一层
//        subgraphs = {@NamedSubgraph(name = "son", attributeNodes = @NamedAttributeNode(value = "children", subgraph = "grandson")), //第二层
//                @NamedSubgraph(name = "grandson", attributeNodes = @NamedAttributeNode(value = "children"))//第三层
//        })
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String categoryName;

    /**
     *  父门类，通过parent_id来维护父子关系。
     *  使用@ToString.Exclude，解决lombok的toString方法循环引用问题。
     */
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private Category parent;

    /**
     * 子门类列表，交由parent来维护两者之间关系。
     */
    @OrderColumn
    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    private Set<Category> children;
    private List<Category> children;

    /**
     * 门类和书是一对多的关系
     * 由多的一方来维护关联关系
     */
    @OneToMany(mappedBy = "category")
    @OrderBy("bookName DESC")
    private List<Book> books;

}
