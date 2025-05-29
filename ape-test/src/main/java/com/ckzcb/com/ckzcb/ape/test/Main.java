package com.ckzcb.com.ckzcb.ape.test;

import com.ckzcb.com.ckzcb.ape.test.t_io.TestPath;
import com.ckzcb.com.ckzcb.ape.test.t_io.TestStream;

/**
 * @ClassName Main
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/29 15:27
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        TestStream testStream = new TestStream();
        testStream.fileWrite("tt.txt", "hello world", true);
        TestPath testPath = new TestPath();
        testPath.info("2tt.txt");
        testPath.fileInfo("t2t.txt");
    }
}
