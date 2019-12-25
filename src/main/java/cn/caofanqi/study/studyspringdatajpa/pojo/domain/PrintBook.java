package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 纸质书籍
 * @author caofanqi
 */
@Data
@Entity
@Table(name = "jpa_print_book")
public class PrintBook extends Book{

    /**
     * 印刷时间
     */
    private LocalDate printDate;

}
