
public class StudentMain {

	private static <T> void print(Stack<T> st) {
		System.out.println("------------");
		if (st == null) {
			System.out.println("null");
		} else if (st.empty()) {
			System.out.println("Empty stack");
		} else {
			Stack<T> ts = new LinkedStack<T>();
			while (!st.empty()) {
				T e = st.pop();
				System.out.println(e);
				ts.push(e);
			}
			while (!ts.empty()) {
				st.push(ts.pop());
			}
		}
		System.out.println("------------");
	}

	private static void testInFixExp() {

	}

	public static void main(String[] args) {
		// Postfix
		{
			String exp = "3 5 + 4 4 - /";
			PostFixExp pfe = new PostFixExpImp();
			pfe.setExp(exp);
			print(pfe.evaluate(6));
		}
		{
			String exp = "3 5 + 4 4 - /";
			PostFixExp pfe = new PostFixExpImp();
			pfe.setExp(exp);
			print(pfe.evaluate(7));
		}
		{
			String exp = "3 5 + 4 2 - *";
			PostFixExp pfe = new PostFixExpImp();
			pfe.setExp(exp);
			System.out.println("Error at: " + pfe.validate());
		}
		{
			String exp = "3 5 + 4 4 - /";
			PostFixExp pfe = new PostFixExpImp();
			pfe.setExp(exp);
			System.out.println("Error at: " + pfe.validate());
		}
		{
			String exp = "3 5 + 4 2 -";
			PostFixExp pfe = new PostFixExpImp();
			pfe.setExp(exp);
			System.out.println("Error at: " + pfe.validate());
		}
		{
			String exp = "3 5 + 4 2 - >=";
			PostFixExp pfe = new PostFixExpImp();
			pfe.setExp(exp);
			InFixExp ife = pfe.toInFix();
			System.out.println(ife.getExp());
		}

		// Infix
		{
			String exp = "3 - 2";
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			Pair<Stack<Integer>, Stack<String>> res = ife.evaluate(4);
			print(res.first);
			print(res.second);
		}
		{
			String exp = "3 - 2";
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			Pair<Stack<Integer>, Stack<String>> res = ife.evaluate(3);
			print(res.first);
			print(res.second);
		}
		{
			String exp = "( 3 - 2 )";
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			Pair<Stack<Integer>, Stack<String>> res = ife.evaluate(6);
			print(res.first);
			print(res.second);
		}
		{
			String exp = "( 3 - 2 ) * ( 5 % 3 ) >= ( ( 4 + 2 ) )";
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			Pair<Stack<Integer>, Stack<String>> res = ife.evaluate(20);
			print(res.first);
			print(res.second);
		}
		{
			String exp = "( 3 - 2 ) * ( 5 % 3 ) >= ( ( 4 + 2 ) )";
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			Pair<Stack<Integer>, Stack<String>> res = ife.evaluate(20);
			System.out.println(ife.toPostFix().getExp());
		}
		{
			String exp = "3 - 2 ) + 5";
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}
		{
			String exp = "( 3 - 2 ) * 4 / 2 )";
			InFixExp ife = new InFixExpImp();
			ife.setExp(exp);
			System.out.println("Error at: " + ife.validate());
		}

	}

}

