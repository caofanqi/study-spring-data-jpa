package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Address;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Admin;
import org.springframework.stereotype.Component;

/**
 * @author caofanqi
 */
@Component
public class MyAdminBean {

    public String getFullAddress(Admin admin) {
        Address address = admin.getAddress();
        return address.getProvince().concat(" ").concat(address.getCity()).concat(" ").concat(address.getCounty())
                .concat(" ").concat(address.getDetailedAddress());
    }

}