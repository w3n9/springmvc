package spittr.web;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.entity.Spitter;
import spittr.entity.Spittle;
import spittr.mapper.SpitterMapper;
import spittr.mapper.SpittleMapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    SpittleMapper spittleMapper;
    @Autowired
    SpitterMapper spitterMapper;

    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String home(){
        return "home";
    }
}
