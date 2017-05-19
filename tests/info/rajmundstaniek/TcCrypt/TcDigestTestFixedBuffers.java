package info.rajmundstaniek.TcCrypt;

import org.junit.Test;

import java.util.Calendar;

import static info.rajmundstaniek.TcCrypt.TcDigest.*;
import static info.rajmundstaniek.TcCrypt.TxtRes.printLog;

/**
 * Created by rajmu on 17.05.20.
 */
public class TcDigestTestFixedBuffers {
    @Test
    public void parallelTiny() throws Exception {
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest();
        d.processParallel(TxtRes.longAssText9000, "okon", ActionType.ENCODE, DigestSystem.BIN, BufferSize.TINY);
        printLog(TxtRes.longAssText9000.length(), BufferSize.TINY.getValue(), (Calendar.getInstance().getTimeInMillis() - timeStart), ActionType.ENCODE, DigestSystem.BIN);
    }

    @Test
    public void parallelSmall() throws Exception {
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest();
        d.processParallel(TxtRes.longAssText9000, "okon", ActionType.ENCODE, DigestSystem.BIN, BufferSize.SMALL);
        printLog(TxtRes.longAssText9000.length(), BufferSize.SMALL.getValue(), (Calendar.getInstance().getTimeInMillis() - timeStart), ActionType.ENCODE, DigestSystem.BIN);
    }

    @Test
    public void parallelStandard() throws Exception {
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest();
        d.processParallel(TxtRes.longAssText9000, "okon", ActionType.ENCODE, DigestSystem.BIN, BufferSize.STANDARD);
        printLog(TxtRes.longAssText9000.length(), BufferSize.STANDARD.getValue(), (Calendar.getInstance().getTimeInMillis() - timeStart), ActionType.ENCODE, DigestSystem.BIN);
    }

    @Test
    public void parallelBig() throws Exception {
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest();
        d.processParallel(TxtRes.longAssText9000, "okon", ActionType.ENCODE, DigestSystem.BIN, BufferSize.BIG);
        printLog(TxtRes.longAssText9000.length(), BufferSize.BIG.getValue(), (Calendar.getInstance().getTimeInMillis() - timeStart), ActionType.ENCODE, DigestSystem.BIN);
    }

    @Test
    public void parallelLarge() throws Exception {
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest();
        d.processParallel(TxtRes.longAssText9000, "okon", ActionType.ENCODE, DigestSystem.BIN, BufferSize.LARGE);
        printLog(TxtRes.longAssText9000.length(), BufferSize.LARGE.getValue(), (Calendar.getInstance().getTimeInMillis() - timeStart), ActionType.ENCODE, DigestSystem.BIN);
    }

    @Test
    public void parallelOMGWTFBBQ() throws Exception {
        long timeStart = Calendar.getInstance().getTimeInMillis();
        TcDigest d = new TcDigest();
        d.processParallel(TxtRes.longAssText9000, "okon", ActionType.ENCODE, DigestSystem.BIN, BufferSize.OMGWTFBBQ);
        printLog(TxtRes.longAssText9000.length(), BufferSize.OMGWTFBBQ.getValue(), (Calendar.getInstance().getTimeInMillis() - timeStart), ActionType.ENCODE, DigestSystem.BIN);
    }
}