package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.*;

import java.io.Serializable;

/**
 * 联合主键类，要有几点要求
 * 1、必须实现java.io.Serializable接口；
 * 2、必须有默认的无参构造函数；
 * 3、必须覆盖equals和hashCode方法。
 *
 * @author caofanqi
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UnionKey implements Serializable {

    private String idOne;

    private String idTwo;

}
