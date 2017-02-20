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
package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.jlab.clas.pdg.PDGParticle;

public class ReadGiBuuOutput {

	private String inputFileName;
	private String outputLundName;

	private int nParticles = 0;
	private int nEntries = 0;
	private int partIndex = 0;
	private double partWeight;
	private double rotationAngle;
	private List<String> allLines;
	private List<List<String>> chunks;

	private List<LundHeader> lundHeader;
	private List<LundParticle> lundParts;

	private FileWriter fw = null;
	private BufferedWriter bw = null;
	private PrintWriter out = null;

	public ReadGiBuuOutput(String inputFileName, String outputLundName) {
		this.inputFileName = inputFileName;
		this.outputLundName = outputLundName;
		allLines = new ArrayList<String>();
		chunks = new ArrayList<List<String>>();
		lundHeader = new ArrayList<LundHeader>();
		lundParts = new ArrayList<LundParticle>();
	}

	public void runConversion() {
		openLundFile();
		readFile();
		setNEntries();
		closeLundFile();
	}

	private void readFile() {
		try {
			allLines = Files.readAllLines(new File(inputFileName).toPath(), Charset.defaultCharset());
			chunks = getChunks(allLines);
			for (int j = 0; j < chunks.size(); j++) {
				List<String> strList = chunks.get(j);
				setNParticles(strList);
				seperateList(strList);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static List<List<String>> getChunks(List<String> allLines) {
		List<List<String>> result = new ArrayList<List<String>>();
		int i = 0;
		int fromIndex = 0;
		int toIndex = 0;
		for (String line : allLines) {
			i++;
			if (line.startsWith("</event>")) {
				toIndex = i - 1;
				result.add(allLines.subList(fromIndex, toIndex));
				fromIndex = i;
			}
		}
		return result;
	}

	public void seperateList(List<String> strList) {
		lundHeader.clear();
		lundParts.clear();
		for (String string : strList) {
			List<String> partList = new ArrayList<String>();
			partList.add(string);
			if (string.contains("<event>") || string.contains("#")) {
				if (string.contains("<event>")) {
					partIndex = 0;
					partWeight = Double.parseDouble(string.split("\\s+")[1]);
				}
				if (string.contains("#")) {
					createLundHeader(partList);
				}
				continue;
			} else if (string.isEmpty()) {
				continue;
			} else {
				createLundParts(partList);
			}
		}
		createLundFile();
	}

	private void createLundHeader(List<String> list) {
		for (String string : list) {
			String[] splited = string.split("\\s+");
			addHeader(getNParticles(), 1, 1, 0.0, 0.0, 0.0, 0.0, Double.parseDouble(splited[3]),
					Double.parseDouble(splited[2]));
			rotationAngle = Double.parseDouble(splited[6]);
		}
	}

	private void createLundParts(List<String> list) {
		partIndex++;
		for (String string : list) {
			String[] splited = string.trim().split("\\s+");
			PDGParticle pdgParticle = GiBUUDatabase.getParticleById(Integer.parseInt(splited[0]),
					Integer.parseInt(splited[1]));
			addParticle(partIndex, pdgParticle.charge(), 1, pdgParticle.pid(), Integer.parseInt(splited[6]), 0,
					Double.parseDouble(splited[3]), Double.parseDouble(splited[4]), Double.parseDouble(splited[5]),
					Double.parseDouble(splited[2]), pdgParticle.mass(), 0.0, 0.0, 0.0);
		}
	}

	public void addParticle(int index, int charge, int type, int pid, int parentIndex, int daughterIndex, double px,
			double py, double pz, double energy, double mass, double vx, double vy, double vz) {
		lundParts.add(new LundParticle(index, charge, type, pid, parentIndex, daughterIndex, px, py, pz, energy, mass,
				vx, vy, vz));
	}

	public void addHeader(int numParticles, int numTargetNuc, int numTargetProt, double targetPol, double beamPol,
			double x, double y, double q2, double nu) {
		double W = Math.sqrt(-q2 + Math.pow(0.938272, 2) + 2.0 * 0.938272 * nu);
		lundHeader.add(new LundHeader(numParticles, numTargetNuc, numTargetProt, targetPol, beamPol, x, partWeight, W,
				q2, nu));
	}

	private void openLundFile() {
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

	private void closeLundFile() {

		out.close();
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createLundFile() {
		for (LundHeader lh : lundHeader) {
			out.write(lh + "\n");
			for (LundParticle lp : lundParts) {
				LundParticle lnew = lp.rotateY(-rotationAngle);
				out.write(lnew + "\n");
			}
			out.flush();
		}
	}

	public int getNEntries() {
		return nEntries;
	}

	public int getNParticles() {
		return nParticles;
	}

	private void setNEntries() {
		this.nEntries = chunks.size();
	}

	private void setNParticles(List<String> list) {
		this.nParticles = list.size() - 2;
	}

	// public static void main(String[] args) {
	// ReadGiBuuOutput myreader = new ReadGiBuuOutput("test.dat", "my.lund");
	// myreader.runConversion();
	// }
}
