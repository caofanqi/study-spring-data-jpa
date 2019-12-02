package cn.caofanqi.study.studyspringdatajpa.repository.custom;


import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.StudentAgeAndAgeCountDTO;

import java.util.List;
import java.util.Optional;

/**
 * 自定义student功能
 *
 * @author caofanqi
 */
public interface StudentRepositoryCustom<T,ID> {

    Optional<T> findById(ID id);

    List<StudentAgeAndAgeCountDTO> findCountGroupByAge();

}
