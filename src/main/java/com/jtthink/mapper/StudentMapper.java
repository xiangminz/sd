package com.jtthink.mapper;

import com.jtthink.entity.ExamDetail;
import com.jtthink.entity.Subject;
import com.jtthink.entity.Switch;
import com.jtthink.util.DBHelper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface StudentMapper {

    @SelectProvider(type= DBHelper.class,method="select_subject_info")
    public Subject selectSubjectInfo();

    @SelectProvider(type= DBHelper.class,method="select_detail_info")
    public ExamDetail selectDetailInfo(@Param("detail_parent") String detail_parent, @Param("detail_step") String detail_step);

    @SelectProvider(type= DBHelper.class,method="select_detail_prestate")
    public ExamDetail selectDetailPreInfo(@Param("detail_parent") String detail_parent, @Param("detail_backup2") String detail_backup2);

    @UpdateProvider(type= DBHelper.class, method="update_detail_info")
    public int updateDetailInfo(@Param("detail_backup1") String detail_backup1, @Param("detail_parent") String detail_parent, @Param("detail_backup2") String detail_backup2);


    @SelectProvider(type= DBHelper.class,method="select_detail_one")
    public List<ExamDetail> selectDetailOne(@Param("detail_parent") String detail_parent, @Param("detail_son") String detail_son);
}
