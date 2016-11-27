# uByte [![Travis master](https://img.shields.io/travis/ak80/uByte/master.svg)](https://travis-ci.org/ak80/uByte) [![Coverage Status](https://coveralls.io/repos/github/ak80/uByte/badge.svg?branch=master)](https://coveralls.io/github/ak80/uByte?branch=master) [![Codacy grade](https://img.shields.io/codacy/grade/858eb2298740414fb3fe8773b1fe8ff8/master.svg?maxAge=3600)](https://www.codacy.com/app/josef-koch/uByte/dashboard) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.ak80.ubyte/uByte/badge.svg?style=flat-square)](https://maven-badges.herokuapp.com/maven-central/org.ak80.ubyte/uByte/) [![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Byte Utilities
 
The Java language does not provide a type for unsigned byte values. It is easier to work with 
signed int values and "just pretentd" that they are unsigned byte. The JVM uses 32 
bit values anyway to store a byte, so no space is saved with using byte.

In addition to conversion methods to and from signed byte to an int holding unsigned byte, this class provides methods
to deal with values of multiple bytes, methods for formatting hex numbers, methods for setting and 
testing bit flags or storing and retrieving numeric values bit by
bit. 

Use Cases can be found in the [tests](/src/test/java/UbyteTest.java)

## Conversion methods

Converting a signed byte to an int holding an unsigned byte:
<pre>
  Ubyte.toUnsignedByte(0);   // returns 0
  Ubyte.toUnsignedByte(255); // returns 255
  Ubyte.toUnsignedByte(-1);  // returns 255
  Ubyte.toUnsignedByte(256); // returns 0
  Ubyte.toUnsignedByte(257); // returns 1
</pre>

This works well for type byte but also for a type int instead of byte. In that case the 24 
highest bits in the int are set to zero.

You can convert back to a signed byte:

<pre>
    Ubyte.toSignedByte(1);    // returns 1;
    Ubyte.toSignedByte(127);  // returns 127;
    Ubyte.toSignedByte(128);  // returns -128;
    Ubyte.toSignedByte(255);  // returns -1;
</pre>

You can convert whole arrays:
<pre>
   Ubyte.toSignedByteArray(new int[]{0, 1, 2, 3}); // returns byte[]
   Ubyte.toUnsignedByteArray(new byte[]{0, 1, -1}); // returns int[]
</pre>

You can test if an int already is an unsigned byte, i.e. in the range of 0 - 255, and do the same for an array
<pre>
  Ubyte.isUnsignedByte(0);    // returns true
  Ubyte.isUnsignedByte(-1);   // returns false
  Ubyte.isUnsignedByte(255);  // returns false
  
  Ubyte.isUnsignedByteArray(new int[]{0, 1, 25}); // returns true;
  Ubyte.isUnsignedByteArray(new int[]{-1, 0});    // returns false;
  Ubyte.isUnsignedByteArray(new int[]{0, 256});   // returns false;
</pre>

## Multiple byte handling

You can also treat an int as containing an unsigned two byte value or four byte value. A four byte value must be stored
in a long as the int is not big enough to hold it unsigned.

Split multiple bytes:
<pre>
    Ubyte.getLowByteFromDoubleByte(0x1234);     // returns 0x34
    Ubyte.getHighByteFromDoubleByte(0x1234);    // returns 0x12
    Ubyte.getBytesFr````omQuadByte(0x12345678L);    // return { 0x12, 0x34, 0x56, 0x78 }
</pre>

Combine multiple bytes
<pre>
    Ubyte.combineTwoBytes(0x12, 0x34);              // returns 0x1234
    Ubyte.combineFourBytes(0x12, 0x34, 0x56, 0x78); // returns 0x12345678
</pre>

## Formatting

* Ubyte.formatByteAsHex()
* Ubyte.formatDoubleByteAsHex()
* Ubyte.formatQuadByteAsHex()
* Ubyte.toIso88591String(int[] array)

See the [tests](/src/test/java/UbyteTest.java) or the javadoc!

## Array methods

* Ubyte.formatUnsignedByteArray()
* Ubyte.parseUnsignedByteArray

See the [tests](/src/test/java/UbyteTest.java) or the javadoc!

## Flags and masks

* Ubyte.bitIsSet()
* Ubyte.setFlag()
* Ubyte.storeUnderMask()
* Ubyte.getWithMask()

See the [tests](/src/test/java/UbyteTest.java) or the javadoc!
