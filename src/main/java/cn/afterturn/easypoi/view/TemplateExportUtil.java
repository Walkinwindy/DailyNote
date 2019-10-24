package cn.afterturn.easypoi.view;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

/**
 * @Author: zhaoXinXing
 * @Date: 2019/10/24 15:10
 */
public class TemplateExportUtil extends EasypoiTemplateExcelView {
    public static Workbook getWorkbook(Map<String, Object> model){
        return ExcelExportUtil.exportExcel((TemplateExportParams)model.get("params"),(Map)model.get("map"));
    }
}
