package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.StudentAgeAndAgeCountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生实体，用于自定义repository
 *
 * @author caofanqi
 */
@Data
@Entity
@Builder
@Table(name = "jpa_student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;


}
