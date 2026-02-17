package testsuite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import services.TestAuthService;
import services.TestBookTableService;
import services.TestChefService;
import services.TestOrderService;

@Suite
@SelectClasses({
        TestChefService.class,
        TestOrderService.class,
        TestAuthService.class,
        TestBookTableService.class
})
public class AllTestsSuite {
}
