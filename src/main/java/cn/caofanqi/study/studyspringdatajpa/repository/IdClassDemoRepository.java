package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.IdClassDemo;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.UnionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdClassDemoRepository extends JpaRepository<IdClassDemo, UnionKey> {
}
