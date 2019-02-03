package travel.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import travel.bean.Customers;
import travel.service.CustService;

@RequestMapping("/Cust")
@Controller
public class CustController {	
	@Autowired
	CustService custservice;
	
	@RequestMapping(value="/Regist",method=RequestMethod.POST)
	public String Regist(Customers customers) {			
		custservice.Regist(customers);
		return "Login";
	}
	
	//��¼
	@RequestMapping(value="/Login",method=RequestMethod.POST)
	public String Login(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) {
		//ȡ�ñ����û�������
		String username=request.getParameter("username");
		String password=request.getParameter("password");		
		//�����û��������ѯ�û�
		Customers customer=custservice.getCustomerByUsernameAndPassword(username, password);
		//������Ϊ�գ������ڵ�¼����
		if(customer==null) {			
			return "Login";
		}
		//�����û����ݱ������session������������
		else {			
			httpSession.setAttribute("cust",customer);			
			return "Home";
		}
	}
	
}
