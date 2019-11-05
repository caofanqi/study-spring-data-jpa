package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 作者详细信息实体类
 *
 * @author caofanqi
 */
@Data
@Entity
@Builder
@Table(name = "jpa_author_info")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idCardNumber;

    /**
     *  如果在详情方需要获取作者，也添加@OneToOne，并指定 mappedBy
     */
    @OneToOne(mappedBy = "authorInfo")
    private Author author;

}
