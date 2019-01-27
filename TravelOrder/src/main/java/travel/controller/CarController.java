package travel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;

import travel.bean.Cars;
import travel.bean.Customers;
import travel.bean.Flightrecord;
import travel.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController {
	@Autowired
	CarService carService;
	
	//����ԤԼ������
	@RequestMapping("/index")
	public String index() {
		return "CarOrder";
	}
	//����ԤԼ��¼����
	@RequestMapping("/record")
	public String record() {
		return "CarRecord";
	}
	@RequestMapping("/cars.json")
	@ResponseJSONP
	public List<Cars> getAllCarsJson(){
		return carService.getAllCars();
	}
	@RequestMapping("/searchcar.json")
	@ResponseJSONP
	public List<Cars> getPathRecordJson(HttpServletRequest request){
		
		String location=request.getParameter("location");
		return carService.searchCarByLocation(location);
	}
	@RequestMapping("/record.json")
	@ResponseJSONP
	public List<Cars> getCarRecordByCustId(HttpServletRequest request){
		Integer custId=Integer.valueOf(request.getParameter("custId"));
		return carService.getCarRecordByCustId(custId);
	}
	
	
	@RequestMapping("/orderCar")
	public String orderFlight(HttpServletRequest request,HttpSession session) {
		//����idͨ�������õ�
		Integer carId=Integer.valueOf(request.getParameter("car_id"));
		//�û�idͨ��session���
		Integer custId=((Customers)session.getAttribute("cust")).getCustId();
		
		carService.orderCar(carId, custId);
		
		return "CarOrder";
	}
	
	//����ȡ��ԤԼ������
	@RequestMapping("/cancelCar")
	public String cancelFlight(HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		Integer carId=Integer.valueOf(request.getParameter("carId"));
		carService.cancelCar(id,carId);
		return "CarRecord";
	}
}
