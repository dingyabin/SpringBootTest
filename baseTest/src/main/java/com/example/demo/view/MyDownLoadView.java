package com.example.demo.view;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by MrDing
 * Date: 2017/12/31.
 * Time:0:40
 * @author MrDing
 */
public class MyDownLoadView extends AbstractView {

    private String fileName;

    private InputStream inputStream;

    private byte[] sourceByte;

    public MyDownLoadView() {
        initContentType();
    }

    public MyDownLoadView(String fileName, InputStream inputStream) {
        this.fileName = fileName;
        this.inputStream = inputStream;
        initContentType();
    }

    public MyDownLoadView(String fileName, byte[] sourceByte) {
        this.fileName = fileName;
        this.sourceByte = sourceByte;
        initContentType();
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType(getContentType());
        response.addHeader("Content-Disposition", String.format("attachment;filename=%s", fileName != null ? fileName : "noName"));
        outputStream.write(inputStream != null ? IOUtils.toByteArray(inputStream) : sourceByte);
        outputStream.flush();
    }



    public void initContentType() {
        super.setContentType("application/octet-stream");
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public byte[] getSourceByte() {
        return sourceByte;
    }

    public void setSourceByte(byte[] sourceByte) {
        this.sourceByte = sourceByte;
    }
}
