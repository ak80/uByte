# uByte
Byte Utilities
 
The Java language does not provide a type for unsigned byte values. It is easier to work with signed int values treating  them as to be unsigned byte. The JVM uses 32 
bit values anyway to store a byte, so no space is saved with using byte.

In addition to conversion method to and from signed byte to an int holding unsigned byte, this class provides formatting of bytes, double bytes or quadruple bytes, and 
methods for setting  testing bit flags or storing numeric value into a byte at a given position

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

This work well for type byte but also for an int instead of byte. In that case the 24 highest bits in the int are set to zero.

You can convert back to a a signed byte:

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
  Ubyte.isUnsignedByteArray(new int[]{0, 256}),   // returns false;
</pre>

## Missing Documentation

See the [tests](/src/test/java/UbyteTest.java) or the javadoc!

For multi Byte Handling
* Ubyte.getLowByteFromDoubleByte()
* Ubyte.getHighByteFromDoubleByte()
* Ubyte.combineTwoBytes()
* Ubyte.combineFourBytes()

For formatting:
* Ubyte.formatByteAsHex()
* Ubyte.formatDoubleByteAsHex()
* Ubyte.formatQuadByteAsHex()
* Ubyte.formatUnsignedByteArray()

For flag handling and masked storing:
* Ubyte.bitIsSet()
* Ubyte.setFlag()
* Ubyte.storeUnderMask()
* Ubyte.getWithMask()




