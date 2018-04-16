package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class HelloController {

    @RequestMapping(value = "/hello")
    public String helloWorld() {
        return "index";
    }

    /**
     * 把Bean数据数据转换为Json数据.
     *
     * @author YC
     * @create 2018/4/16 14:57.
     */
    @RequestMapping(value = "/test", produces = "application/json")
    public List<User> getJson() {
        User user = new User();
        user.setName("YangChen");
        user.setSex("男");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        return userList;
    }

    /**
     * 把请求的json数据转换为Bean数据.
     *
     * @author YC
     * @create 2018/4/16 14:57.
     */
    @RequestMapping(value = "/test1", method = RequestMethod.POST, consumes = "application/json")
    public User setJson(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getSex());
        return user;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST, consumes = "application/json")
    public Map setJson1(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getSex());
        Map map = new HashMap();
        map.put("map-key", user);
        return map;
    }

    @RequestMapping(value = "/test3", method = RequestMethod.POST, consumes = "application/json")
    public List setJson2(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getSex());
        List list = new ArrayList();
        list.add(user);
        return list;
    }

    /**
     * GET请求拼接的参数会对方法中所有的Bean对象的属性进行赋值,这种操作是自动的.
     *
     * @author YC
     * @create 2018/4/16 15:15.
     */
    @RequestMapping(value = "t1", method = RequestMethod.GET)
    public void t1(User user, User1 user1) {
        System.out.println(user.getName());
        System.out.println(user1.getName());
        System.out.println(user.getSex());
        System.out.println(user1.getSex());
    }

    @RequestMapping(value = "/testStatus1")
    public ResponseEntity<User> testStatus1(User user) {
        user = null;
        HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<User>(user, status);
    }

    @RequestMapping(value = "/testStatus2")
    public ResponseEntity<?> testStatus2(User user) {
        user = null;
        Error error = new Error(4, "Spittle [" + user + "] not found");
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpittleNotFoundException.class)
    public ResponseEntity<Error> spittleNotFound(SpittleNotFoundException e) {
        long spittleId = e.getSpittleId();
        Error error = new Error(4, "Spittle [" + spittleId + "] not found");
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/testStatus3")
    public ResponseEntity<User> testStatus3(User user) throws SpittleNotFoundException {
        user = null;
        if (user == null) {
            throw new SpittleNotFoundException(6);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/testStatus4")
    public User testStatus4(User user) throws SpittleNotFoundException {
        user = null;
        if (user == null) {
            throw new SpittleNotFoundException(6);
        }
        return user;
    }

    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error spittleNotFound1(SpittleNotFoundException e) {
        long spittleId = e.getSpittleId();
        return new Error(4, "Spittle [" + spittleId + "] not found");
    }

    /**
     * 指定状态码为201
     *
     * @author YC
     * @create 2018/4/16 17:29.
     */
    @RequestMapping(value = "/test66")
    @ResponseStatus(HttpStatus.CREATED)
    public User setJsonTest(User user) {
        System.out.println(user.getName());
        System.out.println(user.getSex());
        return user;
    }


}
