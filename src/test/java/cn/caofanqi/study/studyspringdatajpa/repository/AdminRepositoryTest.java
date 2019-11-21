package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Address;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Admin;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Role;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.AdminProjections;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.UsernameDTO;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.RoleCode;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.List;

@Transactional
@SpringBootTest
class AdminRepositoryTest {

    @Resource
    private AdminRepository adminRepository;

    @Resource
    private RoleRepository roleRepository;


    @BeforeEach
    void setup(){

        Role superRole = Role.builder().roleName("超级管理员").roleCode(RoleCode.SUPER).build();
        Role generalRole = Role.builder().roleName("普通管理员").roleCode(RoleCode.GENERAL).build();

        Address address1 = Address.builder().province("黑龙江省").city("七台河市").county("桃山区").detailedAddress("xxx小区xxx号楼xxx单元xxx室").zipCode("154600").build();
        Address address2 = Address.builder().province("黑龙江省").city("哈尔滨市").county("南岗区").detailedAddress("xxx小区xxx号楼xxx单元xxx室").zipCode("150000").build();
        Address address3 =  Address.builder().province("山东省").city("济宁市").county("邹城市").detailedAddress("xxx小区xxx号楼xxx单元xxx室").zipCode("273500").build();
        Address address4 = Address.builder().province("陕西省").city("西安市").county("雁塔区").detailedAddress("xxx小区xxx号楼xxx单元xxx室").zipCode("710000").build();
        Address address5 = Address.builder().province("浙江省").city("杭州市").county("滨江区").detailedAddress("xxx小区xxx号楼xxx单元xxx室").zipCode("310000").build();


        Admin admin01 = Admin.builder().username("admin01").password("password01").address(address1).role(superRole).createTime(LocalDate.of(2019, 11, 11)).build();
        Admin admin02 = Admin.builder().username("admin02").password("password02").address(address2).role(superRole).createTime(LocalDate.of(2019, 11, 21)).build();
        Admin admin03 = Admin.builder().username("admin03").password("password03").address(address3).role(generalRole).createTime(LocalDate.of(2019, 11, 21)).build();
        Admin admin04 = Admin.builder().username("admin04").password("password04").address(address4).role(generalRole).createTime(LocalDate.of(2019, 11, 21)).build();
        Admin admin05 = Admin.builder().username("admin05").password("password05").address(address5).role(generalRole).createTime(LocalDate.of(2019, 11, 21)).build();

        roleRepository.saveAll(Lists.newArrayList(superRole,generalRole));
        adminRepository.saveAll(Lists.newArrayList(admin01,admin02,admin03,admin04,admin05));

    }

    @Test
    void findByCreateTime() {
        List<UsernameDTO> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
//        list.forEach(u -> System.out.println(u.getHello("sss")));
        list.forEach(u -> System.out.println(u.getUsername()));
    }


    @Test
    void findByCreateTime2(){
        List<Admin> list1 = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 11), Admin.class);
        list1.forEach( System.out::println);

        System.out.println("===================");

        List<UsernameDTO> list2 = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21), UsernameDTO.class);
        list2.forEach( System.out::println);
    }


}