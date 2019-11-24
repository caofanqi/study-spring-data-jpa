package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Address;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Admin;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Role;
import cn.caofanqi.study.studyspringdatajpa.pojo.domain.projections.*;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.RoleCode;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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


        Admin admin01 = Admin.builder().username("admin01").password("password01").address(address1).role(superRole).phone("111").age(23).createTime(LocalDate.of(2019, 11, 11)).build();
        Admin admin02 = Admin.builder().username("admin02").password("password02").address(address2).role(superRole).phone("222").age(24).createTime(LocalDate.of(2019, 11, 21)).build();
        Admin admin03 = Admin.builder().username("admin03").password("password03").address(address3).role(generalRole).phone("333").age(25).createTime(LocalDate.of(2019, 11, 21)).build();
        Admin admin04 = Admin.builder().username("admin04").password("password04").address(address4).role(generalRole).phone("444").age(26).createTime(LocalDate.of(2019, 11, 21)).build();
        Admin admin05 = Admin.builder().username("admin05").password("password05").address(address5).role(generalRole).phone("555").age(27).createTime(LocalDate.of(2019, 11, 21)).build();

        roleRepository.saveAll(Lists.newArrayList(superRole,generalRole));
        adminRepository.saveAll(Lists.newArrayList(admin01,admin02,admin03,admin04,admin05));

    }

    @Test
    void findByCreateTime() {
        //1、返回实体
//        List<Admin> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
//        list.forEach(u -> System.out.println(u.getUsername()));
        //2、只想返回username
//        List<UsernameOnly> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
//        list.forEach(u -> System.out.println(u.getUsername()));
        //3、只想返回username属性和address属性中的city属性
//        List<AdminUsernameAndCity> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
//        list.forEach(u -> System.out.println(u.getUsername() + " : " + u.getAddress().getCity()));
        //4、用户名和地址拼接投影
//        List<AdminUsernameAndFullAddress> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
//        list.forEach(u -> System.out.println(u.getUsername() + " : " + u.getFullAddress()));
        //5、使用java8默认接口方法全地址拼接投影
//        List<AdminUsernameAndFullAddressWithJava8> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
//        list.forEach(u -> System.out.println(u.getUsername() + " : " + u.getFullAddress()));
        //6、使用Spring Bean的方式返回全地址拼接投影
//        List<AdminUsernameAndFullAddressWithSpringBean> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
//        list.forEach(u -> System.out.println(u.getUsername() + " : " + u.getFullAddress()));
        //7、spel使用方法中的参数值
//        List<PrefixUsername> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
//        list.forEach(u -> System.out.println(u.getPrefixUsername("hello")));
        //8、使用DTO的投影
//        List<UsernameDTO> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
//        list.forEach(u -> System.out.println(u.getUsername()));
        //9、使用DTO返回username和address
        List<AdminUsernameAndAddressDTO> list = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21));
        list.forEach(u -> System.out.println(u.getUsername()+ " : " +  u.getAddress()));

    }


    @Test
    void findByCreateTime2(){
        //返回实体Admin
        List<Admin> list1 = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 11), Admin.class);
        list1.forEach( System.out::println);

        System.out.println("===================");

        //返回接口投影
        List<UsernameOnly> list2 = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 11), UsernameOnly.class);
        list2.forEach(u -> System.out.println(u.getUsername()));

        System.out.println("===================");

        //返回DTO投影
        List<UsernameDTO> list3 = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21), UsernameDTO.class);
        list3.forEach(u -> System.out.println(u.getUsername()));
    }


    @Test
    void findByPhone(){
        UsernameOnly usernameOnly = adminRepository.findByPhone("111");
        System.out.println(usernameOnly.getUsername());
    }


    @Test
    void findByPhone2(){
        Admin admin = adminRepository.findByPhone("111", Admin.class);
        System.out.println(admin);


        UsernameDTO admin2 = adminRepository.findByPhone("222", UsernameDTO.class);
        System.out.println(admin2);
    }

    @Test
    void findByCreateTimeWithPage(){
        Page<AdminUsernameAndAddressDTO> page = adminRepository.findByCreateTime(LocalDate.of(2019, 11, 21), AdminUsernameAndAddressDTO.class, PageRequest.of(1, 2, Sort.Direction.DESC,"username"));
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumberOfElements());
        System.out.println(page.getContent());
    }


    @Test
    void findRoleNameAndAdminCountAndAgeAvgWithJPQL(){
        List<RoleNameAndAdminCountAndAgeAvg> roleNameAndAdminCountAndAgeAvgWithJPQL = roleRepository.findRoleNameAndAdminCountAndAgeAvgWithJPQL();
        roleNameAndAdminCountAndAgeAvgWithJPQL.forEach(r -> System.out.println(r.getRoleName() + " : " + r.getAdminCount() + " : " + r.getAgeAvg()));
    }

    @Test
    void findRoleNameAndAdminCountAndAgeAvgWithSQL(){
        List<RoleNameAndAdminCountAndAgeAvg> roleNameAndAdminCountAndAgeAvgWithSQL = roleRepository.findRoleNameAndAdminCountAndAgeAvgWithSQL();
        roleNameAndAdminCountAndAgeAvgWithSQL.forEach(r -> System.out.println(r.getRoleName() + " : " + r.getAdminCount() + " : " + r.getAgeAvg()));
    }

    @Test
    void findRoleNameAndAdminCountAndAgeAvgWithDTO(){
        List<RoleNameAndAdminCountAndAgeAvgDTO> roleNameAndAdminCountAndAgeAvgWithDTO = roleRepository.findRoleNameAndAdminCountAndAgeAvgWithDTO();
        roleNameAndAdminCountAndAgeAvgWithDTO.forEach(r -> System.out.println(r.getRoleName() + " : " + r.getAdminCount() + " : " + r.getAgeAvg()));
    }

}