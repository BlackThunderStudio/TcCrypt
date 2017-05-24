package info.rajmundstaniek.TcCrypt;

import org.junit.Before;
import org.junit.Test;

import static info.rajmundstaniek.TcCrypt.TcDigest.ActionType;
import static info.rajmundstaniek.TcCrypt.TcDigest.DigestSystem;
import static org.junit.Assert.assertEquals;

/**
 * Created by rajmu on 17.05.20.
 */
public class TcDigestTestComparison {

    private TcDigest tcDigest;
    private final ActionType actionType = ActionType.ENCODE;
    private final DigestSystem system = DigestSystem.BIN;

    @Before
    public void setUp() throws Exception {
        tcDigest = new TcDigest();
    }

    @Test
    public void sequential() throws Exception {
        tcDigest.processData(TxtRes.LONG_TEXT_9000_WORDS, "okon", actionType, system);
    }

    @Test
    public void concurrent() throws Exception {
        tcDigest.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", actionType, system);
    }

    @Test
    public void integrityCheck() throws Exception {
        String parallel = tcDigest.processParallel(TxtRes.LONG_TEXT_9000_WORDS, "okon", actionType, system);
        String sequential = tcDigest.processData(TxtRes.LONG_TEXT_9000_WORDS, "okon", actionType, system);

        assertEquals(parallel, sequential);
    }
}