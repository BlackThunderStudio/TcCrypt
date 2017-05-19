/**
 * Created by rajmu on 17.03.16.
 */
package info.rajmundstaniek.TcCrypt;

import info.rajmundstaniek.TcCrypt.exception.DigestRuntimeException;
import info.rajmundstaniek.TcCrypt.exception.DigestSetupException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TcDigest {

    private class SystemChange{
        private char decToHexChars(int digit){
            if (digit == 10) return 'A';
            else if (digit == 11) return 'B';
            else if (digit == 12) return 'C';
            else if (digit == 13) return 'D';
            else if (digit == 14) return 'E';
            else if (digit == 15) return 'F';
            else return ' ';
        }
        private int hexCharsToDec(char tmp){
            if (tmp == 'A') return 10;
            else if (tmp == 'B') return 11;
            else if (tmp == 'C') return 12;
            else if (tmp == 'D') return 13;
            else if (tmp == 'E') return 14;
            else if (tmp == 'F') return 15;
            else return tmp - 48;
        }

        protected String toNumericSystem(int numberToChange, int system){
            String ret = "";
            while (numberToChange != 0){
                int tmp = numberToChange % system;
                if(tmp > 9) ret = decToHexChars(tmp) + ret;
                else ret = tmp + ret;
                numberToChange /= system;
            }
            ret += " ";
            return ret;
        }
        protected String toDecimal(String text, int system){
            int ret = 0;
            int i = text.length() - 1;
            if(system == 16){
                for (char b : text.toCharArray()){
                    ret += hexCharsToDec(b) * Math.pow(system, i);
                    i--;
                }
            } else {
                for(int a : text.toCharArray()){
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
        SMALL(16),
        STANDARD(64),
        BIG(256),
        LARGE(512);

        private int bufferSize;

        BufferSize(int size) {
            bufferSize = size;
        }

        public int getValue() {
            return bufferSize;
        }
    }

    private SystemChange systemChange;
    private ActionType actionType;
    private DigestSystem digestSystem;
    private String seed;
    public final int bufferSize;

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
     * Default constructor
     */
    public TcDigest(int bufferSize) {
        systemChange = new SystemChange();
        this.bufferSize = bufferSize;
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
     * Computes the data with the given hash code parallelly
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
     *Partitions the input for a fixed sized blocks for parellel processing of the data
     * @param string Input sequence
     * @return output array
     */
    private ArrayList<String> partition(String string, BufferSize buffer) {
        ArrayList<String> chars = new ArrayList<>();
        int blockCount = 1;
        if (string.length() <= buffer.getValue()) {
            chars.add(string);
        } else {
            while ((blockCount * buffer.getValue()) < string.length()) {
                chars.add(string.substring((blockCount - 1) * buffer.getValue(), blockCount * buffer.getValue()));
                blockCount++;
            }
            chars.add(string.substring((blockCount - 1) * buffer.getValue(), string.length()));
        }
        return chars;
    }
}
