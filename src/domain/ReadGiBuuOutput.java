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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jlab.clas.pdg.PDGParticle;

public class ReadGiBuuOutput {

	private String inputFileName;

	private int nParticles = 0;
	private int partIndex = 0;
	private double partWeight;
	private double rotationAngle;
	private List<String> allLines;
	private List<List<String>> chunks;

	private List<LundHeader> lundHeader;
	private List<LundParticle> lundParts;

	private Map<LundHeader, List<LundParticle>> lundMap;

	private int setmotherPID;
	private boolean skimFile = false;

	public ReadGiBuuOutput(String inputFileName) {
		this.inputFileName = inputFileName;
		allLines = new ArrayList<String>();
		chunks = new ArrayList<List<String>>();
		lundHeader = new ArrayList<LundHeader>();
		lundParts = new ArrayList<LundParticle>();
	}

	public void runConversion() {
		readFile();
	}

	public void runConversion(int motherPID) {
		this.setmotherPID = motherPID;
		this.skimFile = true;
		this.lundMap = new LinkedHashMap<>();
		readFile();
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

	private void seperateList(List<String> strList) {
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
			addHeader(getNParticles(), 1, 1, 0.0, 0.0, 0.0, 0.0, Double.parseDouble(splited[3]), Double.parseDouble(splited[2]));
			rotationAngle = Double.parseDouble(splited[6]);
		}
	}

	private void createLundParts(List<String> list) {
		partIndex++;
		for (String string : list) {
			String[] splited = string.trim().split("\\s+");
			PDGParticle pdgParticle = GiBUUDatabase.getParticleById(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
			addParticle(partIndex, pdgParticle.charge(), 1, pdgParticle.pid(), Integer.parseInt(splited[6]), 0,
			        Double.parseDouble(splited[3]), Double.parseDouble(splited[4]), Double.parseDouble(splited[5]),
			        Double.parseDouble(splited[2]), pdgParticle.mass(), 0.0, 0.0, 0.0);
		}
	}

	private void addParticle(int index, int charge, int type, int pid, int parentIndex, int daughterIndex, double px, double py, double pz, double energy, double mass, double vx, double vy, double vz) {
		lundParts.add(new LundParticle(index, charge, type, pid, parentIndex, daughterIndex, px, py, pz, energy, mass, vx, vy, vz));
	}

	private void addHeader(int numParticles, int numTargetNuc, int numTargetProt, double targetPol, double beamPol, double x, double y, double q2, double nu) {
		double W = Math.sqrt(-q2 + Math.pow(0.938272, 2) + 2.0 * 0.938272 * nu);
		lundHeader.add(new LundHeader(numParticles, numTargetNuc, numTargetProt, targetPol, beamPol, x, partWeight, W, q2, nu));
	}

	private void createLundFile() {
		// rotate scattered lepton around z-axis angle 0<theta<2pi
		if (skimFile) {
			int gammaCount = 0;
			int electronCount = 0;
			int positronCount = 0;
			int protonCount = 0;
			int mothercount = 0;

			List<LundParticle> tempParticleList = new ArrayList<>();
			boolean printList = false;
			if (lundParts.size() <= 5) {

				// System.out.println("Number of lundparts" + lundParts.size() + " Number of particles " + this.nParticles);
				// System.out.println(lundParts);
				for (LundParticle lp : lundParts) {
					LundParticle lnew = lp.rotateY(-rotationAngle);
					tempParticleList.add(lnew);
					if (lnew.getPid() == -11) {
						positronCount++;
					}
					if (lnew.getPid() == 11) {
						electronCount++;
					}
					if (lnew.getPid() == 2212) {
						protonCount++;
					}
					if (lnew.getPid() == 22) {
						gammaCount++;
					}
					if (lnew.getParentIndex() == setmotherPID) {
						mothercount++;
					}

				}
				if (mothercount == 3 && protonCount == 1 && positronCount == 1 && electronCount == 2 && gammaCount > 0) {

					printList = true;
				}
				if (printList) {
					// System.out.println(tempParticleList.get(tempParticleList.size() - 1) + " last lund?");
					List<LundParticle> newList = rotateScatteredLepton(tempParticleList);
					// System.out.println(newList.get(newList.size() - 1) + " last lund rotated?");
					lundMap.put(lundHeader.get(0), newList);
					// System.out.println("lund to be printed " + lundHeader.size());
				}

			}
		} else {
			List<LundParticle> tempParticleList = new ArrayList<>();
			for (LundParticle lp : lundParts) {
				LundParticle lnew = lp.rotateY(-rotationAngle);
				tempParticleList.add(lnew);
			}
			List<LundParticle> newList = rotateScatteredLepton(tempParticleList);
			lundMap.put(lundHeader.get(0), newList);
		}
	}

	private List<LundParticle> rotateScatteredLepton(List<LundParticle> aList) {
		List<LundParticle> newList = new ArrayList<>();
		newList.addAll(aList);
		Random rn = new Random();
		double newAngle = (2.0 * Math.PI) * rn.nextDouble();
		LundParticle lParticle = aList.get(aList.size() - 1);
		LundParticle lnew = lParticle.rotateZ(newAngle);

		newList.remove(aList.size() - 1);
		newList.add(aList.size() - 1, lnew);
		return newList;
	}

	private int getNParticles() {
		return nParticles;
	}

	private void setNParticles(List<String> list) {
		this.nParticles = list.size() - 2;
	}

	public Map<LundHeader, List<LundParticle>> getLundMap() {
		return this.lundMap;
	}

}
