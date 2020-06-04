import java.text.NumberFormat;
import java.text.ParsePosition;

public class InFixExpImp implements InFixExp {
	private Queue<String> tmp1;

	public InFixExpImp() {
		// TODO Auto-generated constructor stub
		tmp1 = new LinkedQueue<String>();
	}

	@Override
	public void setExp(String exp) {
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

	public Pair<Stack<Integer>, Stack<String>> evaluate(int k) {
		if (tmp1.length() == 0)
			return null;

		Queue<String> tmp = new LinkedQueue<>();
		for (int i = 0; i < tmp1.length(); i++) {
			String t = tmp1.serve();
			tmp1.enqueue(t);
			tmp.enqueue(t);
		}
		Stack<String> opreation = new LinkedStack<>();
		Stack<Integer> value = new LinkedStack<>();
		int i = 0;
		try {

			if (tmp.length() > 1 /* && n >= k */) {
				Count VaCount = new Count(0);
				int x = 0;
				for (i = 0; i < tmp.length(); i++) {
					String cheak = tmp.serve();
					tmp.enqueue(cheak);
					if (x < k) {
						switch (cheak) {
						case "(":

							opreation.push("(");
							break;
						case ")":

							if (!doit(value, opreation, VaCount))
								return null;
							break;
						case "*":
							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							/*
							 * if (VaCount.co <= 1) return null;
							 */
							String com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
									|| com.equals(">=") || com.equals(">=") || com.equals("!=") || com.equals("==")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;

									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										com = opreation.pop();
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										} else if (com.equals("+") || com.equals("-") || com.equals("<=")
												|| com.equals("<") || com.equals(">=") || com.equals(">=")
												|| com.equals("!=") || com.equals("==")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									} else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case "+":
							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							/*
							 * if (VaCount.co <= 1) return null;
							 */
							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else if (com.equals("<=") || com.equals("<") || com.equals(">=") || com.equals(">=")
									|| com.equals("!=") || com.equals("==")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
										|| com.equals("-")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "+":
										value.push(value.pop() + value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "-":
										v1 = value.pop();
										v2 = value.pop();
										value.push(v2 - v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;

									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										com = opreation.pop();
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										} else if (com.equals("<=") || com.equals("<") || com.equals(">=")
												|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									} else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case "-":

							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else if (com.equals("<=") || com.equals("<") || com.equals(">=") || com.equals(">=")
									|| com.equals("!=") || com.equals("==")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
										|| com.equals("-")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "+":
										value.push(value.pop() + value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "-":
										v1 = value.pop();
										v2 = value.pop();
										value.push(v2 - v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;

									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										com = opreation.pop();
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										} else if (com.equals("<=") || com.equals("<") || com.equals(">=")
												|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									} else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case "/":
							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							/*
							 * if (VaCount.co <= 1) return null;
							 */
							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
									|| com.equals(">=") || com.equals(">=") || com.equals("!=") || com.equals("==")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;

									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty())
										com = opreation.pop();
									else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case "%":
							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}

							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
								break;
							} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
									|| com.equals(">=") || com.equals(">=") || com.equals("!=") || com.equals("==")) {
								{
									opreation.push(com);
									opreation.push(cheak);
								}
								break;
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;

									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										com = opreation.pop();
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										} else if (com.equals("+") || com.equals("-") || com.equals("<=")
												|| com.equals("<") || com.equals(">=") || com.equals(">=")
												|| com.equals("!=") || com.equals("==")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									} else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case ">":

							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else if (com.equals("!=") || com.equals("==")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
										|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
										|| com.equals(">=")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "+":
										value.push(value.pop() + value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "-":
										v1 = value.pop();
										v2 = value.pop();
										value.push(v2 - v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;
									case "<":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 < v1)
											value.push(1);
										else
											value.push(0);

										break;
									case "<=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 <= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">":
										v1 = value.pop();
										v2 = value.pop();
										if (v2 > v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 >= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "==":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 == v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "!=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 != v1)
											value.push(1);
										else
											value.push(0);
										break;
									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										com = opreation.pop();
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										} else if (com.equals("!=") || com.equals("==")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									} else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case ">=":

							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else if (com.equals("!=") || com.equals("==")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
										|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
										|| com.equals(">=")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "+":
										value.push(value.pop() + value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "-":
										v1 = value.pop();
										v2 = value.pop();
										value.push(v2 - v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;
									case "<":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 < v1)
											value.push(1);
										else
											value.push(0);

										break;
									case "<=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 <= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">":
										v1 = value.pop();
										v2 = value.pop();
										if (v2 > v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 >= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "==":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 == v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "!=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 != v1)
											value.push(1);
										else
											value.push(0);
										break;
									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										com = opreation.pop();
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										} else if (com.equals("!=") || com.equals("==")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									}

									else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case "<":

							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else if (com.equals("!=") || com.equals("==")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
										|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
										|| com.equals(">=")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "+":
										value.push(value.pop() + value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "-":
										v1 = value.pop();
										v2 = value.pop();
										value.push(v2 - v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;
									case "<":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 < v1)
											value.push(1);
										else
											value.push(0);

										break;
									case "<=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 <= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">":
										v1 = value.pop();
										v2 = value.pop();
										if (v2 > v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 >= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "==":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 == v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "!=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 != v1)
											value.push(1);
										else
											value.push(0);
										break;
									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										com = opreation.pop();
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										} else if (com.equals("!=") || com.equals("==")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									}

									else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case "<=":

							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else if (com.equals("!=") || com.equals("==")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
										|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
										|| com.equals(">=")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "+":
										value.push(value.pop() + value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "-":
										v1 = value.pop();
										v2 = value.pop();
										value.push(v2 - v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;
									case "<":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 < v1)
											value.push(1);
										else
											value.push(0);

										break;
									case "<=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 <= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">":
										v1 = value.pop();
										v2 = value.pop();
										if (v2 > v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 >= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "==":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 == v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "!=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 != v1)
											value.push(1);
										else
											value.push(0);
										break;
									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										} else if (com.equals("!=") || com.equals("==")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									} else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case "==":

							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
										|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
										|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "+":
										value.push(value.pop() + value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "-":
										v1 = value.pop();
										v2 = value.pop();
										value.push(v2 - v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;
									case "<":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 < v1)
											value.push(1);
										else
											value.push(0);

										break;
									case "<=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 <= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">":
										v1 = value.pop();
										v2 = value.pop();
										if (v2 > v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 >= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "==":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 == v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "!=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 != v1)
											value.push(1);
										else
											value.push(0);
										break;
									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										com = opreation.pop();
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									} else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						case "!=":

							if (opreation.empty()) {
								opreation.push(cheak);
								break;
							}
							com = opreation.pop();
							if (com.equals("(")) {
								opreation.push(com);
								opreation.push(cheak);
							} else
								while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
										|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
										|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
									switch (com) {
									case "*":
										value.push(value.pop() * value.pop());
										break;
									case "+":
										value.push(value.pop() + value.pop());
										break;
									case "%":
										int v1 = value.pop();
										int v2 = value.pop();
										value.push(v2 % v1);
										break;
									case "-":
										v1 = value.pop();
										v2 = value.pop();
										value.push(v2 - v1);
										break;
									case "/":
										int v3 = value.pop();
										int v4 = value.pop();
										value.push(v4 % v3);
										break;
									case "<":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 < v1)
											value.push(1);
										else
											value.push(0);

										break;
									case "<=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 <= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">":
										v1 = value.pop();
										v2 = value.pop();
										if (v2 > v1)
											value.push(1);
										else
											value.push(0);
										break;
									case ">=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 >= v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "==":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 == v1)
											value.push(1);
										else
											value.push(0);
										break;
									case "!=":

										v1 = value.pop();
										v2 = value.pop();
										if (v2 != v1)
											value.push(1);
										else
											value.push(0);
										break;
									default:
										opreation.push(com);
										opreation.push(cheak);
										break;
									}
									if (!opreation.empty()) {
										com = opreation.pop();
										if (com.equals("(")) {
											opreation.push(com);
											opreation.push(cheak);
										}
									} else {
										opreation.push(cheak);
										com = " ";
									}
								}
							break;
						default:
							if (isNumeric(cheak)) {
								value.push(Integer.parseInt(cheak));
								/* VaCount.ci() */;
								;
							} else
								return null;
							break;
						}
						x++;
					} else {

						this.compleat(value, opreation);
						Pair<Stack<Integer>, Stack<String>> pair = new Pair<Stack<Integer>, Stack<String>>(value,
								opreation);

						return pair;

					}
				}
			} else {
				this.compleat(value, opreation);
				Pair<Stack<Integer>, Stack<String>> pair = new Pair<Stack<Integer>, Stack<String>>(value, opreation);

				return pair;

			}
		} catch (Exception e) {
			return null;
		}
		if (i + 1 == k) {
			if (this.compleat(value, opreation)) {
				Pair<Stack<Integer>, Stack<String>> pair = new Pair<Stack<Integer>, Stack<String>>(value, opreation);
				return pair;
			} else
				return null;

		}
		if (this.compleat(value, opreation)) {
			Pair<Stack<Integer>, Stack<String>> pair = new Pair<Stack<Integer>, Stack<String>>(value, opreation);
			return pair;
		}
		return null;
	}

	private boolean isNumeric(String str) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}

	private String isThere(Queue<String> q) {
		String t = "";
		int x = q.length();
		for (int i = 0; i < x; i++) {
			String w = q.serve();
			q.enqueue(w);
			if (i == x - 1)
				t = w;
		}
		return t;
	}

	private class Count {
		private int co;

		Count(int c) {
			co = c;
		}

		void ci() {
			this.co++;
		}

		void cd() {
			this.co--;

		}
	}

	private boolean doit(Stack<Integer> q, Stack<String> s, Count count) {

		String cop = s.pop();

		int v1, v2;
		try {
			while (!cop.equals("(")) {
				switch (cop) {
				case "*":
					v1 = q.pop();
					count.cd();
					v2 = q.pop();
					count.cd();
					;
					q.push(v1 * v2);
					count.ci();
					;
					;
					break;
				case "+":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					q.push(v1 + v2);
					count.ci();
					;
					;
					break;
				case "-":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					q.push(v2 - v1);
					count.ci();
					;
					;
					break;
				case "/":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					q.push(v2 / v1);
					count.ci();
					;
					;
					break;
				case "%":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					q.push(v2 % v1);
					count.ci();
					;
					;
					break;
				case "<":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 < v1)
						q.push(1);
					else
						q.push(0);

					count.ci();
					;
					;
					break;
				case "<=":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 <= v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				case ">":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 > v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				case ">=":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 >= v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				case "==":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 == v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				case "!=":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 != v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				default:
					s.push(cop);

					break;
				}
				if (!s.empty())
					cop = s.pop();
				else
					return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	private boolean compleat(Stack<Integer> q, Stack<String> s) {
		Count count = new Count(0);
		String cop = "";
		boolean f = false;
		if (!s.empty()) {
			cop = s.pop();
			f = true;
		}
		int v1, v2;
		try {
			while (!q.empty() && f) {
				switch (cop) {
				case "*":
					v1 = q.pop();
					count.cd();
					v2 = q.pop();
					count.cd();
					;
					q.push(v1 * v2);
					count.ci();
					;
					;
					break;
				case "+":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					q.push(v1 + v2);
					count.ci();
					;
					;
					break;
				case "-":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					q.push(v2 - v1);
					count.ci();
					;
					;
					break;
				case "/":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					q.push(v2 / v1);
					count.ci();
					;
					;
					break;
				case "%":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					q.push(v2 % v1);
					count.ci();
					;
					;
					break;
				case "<":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 < v1)
						q.push(1);
					else
						q.push(0);

					count.ci();
					;
					;
					break;
				case "<=":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 <= v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				case ">":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 > v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				case ">=":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 >= v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				case "==":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 == v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				case "!=":

					v1 = q.pop();
					count.cd();
					;
					v2 = q.pop();
					count.cd();
					;
					if (v2 != v1)
						q.push(1);
					else
						q.push(0);
					count.ci();
					;
					;
					break;
				default:
					return false;

				}
				if (!s.empty()) {
					cop = s.pop();
					f = true;
				} else {
					f = false;
				}

			}
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		s.push("$");
		return true;
	}

	@Override
	public int getNbTokens() {

		return tmp1.length() + 1;
	}

	@Override
	public int validate() {
		Queue<String> tmp = new LinkedQueue<>();
		for (int i = 0; i < tmp1.length(); i++) {
			String t = tmp1.serve();
			tmp1.enqueue(t);
			tmp.enqueue(t);
		}
		Stack<String> opreation = new LinkedStack<>();
		Stack<Integer> value = new LinkedStack<>();
		int i = 0;
		try {
			if (tmp.length() > 1) {
				// Integer OpCount=new Integer(0);
				Count VaCount = new Count(0);
				int x = 0;
				for (i = 0; i < tmp.length(); i++) {
					String cheak = tmp.serve();
					tmp.enqueue(cheak);

					switch (cheak) {
					case "(":

						opreation.push("(");
						break;
					case ")":

						/*
						 * if (VaCount.co <= 1) return null;
						 */
						if (!doit(value, opreation, VaCount))
							return i;
						break;
					case "*":
						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						/*
						 * if (VaCount.co <= 1) return null;
						 */
						String com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
								|| com.equals(">=") || com.equals(">=") || com.equals("!=") || com.equals("==")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
											|| com.equals(">=") || com.equals(">=") || com.equals("!=")
											|| com.equals("==")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "+":
						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						/*
						 * if (VaCount.co <= 1) return null;
						 */
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("<=") || com.equals("<") || com.equals(">=") || com.equals(">=")
								|| com.equals("!=") || com.equals("==")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "+":
									value.push(value.pop() + value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "-":
									v1 = value.pop();
									v2 = value.pop();
									value.push(v2 - v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									} else if (com.equals("<=") || com.equals("<") || com.equals(">=")
											|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "-":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("<=") || com.equals("<") || com.equals(">=") || com.equals(">=")
								|| com.equals("!=") || com.equals("==")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "+":
									value.push(value.pop() + value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "-":
									v1 = value.pop();
									v2 = value.pop();
									value.push(v2 - v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									} else if (com.equals("<=") || com.equals("<") || com.equals(">=")
											|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "/":
						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						/*
						 * if (VaCount.co <= 1) return null;
						 */
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
								|| com.equals(">=") || com.equals(">=") || com.equals("!=") || com.equals("==")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty())
									com = opreation.pop();
								else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "%":
						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						/*
						 * if (VaCount.co <= 1) return null;
						 */
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
							break;
						} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
								|| com.equals(">=") || com.equals(">=") || com.equals("!=") || com.equals("==")) {
							{
								opreation.push(com);
								opreation.push(cheak);
							}
							break;
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
											|| com.equals(">=") || com.equals(">=") || com.equals("!=")
											|| com.equals("==")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case ">":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("!=") || com.equals("==")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "+":
									value.push(value.pop() + value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "-":
									v1 = value.pop();
									v2 = value.pop();
									value.push(v2 - v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;
								case "<":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 < v1)
										value.push(1);
									else
										value.push(0);

									break;
								case "<=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 <= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">":
									v1 = value.pop();
									v2 = value.pop();
									if (v2 > v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 >= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "==":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 == v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "!=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 != v1)
										value.push(1);
									else
										value.push(0);
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									} else if (com.equals("!=") || com.equals("==")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case ">=":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("!=") || com.equals("==")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "+":
									value.push(value.pop() + value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "-":
									v1 = value.pop();
									v2 = value.pop();
									value.push(v2 - v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;
								case "<":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 < v1)
										value.push(1);
									else
										value.push(0);

									break;
								case "<=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 <= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">":
									v1 = value.pop();
									v2 = value.pop();
									if (v2 > v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 >= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "==":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 == v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "!=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 != v1)
										value.push(1);
									else
										value.push(0);
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									} else if (com.equals("!=") || com.equals("==")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								}

								else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "<":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("!=") || com.equals("==")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "+":
									value.push(value.pop() + value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "-":
									v1 = value.pop();
									v2 = value.pop();
									value.push(v2 - v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;
								case "<":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 < v1)
										value.push(1);
									else
										value.push(0);

									break;
								case "<=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 <= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">":
									v1 = value.pop();
									v2 = value.pop();
									if (v2 > v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 >= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "==":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 == v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "!=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 != v1)
										value.push(1);
									else
										value.push(0);
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									} else if (com.equals("!=") || com.equals("==")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								}

								else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "<=":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("!=") || com.equals("==")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "+":
									value.push(value.pop() + value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "-":
									v1 = value.pop();
									v2 = value.pop();
									value.push(v2 - v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;
								case "<":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 < v1)
										value.push(1);
									else
										value.push(0);

									break;
								case "<=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 <= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">":
									v1 = value.pop();
									v2 = value.pop();
									if (v2 > v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 >= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "==":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 == v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "!=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 != v1)
										value.push(1);
									else
										value.push(0);
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									} else if (com.equals("!=") || com.equals("==")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "==":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "+":
									value.push(value.pop() + value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "-":
									v1 = value.pop();
									v2 = value.pop();
									value.push(v2 - v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;
								case "<":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 < v1)
										value.push(1);
									else
										value.push(0);

									break;
								case "<=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 <= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">":
									v1 = value.pop();
									v2 = value.pop();
									if (v2 > v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 >= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "==":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 == v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "!=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 != v1)
										value.push(1);
									else
										value.push(0);
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "!=":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
								switch (com) {
								case "*":
									value.push(value.pop() * value.pop());
									break;
								case "+":
									value.push(value.pop() + value.pop());
									break;
								case "%":
									int v1 = value.pop();
									int v2 = value.pop();
									value.push(v2 % v1);
									break;
								case "-":
									v1 = value.pop();
									v2 = value.pop();
									value.push(v2 - v1);
									break;
								case "/":
									int v3 = value.pop();
									int v4 = value.pop();
									value.push(v4 % v3);
									break;
								case "<":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 < v1)
										value.push(1);
									else
										value.push(0);

									break;
								case "<=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 <= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">":
									v1 = value.pop();
									v2 = value.pop();
									if (v2 > v1)
										value.push(1);
									else
										value.push(0);
									break;
								case ">=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 >= v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "==":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 == v1)
										value.push(1);
									else
										value.push(0);
									break;
								case "!=":

									v1 = value.pop();
									v2 = value.pop();
									if (v2 != v1)
										value.push(1);
									else
										value.push(0);
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					default:
						if (isNumeric(cheak)) {
							value.push(Integer.parseInt(cheak));
							/* VaCount.ci() */;
							;
						} else
							return i;
						break;
					}
					x++;
				}

			}

			else {
				return i;
			}
		} catch (Exception e) {
			return i;
		}

		if (this.compleat(value, opreation) == false)
			return i;

		return -1;

	}

	@Override
	public PostFixExp toPostFix() {
		Queue<String> tmp = new LinkedQueue<>();
		for (int i = 0; i < tmp1.length(); i++) {
			String t = tmp1.serve();
			tmp1.enqueue(t);
			tmp.enqueue(t);
		}
		String com = "";
		Queue<String> post = new LinkedQueue<>();
		Stack<String> opreation = new LinkedStack<>();
		Stack<String> value = new LinkedStack<>();
		PostFixExpImp p = new PostFixExpImp();
		try {
			if (validate() == -1) {
				for (int i = 0; i < tmp.length(); i++) {
					String cheak = tmp.serve();
					tmp.enqueue(cheak);
					switch (cheak) {
					case "(":
						opreation.push(cheak);
						break;
					case ")":
						workOnIt(value, opreation, post);
						break;
					case "+":
						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}

						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("<=") || com.equals("<") || com.equals(">=") || com.equals(">=")
								|| com.equals("!=") || com.equals("=="))
							opreation.push(cheak);
						else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "+":
									post.enqueue("+");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "-":
									post.enqueue("-");
									break;
								case "/":
									post.enqueue("/");
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "-":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("<=") || com.equals("<") || com.equals(">=") || com.equals(">=")
								|| com.equals("!=") || com.equals("=="))
							opreation.push(cheak);
						else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "+":
									post.enqueue("+");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "-":
									post.enqueue("-");
									break;
								case "/":
									post.enqueue("/");
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "/":
						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						/*
						 * if (VaCount.co <= 1) return null;
						 */
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
								|| com.equals(">=") || com.equals(">=") || com.equals("!=") || com.equals("=="))
							opreation.push(cheak);
						else
							while (com.equals("*") || com.equals("%") || com.equals("/")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "/":
									post.enqueue("/");
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "*":
						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}

						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
								|| com.equals(">=") || com.equals(">=") || com.equals("!=") || com.equals("=="))
							opreation.push(cheak);
						else
							while (com.equals("*") || com.equals("%") || com.equals("/")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "/":
									post.enqueue("/");
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty())
									com = opreation.pop();
								else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "%":
						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}

						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
							break;
						} else if (com.equals("+") || com.equals("-") || com.equals("<=") || com.equals("<")
								|| com.equals(">=") || com.equals(">=") || com.equals("!=") || com.equals("==")) {
							opreation.push(cheak);
							break;
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "/":
									post.enqueue("/");
									break;

								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case ">":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("!=") || com.equals("=="))
							opreation.push(cheak);
						else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "+":
									post.enqueue("+");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "-":
									post.enqueue("-");
									break;
								case "/":
									post.enqueue("/");
									break;
								case "<":
									post.enqueue("<");
									break;
								case "<=":
									post.enqueue("<=");
									break;
								case ">":
									post.enqueue(">");
									break;
								case ">=":
									post.enqueue(">=");
									break;
								case "==":
									post.enqueue("==");
									break;
								case "!=":
									post.enqueue("!=");
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case ">=":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("!=") || com.equals("=="))
							opreation.push(cheak);
						else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "+":
									post.enqueue("+");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "-":
									post.enqueue("-");
									break;
								case "/":
									post.enqueue("/");
									break;
								case "<":
									post.enqueue("<");
									break;
								case "<=":
									post.enqueue("<=");
									break;
								case ">":
									post.enqueue(">");
									break;
								case ">=":
									post.enqueue(">=");
									break;
								case "==":
									post.enqueue("==");
									break;
								case "!=":
									post.enqueue("!=");
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "<":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("!=") || com.equals("=="))
							opreation.push(cheak);
						else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=")) {

								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "+":
									post.enqueue("+");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "-":
									post.enqueue("-");
									break;
								case "/":
									post.enqueue("/");
									break;
								case "<":
									post.enqueue("<");
									break;
								case "<=":
									post.enqueue("<=");
									break;
								case ">":
									post.enqueue(">");
									break;
								case ">=":
									post.enqueue(">=");
									break;
								case "==":
									post.enqueue("==");
									break;
								case "!=":
									post.enqueue("!=");
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "<=":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else if (com.equals("!=") || com.equals("=="))
							opreation.push(cheak);
						else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "+":
									post.enqueue("+");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "-":
									post.enqueue("-");
									break;
								case "/":
									post.enqueue("/");
									break;
								case "<":
									post.enqueue("<");
									break;
								case "<=":
									post.enqueue("<=");
									break;
								case ">":
									post.enqueue(">");
									break;
								case ">=":
									post.enqueue(">=");
									break;
								case "==":
									post.enqueue("==");
									break;
								case "!=":
									post.enqueue("!=");
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "==":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "+":
									post.enqueue("+");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "-":
									post.enqueue("-");
									break;
								case "/":
									post.enqueue("/");
									break;
								case "<":
									post.enqueue("<");
									break;
								case "<=":
									post.enqueue("<=");
									break;
								case ">":
									post.enqueue(">");
									break;
								case ">=":
									post.enqueue(">=");
									break;
								case "==":
									post.enqueue("==");
									break;
								case "!=":
									post.enqueue("!=");
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					case "!=":

						if (opreation.empty()) {
							opreation.push(cheak);
							break;
						}
						com = opreation.pop();
						if (com.equals("(")) {
							opreation.push(com);
							opreation.push(cheak);
						} else
							while (com.equals("*") || com.equals("%") || com.equals("/") || com.equals("+")
									|| com.equals("-") || com.equals("<=") || com.equals("<") || com.equals(">=")
									|| com.equals(">=") || com.equals("!=") || com.equals("==")) {
								switch (com) {
								case "*":
									post.enqueue("*");
									break;
								case "+":
									post.enqueue("+");
									break;
								case "%":
									post.enqueue("%");
									break;
								case "-":
									post.enqueue("-");
									break;
								case "/":
									post.enqueue("/");
									break;
								case "<":
									post.enqueue("<");
									break;
								case "<=":
									post.enqueue("<=");
									break;
								case ">":
									post.enqueue(">");
									break;
								case ">=":
									post.enqueue(">=");
									break;
								case "==":
									post.enqueue("==");
									break;
								case "!=":
									post.enqueue("!=");
									break;
								default:
									opreation.push(com);
									opreation.push(cheak);
									break;
								}
								if (!opreation.empty()) {
									com = opreation.pop();
									if (com.equals("(")) {
										opreation.push(com);
										opreation.push(cheak);
									}
								} else {
									opreation.push(cheak);
									com = " ";
								}
							}
						break;
					default:
						if (isNumeric(cheak)) {
							post.enqueue(cheak);
							;
						}
						break;
					}

				}
			} else
				return null;
			while (!opreation.empty()) {
				post.enqueue(opreation.pop());
			}
			// TODO Auto-generated method stub

			for (int i = 0; i < post.length(); i++) {
				String t = post.serve();
				post.enqueue(t);
				p.setExp(t);

			}
		} catch (Exception e) {
			return null;
		}
		return p;
	}

	private boolean workOnIt(Stack<String> q, Stack<String> s, Queue<String> post) {

		String cop = s.pop();

		String v1, v2;
		try {
			while (!cop.equals("(")) {
				switch (cop) {
				case "*":

					post.enqueue("*");

					break;
				case "+":

					post.enqueue("+");
					break;
				case "-":

					post.enqueue("-");
					break;
				case "/":
					post.enqueue("/");
					break;
				case "%":

					post.enqueue("%");
					break;
				case "<":
					post.enqueue("<");
					break;
				case "<=":
					post.enqueue("<=");
					break;
				case ">":

					post.enqueue(">");
					break;
				case ">=":

					post.enqueue(">=");
					break;
				case "==":
					post.enqueue("==");
					break;
				case "!=":
					post.enqueue("!=");
					break;
				default:
					s.push(cop);

					break;

				}
				if (!s.empty())
					cop = s.pop();
				else
					return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
}