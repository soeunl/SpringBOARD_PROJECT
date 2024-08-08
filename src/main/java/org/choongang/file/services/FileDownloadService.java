package org.choongang.file.services;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.exceptions.FileNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class FileDownloadService {
    private final FileInfoService infoService;
    private final HttpServletResponse response;

    public void download(Long seq) {
        FileInfo data = infoService.get(seq);
        // 없으면 예외가 던져지고
        // 있으면 파일 정보가 있다.

        String filePath = data.getFilePath();
        String fileName = new String(data.getFileName().getBytes(), StandardCharsets.ISO_8859_1);
        // ENUM 상수로 이미 정의된 내용이므로 명확해서 예외가 발생하지 않는다.

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        String contentType = data.getContentType();
        // DB에 있는 contentType이 있으면 그것을 쓰고, 아니면 여기서 설정한대로 한다.
        contentType = StringUtils.hasText(contentType) ? contentType : "application/octet-stream";

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType(contentType);
            response.setIntHeader("Expires", 0); // 만료시간 X
            response.setHeader("Cache-Control", "must-revalidate");
            response.setContentLengthLong(file.length());

            OutputStream out = response.getOutputStream();
            out.write(bis.readAllBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
