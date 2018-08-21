package com.jtthink.service;


import com.jtthink.entity.ExamDetail;
import com.jtthink.entity.QuestionResult;
import com.jtthink.entity.Subject;
import com.jtthink.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentMapper studentMapper;

    private QuestionResult getResult(ExamDetail examDetail, int userAnswer){
        if( examDetail.getDetail_backup1().equals("0") ){
            if( examDetail.getDetail_result() == userAnswer ){
                studentMapper.updateDetailInfo("1", examDetail.getDetail_parent()+"", examDetail.getDetail_backup2());
                return new QuestionResult("0", "答题正确");
            }else{
                studentMapper.updateDetailInfo("2", examDetail.getDetail_parent()+"", examDetail.getDetail_backup2());
                return new QuestionResult("1", "答题错误");
            }
        }else{
            return new QuestionResult("2", "答题顺序错误");
        }
    }

    //答题 按顺序的服务
    public QuestionResult getResultSequence(String QuesKey){

        String[] resultArg = QuesKey.split("-"); //第一个是key，第二个是答题结果
        QuestionResult qr = null;

        //1 查大题表
        Subject subject = studentMapper.selectSubjectInfo();

        //2 拿到小题
        String subjectId = subject.getSubject_backup1();
        String key = resultArg[0];
        ExamDetail examDetail = studentMapper.selectDetailInfo(subjectId, key);

        //3 判定为第一个题目
        if( examDetail.getDetail_backup2().equals("1") ){
            qr = getResult(examDetail, Integer.parseInt(resultArg[1]) );
            return qr;
        }

        //4 最后一题的判定
        if( examDetail.getDetail_backup2().equals(subject.getSubject_backup2()) ){
            qr = getResult(examDetail, Integer.parseInt(resultArg[1]) );
            return qr;
        }

        //5 中间题目的判定
        //本题的上一个题目是否答完，根据 detail_parent 和 detail_backup2 来判定
        String preIndex = (Integer.parseInt(examDetail.getDetail_backup2()) - 1)+"";
        ExamDetail examDetailPre = studentMapper.selectDetailPreInfo(subjectId, preIndex);
        if( examDetailPre.getDetail_backup1().equals("0") ){
            return new QuestionResult("2", "答题顺序错误");
        }

        //本题是否答完
        qr = getResult( examDetail, Integer.parseInt(resultArg[1]) );
        return qr;


        /* 处理逻辑
        * 查询逻辑  1 拿到启用的大题，题目数
        *               查询大题表，拿到题目总数
        *
        *          2 根据大题号和key拿到 题目序号
        *
        *          3 根据序号判定是否为第一个题目，是则可以判定答题（看做过没有），不是则继续判定
        *               拿到key判定题目顺序是否为1，是则答题
        *
        *          4 根据序号判定是否是最后一个题目，是则可以判定答题（看做过没有），不是则继续判定
        *               拿到key判定题目顺序是否为题目数，是则答题
        *
        *          5 中间题目的判定，根据序号，上一个题目是否答完，本题是否未答
        *               是则判定答题（看做过没有），不是则提示答题错误
        * */
    }



    //答题 按分组的服务
    public QuestionResult getResultSequenceGroup(String QuesKey){

        String[] resultArg = QuesKey.split("-"); //第一个是key，第二个是答题结果
        QuestionResult qr = null;

        //1 查大题表
        Subject subject = studentMapper.selectSubjectInfo();

        //2 拿到小题
        String subjectId = subject.getSubject_backup1();
        String key = resultArg[0];
        ExamDetail examDetail = studentMapper.selectDetailInfo(subjectId, key);

        //3 第一组 与 4 最后一组
        if( examDetail.getDetail_son() == 1 || examDetail.getDetail_son() == subject.getSubject_num() ){

            List<ExamDetail> listOne = studentMapper.selectDetailOne(examDetail.getDetail_parent()+"", examDetail.getDetail_son()+"");
            int countOne = 0;
            for(ExamDetail ed : listOne){
                if( ed.getDetail_backup1().equals("1") ){
                    countOne++;
                }
            }
            if( countOne == listOne.size() ){
                return new QuestionResult("2", "答题顺序错误");
            }

            qr = getResult(examDetail, Integer.parseInt(resultArg[1]) );
            return qr;
        }

        //5 中间组 需要查看上一组 和 本组
        //上一题
        List<ExamDetail> listMiddle = studentMapper.selectDetailOne(examDetail.getDetail_parent()+"", (examDetail.getDetail_son()-1)+"");
        for(ExamDetail ed : listMiddle){
            if( ed.getDetail_backup1().equals("0") ){
                return new QuestionResult("2", "答题顺序错误");
            }
        }

        //本题
        List<ExamDetail> listMiddle1 = studentMapper.selectDetailOne(examDetail.getDetail_parent()+"", examDetail.getDetail_son()+"");
        int countMiddle = 0;
        for(ExamDetail ed : listMiddle1){
            if( ed.getDetail_backup1().equals("1") ){
                countMiddle++;
            }
        }
        if( countMiddle == listMiddle1.size() ){
            return new QuestionResult("2", "答题顺序错误");
        }

        qr = getResult(examDetail, Integer.parseInt(resultArg[1]) );
        return qr;

        /* 处理逻辑
        *       1 拿到启用的大题，序号、二级题目数、题目总数
        *
        *       2 根据大题 和 key 拿到题目这条记录，二级序号，自身序号
        *
        *       3 如果二级序号是1，则判定是否这组是否答完，答完则错误，未答完看本题是否答过
        *           没打过区答题，打过则返回错误
        *
        *       4 如果是最后一个二级序号，判断上一级是否答完，没打完则错误，答完则判定本级是否答完
        *           答完则判定错误，没打完则判定本题是否做过
        *           做过则错误，没做过答题
        *
        *       5 如果是中间二级序号，判定上一个二级题目是否答完，下一个二级题目是否答完
        *           判定中间序号是否答完，答完则错误，未答完则判定此题目是否答过
        *           答过则错误，未答过则答题
        * */

    }


}
