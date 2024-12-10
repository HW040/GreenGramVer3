package green.greengram.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyFileUtilsTest {

    MyFileUtils myFileUtils;

    @BeforeEach
    void setUp() {
        myFileUtils = new MyFileUtils("D:/2024-02/download/greengram_ver3");
    }

    @Test
    void deleteFolder() {
        String path = String.format("%s/user/aaa", myFileUtils.getUploadPath());
        myFileUtils.deleteFolder(path, false);
    }
}