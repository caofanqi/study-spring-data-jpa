package cn.caofanqi.study.studyspringdatajpa.controller;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.User;
import cn.caofanqi.study.studyspringdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author caofanqi
 */
@RestController
@RequestMapping("/user")
public class UserController {


    /**
     * 这里会直接调用User对应的Repository来进行findById查询。
     *  需要Repository实现CrudRepository才有资格被发现可以进行转换
     */
    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id")User user){
        return user;
    }


    @Resource
    private UserRepository userRepository;

    /**
     * 参数page：默认为0。
     * 参数size：默认为20。
     * 参数sort：排序按property,property(,ASC|DESC)的方式来填写，默认为升序，如果想要属性有不同的排序方向，要用多个sort参数
     */
    @GetMapping
    public Page<User> listUser(Pageable pageable){
        System.out.println("page：" + pageable.getPageNumber());
        System.out.println("size：" + pageable.getPageSize());
        System.out.println("sort：" + pageable.getSort());
        return userRepository.findAll(pageable);
    }


    @GetMapping("/sort")
    public String sort(Sort sort){
        System.out.println("sort：" + sort);
        return "sort";
    }


    /**
     *  如果有多个分页或排序时（多个表），用@Qualifier来解决，请求参数要以${qualifier}_开头
     */
    @GetMapping("/multi/pageable")
    public String multiPageable(@Qualifier("user") Pageable userPageable, @Qualifier("book") Pageable bookPageable){
        System.out.println("userPageable page：" + userPageable.getPageNumber());
        System.out.println("userPageable size：" + userPageable.getPageSize());
        System.out.println("userPageable sort：" + userPageable.getSort());

        System.out.println("bookPageable page：" + bookPageable.getPageNumber());
        System.out.println("bookPageable size：" + bookPageable.getPageSize());
        System.out.println("bookPageable sort：" + bookPageable.getSort());
        return "test";
    }

    /**
     * 使用@PageableDefault自定义pageable
     */
    @GetMapping("pageable/default")
    public String pageableDefault(@PageableDefault(page = 2,size = 20,sort = {"username","age"},direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println("page：" + pageable.getPageNumber());
        System.out.println("size：" + pageable.getPageSize());
        System.out.println("sort：" + pageable.getSort());
        return "PageableDefault";
    }

}
