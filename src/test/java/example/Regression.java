package example;

import example.category.RegressionCat;
import example.service01.CreateUser;
import example.service01.GetUser;
import example.service01.GetUsersOnPage;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.experimental.categories.Category;

@RunWith(Categories.class)
@Categories.IncludeCategory(RegressionCat.class)
@Suite.SuiteClasses({
        CreateUser.class,
        GetUser.class,
        GetUsersOnPage.class
})

public class Regression {
}
