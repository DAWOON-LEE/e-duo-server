package com.educator.eduo.auth.model.service;

import com.educator.eduo.auth.model.entity.Token;
import java.util.Optional;

public interface TokenService {

    Optional<Token> findTokenByUserId(String userId);

    Optional<Token> findTokenByRefreshToken(String refreshToken);

    boolean registerToken(Token token);

    boolean updateTokenByUserId(Token newToken);

    boolean deleteTokenByUserId(String userId);

}