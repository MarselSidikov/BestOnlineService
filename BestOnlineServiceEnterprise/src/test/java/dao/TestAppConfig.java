package dao;

import org.springframework.context.annotation.Configuration;

/**
 * 21.05.2017
 * TestAppConfig @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
@Configuration
public class TestAppConfig {

    public FilmsNamedJdbcTemplateDaoImpl filmsDaoIml(){
        return new FilmsNamedJdbcTemplateDaoImpl();
    }
}
