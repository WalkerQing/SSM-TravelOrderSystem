package travel.map;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import travel.bean.Carrecord;
import travel.bean.Cars;

public interface CarrecordMapper {
	//ͨ���û�id��ѯ�û�ԤԼ�ĳ��ļ�¼
	List<Cars> selectCarRecordByCustId(Integer custId);
	//ȡ��ԤԼ��
	int updateCarStatusByRecordId(@Param("id") Integer id,@Param("car_id") Integer carId);
	
    int insert(Carrecord record);

    int insertSelective(Carrecord record);
}