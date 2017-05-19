package info.rajmundstaniek.TcCrypt;

import org.junit.Test;

import java.util.Calendar;

import static info.rajmundstaniek.TcCrypt.TxtRes.printLog;

/**
 * Created by rajmu on 17.05.19.
 */
public class TcDigestParallelStressTest {

    @Test
    public void parallelTest4500BIN() throws Exception {
        System.out.println();
        System.err.println("Test: 4500 BIN");
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest(16);
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(64);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(256);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(512);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(1024);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(2048);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(4096);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
    }

    @Test
    public void parallelTest4500HEX() throws Exception {
        System.out.println();
        System.err.println("Test: 4500 HEX");
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest(16);
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(64);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(256);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(512);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(1024);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(2048);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(4096);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
    }

    @Test
    public void parallelTest4500UTF8() throws Exception {
        System.out.println();
        System.err.println("Test: 4500 UTF8");
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest(16);
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(64);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(256);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(512);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(1024);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(2048);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(4096);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText4500, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText4500.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
    }

    @Test
    public void parallelTest9000BIN() throws Exception {
        System.out.println();
        System.err.println("Test: 9000 BIN");
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest(16);
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(64);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(256);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(512);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(1024);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(2048);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(4096);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
    }

    @Test
    public void parallelTest9000HEX() throws Exception {
        System.out.println();
        System.err.println("Test: 9000 HEX");
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest(16);
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(64);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(256);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(512);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(1024);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(2048);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        //new test
        d = new TcDigest(4096);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.HEX);
    }

    @Test
    public void parallelTest9000UTF8() throws Exception {
        System.out.println();
        System.err.println("Test: 9000 UTF8");
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest(16);
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(64);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(256);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(512);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(1024);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(2048);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(4096);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.longAssText9000, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.longAssText9000.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
    }

}