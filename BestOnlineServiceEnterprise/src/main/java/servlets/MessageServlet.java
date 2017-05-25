package servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import services.MessageService;
import services.impl.MessageServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageServlet extends HttpServlet{

    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        GenericXmlApplicationContext context = new  GenericXmlApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.addActiveProfile("dev");
        context.load("ru.itis\\spring\\context.xml");
        context.refresh();
        messageService = context.getBean(MessageService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message",messageService.findAll());
        request.getRequestDispatcher("/jsp/message.jsp").forward(request,response);
    }
}
