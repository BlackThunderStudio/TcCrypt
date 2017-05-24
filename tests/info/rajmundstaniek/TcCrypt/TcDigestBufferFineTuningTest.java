package info.rajmundstaniek.TcCrypt;

import org.junit.Test;

import java.util.Calendar;

import static info.rajmundstaniek.TcCrypt.TxtRes.printLog;

/**
 * Created by rajmu on 17.05.20.
 */
public class TcDigestBufferFineTuningTest {

    @Test
    public void parallelTest9000UTF8() throws Exception {
        System.out.println();
        System.err.println("Test: 9000 UTF8");
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest(48);
        d.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.LONG_TEXT_9000_WORDS.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(64);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.LONG_TEXT_9000_WORDS.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(72);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.LONG_TEXT_9000_WORDS.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        //new test
        d = new TcDigest(128);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
        printLog(TxtRes.LONG_TEXT_9000_WORDS.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.UTF8);
    }

    @Test
    public void parallelTest9000BIN() throws Exception {
        System.out.println();
        System.err.println("Test: 9000 BIN");
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest(48);
        d.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.LONG_TEXT_9000_WORDS.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(64);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.LONG_TEXT_9000_WORDS.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(72);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.LONG_TEXT_9000_WORDS.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        //new test
        d = new TcDigest(128);
        timeStart = Calendar.getInstance().getTimeInMillis();
        d.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
        printLog(TxtRes.LONG_TEXT_9000_WORDS.length(), d.bufferSize, (Calendar.getInstance().getTimeInMillis() - timeStart), TcDigest.ActionType.ENCODE, TcDigest.DigestSystem.BIN);
    }

}