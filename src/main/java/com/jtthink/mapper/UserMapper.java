package com.jtthink.mapper;


import com.jtthink.entity.UserInfo;
import com.jtthink.util.DBHelper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {


    @SelectProvider(type= DBHelper.class,method="select_users_bynpt")
    public UserInfo selectUser(UserInfo userInfo);


    /* ====下面为参考====
    //查询
    @Select("select user_name, user_pass from exam_user where user_name=#{user_name}")
    public String getUserById(@Param("user_name") String user_name);

    //插入
    @InsertProvider(type= DBHelper.class, method="insert_users_login")
    @SelectKey(keyProperty = "news_id", before = false, resultType = int.class, statement = "select LAST_INSERT_ID()")
    public int addNews(UserInfo userInfo);

    //更新
    @UpdateProvider(type= DBHelper.class, method="update_users_coin")
    public int updateUserCoin(@Param("user_name") String userName, @Param("coin_value") int coinValue);
    */


}
