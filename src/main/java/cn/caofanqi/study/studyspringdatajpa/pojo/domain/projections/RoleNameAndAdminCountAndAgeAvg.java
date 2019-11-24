package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

/**
 * 角色名称，admin个数count ,admin平均年龄 投影
 * @author caofanqi
 */
public interface RoleNameAndAdminCountAndAgeAvg {

    String getRoleName();

    Long getAdminCount();

    Double getAgeAvg();

}
