package ape.test;

import com.ckzcb.ape.user.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName SysUserService
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/6/4 16:17
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserServiceTest {
    @Resource
    private UserServiceImpl userService;

    @Test
    public void test() {
        userService.test("2");
    }

}
