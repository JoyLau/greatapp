package cn.lfdevelopment.www.app.exampool.service;

import cn.lfdevelopment.www.app.exampool.mapper.ExamPoolMapper;
import cn.lfdevelopment.www.app.exampool.pojo.CivilServantChoice;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LiuFa on 2016/10/8.
 * cn.lfdevelopment.www.app.exampool.service
 * DevelopmentApp
 */
@Service
public class ExamPoolService {
    @Autowired
    private ExamPoolMapper examPoolMapper;

    public List<CivilServantChoice> getAll(){
        return examPoolMapper.selectAll();
    }

    public List<CivilServantChoice> getQueryData(int page,int limit){
        PageHelper.startPage(page, limit);
        return examPoolMapper.getQueryData();
    }

    public int save(CivilServantChoice civilServantChoice) {
        return examPoolMapper.insert(civilServantChoice);
    }
}
