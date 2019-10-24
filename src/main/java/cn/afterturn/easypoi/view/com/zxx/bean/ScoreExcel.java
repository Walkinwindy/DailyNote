package cn.afterturn.easypoi.view.com.zxx.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * 模板导出测试类
 * @Author: zhaoXinXing
 * @Date: 2019/10/24 14:02
 */
@Data
public class ScoreExcel implements Serializable {
    @Excel(name="姓名")
    private String name;
    @Excel(name="性别",replace = {"男_1","女_0"})
    private Integer sex;
    @Excel(name="年龄")
    private Integer age;
    @Excel(name="分数")
    private Integer score;
    @Excel(name="课程",replace = {"语文_0","数学_1","英语_2","地理_3","历史_4","政治_5","生物_6"})
    private Integer lesson;
    @Excel(name="年级",replace = {"七年级_0","八年级_1","九年级_2"})
    private String classLevel;
    @Excel(name="总分")
    private Double sumScore;
    @Excel(name="平均分")
    private Double averageScore;
}
