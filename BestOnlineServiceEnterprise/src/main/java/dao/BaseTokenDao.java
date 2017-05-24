package dao;

import models.Token;

/**
 * 13.05.2017
 * BaseTokenDao
 * @author Aivar Yusupov
 * @version v0.1 /
 */

public interface BaseTokenDao extends BaseDao<Token> {
	
	Token findByToken(String token);
	
}