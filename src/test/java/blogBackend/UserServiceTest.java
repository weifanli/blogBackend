package blogBackend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liweifan.Application;
import com.liweifan.modules.sys.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Test
	public void test1(){
		userService.queryLoginUserByUserName("admin");
	}
}
