package com.ckzcb.com.ckzcb.ape.test.t_io;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName TestStream
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/29 16:02
 * @Version 1.0
 */
@Getter
@Setter
public class TestStream {
    private InputStream fileInputStream;
    private InputStream bufferedInputStream;

    private OutputStream fileOutputStream;
    private OutputStream bufferedOutputStream;

    public void fileRead(String path) {
        try {
            fileInputStream = new FileInputStream(path);
            var bytes = fileInputStream.readAllBytes();
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fileWrite(String path, String text, boolean append) {
        try {
            fileOutputStream = new FileOutputStream(path, append);
            fileOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void bufferedRead(String path) {
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
            var bytes = bufferedInputStream.readAllBytes();
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void bufferedWrite(String path, String text, boolean append) {
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path, append));
            bufferedOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
            bufferedOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
