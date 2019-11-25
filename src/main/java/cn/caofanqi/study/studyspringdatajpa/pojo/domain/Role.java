package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.RoleNameAndAdminCountAndAgeAvgDTO;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.RoleCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * role实体
 *
 * @author caofanqi
 */
@NamedNativeQueries({
        @NamedNativeQuery(name = "Role.findRoleNameAndAdminCountAndAgeAvgDTOWithSQL",
                query = "SELECT r.role_name AS roleName,COUNT(*) AS adminCount,AVG(a.age) AS ageAvg FROM cfq_jpa_role r INNER JOIN cfq_jpa_admin a ON r.id = a.role_id GROUP BY r.role_name",
                resultSetMapping = "roleNameAndAdminCountAndAgeAvgDTO")})
@SqlResultSetMapping(
        name = "roleNameAndAdminCountAndAgeAvgDTO",
        classes = @ConstructorResult(targetClass = RoleNameAndAdminCountAndAgeAvgDTO.class,
                columns = {
                        @ColumnResult(name = "roleName", type = String.class),
                        @ColumnResult(name = "adminCount", type = Long.class),
                        @ColumnResult(name = "ageAvg", type = Double.class)
                }))
@Data
@Entity
@Builder
@Table(name = "jpa_role")
@NoArgsConstructor
@AllArgsConstructor
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleCode roleCode;

    @OneToMany(mappedBy = "role")
    private List<Admin> admins;


}
