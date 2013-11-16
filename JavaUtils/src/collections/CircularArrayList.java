package collections;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class CircularArrayList<E> extends ArrayList<E> {
	
	@Override
	public Iterator<E> iterator() {
		return new CircularIterator();
	}

	private void removeElement(int index) {
		remove(index);
	}

	private class CircularIterator implements Iterator<E> {

		private int current;
		private int last;

		@Override
		public boolean hasNext() {
			return !isEmpty();
		}

		@Override
		public E next() {
			if (current >= size())
				current = 0;
			last = current;
			return get(current++);
		}

		@Override
		public void remove() {
			current--;
			removeElement(last);
		}
		
	}
}
