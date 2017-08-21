package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

public class OpenCloseLundFileImpl implements OpenCloseLundFile {

	private String outputLundName;
	private FileWriter fw = null;
	private BufferedWriter bw = null;
	private PrintWriter out = null;

	public OpenCloseLundFileImpl(String outputLundName) {
		this.outputLundName = outputLundName;
	}

	public void openLundFile() {
		if (new File(outputLundName).exists()) {
			System.err.println("This Lund file already exists");
			System.err.println("Please delete it or rename the output file");
			System.exit(0);
		} else {
			try {
				fw = new FileWriter(outputLundName, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void openLundFile(String outputLundName) {
		if (new File(outputLundName).exists()) {
			System.err.println("This Lund file already exists");
			System.err.println("Please delete it or rename the output file");
			System.exit(0);
		} else {
			try {
				fw = new FileWriter(outputLundName, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeLundFile() {
		try {
			out.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeEvent(LundHeader lh) {
		out.write(lh + "\n");
	}

	public void writeEvent(LundParticle lp) {
		out.write(lp + "\n");
	}

	public void writeFlush() {
		out.flush();
	}

}
