/**
 * Library made for encrypting / decrypting chunks of text and files
 * File part is under construction at the moment
 *
 * @author Rajmund Staniek
 * @created 16.03.2017
 */
package info.rajmundstaniek.TcCrypt;

import info.rajmundstaniek.TcCrypt.exception.DigestRuntimeException;
import info.rajmundstaniek.TcCrypt.exception.DigestSetupException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TcDigest {

    private SystemChange systemChange;
    private ActionType actionType;
    private DigestSystem digestSystem;
    private String seed;
    final int bufferSize;
    private final boolean testingMode;

    public static final int MAX_BYTE_ARR_SIZE = 1048576; //roughly around 1MB

    /***
     * Default constructor
     */
    public TcDigest() {
        systemChange = new SystemChange();
        bufferSize = BufferSize.STANDARD.getValue();
        testingMode = false;
    }

    /***
     * Constructor with a custom buffer size functionality
     *
     * WARNING!!!
     *
     * NOT RECOMMENDED
     *
     * @param bufferSize size of the container for each thread to process
     */
    @Deprecated
    public TcDigest(int bufferSize) {
        systemChange = new SystemChange();
        this.bufferSize = bufferSize;
        testingMode = true;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public DigestSystem getDigestSystem() {
        return digestSystem;
    }

    public void setDigestSystem(DigestSystem digestSystem) {
        this.digestSystem = digestSystem;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    /***
     * Downloads resource from the web
     * @param toDownload internet address of the the file
     * @return byte array of a decoded object
     */
    public static byte[] downloadUrl(URL toDownload) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] result;

        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            InputStream stream = toDownload.openStream();

            while ((bytesRead = stream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }

            result = outputStream.toByteArray();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    /***
     * Computes the data with the given hash code parallelly
     * @param input String of text / data to be processed
     * @param seed hash used to compute the data
     * @param flag Action type: [ENCODE / DECODE]
     * @param system Numerical system of input / output: [UTF8 / BIN / HEX]
     * @param bufferSize buffer size
     * @return String of digested data
     */
    public String processParallel(String input, String seed, ActionType flag, DigestSystem system, BufferSize bufferSize) {
        ArrayList<String> buffer = partition(input, bufferSize);
        return buffer.parallelStream()
                .map(e -> {
                    try {
                        return e = run(e, seed, flag, system);
                    } catch (DigestRuntimeException e1) {
                        e1.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.joining());
    }

    /***
     * Computes the data with the given hash code in parallel
     * Buffer size in this setting is set to: 64
     * @param input String of text / data to be processed
     * @param seed hash used to compute the data
     * @param flag Action type: [ENCODE / DECODE]
     * @param system Numerical system of input / output: [UTF8 / BIN / HEX]
     * @return String of digested data
     */
    public String processParallel(String input, String seed, ActionType flag, DigestSystem system) {
        return processParallel(input, seed, flag, system, BufferSize.STANDARD);
    }

    /***
     * Computes the data with the given hash code parallelly
     * WARNING!!!
     * Hash code, Action type and numerical system have to be set manually!!!
     * @param input String of text / data to be processed
     * @return String of digested data
     * @throws DigestSetupException Throws an exception when the setup failed
     */
    public String processParallel(String input) throws DigestSetupException {
        if (seed == null) throw new DigestSetupException("Seed string not initialized. Set the seed first!");
        if (seed.isEmpty()) throw new DigestSetupException("Seed string cannot be empty!");
        if (actionType == null) throw new DigestSetupException("Unspecified action type!");
        if (digestSystem == null) throw new DigestSetupException("Unspecified numeric system!");
        return processParallel(input, seed, actionType, digestSystem);
    }

    /***
     * Computes the data with the given hash code parallelly
     * @param inputBytes input data
     * @param seed seed used to process the data
     * @param flag Action type: [ENCODE / DECODE]
     * @param system Numerical system of input / output: [UTF8 / BIN / HEX]
     * @return byte array of processed data
     */ //TODO:WIP
    public byte[] processParallelBytes(byte[] inputBytes, String seed, ActionType flag, DigestSystem system) {
        int sectorCount = (inputBytes.length / MAX_BYTE_ARR_SIZE) + 1;
        byte[][] partitionedBytes = new byte[sectorCount][];
        if (inputBytes.length <= MAX_BYTE_ARR_SIZE) {
            //TODO: process normally
        } else {
            for (int i = 0; i < sectorCount - 1; i++) {
                partitionedBytes[i] = new byte[MAX_BYTE_ARR_SIZE];
                System.arraycopy(inputBytes, i * MAX_BYTE_ARR_SIZE, partitionedBytes[i], 0, MAX_BYTE_ARR_SIZE);
            }
            partitionedBytes[sectorCount - 1] = new byte[MAX_BYTE_ARR_SIZE];
            System.arraycopy(inputBytes, (sectorCount - 1) * MAX_BYTE_ARR_SIZE, partitionedBytes[sectorCount - 1], 0, inputBytes.length - ((sectorCount - 1) * MAX_BYTE_ARR_SIZE));
            //TODO: process partitioned
        }
        return null;
    }

    /***
     * Computes the data with the given hash code
     * @param input String of text / data to be processed
     * @param seed hash used to compute the data
     * @param flag Action type: [ENCODE / DECODE]
     * @param system Numerical system of input / output: [UTF8 / BIN / HEX]
     * @return String of digested data
     * @throws DigestSetupException Throws an exception when the setup failed
     * @throws DigestRuntimeException Throws an exception when digesting algorithm fails
     */
    public String processData(String input, String seed, ActionType flag, DigestSystem system) throws DigestSetupException, DigestRuntimeException {
        if (seed.isEmpty()){
            throw new DigestSetupException("Seed string cannot be empty!");
        }
        return run(input, seed, flag, system);
    }

    /***
     * Computes the data with the given hash code.
     * WARNING!!!
     * Hash code, Action type and numerical system have to be set manually!!!
     * @param input String of text / data to be processed
     * @return String of digested data
     * @throws DigestSetupException Throws an exception when the setup failed
     * @throws DigestRuntimeException Throws an exception when digesting algorithm fails
     */
    public String processData(String input) throws DigestSetupException, DigestRuntimeException {
        if(seed == null) throw new DigestSetupException("Seed string not initialized. Set the seed first!");
        if(seed.isEmpty()) throw new DigestSetupException("Seed string cannot be empty!");
        if(actionType == null) throw new DigestSetupException("Unspecified action type!");
        if(digestSystem == null) throw new DigestSetupException("Unspecified numeric system!");
        return run(input, seed, actionType, digestSystem);
    }


    /***
     * WARNING for small files only!!! (<1MB)
     * @param input file
     * @param seed hash
     * @return computed string
     * @throws DigestSetupException
     * @throws DigestRuntimeException
     */
    @Deprecated
    public String fromFile(File input, String seed) throws DigestSetupException, DigestRuntimeException {
        if(seed.isEmpty()) throw new DigestSetupException("Seed string cannot be empty!");
        if(actionType == null) throw new DigestSetupException("Unspecified action type!");
        if(digestSystem == null) throw new DigestSetupException("Unspecified numeric system!");

        String text = "";

        try {
            FileInputStream fileInputStream = new FileInputStream(input);
            byte[] bytes = new byte[(int)input.length()];
            fileInputStream.read(bytes);
            text = Arrays.toString(bytes);
        } catch (FileNotFoundException e) {
            throw new DigestSetupException("Input file could not be found!", e);
        } catch (IOException e) {
            throw new DigestSetupException("Could not read the file!", e);
        }

        return run(text, seed, actionType, digestSystem);
    }

    @Deprecated
    public void fromFile(File inputFile, File outputFile, String seed) throws DigestSetupException {
        if(seed.isEmpty()) throw new DigestSetupException("Seed string cannot be empty!");
        if(actionType == null) throw new DigestSetupException("Unspecified action type!");
        if(digestSystem == null) throw new DigestSetupException("Unspecified numeric system!");

        //TODO: to be implemented
    }

    //private methods and sub classes
    private String run(String input, String seed, ActionType flag, DigestSystem system) throws DigestRuntimeException {
        int digestHash = 0;
        for (int temp : seed.toCharArray()){
            digestHash += temp;
        }
        if(system.equals(DigestSystem.UTF8)) digestHash = digestHash % 2048;
        String output = "";
        if(flag.equals(ActionType.ENCODE)){
            for (int character : input.toCharArray()){
                if(system.equals(DigestSystem.UTF8)){
                    output += Character.toString((char)(character + digestHash));
                } else {
                    if(system.equals(DigestSystem.BIN)){
                        output += systemChange.toNumericSystem(character + digestHash, 2);
                    }
                    if(system.equals(DigestSystem.HEX)){
                        output += systemChange.toNumericSystem(character + digestHash, 16);
                    }
                }
            }//end of loop
        }//end of encoding
        if(flag.equals(ActionType.DECODE)){
            if(system.equals(DigestSystem.UTF8)){
                for (int character : input.toCharArray()){
                    output += (char) (character - digestHash);
                }
            } else {
                String[] tmpInput = input.split(" ");
                for(String temp : tmpInput){
                    if(system.equals(DigestSystem.BIN)){
                        try{
                            output += (char) (Integer.parseInt(systemChange.toDecimal(temp, 2)) - digestHash);
                        } catch (NumberFormatException ex){
                            throw new DigestRuntimeException("Error processing data. Invalid String to integer conversion. Could not convert BIN encrypted data into a String.\n" + ex.getMessage(),
                                    ex.getCause());
                        }
                    }
                    if(system.equals(DigestSystem.HEX)){
                        try{
                            output += (char) (Integer.parseInt(systemChange.toDecimal(temp, 16)) - digestHash);
                        } catch (NumberFormatException ex){
                            throw new DigestRuntimeException("Error processing data. Invalid String to integer conversion. Could not convert HEX encrypted data into a String.\n" + ex.getMessage(),
                                    ex.getCause());
                        }
                    }
                }
            }
        }//end of decoding
        if(output.isEmpty()) throw new DigestRuntimeException("Unknown TcDigest exception!");
        return output;
    }//end of run()

    /***
     *P artitions the input for a fixed sized blocks for parellel processing of the data
     * @param string Input sequence
     * @param buffer size of a buffer for data
     * @return output array
     */
    protected ArrayList<String> partition(String string, BufferSize buffer) {
        int buff = buffer.getValue();
        if (testingMode) buff = bufferSize;
        ArrayList<String> chars = new ArrayList<>();
        int blockCount = 1;
        if (string.length() <= buff) {
            chars.add(string);
        } else {
            while ((blockCount * buff) < string.length()) {
                chars.add(string.substring((blockCount - 1) * buff, blockCount * buff));
                blockCount++;
            }
            chars.add(string.substring((blockCount - 1) * buff, string.length()));
        }
        return chars;
    }

    /***
     * Partitions the input for a fixed sized blocks for parellel processing of the data
     * @param bytes array of input bytes
     * @param buffer size of a buffer for data
     * @return output array of bytes
     */
    protected ArrayList<byte[]> partitionBytes(byte[] bytes, BufferSize buffer) {
        int bufferValue = buffer.getValue();
        if (testingMode) bufferValue = bufferSize;
        ArrayList<byte[]> bytesArray = new ArrayList<>();
        int blockCount = 1;
        if (bytes.length <= bufferValue) {
            bytesArray.add(bytes);
        } else {
            byte[] tmp;
            while ((blockCount * bufferValue) < bytes.length) {
                tmp = new byte[bufferValue];
                System.arraycopy(bytes, ((blockCount - 1) * bufferValue), tmp, 0, bufferValue);
                bytesArray.add(tmp);
                blockCount++;
            }
            tmp = new byte[bufferValue];
            System.arraycopy(bytes, ((blockCount - 1) * bufferValue), tmp, 0, (bytes.length - ((blockCount - 1) * bufferValue)));
            bytesArray.add(tmp);
        }
        return bytesArray;
    }

    private class SystemChange {
        private char decToHexChars(int digit) {
            if (digit == 10) return 'A';
            else if (digit == 11) return 'B';
            else if (digit == 12) return 'C';
            else if (digit == 13) return 'D';
            else if (digit == 14) return 'E';
            else if (digit == 15) return 'F';
            else return ' ';
        }

        private int hexCharsToDec(char tmp) {
            if (tmp == 'A') return 10;
            else if (tmp == 'B') return 11;
            else if (tmp == 'C') return 12;
            else if (tmp == 'D') return 13;
            else if (tmp == 'E') return 14;
            else if (tmp == 'F') return 15;
            else return tmp - 48;
        }

        protected String toNumericSystem(int numberToChange, int system) {
            String ret = "";
            while (numberToChange != 0) {
                int tmp = numberToChange % system;
                if (tmp > 9) ret = decToHexChars(tmp) + ret;
                else ret = tmp + ret;
                numberToChange /= system;
            }
            ret += " ";
            return ret;
        }

        protected String toDecimal(String text, int system) {
            int ret = 0;
            int i = text.length() - 1;
            if (system == 16) {
                for (char b : text.toCharArray()) {
                    ret += hexCharsToDec(b) * Math.pow(system, i);
                    i--;
                }
            } else {
                for (int a : text.toCharArray()) {
                    ret += (a - 48) * Math.pow(system, i);
                    i--;
                }
            }
            return String.valueOf(ret);
        }
    }

    public enum ActionType {
        ENCODE,
        DECODE
    }

    public enum DigestSystem {
        UTF8,
        BIN,
        HEX
    }

    public enum BufferSize {
        TINY(8),
        SMALL(16),
        STANDARD(64),
        BIG(256),
        LARGE(512),
        OMGWTFBBQ(2048);

        private int bufferSize;

        BufferSize(int size) {
            bufferSize = size;
        }

        public int getValue() {
            return bufferSize;
        }
    }
}
