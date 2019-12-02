package cn.caofanqi.study.studyspringdatajpa.repository.custom.impl;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Student;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.StudentAgeAndAgeCountDTO;
import cn.caofanqi.study.studyspringdatajpa.repository.custom.StudentRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.jdbc.object.SqlQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.transform.Transformer;
import java.util.List;
import java.util.Optional;


/**
 * 自定义实现repository功能
 *
 * @author caofnqi
 */
@Slf4j
public class StudentRepositoryCustomImpl<T,ID> implements StudentRepositoryCustom<T,ID> {


    /**
     * 通过@PersistenceContext注解可以获得entityManager
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<T> findById(ID id) {
        log.info("自定义的findById");
        T t = (T) entityManager.find(Student.class, id);
        return Optional.of(t);
    }



    @Override
    public List<StudentAgeAndAgeCountDTO> findCountGroupByAge(){

        /*
         *sql可以是更复杂的
         */
        String sql = "SELECT age,count(*) AS ageCount FROM cfq_jpa_student GROUP BY age ";

        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQuery.class)
                //设置类型
                .addScalar("age", StandardBasicTypes.INTEGER)
                .addScalar("ageCount",StandardBasicTypes.LONG)
                //设置返回bean
                .setResultTransformer(Transformers.aliasToBean(StudentAgeAndAgeCountDTO.class));
        return nativeQuery.getResultList();

    }

}
