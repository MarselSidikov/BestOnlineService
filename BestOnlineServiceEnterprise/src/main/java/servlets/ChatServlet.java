package servlets;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import services.ChatService;

import services.ChatService;
import services.impl.ChatServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatServlet extends HttpServlet{

    private ChatService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        GenericXmlApplicationContext context = new  GenericXmlApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.addActiveProfile("dev");
        context.load("ru.itis\\spring\\context.xml");
        context.refresh();
        messageService = context.getBean(ChatService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/chat.jsp").forward(request,response);
    }
}