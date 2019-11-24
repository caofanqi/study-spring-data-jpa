package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;


import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Address;
import lombok.Value;

/**
 * 使用DTO的方式返回用户名，需要构造函数，我们使用lombok的@Value方法来简化代码
 * @author caofanqi
 */
@Value
public class AdminUsernameAndAddressDTO {

    private String username;

    private Address address;

}