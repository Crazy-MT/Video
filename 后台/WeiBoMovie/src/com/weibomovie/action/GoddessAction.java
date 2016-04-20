package com.weibomovie.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.weibomovie.dao.GoddessDao;
import com.weibomovie.model.Goddess;

public class GoddessAction {

	public void add(Goddess goddess) throws Exception{
		GoddessDao dao=new GoddessDao();
		goddess.setSex(1);
		goddess.setCreate_user("ADMIN");
		goddess.setUpdate_user("ADMIN");
		goddess.setIsdel(0);
		dao.addGoddess(goddess);
	}
	
	public Goddess get(Integer id) throws SQLException{
		GoddessDao dao=new GoddessDao();
		return dao.get(id);
	}
	
	public void edit(Goddess goddess) throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.updateGoddess(goddess);
	}
	public void del(Integer id) throws SQLException{
		GoddessDao dao=new GoddessDao();
		dao.delGoddess(id);
	}
	
	public List<Goddess>  query() throws Exception{
		GoddessDao dao=new GoddessDao();
		return dao.query();
	}
	public List<Goddess> query(List<Map<String, Object>> params) throws Exception{
		GoddessDao dao=new GoddessDao();
		return dao.query(params);
	}
}
