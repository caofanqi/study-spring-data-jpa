package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 *  地址对象
 *
 * @author caofanqi
 */
@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String province;

    private String city;

    private String county;

    /**
     * 可嵌入对象中的字段也可以使用@Column进行修饰
     */
    @Column(nullable = false)
    private String detailedAddress;

    private String zipCode;

}
