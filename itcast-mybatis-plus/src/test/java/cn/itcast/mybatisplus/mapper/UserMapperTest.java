package cn.itcast.mybatisplus.mapper;

import cn.itcast.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectByLike(){
        QueryWrapper queryWrapper = new QueryWrapper(new User());//查询名字中包含“o”的用户
        queryWrapper.like("name", "o");
        List<User> users = this.userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectByLe(){
        QueryWrapper queryWrapper = new QueryWrapper(new User());// 查询年龄小于等于20的用户
        queryWrapper.le("age", 20);
        List<User> users = this.userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setAge(25);
        user.setEmail("zhangsan@qq.com");
        user.setName("zhangsan");
        int count = this.userMapper.insert(user);
        System.out.println("新增数据成功! count => " + count);
    }

    @Test
    public void testDelete(){
        this.userMapper.deleteById(7L);
        System.out.println("删除成功!");
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(6L);
        user.setName("lisi");
        this.userMapper.updateById(user);
        System.out.println("修改成功!");
    }

    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1, 2);
        IPage<User> userIPage = this.userMapper.selectPage(page, null);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        List<User> records = userIPage.getRecords();
        for (User user : records) {
            System.out.println(user);
        }
    }

}
