package com.ashtensanchezcustomersupport.site;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.View;

import java.util.Map;

public class DownloadView implements View {

    private final String fileName;
    private final byte[] contents;

    public DownloadView(String fileName, byte[] content) {

        this.fileName = fileName;
        this.contents = content;
    }

    @Override
    public String getContentType() {
        return View.super.getContentType();
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/octet-stream");

        ServletOutputStream out = response.getOutputStream();
        out.write(contents);
        out.flush();
        out.close();
    }
}
