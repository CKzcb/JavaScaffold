package com.ckzcb.com.ckzcb.ape.test.t_io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName TestPath
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/29 16:17
 * @Version 1.0
 */
public class TestPath {

    public void getName(String p) {
        System.out.println(Paths.get(p).getFileName());
    }

    public void info(String p) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        Path path = Paths.get(p);
        System.out.println("fileName: " + path.getFileName());
        System.out.println("parent: " + path.toAbsolutePath().getParent());
        System.out.println("root: " + path.getRoot());
        System.out.println("toAbsolutePath: " + path.toAbsolutePath());
        try {
            System.out.println("toRealPath: " + path.toRealPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("toUri: " + path.toUri());
    }

    public void fileInfo(String p) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        File file = new File(p);
        System.out.println("fileName: " + file.getName());
        System.out.println("parent: " + file.getParent());
        System.out.println("path: " + file.getPath());
        System.out.println("absolutePath: " + file.getAbsolutePath());
        try {
            System.out.println("canonicalPath: " + file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("toURI: " + file.toURI());
        System.out.println("exists: " + file.exists());
        System.out.println("isDirectory: " + file.isDirectory());
        System.out.println("isFile: " + file.isFile());
        System.out.println("isHidden: " + file.isHidden());
    }

}
