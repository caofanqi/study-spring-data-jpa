package cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections;

import lombok.Value;

/**
 * 角色名称，对应的管理管个数，管理员平均年龄
 * @author caofanqi
 */
@Value
public class RoleNameAndAdminCountAndAgeAvgDTO {

    private String roleName;

    private Long adminCount;

    private Double ageAvg;

}
