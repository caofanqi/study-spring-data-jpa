package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *电子书籍
 * @author caofanqi
 */
@Data
@Entity
@Table(name = "jpa_ebook")
public class EBook extends Book{

    /**
     * 格式
     */
    private String format;

}
