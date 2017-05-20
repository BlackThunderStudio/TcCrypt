package info.rajmundstaniek.TcCrypt;

import info.rajmundstaniek.TcCrypt.exception.DigestSetupException;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;

import static info.rajmundstaniek.TcCrypt.TcDigest.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by rajmu on 17.03.16.
 */
public class TcDigestTest {
    private TcDigest digest;
    private String input;
    private String hashCode;
    private ActionType actionType;

    @Before
    public void setUp() throws Exception {
        digest = new TcDigest(16);
        input = "She sells sea shells on a sea shore.";
        hashCode = "bbc123";
        actionType = ActionType.ENCODE;
    }

    @Test
    public void processDataUtf8() throws Exception {
        String digestedUTF = digest.processData(input, hashCode, actionType, DigestSystem.UTF8);
        System.out.println("Digested to UTF8: " + digestedUTF);
        String decoded = digest.processData(digestedUTF, hashCode, ActionType.DECODE, DigestSystem.UTF8);
        System.out.println("Digested from UTF8: " + decoded);

        assertEquals(decoded, input);
    }

    @Test
    public void processDataBin() throws Exception {
        String digestedBin = digest.processData(input, hashCode, actionType, DigestSystem.BIN);
        System.out.println("Digested to BIN: " + digestedBin);
        String decoded = digest.processData(digestedBin, hashCode, ActionType.DECODE, DigestSystem.BIN);
        System.out.println("Digested from BIN: " + decoded);

        assertEquals(decoded, input);
    }

    @Test
    public void processDataHex() throws Exception {
        String digestedHex = digest.processData(input, hashCode, actionType, DigestSystem.HEX);
        System.out.println("Digested to HEX: " + digestedHex);
        String decoded = digest.processData(digestedHex, hashCode, ActionType.DECODE, DigestSystem.HEX);
        System.out.println("Digested from HEX: " + decoded);

        assertEquals(decoded, input);
    }

    @Test
    public void processDataBinSimple() throws Exception {
        String digested = digest.processData("abc", "a", ActionType.ENCODE, DigestSystem.BIN);
        String decoded = digest.processData(digested, "a", ActionType.DECODE, DigestSystem.BIN);

        assertEquals("abc", decoded);
    }

    @Test(expected = DigestSetupException.class)
    public void testPrepExceptionSeed() throws Exception {
        String s = digest.processData("some gay shit");
    }

    @Test(expected = DigestSetupException.class)
    public void testPrepExceptionAction() throws Exception {
        digest.setSeed("doopa");
        String s = digest.processData("some gay shit");
    }

    @Test(expected = DigestSetupException.class)
    public void testPrepExceptionSystem() throws Exception {
        digest.setSeed("doopa");
        digest.setActionType(ActionType.ENCODE);
        String s = digest.processData("some gay shit");
    }

    @Test(timeout = 1000)
    public void executionTimeTest4500() throws Exception {
        System.out.println("Strain test text length: " + TxtRes.longAssText4500.length());
        String encoded = digest.processData(TxtRes.longAssText4500, "okon", ActionType.ENCODE, DigestSystem.UTF8);
        //String decoded = digest.processData(encoded, "okon", TcDigest.ActionType.DECODE, TcDigest.DigestSystem.UTF8);

        //assertEquals(input, decoded);
    }

    @Test(timeout = 2000)
    public void executionTimeTest9000() throws Exception {
        System.out.println("Strain test text length: " + TxtRes.longAssText9000.length());
        String encoded = digest.processData(TxtRes.longAssText9000, "okon", ActionType.ENCODE, DigestSystem.UTF8);
        //String decoded = digest.processData(encoded, "okon", TcDigest.ActionType.DECODE, TcDigest.DigestSystem.UTF8);

        //assertEquals(input, decoded);
    }

    @Test
    public void processParellelTest() throws Exception {
        String in = "Ei deserunt dissentiunt vituperatoribus per, essent vocent has at. Pri eros vero veritus ad. Sed ne malis inani invenire, ex nonumy aliquando est. Sea atqui solet ei. Eos id nihil moderatius.";
        String out = digest.processParallel(in, "okon", ActionType.ENCODE, DigestSystem.HEX);
        String decrypt = digest.processParallel(out, "okon", ActionType.DECODE, DigestSystem.HEX);
        assertEquals(in, decrypt);
    }

    @Test
    public void partitionBytesTest() throws Exception {
        byte[] data = new byte[100];
        for (int i = 0; i < 100; i++) {
            data[i] = (byte) i;
        }
        ArrayList<byte[]> arr = digest.partitionBytes(data, TcDigest.BufferSize.STANDARD);
        System.out.println();
    }

    @Test
    public void processParallelBytes() throws Exception {
        int size = 2 * MAX_BYTE_ARR_SIZE + 8192;
        byte[] data = new byte[size];

        for (int i = 0; i < size; i++) {
            data[i] = (byte) i;
        }

        byte[] result = digest.processParallelBytes(data, "okon", ActionType.ENCODE, DigestSystem.HEX);
    }

    @Test(timeout = 1000 * 30)
    public void downloadBytesFromFile() throws Exception {
        String path = "http://remix1436.ct8.pl/resources/Media/Thomas_Jack_-_Booka_Shake.mp3";
        byte[] data = TcDigest.downloadUrl(new URL(path));
    }
}