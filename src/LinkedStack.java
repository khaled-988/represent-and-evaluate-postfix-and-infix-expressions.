
public class LinkedStack<T> implements Stack<T> {

	private Node<T> top;

	public LinkedStack() {
		top = null;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return top == null;
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void push(T e) {

		Node<T> tmp = new Node<T>(e);
		tmp.next = top;
		top = tmp;
	}

	@Override
	public T pop() {
		T tmp = top.data;
		top = top.next;
		return tmp;
	}

	@Override
	public void reverse() {
		LinkedStack<T> l = new LinkedStack<>();

		while (top != null)
			l.push(this.pop());
		reverse(l);
	}

	private void reverse(LinkedStack<T> l) {
		if (l.empty())
			return;
		T tmp = l.pop();
		reverse(l);
		;
		this.push(tmp);
	}

}
