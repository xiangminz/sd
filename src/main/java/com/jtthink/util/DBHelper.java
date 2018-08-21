package com.jtthink.util;

import org.apache.ibatis.jdbc.SQL;

public class DBHelper {

    //学生端
    //单题逻辑
    //拿到总题数
    public String select_subject_info(){
        return new SQL(){
            {
                SELECT("subject_id", "subject_name", "subject_num", "subject_use",
                        "subject_type",  "subject_backup1", "subject_backup2");
                FROM("exam_subject");
                WHERE("subject_use=1");
            }
        }.toString();
    }

    //拿到具体小题
    public String select_detail_info(){
        return new SQL(){
            {
                SELECT("detail_id", "detail_parent", "subject_name as detail_parent_name",
                        "detail_son", "detail_son_name", "detail_grandson", "detail_grandson_name",
                        "detail_result", "detail_score", "detail_switch_use", "detail_switch_car_id",
                        "detail_step", "detail_backup1", "detail_backup2");
                FROM("exam_subject a, exam_detail b");
                WHERE("a.subject_backup1 = b.detail_parent");
                WHERE("detail_parent = #{detail_parent}");
                WHERE("detail_step = #{detail_step}");
            }
        }.toString();
    }

    //中间题目状态的判定
    public String select_detail_prestate(){
        return new SQL(){
            {
                SELECT("detail_backup1");
                FROM("exam_detail");
                WHERE("detail_parent = #{detail_parent}");
                WHERE("detail_backup2 = #{detail_backup2}");
            }
        }.toString();
    }

    //更新题目状态
    public String update_detail_info(){
        return new SQL(){
            {
                UPDATE("exam_detail");
                SET("detail_backup1=#{detail_backup1}");
                WHERE("detail_parent = #{detail_parent}");
                WHERE("detail_backup2 = #{detail_backup2}");
            }
        }.toString();
    }

    //学生端
    //分组逻辑
    public String select_detail_one(){
        return new SQL(){
            {
                SELECT("detail_id", "detail_parent", "subject_name as detail_parent_name",
                        "detail_son", "detail_son_name", "detail_grandson", "detail_grandson_name",
                        "detail_result", "detail_score", "detail_switch_use", "detail_switch_car_id",
                        "detail_step", "detail_backup1", "detail_backup2");
                FROM("exam_subject a, exam_detail b");
                WHERE("a.subject_backup1 = b.detail_parent");
                WHERE("detail_parent = #{detail_parent}");
                WHERE("detail_son = #{detail_son}");
                GROUP_BY("detail_parent", "detail_son");
            }
        }.toString();
    }







    //教师端查询=================
    public String select_users_bynpt(){ //根据用户名 和 密码 返回
        return new SQL(){
            {
                SELECT("user_name", "user_pass", "user_type");
                FROM("exam_user");
                WHERE("user_name=#{user_name}");
                WHERE("user_pass=#{user_pass}");
                WHERE("user_type=#{user_type}");
            }
        }.toString();
    }

    public String update_exam_subject_init(){
        return new SQL(){
            {
                UPDATE("exam_subject");
                SET("subject_use=0");

            }
        }.toString();
    }

    public String update_exam_subject_init1(){
        return new SQL(){
            {
                UPDATE("exam_subject");
                SET("subject_use=1");
                WHERE("subject_backup1=#{subject_backup1}");
            }
        }.toString();
    }

    public String update_exam_detail_init(){
        return new SQL(){
            {
                UPDATE("exam_detail");
                SET("detail_score=0, detail_backup1=0");
                WHERE("detail_parent=#{detail_parent}");
            }
        }.toString();
    }

    public String update_exam_switch_init(){
        return new SQL(){
            {
                UPDATE("exam_switch");
                SET("switch_state=#{switch_state}");
                WHERE("switch_id=#{switch_id}");
            }
        }.toString();
    }


    /*
    select detail_id, detail_parent, subject_name as detail_parent_name,
        detail_son, detail_son_name, detail_grandson, detail_grandson_name,
        detail_result, detail_score, detail_switch_use, detail_switch_car_id,
        detail_step, detail_backup1, detail_backup2
    from exam_subject a, exam_detail b
    where a.subject_backup1 = b.detail_parent and detail_parent = 1
    GROUP BY detail_parent, detail_son, detail_grandson
    */
    //查询
    public String select_exam_detail(){
        return new SQL(){
            {
                SELECT("detail_id", "detail_parent", "subject_name as detail_parent_name",
                        "detail_son", "detail_son_name", "detail_grandson", "detail_grandson_name",
                        "detail_result", "detail_score", "detail_switch_use", "detail_switch_car_id",
                        "detail_step", "detail_backup1", "detail_backup2");
                FROM("exam_subject a, exam_detail b");
                WHERE("a.subject_backup1 = b.detail_parent");
                WHERE("detail_parent = #{detail_parent}");
                GROUP_BY("detail_parent","detail_son","detail_grandson");
            }
        }.toString();
    }

    public String select_exam_cuit(){
        return new SQL(){
            {
                SELECT("switch_id", "switch_type", "switch_name", "switch_state");
                FROM("exam_switch");
            }
        }.toString();
    }


    /*
    //增加
    public String insert_users_login(){

        SQL sqlBuilder = new SQL();
        sqlBuilder.INSERT_INTO("jt_news")
                .VALUES("news_title,news_content","#{news_title},#{news_content}");
        return sqlBuilder.toString();

    }

    //更新
    public String update_users_coin(){
        return new SQL(){
            {
                UPDATE("user_coin");
                SET("coin=coin-#{coin_value}");
                WHERE("user_name=#{user_name} and (coin - #{coin_value}) >=0");
            }
        }.toString();
    }

    */


}
