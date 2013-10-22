public class LCG {

	
	double x;
	double a = 2653;
	double b = 1;
	double n = Math.pow(2, 32);

	public LCG(int startwert) {
		x = startwert;
	}

	public int nextValue() {

		double next = (a * x + b) % n;
		x = next;

		if (next < n / 2)
			return 0;
		else
			return 1;
	}

	public static void main(String[] args) {

		LCG generator = new LCG(6);
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());
		System.out.println(generator.nextValue());

	}

}
