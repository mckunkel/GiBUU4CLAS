/*  +__^_________,_________,_____,________^-.-------------------,
 *  | |||||||||   `--------'     |          |                   O
 *  `+-------------USMC----------^----------|___________________|
 *    `\_,---------,---------,--------------'
 *      / X MK X /'|       /'
 *     / X MK X /  `\    /'
 *    / X MK X /`-------'
 *   / X MK X /
 *  / X MK X /
 * (________(                @author m.c.kunkel
 *  `------'
*/
package clients;

import java.util.List;
import java.util.Map;

import domain.LundHeader;
import domain.LundParticle;
import domain.OpenCloseLundFile;
import domain.OpenCloseLundFileImpl;
import domain.ReadGiBuuOutput;

public class UseGiBuuToLund {

	public static void main(String[] args) {
		ReadGiBuuOutput myreader = new ReadGiBuuOutput(args[0]);
		myreader.runConversion(106);
		Map<LundHeader, List<LundParticle>> lundMap = myreader.getLundMap();
		String outputStr = null;

		if (args[1] instanceof String) {
			outputStr = args[1];

		} else {
			System.err.println("Second arguement must be a String");
			System.exit(1);
		}
		int eventNumber = 0;
		int lundPartNum = 1;

		outputStr.replaceAll(".lund", "_" + lundPartNum + ".lund");
		OpenCloseLundFile openCloseLundFile = new OpenCloseLundFileImpl(outputStr);

		for (Map.Entry<LundHeader, List<LundParticle>> entry : lundMap.entrySet()) {
			if (eventNumber == Integer.valueOf(args[3])) {
				eventNumber = 0;
				lundPartNum++;
				openCloseLundFile.closeLundFile();
				outputStr.replaceAll((lundPartNum - 1) + ".lund", lundPartNum - 1 + ".lund");
				openCloseLundFile.openLundFile(outputStr);
			}
			openCloseLundFile.writeEvent(entry.getKey());
			for (LundParticle lundParticle : entry.getValue()) {
				openCloseLundFile.writeEvent(lundParticle);
			}
			openCloseLundFile.writeFlush();
			eventNumber++;
		}
		openCloseLundFile.closeLundFile();
		System.exit(0);
	}

}
