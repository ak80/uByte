import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for class {@link Ubyte}
 * <p/>
 * Copyright [2015] [Alexander Koch]
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class UbyteTest {

  /**
   * Test that a int value is "forced" to be an unsigned byte value
   */
  @Test
  public void convertToUnsignedByte_toUnsignedByte_various() {
    // When Then
    assertThat(Ubyte.toUnsignedByte(0), is(0));
    assertThat(Ubyte.toUnsignedByte(255), is(255));
    assertThat(Ubyte.toUnsignedByte(-1), is(255));
    assertThat(Ubyte.toUnsignedByte(256), is(0));
    assertThat(Ubyte.toUnsignedByte(257), is(1));

    assertThat(Ubyte.toUnsignedByte((byte) 0b00000000), is(0b00000000));
    assertThat(Ubyte.toUnsignedByte((byte) 0b00000001), is(0b00000001));
    assertThat(Ubyte.toUnsignedByte((byte) 0b10000000), is(0b10000000));
    assertThat(Ubyte.toUnsignedByte((byte) 0b11111111), is(0b11111111));
    assertThat(Ubyte.toUnsignedByte((byte) 0b10101010), is(0b10101010));

    assertThat(Ubyte.toSignedByte(1), is((byte) 1));
    assertThat(Ubyte.toSignedByte(127), is((byte) 127));
    assertThat(Ubyte.toSignedByte(-128), is((byte) 128));
    assertThat(Ubyte.toSignedByte(-1), is((byte) 255));
  }

  /**
   * Test that a unsigned byte stored in an int is converted to signed byte
   */
  @Test
  public void convertToSignedByte_toUnsignedByte_various() {
    // When Then
    assertThat(Ubyte.toSignedByte(0b00000000), is((byte) 0b00000000));
    assertThat(Ubyte.toSignedByte(0b00000001), is((byte) 0b00000001));
    assertThat(Ubyte.toSignedByte(0b10000000), is((byte) 0b10000000));
    assertThat(Ubyte.toSignedByte(0b11111111), is((byte) 0b11111111));
    assertThat(Ubyte.toSignedByte(0b10101010), is((byte) 0b10101010));

    assertThat(Ubyte.toSignedByte(1), is((byte) 1));
    assertThat(Ubyte.toSignedByte(127), is((byte) 127));
    assertThat(Ubyte.toSignedByte(128), is((byte) -128));
    assertThat(Ubyte.toSignedByte(255), is((byte) -1));
  }

  /**
   * Test that a number of type int is correctly determined to be either a byte or not depending on whether its value
   * is in the valid byte range, i.e. from 0 to 255
   */
  @Test
  public void verifyInt_isUnsignedByte_various() {
    // When Then
    assertThat(Ubyte.isUnsignedByte(0), is(true));
    assertThat(Ubyte.isUnsignedByte(1), is(true));
    assertThat(Ubyte.isUnsignedByte(255), is(true));
    assertThat(Ubyte.isUnsignedByte(256), is(false));
    assertThat(Ubyte.isUnsignedByte(-1), is(false));
  }

  /**
   * Test that the rightmost eight bits of an int value are correctly returned
   */
  @Test
  public void verifyLowByte_getLowByteFromDoubleByte_various() {
    // When Then
    assertThat(Ubyte.getLowByteFromDoubleByte(0), is(0));
    assertThat(Ubyte.getLowByteFromDoubleByte(1), is(1));
    assertThat(Ubyte.getLowByteFromDoubleByte(255), is(255));
    assertThat(Ubyte.getLowByteFromDoubleByte(-1), is(255));
    assertThat(Ubyte.getLowByteFromDoubleByte(256), is(0));
    assertThat(Ubyte.getLowByteFromDoubleByte(257), is(1));
  }

  /**
   * Test that the second most bits 9-16 of an int value are correctly returned
   */
  @Test
  public void verifyHighByte_getHighByteFromDoubleByte_various() {
    // When Then
    assertThat(Ubyte.getHighByteFromDoubleByte(0), is(0));
    assertThat(Ubyte.getHighByteFromDoubleByte(255), is(0));
    assertThat(Ubyte.getHighByteFromDoubleByte(-1), is(255));
    assertThat(Ubyte.getHighByteFromDoubleByte(256), is(1));
    assertThat(Ubyte.getHighByteFromDoubleByte(1024), is(4));
    assertThat(Ubyte.getHighByteFromDoubleByte(0xff00), is(255));
  }

  /**
   * Test combining two unsigned byte values into a two byte value
   */
  @Test
  public void verifyGetDoubleByte_combineTwoBytes_various() {
    // When Then
    assertThat(Ubyte.combineTwoBytes(0x12, 0x34), is(0x1234));
  }


  /**
   * Test combining four unsigned byte values into a four byte value
   */
  @Test
  public void verifyGetQuadByte_combineFourBytes_various() {
    // When Then
    assertThat(Ubyte.combineFourBytes(0x12, 0x34, 0x56, 0x78), is(0x12345678L));
  }


  /**
   * Test formatting an unsigned byte to a hex string
   */
  @Test
  public void toHexByte_unsignedByte_various() {
    // When Then
    assertThat(Ubyte.formatByteAsHex(0), is("0x00"));
    assertThat(Ubyte.formatByteAsHex(1), is("0x01"));
    assertThat(Ubyte.formatByteAsHex(255), is("0xff"));

    assertThat(Ubyte.formatByteAsHex(-1), is("0xff"));
    assertThat(Ubyte.formatByteAsHex(256), is("0x00"));
    assertThat(Ubyte.formatByteAsHex(1024), is("0x00"));
  }

  /**
   * Test formatting an int holding two bytes a hex string
   */
  @Test
  public void toHexByte_doubleByte_various() {
    // When Then
    assertThat(Ubyte.formatDoubleByteAsHex(0), is("0x0000"));
    assertThat(Ubyte.formatDoubleByteAsHex(1), is("0x0001"));
    assertThat(Ubyte.formatDoubleByteAsHex(255), is("0x00ff"));

    assertThat(Ubyte.formatDoubleByteAsHex(-1), is("0xffff"));
    assertThat(Ubyte.formatDoubleByteAsHex(256), is("0x0100"));
    assertThat(Ubyte.formatDoubleByteAsHex(1024), is("0x0400"));

    assertThat(Ubyte.formatDoubleByteAsHex(0x1234), is("0x1234"));
  }

  /**
   * Test formatting an int holding four bytes to a hex string
   */
  @Test
  public void toHexByte_quadByte_various() {
    // When Then
    assertThat(Ubyte.formatQuadByteAsHex(0L), is("0x00000000"));
    assertThat(Ubyte.formatQuadByteAsHex(1L), is("0x00000001"));
    assertThat(Ubyte.formatQuadByteAsHex(255L), is("0x000000ff"));
    assertThat(Ubyte.formatQuadByteAsHex(0x0000ffff), is("0x0000ffff"));
    assertThat(Ubyte.formatQuadByteAsHex(0x12345678), is("0x12345678"));
    assertThat(Ubyte.formatQuadByteAsHex(0xffffffff), is("0xffffffff"));
  }

  /**
   * Test formatting an array holding unsigned bytes to a hex string
   */
  @Test
  public void toHexString_byteArray_various() {
    // When Then
    assertThat(Ubyte.formatUnsignedByteArray(new int[]{0, 1, 255, -1, 256, 1024}), is("{ 0x00, 0x01, 0xff, 0xff, 0x00, 0x00 }"));
    assertThat(Ubyte.formatUnsignedByteArray(new int[]{1}), is("{ 0x01 }"));
    assertThat(Ubyte.formatUnsignedByteArray(new int[]{}), is("{ }"));
  }

  /**
   * Test the detection of a flag bits, whether it is set or not
   */
  @Test
  public void flag_bitIsSet_various() {
    // When Then
    assertThat(Ubyte.bitIsSet(0b10000000, Bits.BIT_7), is(true));
    assertThat(Ubyte.bitIsSet(0b01000000, Bits.BIT_6), is(true));
    assertThat(Ubyte.bitIsSet(0b00100000, Bits.BIT_5), is(true));
    assertThat(Ubyte.bitIsSet(0b00010000, Bits.BIT_4), is(true));
    assertThat(Ubyte.bitIsSet(0b00001000, Bits.BIT_3), is(true));
    assertThat(Ubyte.bitIsSet(0b00000100, Bits.BIT_2), is(true));
    assertThat(Ubyte.bitIsSet(0b00000010, Bits.BIT_1), is(true));
    assertThat(Ubyte.bitIsSet(0b00000001, Bits.BIT_0), is(true));

    assertThat(Ubyte.bitIsSet(0b01111111, Bits.BIT_7), is(false));
    assertThat(Ubyte.bitIsSet(0b10111111, Bits.BIT_6), is(false));
    assertThat(Ubyte.bitIsSet(0b11011111, Bits.BIT_5), is(false));
    assertThat(Ubyte.bitIsSet(0b11101111, Bits.BIT_4), is(false));
    assertThat(Ubyte.bitIsSet(0b11110111, Bits.BIT_3), is(false));
    assertThat(Ubyte.bitIsSet(0b11111011, Bits.BIT_2), is(false));
    assertThat(Ubyte.bitIsSet(0b11111101, Bits.BIT_1), is(false));
    assertThat(Ubyte.bitIsSet(0b11111110, Bits.BIT_0), is(false));
  }

  /**
   * Test setting a flag bit based on condition
   */
  @Test
  public void mask_setFlag_various() {
    // When Then
    assertThat(Ubyte.setFlag(0b00000000, Bits.BIT_7, true), is(0b10000000));
    assertThat(Ubyte.setFlag(0b00000000, Bits.BIT_6, true), is(0b01000000));
    assertThat(Ubyte.setFlag(0b00000000, Bits.BIT_5, true), is(0b00100000));
    assertThat(Ubyte.setFlag(0b00000000, Bits.BIT_4, true), is(0b00010000));
    assertThat(Ubyte.setFlag(0b00000000, Bits.BIT_3, true), is(0b00001000));
    assertThat(Ubyte.setFlag(0b00000000, Bits.BIT_2, true), is(0b00000100));
    assertThat(Ubyte.setFlag(0b00000000, Bits.BIT_1, true), is(0b00000010));
    assertThat(Ubyte.setFlag(0b00000000, Bits.BIT_0, true), is(0b00000001));

    assertThat(Ubyte.setFlag(0b11111111, Bits.BIT_7, false), is(0b01111111));
    assertThat(Ubyte.setFlag(0b11111111, Bits.BIT_6, false), is(0b10111111));
    assertThat(Ubyte.setFlag(0b11111111, Bits.BIT_5, false), is(0b11011111));
    assertThat(Ubyte.setFlag(0b11111111, Bits.BIT_4, false), is(0b11101111));
    assertThat(Ubyte.setFlag(0b11111111, Bits.BIT_3, false), is(0b11110111));
    assertThat(Ubyte.setFlag(0b11111111, Bits.BIT_2, false), is(0b11111011));
    assertThat(Ubyte.setFlag(0b11111111, Bits.BIT_1, false), is(0b11111101));
    assertThat(Ubyte.setFlag(0b11111111, Bits.BIT_0, false), is(0b11111110));
  }


  /**
   * Test setting a flag bit
   */
  @Test
  public void mask_setBit_various() {
    // When Then
    assertThat(Ubyte.setBit(0b00000000, Bits.BIT_7), is(0b10000000));
    assertThat(Ubyte.setBit(0b00000000, Bits.BIT_6), is(0b01000000));
    assertThat(Ubyte.setBit(0b00000000, Bits.BIT_5), is(0b00100000));
    assertThat(Ubyte.setBit(0b00000000, Bits.BIT_4), is(0b00010000));
    assertThat(Ubyte.setBit(0b00000000, Bits.BIT_3), is(0b00001000));
    assertThat(Ubyte.setBit(0b00000000, Bits.BIT_2), is(0b00000100));
    assertThat(Ubyte.setBit(0b00000000, Bits.BIT_1), is(0b00000010));
    assertThat(Ubyte.setBit(0b00000000, Bits.BIT_0), is(0b00000001));
  }

  /**
   * Test storing a number bit by bit with given mask
   */
  @Test
  public void bitNumberStoring_storeUnderMask_various() {
    // When Then
    assertThat(Ubyte.storeUnderMask(0b00000000, 0b00000000, 0b0000), is(0b00000000));
    assertThat(Ubyte.storeUnderMask(0b11111111, 0b00000000, 0b0000), is(0b11111111));

    assertThat(Ubyte.storeUnderMask(0b11111111, 0b11111111, 0b00000000), is(0b00000000));
    assertThat(Ubyte.storeUnderMask(0b00000000, 0b11111111, 0b11111111), is(0b11111111));

    assertThat(Ubyte.storeUnderMask(0b00000000, 0b00001100, 0b11), is(0b00001100));
    assertThat(Ubyte.storeUnderMask(0b00000000, 0b00001100, 0b00), is(0b00000000));
    assertThat(Ubyte.storeUnderMask(0b11111111, 0b00001100, 0b00), is(0b11110011));
    assertThat(Ubyte.storeUnderMask(0b00000000, 0b00001100, 0b10), is(0b00001000));
    assertThat(Ubyte.storeUnderMask(0b00000000, 0b00001100, 0b01), is(0b00000100));
  }

  /**
   * Test getting a number bit by bit with given mask
   */
  @Test
  public void bitNumberStoring_getWithMask_various() {
    // When Then
    assertThat(Ubyte.getWithMask(0b00000000, 0b00000000), is(0b00000000));
    assertThat(Ubyte.getWithMask(0b11111111, 0b00000000), is(0b00000000));
    assertThat(Ubyte.getWithMask(0b00000000, 0b11111111), is(0b00000000));
    assertThat(Ubyte.getWithMask(0b11111111, 0b11111111), is(0b11111111));

    assertThat(Ubyte.getWithMask(0b11111100, 0b00000011), is(0b00000000));
    assertThat(Ubyte.getWithMask(0b11111111, 0b00000011), is(0b00000011));
    assertThat(Ubyte.getWithMask(0b00000011, 0b00000011), is(0b00000011));

    assertThat(Ubyte.getWithMask(0b11111100, 0b00001111), is(0b00001100));
    assertThat(Ubyte.getWithMask(0b11111111, 0b00001111), is(0b00001111));
    assertThat(Ubyte.getWithMask(0b00000011, 0b00001111), is(0b00000011));

    assertThat(Ubyte.getWithMask(0b11111100, 0b00001010), is(0b00001000));
    assertThat(Ubyte.getWithMask(0b11111111, 0b00001010), is(0b00001010));
    assertThat(Ubyte.getWithMask(0b00000011, 0b00001010), is(0b00000010));
  }

  /**
   * Test that each int in an array of ints is correctly determined to be either a byte or not depending on whether its value
   * is in the valid byte range, i.e. from 0 to 255
   */
  @Test
  public void verifyIntArray_isUnsignedByteArray_various() {
    // When Then
    assertThat(Ubyte.isUnsignedByteArray(new int[]{0, 1, 25}), is(true));
    assertThat(Ubyte.isUnsignedByteArray(new int[]{-1, 0}), is(false));
    assertThat(Ubyte.isUnsignedByteArray(new int[]{0, 256}), is(false));
  }

  /**
   * Test that an array of int value is converted to be an array of signed byte value
   */
  @Test
  public void convertToSignedByteArray_toSignedByteArray_various() {
    // When Then
    assertThat(Ubyte.toSignedByteArray(
        new int[]{0, 1, 127, 255, 256, 257, -1, -128}), is(
        new byte[]{0, 1, 127, -1, 0, 1, -1, -128}));
  }

  /**
   * Test that an array of signed byte values is converted to be an array of unsigned byte values stored as ints
   */
  @Test
  public void convertToUnsignedByteArray_toUnsignedByteArray_various() {
    // When Then
    assertThat(Ubyte.toUnsignedByteArray(new byte[]{0, 1, 127, -1, 0, 1, -1, -128}),
        is(new int[]{0, 1, 127, 255, 0, 1, 255, 128}));
  }

}
