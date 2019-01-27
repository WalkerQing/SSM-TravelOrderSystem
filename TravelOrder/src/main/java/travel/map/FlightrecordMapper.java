package travel.map;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import travel.bean.Flightrecord;

public interface FlightrecordMapper {
	//ͨ���û�id��ѯ�û�ԤԼ�ĺ���
	List<Flightrecord> selectFlightRecordByCustId(Integer custId);
	
	//ͨ���û�id��ѯ�û��ѳ��еĺ���
	List<Flightrecord> selectPathRecordByCustId(Integer custId);
	
	int updateFlightStatusByRecordId(@Param("id") Integer id,@Param("flight_id") Integer flightId);
	
    int insert(Flightrecord record);

    int insertSelective(Flightrecord record);
}