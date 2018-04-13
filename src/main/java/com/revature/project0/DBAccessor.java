package com.revature.project0;

public class DBAccessor implements DBAccess{
	private static DBAccessor instance;
	
	private DBAccessor() {
		// TODO Auto-generated constructor stub
	}
	
	public static DBAccessor getInstance() {
		if (instance == null) {
			instance = new DBAccessor();
		}
		return instance;
	}
	
	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		
		return false;
	}
}
