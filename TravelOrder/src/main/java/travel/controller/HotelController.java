package travel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;

import travel.bean.Cars;
import travel.bean.Customers;
import travel.bean.Flightrecord;
import travel.bean.Hotels;
import travel.service.HotelService;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@RequestMapping("/index")
	public String Index() {
		return "HotelOrder";
	}
	//�õ�ԤԼ��¼����
	@RequestMapping("/record")
	public String record() {
		return "HotelRecord";
	}
	@RequestMapping("/hotels.json")
	@ResponseJSONP
	List<Hotels> getAllHotels(){
		return hotelService.getAllHotels();
	}

	@RequestMapping("/searchhotel.json")
	@ResponseJSONP
	public List<Hotels> getPathRecordJson(HttpServletRequest request){
		
		String location=request.getParameter("location");
		return hotelService.searchHotelsByLocation(location);
	}
	
	@RequestMapping("/record.json")
	@ResponseJSONP
	public List<Hotels> getCarRecordByCustId(HttpServletRequest request){
		Integer custId=Integer.valueOf(request.getParameter("custId"));
		return hotelService.getHotelsRecordByCustId(custId);
	}
	
	@RequestMapping("/orderHotel")
	public String orderFlight(HttpServletRequest request,HttpSession session) {
		//����idͨ�������õ�
		Integer hotelId=Integer.valueOf(request.getParameter("hotel_id"));
		//�û�idͨ��session���
		Integer custId=((Customers)session.getAttribute("cust")).getCustId();
		
		hotelService.orderHotel(hotelId, custId);
		
		return "HotelOrder";
	}
	
	//����ȡ���õ�����
	@RequestMapping("/cancelHotel")
	public String cancelHotel(HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		Integer hotelId=Integer.valueOf(request.getParameter("hotelId"));
		hotelService.cancelHotel(id,hotelId);
		return "HotelRecord";
	}
}
