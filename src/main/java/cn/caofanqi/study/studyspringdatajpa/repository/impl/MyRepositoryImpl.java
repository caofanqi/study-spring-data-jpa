package cn.caofanqi.study.studyspringdatajpa.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * 自定义base Repository
 *
 * @author caofanqi
 */
@Slf4j
public class MyRepositoryImpl<T,ID> extends SimpleJpaRepository<T,ID> {

    private final EntityManager entityManager;

    MyRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public <S extends T> S save(S entity) {
        S save = super.save(entity);
        log.info("保存了：{}",save);
        return  save;
    }

}
