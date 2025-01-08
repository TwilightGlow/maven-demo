package com.example.stream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author zhangjw54
 */
public class ExcelToHtml {

    final static String PATH = "C:\\Users\\Jinwei Zhang\\IdeaProjects\\maven-demo\\jdk8-demo\\src\\main\\resources\\file\\";
    final static String FILE_NAME = "Excel2007.xlsx";

    public static void main(String[] args) throws Exception {
        try (InputStream inputStream = Files.newInputStream(Paths.get(PATH + FILE_NAME))) {
            // 创建 HSSFWorkbook 对象
            // HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

            HSSFWorkbook workbook = convertXSSFToHSSF(new XSSFWorkbook(inputStream));

            // 创建 ExcelToHtmlConverter 对象
            ExcelToHtmlConverter htmlConverter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
            htmlConverter.processWorkbook(workbook);

            // 处理图片
            processPictures(workbook);

            // 获取生成的 HTML 文档
            Document htmlDocument = htmlConverter.getDocument();

            // 将 HTML 文档转换为字符串
            String htmlContent = convertDocumentToString(htmlDocument);

            // 将 HTML 内容写入文件
            FileUtils.writeStringToFile(new File(PATH + "网页文件.html"), htmlContent, "UTF-8");

        }
    }

    private static HSSFWorkbook convertXSSFToHSSF(XSSFWorkbook xssfWorkbook) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
            HSSFSheet hssfSheet = hssfWorkbook.createSheet(xssfSheet.getSheetName());

            // 复制合并区域
            for (int j = 0; j < xssfSheet.getNumMergedRegions(); j++) {
                CellRangeAddress mergedRegion = xssfSheet.getMergedRegion(j);
                hssfSheet.addMergedRegion(mergedRegion);
            }

            // 复制行和单元格数据
            for (Row xssfRow : xssfSheet) {
                HSSFRow hssfRow = hssfSheet.createRow(xssfRow.getRowNum());
                for (Cell xssfCell : xssfRow) {
                    HSSFCell hssfCell = hssfRow.createCell(xssfCell.getColumnIndex(), xssfCell.getCellType());
                    copyCellValue(xssfCell, hssfCell);
                }
            }
        }

        // 处理图片
        List<XSSFPictureData> xssfPictures = xssfWorkbook.getAllPictures();
        for (XSSFPictureData xssfPicture : xssfPictures) {
            byte[] data = xssfPicture.getData();
            int pictureType = getPictureType(xssfPicture.getMimeType());
            hssfWorkbook.addPicture(data, pictureType);
        }

        return hssfWorkbook;
    }

    private static void copyCellValue(Cell source, Cell target) {
        switch (source.getCellType()) {
            case STRING:
                target.setCellValue(source.getStringCellValue());
                break;
            case NUMERIC:
                target.setCellValue(source.getNumericCellValue());
                break;
            case BOOLEAN:
                target.setCellValue(source.getBooleanCellValue());
                break;
            case FORMULA:
                target.setCellFormula(source.getCellFormula());
                break;
            case BLANK:
                target.setBlank();
                break;
            default:
                target.setCellValue("");
        }
    }

    private static int getPictureType(String contentType) {
        switch (contentType) {
            case "image/png":
                return HSSFWorkbook.PICTURE_TYPE_PNG;
            case "image/jpeg":
                return HSSFWorkbook.PICTURE_TYPE_JPEG;
            default:
                return HSSFWorkbook.PICTURE_TYPE_JPEG;
        }
    }

    private static void processPictures(HSSFWorkbook workbook) throws Exception {
        List<HSSFPictureData> pictures = workbook.getAllPictures();
        if (pictures != null) {
            for (HSSFPictureData picture : pictures) {
                File outputFile = new File(PATH, "image" + System.currentTimeMillis() + "." + picture.suggestFileExtension());
                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    fos.write(picture.getData());
                }
            }
        }
    }

    private static String convertDocumentToString(Document document) throws Exception {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "html");
            transformer.transform(new DOMSource(document), new StreamResult(outputStream));
            return outputStream.toString("UTF-8");
        }
    }

    // public static void main(String[] args) throws Exception {
    //
    //     InputStream input = Files.newInputStream(Paths.get(path + file));
    //     HSSFWorkbook excelBook = new HSSFWorkbook(input);
    //     ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
    //     excelToHtmlConverter.processWorkbook(excelBook);
    //     List pics = excelBook.getAllPictures();
    //     if (pics != null) {
    //         for (int i = 0; i < pics.size(); i++) {
    //             Picture pic = (Picture) pics.get(i);
    //             try {
    //                 pic.writeImageContent(new FileOutputStream(path + pic.suggestFullFileName()));
    //             } catch (FileNotFoundException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    //     Document htmlDocument = excelToHtmlConverter.getDocument();
    //     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    //     DOMSource domSource = new DOMSource(htmlDocument);
    //     StreamResult streamResult = new StreamResult(outStream);
    //     TransformerFactory tf = TransformerFactory.newInstance();
    //     Transformer serializer = tf.newTransformer();
    //     serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
    //     serializer.setOutputProperty(OutputKeys.INDENT, "yes");
    //     serializer.setOutputProperty(OutputKeys.METHOD, "html");
    //     serializer.transform(domSource, streamResult);
    //     outStream.close();
    //
    //     String content = new String(outStream.toByteArray());
    //
    //     FileUtils.writeStringToFile(new File(path, "网页文件.html"), content, "utf-8");
    // }
}
