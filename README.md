# uByte [![Travis master](https://img.shields.io/travis/ak80/uByte/master.svg?maxAge=3600)](https://travis-ci.org/ak80/uByte) [![Coverage Status](https://coveralls.io/repos/github/ak80/uByte/badge.svg?maxAge=3600)](https://coveralls.io/github/ak80/uByte?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/f8066f220fe74d27bacf984c61f5d5d4)](https://www.codacy.com/app/josef-koch/uByte?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ak80/uByte&amp;utm_campaign=Badge_Grade) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.ak80.ubyte/uByte/badge.svg?style=flat-square)](https://maven-badges.herokuapp.com/maven-central/org.ak80.ubyte/uByte/) [![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Byte Utilities
 
The Java language does not provide a type for unsigned byte values. It is easier to work with 
signed int values and "just pretentd" that they are unsigned byte. The JVM uses 32 
bit values anyway to store a byte, so no space is saved with using byte.

In addition to conversion methods to and from signed byte to an int holding unsigned byte, this class provides methods
to deal with values of multiple bytes, methods for formatting hex numbers, methods for setting and 
testing bit flags or storing and retrieving numeric values bit by
bit. 

Use Cases can be found in the [tests](/src/test/java/org/ak80/ubyte/UbyteTest.java)

## Conversion methods

Converting a signed byte to an int holding an unsigned byte:
<pre>
  org.ak80.ubyte.Ubyte.toUnsignedByte(0);   // returns 0
  org.ak80.ubyte.Ubyte.toUnsignedByte(255); // returns 255
  org.ak80.ubyte.Ubyte.toUnsignedByte(-1);  // returns 255
  org.ak80.ubyte.Ubyte.toUnsignedByte(256); // returns 0
  org.ak80.ubyte.Ubyte.toUnsignedByte(257); // returns 1
</pre>

This works well for type byte but also for a type int instead of byte. In that case the 24 
highest bits in the int are set to zero.

You can convert back to a signed byte:

<pre>
    org.ak80.ubyte.Ubyte.toSignedByte(1);    // returns 1;
    org.ak80.ubyte.Ubyte.toSignedByte(127);  // returns 127;
    org.ak80.ubyte.Ubyte.toSignedByte(128);  // returns -128;
    org.ak80.ubyte.Ubyte.toSignedByte(255);  // returns -1;
</pre>

You can convert whole arrays:
<pre>
   org.ak80.ubyte.Ubyte.toSignedByteArray(new int[]{0, 1, 2, 3}); // returns byte[]
   org.ak80.ubyte.Ubyte.toUnsignedByteArray(new byte[]{0, 1, -1}); // returns int[]
</pre>

You can test if an int already is an unsigned byte, i.e. in the range of 0 - 255, and do the same for an array
<pre>
  org.ak80.ubyte.Ubyte.isUnsignedByte(0);    // returns true
  org.ak80.ubyte.Ubyte.isUnsignedByte(-1);   // returns false
  org.ak80.ubyte.Ubyte.isUnsignedByte(255);  // returns false
  
  org.ak80.ubyte.Ubyte.isUnsignedByteArray(new int[]{0, 1, 25}); // returns true;
  org.ak80.ubyte.Ubyte.isUnsignedByteArray(new int[]{-1, 0});    // returns false;
  org.ak80.ubyte.Ubyte.isUnsignedByteArray(new int[]{0, 256});   // returns false;
</pre>

## Multiple byte handling

You can also treat an int as containing an unsigned two byte value or four byte value. A four byte value must be stored
in a long as the int is not big enough to hold it unsigned.

Split multiple bytes:
<pre>
    org.ak80.ubyte.Ubyte.getLowByteFromDoubleByte(0x1234);     // returns 0x34
    org.ak80.ubyte.Ubyte.getHighByteFromDoubleByte(0x1234);    // returns 0x12
    org.ak80.ubyte.Ubyte.getBytesFromQuadByte(0x12345678L);    // return { 0x12, 0x34, 0x56, 0x78 }
</pre>

Combine multiple bytes
<pre>
    org.ak80.ubyte.Ubyte.combineTwoBytes(0x12, 0x34);              // returns 0x1234
    org.ak80.ubyte.Ubyte.combineFourBytes(0x12, 0x34, 0x56, 0x78); // returns 0x12345678
</pre>

## Formatting

* org.ak80.ubyte.Ubyte.formatByteAsHex()
* org.ak80.ubyte.Ubyte.formatDoubleByteAsHex()
* org.ak80.ubyte.Ubyte.formatQuadByteAsHex()
* org.ak80.ubyte.Ubyte.toIso88591String(int[] array)

See the [tests](/src/test/java/org/ak80/ubyte/UbyteTest.java) or the javadoc!

## Array methods

* org.ak80.ubyte.Ubyte.formatUnsignedByteArray()
* org.ak80.ubyte.Ubyte.parseUnsignedByteArray

See the [tests](/src/test/java/org/ak80/ubyte/org.ak80.ubyte.UbyteTest.java) or the javadoc!

## Flags and masks

* org.ak80.ubyte.Ubyte.bitIsSet()
* org.ak80.ubyte.Ubyte.setFlag()
* org.ak80.ubyte.Ubyte.storeUnderMask()
* org.ak80.ubyte.Ubyte.getWithMask()

See the [tests](/src/test/java/org/ak80/ubyte/UbyteTest.java) or the javadoc!
