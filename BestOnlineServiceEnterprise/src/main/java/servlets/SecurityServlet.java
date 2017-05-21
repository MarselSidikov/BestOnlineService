package servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.SecurityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 21.05.2017
 * ${NAME}
 *
 * @author Aivar Yusupov
 * @version v0.1 /
 */
public class SecurityServlet extends HttpServlet {

    private SecurityService securityService;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis\\spring\\posterContext.xml");
        securityService = context.getBean(SecurityService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("security", securityService.findAll());
        request.getRequestDispatcher("/jsp/security.jsp").forward(request,response);
    }
}
