package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

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
