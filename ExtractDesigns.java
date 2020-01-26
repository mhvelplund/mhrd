import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import controller.data.DesignRepo;
import controller.data.MemorySystem;
import model.Design;


/** 
	Set the working directory to the same folder that holds the Steam mhrd.jar file, and add the jar to the path.
	Solved files are dumped as component.wire, while specs for builtins are dumped as component.unwired.
*/
public class ExtractDesigns {
	private static final OUTPUT_PATH = "/home/mhvelplund/Documents/";
	public static void main(String[] args) throws IOException {
		MemorySystem.load();
		if (!MemorySystem.isLoaded())
			System.exit(1);
		Set<Entry<String, Design>> entrySet = DesignRepo.designMap.entrySet();
		for (Entry<String, Design> entry : entrySet.stream().filter(e -> e.getValue().code != null)
				.collect(Collectors.toList())) {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(OUTPUT_PATH + entry.getKey() + ".wire"));
			writer.write(entry.getValue().code.trim());
			writer.close();
			System.out.println("Wrote '" + OUTPUT_PATH + entry.getKey() + ".wire");
		}

		for (Entry<String, Design> entry : entrySet.stream().filter(e -> e.getValue().code == null)
				.collect(Collectors.toList())) {
			FileWriter fileWriter = new FileWriter(OUTPUT_PATH + entry.getKey() + ".unwired");
			PrintWriter writer = new PrintWriter(fileWriter);

			if (entry.getValue().briefing != null)
				writer.println("briefing: " + entry.getValue().briefing);
			if (entry.getValue().spec != null)
				writer.println("spec: " + entry.getValue().spec);
			if (entry.getValue().tests != null)
				writer.println("tests: " + entry.getValue().tests);
			if (entry.getValue().iface != null)
				writer.println("iface: " + entry.getValue().iface);
			if (entry.getValue().stats != null)
				writer.println("stats: " + entry.getValue().stats);
			writer.println("completed: " + entry.getValue().completed);
			writer.println("custom: " + entry.getValue().custom);

			writer.close();
			System.out.println("Wrote '" + OUTPUT_PATH + entry.getKey() + ".unwired'");
		}
	}
}
