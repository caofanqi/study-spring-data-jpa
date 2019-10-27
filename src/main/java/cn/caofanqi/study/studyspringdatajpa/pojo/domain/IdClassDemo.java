package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 联合主键的使用，在类上添加@IdClass并指定外部主键类，每个主键上都要添加@Id注解。
 */
@Data
@Entity
@IdClass(value = UnionKey.class)
@Table(name = "jpa_id_class_demo")
public class IdClassDemo {

    @Id
    private String idOne;

    @Id
    private String idTwo;

    private String context;

}
