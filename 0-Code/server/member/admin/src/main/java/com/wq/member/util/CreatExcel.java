package com.wq.member.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreatExcel {

    public static void writeExcel(List<Map<String,Object>> dataList, String filePath) throws Exception {
        System.out.println("dataList = " + dataList + ", filePath = " + filePath);
        //创建文件
        File path = new File(filePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".xlsx";
        File file = new File(path, fileName);

        //创建一个excel对象
        Workbook workbook = new XSSFWorkbook();

        //创建第一个工作簿
        Sheet sheet = workbook.createSheet("sheet1");

        //将数据库字段（map的key）写入第一行
        Map<String, Object> firstRowMap = dataList.get(0);//firstRowMap = {minioId=17, objectPath=/Users/wq/NIFI/randomDoc/10.322074786810381.xlsx, createTime=Tue Mar 03 11:39:24 CST 2020, objectWay=0, updateUid=null, failureReason=java.net.ConnectException: Failed to connect to /192.168.201.215:9000, fileAnalysisStatus=0, objectName=10.322074786810381.xlsx, updateTime=Tue Mar 03 11:39:24 CST 2020, id=42, createUid=null, status=1}

        Set<String> keySet = firstRowMap.keySet();//keySet = [minioId, objectPath, createTime, objectWay, updateUid, failureReason, fileAnalysisStatus, objectName, updateTime, id, createUid, status]

        Object[] keyArr = keySet.toArray();
        Row row0 = sheet.createRow(0);

        for (int i = 0; i < keyArr.length; i++) {
            //创建单元格并写入数据
            // Cell cell = row0.createCell(i);
            // cell.setCellValue(keyArr[i].toString());
            Cell cell ;
            switch(keyArr[i].toString())
            {
                case "id":
                    cell = row0.createCell(0);
                    cell.setCellValue(keyArr[i].toString());
                    break;
                case "objectName":
                    cell = row0.createCell(1);
                    cell.setCellValue(keyArr[i].toString());
                    break;
                case "objectWay":
                    cell = row0.createCell(2);
                    cell.setCellValue(keyArr[i].toString());
                    break;
                case "status":
                    cell = row0.createCell(3);
                    cell.setCellValue(keyArr[i].toString());
                    break;

            }

        }

        //将数据写入其他行
        for (int i = 0; i < dataList.size(); i++) {
            //控制有多少行
            Map<String, Object> rowMap = dataList.get(i);
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < rowMap.size(); j++) {

                Cell cell ;
                Object object;
                String string;
                switch(keyArr[j].toString())
                {
                    case "id":
                        cell = row.createCell(0);
                        // 取第"id"列的值
                        object = rowMap.get("id");
                        //除掉空值,也可以写入对应类型,此处直接写入string
                        string = null==object? "" :object.toString();
                        cell.setCellValue(string);
                        break;
                    case "objectName":
                        cell = row.createCell(1);
                        object = rowMap.get("objectName");
                        string = null==object? "" :object.toString();
                        cell.setCellValue(string);
                        break;
                    case "objectWay":
                        cell = row.createCell(2);
                        object = rowMap.get("objectWay");
                        string = object.equals(0)? "上传" : "下载";
                        cell.setCellValue(string);
                        break;
                    case "status":
                        cell = row.createCell(3);
                        object = rowMap.get("status");
                        string = object.equals(0)? "成功" : "失败";
                        cell.setCellValue(string);
                        break;

                }
            }
        }

        //输出文件
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
    }

}
