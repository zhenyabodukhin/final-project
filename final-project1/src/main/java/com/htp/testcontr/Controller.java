package com.htp.testcontr;

import com.htp.dao.UserRepository;
import com.htp.dao.UserRepositoryImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends  HttpServlet {

    private UserRepository repository = new UserRepositoryImpl();

    public Controller() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process (HttpServletRequest req, HttpServletResponse resp) {
        String queryString = req.getQueryString();
        List<String> params = Arrays.asList(queryString.split("&"));
        Long userID = 0L;
        for (String param : params) {
            String[] split = param.split("=");
            userID = Long.valueOf(split[1]);

        }



        System.out.println(repository.findAll());
    }


}
