package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Address;

/**
 * 使用默认接口方法返回全地址拼接路径投影
 * @author caofanqi
 */
public interface AdminUsernameAndFullAddressWithJava8 {

    String getUsername();

    /**
     * 要提供address的get方法供使用。
     */
    Address getAddress();

    default String getFullAddress() {
        return getAddress().getProvince().concat(" ").concat(getAddress().getCity()).concat(" ").concat(getAddress().getCounty())
                .concat(" ").concat(getAddress().getDetailedAddress());
    }

}