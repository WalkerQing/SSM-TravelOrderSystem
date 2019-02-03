package travel.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;


import travel.bean.Customers;
import travel.bean.Flightrecord;
import travel.bean.Flights;
import travel.service.FlightService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	FlightService flightservice;
	
	//������
	@RequestMapping("/index")
	public String index() {
		return "Home";
	}
	
	//����ԤԼ��¼����
	@RequestMapping("/record")
	public String record() {
		return "FlightRecord";
	}
	
	//�г̼�¼����
	@RequestMapping("/pathrecord")
	public String path() {
		return "PathRecord";
	}
	
	//��ȡȫ�������json����
	@RequestMapping("/flight.json")
	@ResponseJSONP
	public List<Flights> getAllFlightsJson() {		
		return flightservice.getAllFlights();
	}
	
	//ͨ�������յ��ѯ�����json����
	@RequestMapping("/searchflight.json")
	@ResponseJSONP
	public List<Flights> getSearchFlightsJson(HttpServletRequest request) {	
		String start=request.getParameter("start");
		String end=request.getParameter("end");		
		return flightservice.searchFlightsByStartAndEnd(start, end);
	}
	
	//����Ԥ����������
	@RequestMapping("/orderFlight")
	public String orderFlight(HttpServletRequest request,HttpSession session) {
		//����idͨ�������õ�
		Integer flightId=Integer.valueOf(request.getParameter("flight_id"));
		//�û�idͨ��session���
		Integer custId=((Customers)session.getAttribute("cust")).getCustId();
		
		flightservice.orderFlight(flightId, custId);
		
		return "Home";
	}
	
	//����ȡ����������
	@RequestMapping("/cancelFlight")
	public String cancelFlight(HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		Integer flightId=Integer.valueOf(request.getParameter("flightId"));

		flightservice.cancelFlight(id,flightId);
		return "FlightRecord";
	}
	//�����������
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String createFlight(Flights flights) {		
		flightservice.createFlight(flights);
		
		return "Home";
	}
	
	
	//�����������
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateFlight(Flights flights) {
		
		flightservice.updateFlightByFlightNum(flights);
		
		return "Home";
	}
	
	//����ɾ������
	@RequestMapping(value="/delete")
	public String deleteFlight(HttpServletRequest request) {
		Integer flightId=Integer.valueOf(request.getParameter("flight_id"));
		flightservice.deleteFlightByFlightId(flightId);
		
		return "Home";
	}
	
	//����Ԥ�������¼��json
	@RequestMapping("/record.json")
	@ResponseJSONP
	public List<Flightrecord> getFlightRecordJson(HttpServletRequest request){
		Integer custId=Integer.valueOf(request.getParameter("custId"));
		return flightservice.getFlightRecordByCustId(custId);
	}
	
	//�����г̼�¼��json
	@RequestMapping("/pathrecord.json")
	@ResponseJSONP
	public List<Flightrecord> getPathRecordJson(HttpServletRequest request){
		
		Integer custId=Integer.valueOf(request.getParameter("custId"));
		return flightservice.getPathRecordByCustId(custId);
	}
}
