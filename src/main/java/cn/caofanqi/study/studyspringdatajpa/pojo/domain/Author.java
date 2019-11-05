package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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



}
