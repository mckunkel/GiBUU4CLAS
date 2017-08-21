package clients;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.LundHeader;
import domain.LundParticle;
import domain.OpenCloseLundFile;
import domain.OpenCloseLundFileImpl;
import domain.ReadGiBuuOutput;

public class App {

	public static void main(String[] args) {

		String inputDir = "/Users/michaelkunkel/WORK/GiBUU/clas/diLepton/fileOutput/";
		String filePart = "Dilepton_FullEvents_";
		String outputDir = "/Users/michaelkunkel/WORK/GiBUU/clas/diLepton/fileOutput/lund/";

		Map<LundHeader, List<LundParticle>> lundMap = new LinkedHashMap<>();
		for (int i = 50; i < 500; i++) {
			ReadGiBuuOutput myreader = new ReadGiBuuOutput(inputDir + filePart + i + ".dat");

			myreader.runConversion(106);
			lundMap.putAll(myreader.getLundMap());
			System.out.println("Done with " + outputDir + filePart + i + ".lund");
		}
		System.out.println("Done!");
		// System.out.println(Arrays.asList(lundMap)); // method 1
		// System.out.println(Collections.singletonList(lundMap));
		int eventNumber = 0;
		int lundPartNum = 1;
		OpenCloseLundFile openCloseLundFile = new OpenCloseLundFileImpl(outputDir + filePart + lundPartNum + ".lund");
		openCloseLundFile.openLundFile();
		for (Map.Entry<LundHeader, List<LundParticle>> entry : lundMap.entrySet()) {
			if (eventNumber == 100000) {
				eventNumber = 0;
				lundPartNum++;
				openCloseLundFile.closeLundFile();
				openCloseLundFile.openLundFile(outputDir + filePart + lundPartNum + ".lund");
			}
			openCloseLundFile.writeEvent(entry.getKey());
			// System.out.println(entry.getKey());// + " : " + entry.getValue());
			for (LundParticle lundParticle : entry.getValue()) {
				openCloseLundFile.writeEvent(lundParticle);

				// System.out.println(lundParticle);
			}
			openCloseLundFile.writeFlush();
			eventNumber++;
		}
		openCloseLundFile.closeLundFile();
		// for (LundHeader lh : headers) {
		// System.out.println(lh);
		// for (LundParticle lp2 : particles) {
		// System.out.println(lp2);
		// }
		// }
		System.exit(0);

	}

}
