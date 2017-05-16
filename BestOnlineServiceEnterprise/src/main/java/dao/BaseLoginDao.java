package dao;

/**
 * 13.05.2017
 * BaseLoginDao
 * @author Aivar Yusupov
 * @version v0.1 /
 */

public interface BaseLoginDao extends BaseDao<Login> {
	
	T findByLogin(String login);
	
}