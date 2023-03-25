package com.lxg.exams.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxg.exams.bean.Question;
import com.lxg.exams.service.QuestionService;
import com.lxg.exams.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author xiaolin
* @description 针对表【question】的数据库操作Service实现
* @createDate 2023-03-25 13:11:18
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




