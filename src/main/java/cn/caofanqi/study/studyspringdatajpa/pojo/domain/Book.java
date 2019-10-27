package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author caofanqi
 */
@Data
@Entity
public class Book {

    @Id
    private Long id;


}
