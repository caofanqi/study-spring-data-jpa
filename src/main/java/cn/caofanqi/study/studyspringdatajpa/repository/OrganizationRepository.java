package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Organization;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

/**
 * @author caofanqi
 */
public interface OrganizationRepository extends JpaRepositoryImplementation<Organization,Long> {


    Optional<Organization> findOrganizationById(Long id);

    @Query(value = "select o from  Organization o where  o.id = :id ")
    Optional<Organization> selectById(Long id);

    @Modifying
    @Query("update Organization  o set o.name = :name where o.id = :id")
    void  updateNameById1(Long id,String name);

    @Modifying(clearAutomatically = true)
    @Query("update Organization  o set o.name = :name where o.id = :id")
    void  updateNameById2(Long id,String name);


}
