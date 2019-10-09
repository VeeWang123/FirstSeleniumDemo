package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
/**
 * Created by tianweiwang on 2019/8/31.
 */
public class CommonUtils {
    /*
    return random int with len：
    随机数，再乘以需要的位数。这里用乘9，再加1，而没有用乘10的方式，是为了防止生成的随机数比较小，导致乘以位数后小于指定位数。
    防止0.0****的情况。但是不能防止0.00*****的情况（概率1%，暂时不考虑）
     */
    public static String getRandom(int len) {
        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
        return String.valueOf(rs);
    }


//    return random int+letter
    public static String randomString(int len){
        String val = "";
        Random random = new Random();
        for(int i = 0; i < len; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if( "char".equalsIgnoreCase(charOrNum) ) { //此处应该用getRandomInRanges()，否则数字和字母概率都是50%
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

//    return random int within different ranges
//    int[][] ranges={{1,2},{10,11}};
    public static String getRandomInRanges(int[][] ranges) {
        int sum=0;
        for (int[] range : ranges) {
            range[1]=range[1]+1;
            sum+=(range[1]-range[0]);

        }
        System.out.println("sum: "+sum);
        int ran= (new Random()).nextInt(sum);
        for (int[] range : ranges) {
            if(ran<(range[1]-range[0])){
                ran+=range[0];
                System.out.println("random range: "+range[0]+" - "+(range[1]-1));
                break;

            }else {
                ran-=(range[1]-range[0]);
            }

        }
        return String.valueOf(ran);
    }

    public static List<List<String>> readExcel(String path){
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        // return a list contains many list
        List<List<String>> lists = new ArrayList<List<String>>();
        //读取excel文件
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            //获取工作薄
            Workbook wb = null;
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }
            //读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);
            //第一行为标题
            for (Row row : sheet) {
                ArrayList<String> list = new ArrayList<String>();
                for (Cell cell : row) {
                    //根据不同类型转化成字符串
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
                lists.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }

    /*
    return workbook with new sheet:
            List line1=new ArrayList<String>() {{add("aa");add("bb");}};
            List<String> line2 = Arrays.asList("ccc", "xxx", "oooo");
            List<List<String>> lists=new ArrayList<>();
            lists.add(line1);
            lists.add(line2);
            creatExcel(lists, new String[]{"col01", "col02"},"testsheet04","./src/test/java/testData/testdata.xlsx");

     */
    public static void creatSheet(List<List<String>> lists, String[] titles, String name, String path) throws IOException {
        System.out.println(lists);
        FileInputStream is;
        is = new FileInputStream(path);
//        POIFSFileSystem ps=new POIFSFileSystem(is);  //使用POI提供的方法得到excel的信息
        Workbook wb=new XSSFWorkbook(is);

        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(name);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<titles.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
//        f.setBoldweight(Font.BOLDWEIGHT_BOLD);  没找到此方法

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        /*
        没找到此方法
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        */

        // 设置第二种单元格的样式（用于值）
        /*
        没找到此方法
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        */
        //设置列名
        for(int i=0;i<titles.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(cs);
        }
        if(lists == null || lists.size() == 0){
//            return wb;
        }
        //设置每行每列的值
        for (short i = 1; i <= lists.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow(i);
            for(short j=0;j<titles.length;j++){
                // 在row行上创建一个方格
                Cell cell = row1.createCell(j);
                cell.setCellValue(lists.get(i-1).get(j));
                cell.setCellStyle(cs2);
            }
        }
        FileOutputStream os=null;
        os=new FileOutputStream(path);
        wb.write(os);
        os.close();
    }


    /*
    yyyyMMdd_HHmm
    yyMMdd
    yyyy年MM月dd日 HH:mm:ss
    yyyy-MM-dd HH:mm:ss
    ...
     */
    public static String getDate(String type){
        SimpleDateFormat sdf=new SimpleDateFormat(type);
        return sdf.format(new Date());
    }

    public static void takeFullScreenshot(String retry) {
        String date=getDate("yyMMdd");
        //mkdir folder
        if (!new File("./src/test/java/log/"+date).exists()){
            new File("./src/test/java/log/"+date).mkdirs();
        }

        String time=getDate("yyyy-MM-dd HH:mm:ss");

        String imgpath="./src/test/java/log/"+date+"/"+retry+"_"+time+".png";
        File file = new File(imgpath);
        if (!file.exists())
            file.mkdirs();
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    生成永不重复的随机值


}
