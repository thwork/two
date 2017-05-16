import java.util.ArrayDeque;
import java.util.Deque;

public class BowlingGame {

	private static final String strike = "X", spare = "/", miss = "-";


	private int getNum(String s) {
		if (s.equals(strike))
			return 10;
		if (s.equals(miss))
			return 0;
		return Integer.parseInt(s);
	}

	public int getBowlingScore(String bowlingCode) {
		String[] m = bowlingCode.split("\\|");
		int len = m.length;
		int sum = 0;
		Deque<Integer> d = new ArrayDeque<>();
		boolean flag = true;
		for (int i = 0; i < len; i++) {
			String cur = m[i];
			if (cur.length() == 0){
				flag = false;
				continue;
			}
			if (i != len - 1 &&cur.equals(strike)) {
				int size = d.size();
				for (int j = 0; j < size; j++) {
					int p = d.poll();
					if (p == 2) {
						d.add(1);
					}
					sum += 10;
				}
				sum += 10;
				d.add(2);
				continue;
			}
			if (i != len - 1 && (cur.charAt(1) + "").equals(spare)) {
				int size = d.size();
				for (int j = 0; j < size; j++) {
					int p = d.poll();
					if (p == 2) {
						sum += 10;
					} else {
						sum += getNum(cur.charAt(0) + "");
					}
				}
				sum += 10;
				d.add(1);
				continue;
			}
			int size = d.size();
			for (int j = 0; j < size; j++) {
				int p = d.poll();
				if (p == 2) {
					if ((cur.length() > 1 && ((cur.charAt(1) + "")
									.equals(spare))))
						sum += 10;
					else
						sum += getNum(cur.charAt(0) + "")
								+ getNum(cur.charAt(1) + "");
				} else {
					if (cur.equals(strike))
						sum += 10;
					else
						sum += getNum(cur.charAt(0) + "");
				}
			}
			if (flag)
				sum += getNum(cur.charAt(0) + "") + getNum(cur.charAt(1) + "");
		}
		return sum;
	}
}
