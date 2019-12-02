package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entityManager使用的结果映射，需要一个无参构造函数与set方法，这一点与投影不一样
 * @author caofanqi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAgeAndAgeCountDTO {

    private Integer age;

    private Long ageCount;

}
