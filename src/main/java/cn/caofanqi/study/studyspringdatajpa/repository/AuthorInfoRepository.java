package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.AuthorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 作者详情持久层
 *
 * @author caofanqi
 */
public interface AuthorInfoRepository extends JpaRepository<AuthorInfo,Long> {
}
