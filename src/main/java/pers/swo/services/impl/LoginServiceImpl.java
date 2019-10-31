package pers.swo.services.impl;

import pers.swo.dao.LoginDao;
import pers.swo.dao.impl.LoginDaoImpl;
import pers.swo.pojo.User;
import pers.swo.services.LoginService;

public class LoginServiceImpl implements LoginService {
	//创建Dao层过度向
	LoginDao ld=new LoginDaoImpl();
	//校验用户登录信息
	@Override
	public User checkLoginService(String uname, String pwd) {

		return ld.checkLoginDao(uname, pwd);
	}
	//校验Cookie信息
	@Override
	public User checkUidService(String uid) {
		return ld.checkUidDao(uid);
	}
	
}
