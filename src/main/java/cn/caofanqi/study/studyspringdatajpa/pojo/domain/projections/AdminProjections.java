package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Address;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * 存放admin实体的投影
 * @author caofanqi
 */
public class AdminProjections {

    /**
     * 只想返回用户名
     */
    public interface UsernameOnly{
        String getUsername();
    }

    /**
     * 想返回 username 和 所在城市
     */
    public interface AdminSummary{
        String getUsername();
        AddressSummary getAddress();

        interface AddressSummary {
            String getCity();
        }
    }

    public interface FullAddress{
        String getUsername();

        @Value("#{target.address.province + ' ' + target.address.city + ' ' + target.address.county + ' ' + target.address.detailedAddress}")
        String getFullAddress();
    }


    public interface FullAddressJava8{
        String getUsername();
        Address getAddress();

        default String getFullAddress(){
            return getAddress().getProvince().concat(" ").concat(getAddress().getCity()).concat(" ").concat(getAddress().getCounty())
                    .concat(" ").concat(getAddress().getDetailedAddress());
        }

    }


    public interface FullAddressForSpringBean{

        String getUsername();

        @Value("#{@myAdminBean.getFullAddress(target)}")
         String getFullAddress();

    }

    public interface HelloUsername{

        String getUsername();

        @Value("#{args[0] + '' + target.username + '!'}")
        String getHello(String prefix);
    }







}
