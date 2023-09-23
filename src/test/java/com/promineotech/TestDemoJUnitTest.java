package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
class TestDemoJUnitTest {

	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource ("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b,
			int expected, boolean expectException) {
		if (!expectException) {

			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);

		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b))
			.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4,5)).isEqualTo(9);
		assertThatThrownBy(() -> testDemo.addPositive(-1, 1))
		.isInstanceOf(IllegalArgumentException.class);
		
		assertThat(testDemo.addPositive(40,50)).isEqualTo(90);
	}
	
	@ParameterizedTest
	@MethodSource ("com.promineotech.TestDemoJUnitTest#argumentsForDivisbleByTwo")
	void assertThatDivisbleByTwoWorksAsIntended(int a, String expected,
			boolean expectException) {
		// Given: a number to test
		if (expectException == false) {
		// When: the method to mod the number by two is called
		// Then: The number is divisible by 2 with no remainder
		// and a string reflecting that is returned
			assertThat(testDemo.divisibleByTwo(a)).isEqualTo(expected);
		}else {
		// When: the method to mod the number by two is called
		// Then: the number isn't divisible by 2 and
		// an illegal argument exception is returned
			assertThatThrownBy(() -> testDemo.divisibleByTwo(a))
			.isInstanceOf(IllegalArgumentException.class);
		}
	}
  
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSqaured = mockDemo.randomNumberSquared();
		assertThat(fiveSqaured).isEqualTo(25);
	}
	
	static Stream<Arguments> argumentsForAddPositive(){
	// @formatter:off
	return Stream.of(
			arguments(2, 4, 6, false),		
			arguments(0, 0, 0, true),
			arguments(-2, -4, -6, true),
			arguments(1, -1, 0, true),
			arguments(-1, 1, 0, true)
			);
	// @formatter:on
}
	static Stream<Arguments> argumentsForDivisbleByTwo() {
		// @formatter:off
		return Stream.of(
				arguments(2, String.valueOf(2) + " is divisible by two", false),
				arguments(3, String.valueOf(3) + " is not divisible by two", true),
			//	*Didn't need to give a value for an Illegal Argument, but I did it
			//	 to show that the test will pass regardless.	
				arguments(1, null, true),
				arguments(-1, null, true),
				arguments(-2, String.valueOf(-2) + " is divisible by two", false)
				);
		// @formatter:on
	}
}
