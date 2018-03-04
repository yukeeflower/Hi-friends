package com.ch.service;

import com.ch.dao.TipsDAO;
import com.ch.dao.TokenDAO;
import com.ch.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by apple on 2018/3/4.
 */
@Service
public class TokenService {
    @Autowired
    private  TokenDAO tokenDAO;

    public Token getTokenByTicket(String token){
        return tokenDAO.selectByToken(token);
    }
}
