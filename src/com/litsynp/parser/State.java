package com.litsynp.parser;

import java.util.HashMap;
import java.util.Map;

/***
 * Defines states in the SLR parsing table of the syntax analyzer.
 */
public enum State {
	Q0(0), Q1(1), Q2(2), Q3(3), Q4(4), Q5(5), Q6(6), Q7(7), Q8(8), Q9(9), Q10(10), Q11(11), Q12(12), Q13(13), Q14(14),
	Q15(15), Q16(16), Q17(17), Q18(18), Q19(19), Q20(20), Q21(21), Q22(22), Q23(23), Q24(24), Q25(25), Q26(26), Q27(27),
	Q28(28), Q29(29), Q30(30), Q31(31), Q32(32), Q33(33), Q34(34), Q35(35), Q36(36), Q37(37), Q38(38), Q39(39), Q40(40),
	Q41(41), Q42(42), Q43(43), Q44(44), Q45(45), Q46(46), Q47(47), Q48(48), Q49(49), Q50(50), Q51(51), Q52(52), Q53(53),
	Q54(54), Q55(55), Q56(56), Q57(57), Q58(58), Q59(59), Q60(60), Q61(61), Q62(62), Q63(63), Q64(64), Q65(65), Q66(66),
	Q67(67), Q68(68), Q69(69), Q70(70), Q71(71), Q72(72), Q73(73), Q74(74), Q75(75), Q76(76), Q77(77), Q78(78), Q79(79),
	Q80(80), Q81(81), Q82(82), Q83(83), Q84(84);

	/***
	 * State number.
	 */
	private int stateNumber;

	/***
	 * Hash map to find a state by the state number.
	 */
	private static Map<Integer, State> map = new HashMap<Integer, State>();

	static {
		for (State stateEnum : State.values()) {
			map.put(stateEnum.stateNumber, stateEnum);
		}
	}

	/***
	 * Initializes the state number.
	 * 
	 * @param value the state number
	 */
	private State(final int stateNumber) {
		this.stateNumber = stateNumber;
	}

	/***
	 * Finds the state by the state number.
	 * 
	 * @param value the state number
	 * @return the state enum
	 */
	public static State valueOf(int value) {
		return map.get(value);
	}

	public String toString() {
		return "Q" + Integer.toString(stateNumber);
	}
}
