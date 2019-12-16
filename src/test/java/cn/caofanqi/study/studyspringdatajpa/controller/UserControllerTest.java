package cn.caofanqi.study.studyspringdatajpa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    void findUserById() throws Exception {

        String result = this.mockMvc.perform(get("/user/47"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);

    }

    @Test
    void listUser1() throws Exception {
        String result = this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    void listUser2() throws Exception {
        String result = this.mockMvc.perform(get("/user")
                .param("page","2").param("size","2").param("sort","username,asc")
                .param("sort","age,desc"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }



    @Test
    void multiPageable() throws Exception {
         this.mockMvc.perform(get("/user/multi/pageable")
                .param("user_page","2").param("user_size","2").param("user_sort","username,asc").param("user_sort","age,desc")
                .param("book_page","20").param("book_size","20").param("book_sort","bookName").param("book_sort","price,desc"))
                .andExpect(status().isOk());
    }

    @Test
    void pageableDefault() throws Exception {
        this.mockMvc.perform(get("/user/pageable/default"))
                .andExpect(status().isOk());
    }


    /**
     * 测试properties配置文件中的属性
     */
    @Test
    void listUser3() throws Exception {
        String result = this.mockMvc.perform(get("/user")
                .param("prepageIndex","2").param("prepageSize","101").param("pageSort","username,asc"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }



}