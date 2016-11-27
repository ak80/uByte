package org.ak80.ubyte;

import org.ak80.ubyte.Bits;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

@SuppressWarnings("PMD.MethodNamingConventions")
public class BitsTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testGetBit() {
    assertThat(Bits.getBit(0),is(Bits.BIT_0));
    assertThat(Bits.getBit(1),is(Bits.BIT_1));
    assertThat(Bits.getBit(2),is(Bits.BIT_2));
    assertThat(Bits.getBit(3),is(Bits.BIT_3));
    assertThat(Bits.getBit(4),is(Bits.BIT_4));
    assertThat(Bits.getBit(5),is(Bits.BIT_5));
    assertThat(Bits.getBit(6),is(Bits.BIT_6));
    assertThat(Bits.getBit(7),is(Bits.BIT_7));
  }

  @Test
  public void getMask() {
    assertThat(Bits.BIT_0.getMask(),is(1));
    assertThat(Bits.BIT_1.getMask(),is(2));
    assertThat(Bits.BIT_2.getMask(),is(4));
    assertThat(Bits.BIT_3.getMask(),is(8));
    assertThat(Bits.BIT_4.getMask(),is(16));
    assertThat(Bits.BIT_5.getMask(),is(32));
    assertThat(Bits.BIT_6.getMask(),is(64));
    assertThat(Bits.BIT_7.getMask(),is(128));
  }

  @Test
  public void getBit_lessThanZero_throwsException() {
    expectedException.expect(IllegalArgumentException.class);
    Bits.getBit(-1);
    fail("never reached");
  }

  @Test
  public void getBit_greaterThanSeven_throwsException() {
    expectedException.expect(IllegalArgumentException.class);
    Bits.getBit(8);
    fail("never reached");
  }


}
