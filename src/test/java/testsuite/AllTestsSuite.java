package testsuite;

import org.junit.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import services.*;

@Suite
@SelectClasses({
        TestChefService.class,
        TestOrderService.class,
        TestAuthService.class,
        TestBookTableService.class,
        TestWaiterService.class,
        TestManagerService.class,
        TestCustomerService.class,
        TestReceptionistService.class,
        TestMenuService.class

})
public class AllTestsSuite {
}
