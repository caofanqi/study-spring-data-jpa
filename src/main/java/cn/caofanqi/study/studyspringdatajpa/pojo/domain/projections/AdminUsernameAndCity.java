package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

/**
 * 想返回 username 和 所在城市的投影
 * @author caofanqi
 */
public interface AdminUsernameAndCity {

    String getUsername();

    AddressCity getAddress();

}