package info.rajmundstaniek.TcCrypt;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * Created by rajmu on 17.03.16.
 */
public class CryptoUtilsTest {
    private static File in;
    private static File out;

    @Before
    public void setUp() throws Exception {
        out = new File("temp.mp3");
        URL url = this.getClass().getResource("/testFile.mp3");
        in = new File(url.getPath());
    }

    @Test
    public void consistencyTest() throws Exception {
        String key = "dabsdfgsdfga1234";
        File encrypted = new File("test.enc");
        CryptoUtils.encrypt(key, in, encrypted);
        CryptoUtils.decrypt(key, encrypted, out);

        //assertEquals(in, out);
    }
}