public class LCG {

	long x;
	long a = 1103515245;
	long b = 12345;
	long n = (long) Math.pow(2, 31);

	public LCG(long startwert) {
		x = startwert;
	}

	public double nextValue() {

		long next = (a * x + b) % n;
		x = next;
		return ((double) x / (double) n);
	}

	// ****************************************************************
	public static void main(String[] args) {

		LCG generator = new LCG(1);
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());

	}

}
