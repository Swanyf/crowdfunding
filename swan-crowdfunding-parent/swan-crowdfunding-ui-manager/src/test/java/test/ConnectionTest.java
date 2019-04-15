package test;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swan.crowdfunding.component.mapper.PermissionDOMapper;
import com.swan.crowdfunding.component.mapper.RoleDOMapper;
import com.swan.crowdfunding.component.mapper.UserDOMapper;
import com.swan.crowdfunding.entity.UserDO;

public class ConnectionTest {

    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-tx.xml");
	
	@Test
	public void insertUser() {
	    UserDOMapper userDOMapper = applicationContext.getBean(UserDOMapper.class);
	    for (int i = 2; i <= 250; i++) {
	        UserDO userDO = new UserDO(null, "tom"+i, "4297F44B13955235245B2497399D7A93", "汤姆"+i, "tom"+i+"@crowd.com", null);
            userDOMapper.insert(userDO);
        }
	}
	
	@Test
	public void test() {
	    UserDOMapper bean = applicationContext.getBean(UserDOMapper.class);
	    bean.updateByPrimaryKey(new UserDO(13, "tom", null, "mmm", "tom@qq.com", null));
	}
	
	@Test
	public void test2() {
        PermissionDOMapper bean = applicationContext.getBean(PermissionDOMapper.class);
        System.out.println(bean.selectByPrimaryKey(19));
    }
	
	@Test
	public void test3() {
        RoleDOMapper bean = applicationContext.getBean(RoleDOMapper.class);
        System.out.println(bean.getAssignList(11));
	}
	
	@Test
	public void test4() {
        RoleDOMapper bean = applicationContext.getBean(RoleDOMapper.class);
        bean.getPermissionsByKeyword("").forEach(System.out::println);
	}
	
}
