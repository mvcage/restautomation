package example;

import example.service01.CreateUser;
import example.service01.GetUser;
import example.service01.GetUsersOnPage;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateUser.class,
        GetUser.class,
        GetUsersOnPage.class
})
public class Service01Tests {
}
