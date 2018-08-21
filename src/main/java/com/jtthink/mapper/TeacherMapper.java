package com.jtthink.mapper;

import com.jtthink.entity.ExamDetail;
import com.jtthink.entity.Switch;
import com.jtthink.entity.UserInfo;
import com.jtthink.util.DBHelper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @UpdateProvider(type= DBHelper.class, method="update_exam_subject_init")
    public int updateExamSubject();

    @UpdateProvider(type= DBHelper.class, method="update_exam_subject_init1")
    public int updateExamSubject1(@Param("subject_backup1") String subject_backup1);

    @UpdateProvider(type= DBHelper.class, method="update_exam_detail_init")
    public int updateExamDetail(@Param("detail_parent") String detail_parent);

    @UpdateProvider(type= DBHelper.class, method="update_exam_switch_init")
    public int updateExamSwitch(@Param("switch_state") String switch_state, @Param("switch_id") String switch_id);

    @SelectProvider(type= DBHelper.class,method="select_exam_detail")
    public List<ExamDetail> selectDetail(@Param("detail_parent") String detail_parent);

    @SelectProvider(type= DBHelper.class,method="select_exam_cuit")
    public List<Switch> selectSwitch();

}
