package cn.afterturn.easypoi.view;

import cn.afterturn.easypoi.entity.vo.TemplateExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.styler.ExcelExportStylerDefaultImpl;
import cn.afterturn.easypoi.test.entity.temp.TemplateExcelExportEntity;
import cn.afterturn.easypoi.view.com.zxx.bean.ScoreExcel;
import lombok.Cleanup;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Author: zhaoXinXing
 * @Date: 2019/10/24 14:01
 */
public class MyExportTest {
    private Random random = new Random();

    /**
     * 测试 普通导出
     *
     * @param null
     * @author zhaoXinXing
     * @date 2019/10/24
     */
    @Test
    public void test1111() {
        ArrayList<ScoreExcel> list = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            ScoreExcel scos = new ScoreExcel();
            scos.setAge(random.nextInt(7) + 11);
            scos.setSex(random.nextInt(2));
            scos.setName("小明" + i + "号");
            scos.setScore(random.nextInt(100));
            scos.setClassLevel(String.valueOf(random.nextInt(3)));
            scos.setLesson(random.nextInt(6));
            list.add(scos);
        }
        String title = "2019年期末学生成绩表";
        String fileName = "学生成绩表.xls";
        String sheetName = "表一";
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.HSSF);
        exportParams.setStyle(ExcelExportStylerDefaultImpl.class);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ScoreExcel.class, list);
        try {
            @Cleanup FileOutputStream fos = new FileOutputStream(new File("D:/" + fileName));
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试模板导出
     *
     * @param null
     * @author zhaoXinXing
     * @date 2019/10/24
     */
    @Test
    public void test1112() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> modelMap = new HashMap<>();
        TemplateExportParams params = new TemplateExportParams(
                "doc/foreach.xlsx");
        List<TemplateExcelExportEntity> list = new ArrayList<TemplateExcelExportEntity>();

        for (int i = 0; i < 4; i++) {
            TemplateExcelExportEntity entity = new TemplateExcelExportEntity();
            entity.setIndex(i + 1 + "");
            entity.setAccountType("开源项目");
            entity.setProjectName("EasyPoi " + i + "期");
            entity.setAmountApplied(i * 10000 + "");
            entity.setApprovedAmount((i + 1) * 10000 - 100 + "");
            list.add(entity);
        }
        map.put("entitylist", list);
        map.put("manmark", "1");
        map.put("letest", "12345678");
        map.put("fntest", "12345678.2341234");
        map.put("fdtest", null);
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 1; i++) {
            Map<String, Object> testMap = new HashMap<String, Object>();

            testMap.put("id", "xman");
            testMap.put("name", "小明" + i);
            testMap.put("sex", "1");
            mapList.add(testMap);
        }
        map.put("maplist", mapList);

        mapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 6; i++) {
            Map<String, Object> testMap = new HashMap<String, Object>();

            testMap.put("si", "xman");
            mapList.add(testMap);
        }
        map.put("sitest", mapList);
        modelMap.put(TemplateExcelConstants.FILE_NAME, "用户信息");
        modelMap.put(TemplateExcelConstants.PARAMS, params);
        modelMap.put(TemplateExcelConstants.MAP_DATA, map);
        Workbook workbook = TemplateExportUtil.getWorkbook(modelMap);
        try {
            @Cleanup FileOutputStream fos = new FileOutputStream(new File("D:/" + "模板导出.xlsx"));
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
