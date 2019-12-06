package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 抽象id父类
 *
 * @author caofanqi
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbstractID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
