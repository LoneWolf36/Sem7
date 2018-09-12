import java.util.*;

class aprioriAlgorithm {
	public static void main(String args[]) {
		
		AprioriCalculation ap = new AprioriCalculation();
		ap.aprioriProcess();
		ap.display();
		ap.deleteUnnecessary();
		ap.display1();
	}
}

class AprioriCalculation {
	Scanner scan = new Scanner(System.in);
	ArrayList<String[]> items = new ArrayList<String[]>();
	Map<String, Integer> items_count = new HashMap<>();
	ArrayList<String> nextStage;
	int min_support;

	public void aprioriProcess() {
		
		int count;

		System.out.print("How many transactions do you want to enter?: ");
		count = scan.nextInt();

		System.out.print("\nWhat is the minimum support?: ");
		min_support = scan.nextInt();

		for (int i = 0; i < count; i++) {

			System.out.print("\n How many items in " + (i + 1) + " transaction: ");
			int j = scan.nextInt();
			String temp_array[] = new String[j];

			for (int k = 0; k < j; k++) {

				System.out.println("Item " + (i + 1) + " " + (k + 1));
				String value = scan.next();
				temp_array[k] = value;
				String check_if_in_map = value;

				if (items_count.containsKey(check_if_in_map)) {
					int temp_count = items_count.get(check_if_in_map);
					items_count.put(check_if_in_map, ++temp_count);
				} else
					items_count.put(check_if_in_map, 1);
			}
			items.add(temp_array);
		}
	}

	public void analyzeData() {
		nextStage = new ArrayList<>(items_count.keySet());
	}

	public void display1() {
		for (String i : nextStage) {
			System.out.print(i + " ");
		}
	}

	public void display() {
		int k = 1;
		for (String i[] : items) {
			System.out.print("Set " + k++ + ": " + "{ ");
			for (String j : i) {
				System.out.print(j + " ");
			}
			System.out.print("}\n\n");
		}
		for (String key : items_count.keySet()) {
			System.out.printf("Item = %s\t\t", key);
		}
		System.out.println();
		// Iterating over values only
		for (Integer value : items_count.values()) {
			System.out.printf("Count = %d\t", value);
		}
	}

	public void deleteUnnecessary() {
		items_count.entrySet().removeIf(item_to_remove -> item_to_remove.getValue().compareTo(min_support) < 0);
	}

}
