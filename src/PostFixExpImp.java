import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.regex.Pattern;

public class PostFixExpImp implements PostFixExp {
	private Queue<String> tmp1;

	public PostFixExpImp() {
		tmp1 = new LinkedQueue<String>();

	}

	@Override
	public void setExp(String exp) {
		if (exp == null)
			return;

		String[] c = exp.split(" ");

		int x = c.length;
		for (int i = 0; i < x; i++) {
			if (!c[i].equals(" ")) {
				tmp1.enqueue(c[i]);
			}
		}

	}

	@Override
	public String getExp() {

		StringBuilder sb = new StringBuilder();

		String exp = "";
		for (int i = 0; i < tmp1.length(); i++) {
			String t = tmp1.serve();
			tmp1.enqueue(t);
			sb.append(t + " ");
			sb.append(" ");
		}
		exp = sb.toString();
		return exp;
	}

	@Override
	public Stack<Integer> evaluate(int k) {
		if (tmp1.length() == 0)
			return null;

		Queue<String> tmp = new LinkedQueue<>();
		for (int i = 0; i < tmp1.length(); i++) {
			String t = tmp1.serve();
			tmp1.enqueue(t);
			tmp.enqueue(t);
		}

		Stack<Integer> v = new LinkedStack<Integer>();
		Stack<Integer> value = new LinkedStack<Integer>();
		int x = 0;
		int i = 0;
		try {

			if (tmp.length() > 1 /* && n >= k */) {
				for (i = 0; i < tmp.length(); i++, x++) {

					if (x < k) {
						String com = tmp.serve();
						tmp.enqueue(com);
						switch (com) {
						case "*":
							value.push(value.pop() * value.pop());
							break;
						case "+":
							value.push(value.pop() + value.pop());
							break;
						case "-":
							int d = value.pop();
							int d1 = value.pop();
							value.push(d1 - d);
							break;
						case "/":
							int q = value.pop();
							int q1 = value.pop();
							if (q == 0)
								return null;
							value.push(q1 / q);
							break;
						case "%":
							int qq = value.pop();
							int qqq = value.pop();
							if (qq == 0)
								return null;
							value.push(qqq % qq);
							break;
						case "<":
							int f = value.pop();
							int s = value.pop();
							if (s < f)
								value.push(1);
							else
								value.push(0);
							break;
						case "<=":
							int f1 = value.pop();
							int s1 = value.pop();
							if (s1 <= f1)
								value.push(1);
							else
								value.push(0);
							break;
						case ">":
							int f2 = value.pop();
							int s2 = value.pop();
							if (s2 > f2)
								value.push(1);
							else
								value.push(0);
							break;
						case ">=":
							int f3 = value.pop();
							int s3 = value.pop();
							if (s3 >= f3)
								value.push(1);
							else
								value.push(0);
							break;
						case "==":
							int f4 = value.pop();
							int s4 = value.pop();
							if (s4 == f4)
								value.push(1);
							else
								value.push(0);
							break;
						case "!=":
							int f5 = value.pop();
							int s5 = value.pop();
							if (s5 != f5)
								value.push(1);
							else
								value.push(0);
							break;
						default:
							value.push(Integer.parseInt(com));
							break;
						}

					} else {
						value.reverse();
						while (!value.empty())
							v.push(value.pop());
						return v;

					}

				}
			} else {
				return null;
			}

			if (i - 1 == k) {
				while (!value.empty())
					v.push(value.pop());

				return v;
			}
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	@Override
	public int validate() {

		Queue<String> tmp = new LinkedQueue<>();
		for (int i = 0; i < tmp1.length(); i++) {
			String t = tmp1.serve();
			tmp1.enqueue(t);
			tmp.enqueue(t);
		}

		Stack<Integer> value = new LinkedStack<Integer>();
		int x = 0;
		int i = 0;
		int cheak = 0;
		try {
			for (; i < tmp.length(); i++, x++) {

				String com = tmp.serve();
				tmp.enqueue(com);
				// int in=Integer.parseInt(com);

				switch (com) {
				case "*":
					value.push(value.pop() * value.pop());
					break;
				case "+":
					value.push(value.pop() + value.pop());
					break;
				case "-":
					int v1 = value.pop();
					int v2 = value.pop();
					value.push(v2 - v1);
					break;
				case "/":
					int q = value.pop();
					int q1 = value.pop();
					if (q == 0)
						return i;
					value.push(q1 / q);
					break;
				case "%":
					int qq = value.pop();
					int qqq = value.pop();
					if (qq == 0)
						return i;
					value.push(qqq % qq);
					break;
				case "<":
					int f = value.pop();
					int s = value.pop();
					if (s < f)
						value.push(1);
					else
						value.push(0);
					break;
				case "<=":
					int f1 = value.pop();
					int s1 = value.pop();
					if (s1 <= f1)
						value.push(1);
					else
						value.push(0);
					break;
				case ">":
					int f2 = value.pop();
					int s2 = value.pop();
					if (s2 > f2)
						value.push(1);
					else
						value.push(0);
					break;
				case ">=":
					int f3 = value.pop();
					int s3 = value.pop();
					if (s3 >= f3)
						value.push(1);
					else
						value.push(0);
					break;
				case "==":
					int f4 = value.pop();
					int s4 = value.pop();
					if (s4 == f4)
						value.push(1);
					else
						value.push(0);
					break;
				case "!=":
					int f5 = value.pop();
					int s5 = value.pop();
					if (s5 != f5)
						value.push(1);
					else
						value.push(0);
					break;
				default:
					if (isNumeric(com))
						value.push(Integer.parseInt(com));
					else
						return i;
					break;
				}

			}
			cheak = 0;
			while (!value.empty()) {
				cheak++;
				value.pop();

			}

		} catch (Exception e) {
			return i;
		}
		if (cheak > 1)
			return i;
		return -1;
	}

	private boolean isNumeric(String str) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}

	@Override
	public InFixExp toInFix() {
		Queue<String> tmp = new LinkedQueue<>();
		for (int i = 0; i < tmp1.length(); i++) {
			String t = tmp1.serve();
			tmp1.enqueue(t);
			tmp.enqueue(t);
		}

		Stack<Integer> result = new LinkedStack<Integer>();
		int total;
		boolean istrue = false;

		try {
			// String[] POSTFIX = pfix.split(" ");
			for (int i = 0; i < tmp.length(); i++) {
				String cheak = tmp.serve();
				tmp.enqueue(cheak);
				if (isNumeric(cheak)) {
					result.push(Integer.valueOf(cheak));
				}

				else {
					int first = result.pop();
					int second = result.pop();
					total = 0;
					boolean gohead = true;
					while (gohead) {
						if (cheak.equals("+")) {
							total = second + first;
							gohead = false;
						} else if (cheak.equals("-")) {
							total = second - first;
							gohead = false;
						} else if (cheak.equals("*")) {
							total = second * first;
							gohead = false;
						} else if (cheak.equals("/")) {
							if (first == 0 || second == 0) {
								total = 0;
							} else {
								total = second / first;
							}
							gohead = false;
						} else if (cheak.equals("%")) {
							if (first == 0 || second == 0) {
								total = 0;
							} else {
								total = second % first;
							}
							gohead = false;
						} else if (cheak.equals(">")) {

							if (second > first) {
								total = 1;
							} else
								total = 0;

							gohead = false;
						} else if (cheak.equals("<")) {
							if (second < first) {
								total = 1;
							} else
								total = 0;

							gohead = false;
						} else if (cheak.equals(">=")) {
							if (second >= first) {
								total = 1;
							} else
								total = 0;

							gohead = false;
						} else if (cheak.equals("<=")) {
							if (second <= first) {
								total = 1;
							} else
								total = 0;

							gohead = false;
						} else if (cheak.equals("==")) {
							if (second == first) {
								total = 1;
							} else
								total = 0;

							gohead = false;
						} else if (cheak.equals("!=")) {
							if (second != first) {
								total = 1;
							} else
								total = 0;

							gohead = false;
						} else
							gohead = false;
					}

					result.push(total);

				}

			}
		} catch (Exception e) {
			istrue = false;
		}

		if (istrue = false)
			return null;
		Stack<String> finish = new LinkedStack<String>();

		try {
			Queue<String> tmp2 = new LinkedQueue<>();
			for (int i = 0; i < tmp1.length(); i++) {
				String t = tmp1.serve();
				tmp1.enqueue(t);
				tmp2.enqueue(t);
			}
			for (int i = 0; i < tmp2.length(); i++) {
				String cheak = tmp2.serve();
				tmp2.enqueue(cheak);
				if (isNumeric(cheak)) {
					finish.push(cheak);
				}

				else {
					String first = finish.pop();
					String second = finish.pop();
					String exceprtion = '(' + " " + second + " " + cheak + " " + first + " " + ')';
					finish.push(exceprtion);

				}

			}

		} catch (Exception e) {
			return null;
		}

		String infixStatment = finish.pop();
		if (finish.empty()) {
			InFixExp infix = new InFixExpImp();
			infix.setExp(infixStatment);
			return infix;
		}
		return null;
	}
}
