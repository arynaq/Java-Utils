package collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	public static void main(String[] args) {
		long t0 = System.nanoTime();
		for (int i = 0; i < 1E+8; i++) {
			CircularArrayList<String> list = new CircularArrayList<>();
			list.add("This");
			list.add("Is");
			list.add("A");
			list.add("Very");
			list.add("Nice");
			list.add("And");
			list.add("Cool");
			list.add("Sentence");

			for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
				iter.next();
				iter.remove();
			}
		}
		double t1 = System.nanoTime();
		System.out.println("Time spent circular: " + (1.0 * t1 - t0) / (1E+9));

		t0 = System.nanoTime();
		for (int i = 0; i < 1E+8; i++) {
			List<String> list = new ArrayList<>();
			list.add("This");
			list.add("Is");
			list.add("A");
			list.add("Very");
			list.add("Nice");
			list.add("And");
			list.add("Cool");
			list.add("Sentence");

			for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
				iter.next();
				iter.remove();
			}
		}
		t1 = System.nanoTime();
		System.out.println("Time spent normal: " + (1.0 * t1 - t0) / (1E+9));

	}
}
