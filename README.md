# TcCrypt
Simple library encoding / decoding strings and byte arrays with a given hash code and encrption output method


## Author
[Rajmund Staniek](https://github.com/BlackThunderStudio)

Created on: *17.03.2017*

## Download
[TcCrypt version 0.9.12](http://remix1436.ct8.pl/resources/misc/TcCrypt-0.9.12.jar)

## [TcDigest](src/info/rajmundstaniek/TcCrypt/TcDigest.java)
Within this class you can process chunks of text or raw byte arrays in order to encrypt or decrypt its contents. Algorithm used there is very simple, yet not so obvious, which with the recent implementation of concurrent data processing makes it very fast to execute and not so easy to crack.

#### List of essential public methods:
- `public static byte[] downloadUrl(URL toDownload);`
- `public String processParallel(String input, String seed, ActionType flag, DigestSystem system);`
- `public String processParallel(String input, String seed, ActionType flag, DigestSystem system, BufferSize bufferSize);`
- `public byte[] processParallelBytes(byte[] inputBytes, String seed, ActionType flag, DigestSystem system);' **_WARNING!!!_ This method is not fully implemented yet**

## [CryptoUtils](src/info/rajmundstaniek/TcCrypt/CryptoUtils.java)
Support class for AES encrption implementation.

#### Public methods featured:
- `public static void encrypt(String key, File inputFile, File outputFile);`
- `public static void decrypt(String key, File inputFile, File outputFile);`
