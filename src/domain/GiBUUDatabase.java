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

import java.util.HashMap;

import org.jlab.clas.pdg.PDGParticle;

public class GiBUUDatabase {

	static final HashMap<GiBUUParticle, PDGParticle> gibuuDatabase = initDatabase();

	public GiBUUDatabase() {
	}

	private static HashMap<GiBUUParticle, PDGParticle> initDatabase() {

		HashMap<GiBUUParticle, PDGParticle> particleMap = new HashMap<GiBUUParticle, PDGParticle>();
		particleMap.put(new GiBUUParticle(901, -1), new PDGParticle("e-", 11, 3, 0.0005, -1));
		particleMap.put(new GiBUUParticle(-901, 1), new PDGParticle("e+", -11, 2, 0.0005, 1));
		particleMap.put(new GiBUUParticle(911, 0), new PDGParticle("nue", 12, 0, 0.320e-6, 0));
		particleMap.put(new GiBUUParticle(-911, 0), new PDGParticle("nue-", -12, 0, 0.320e-6, 0));
		particleMap.put(new GiBUUParticle(902, -1), new PDGParticle("mu-", 13, 6, 0.1056583715, -1));
		particleMap.put(new GiBUUParticle(902, 1), new PDGParticle("mu+", -13, 5, 0.1056583715, 1));
		particleMap.put(new GiBUUParticle(999, 0), new PDGParticle("gamma", 22, 1, 0.000, 0));

		particleMap.put(new GiBUUParticle(101, 0), new PDGParticle("pi0", 111, 7, 0.1349766, 0));
		particleMap.put(new GiBUUParticle(101, 1), new PDGParticle("pi+", 211, 8, 0.13957018, 1));
		particleMap.put(new GiBUUParticle(101, -1), new PDGParticle("pi-", -211, 9, 0.13957018, -1));

		particleMap.put(new GiBUUParticle(102, 0), new PDGParticle("eta", 221, 0.547853, 0));
		particleMap.put(new GiBUUParticle(103, 0), new PDGParticle("rho0", 113, 0.7754, 0));
		particleMap.put(new GiBUUParticle(103, 1), new PDGParticle("rho+", 213, 0.7754, 1));
		particleMap.put(new GiBUUParticle(103, -1), new PDGParticle("rho-", -213, 0.7754, -1));

		particleMap.put(new GiBUUParticle(104, 0), new PDGParticle("sigma", 9000221, 0.8, 0));

		particleMap.put(new GiBUUParticle(105, 0), new PDGParticle("omega", 223, 0.78265, 0));
		particleMap.put(new GiBUUParticle(106, 0), new PDGParticle("etap", 331, 0.95766, 0));
		particleMap.put(new GiBUUParticle(107, 0), new PDGParticle("phi", 333, 0.1019455, 0));
		particleMap.put(new GiBUUParticle(108, 0), new PDGParticle("etaC", 441, 2.9800, 0));

		particleMap.put(new GiBUUParticle(109, 0), new PDGParticle("JPsi", 443, 3.0969, 0));

		particleMap.put(new GiBUUParticle(110, 1), new PDGParticle("K+", 321, 11, 0.49367716, 1));
		particleMap.put(new GiBUUParticle(111, -1), new PDGParticle("K-", -321, 12, 0.49367716, -1));
		particleMap.put(new GiBUUParticle(110, 0), new PDGParticle("K0", 311, 10, 0.49761424, 0));
		// particleMap.put(new GiBUUParticle(311, 0), new PDGParticle("K0L",
		// 130, 10, 0.49761424, 0));
		particleMap.put(new GiBUUParticle(111, 0), new PDGParticle("K0S", 310, 0.49761424, 0));

		// particleMap.put(new GiBUUParticle(112, 0), new PDGParticle("KStar+",
		// 310, 10, 0.49761424, 0));
		// particleMap.put(new GiBUUParticle(112, 0), new PDGParticle("K0S",
		// 310, 10, 0.49761424, 0));
		// particleMap.put(new GiBUUParticle(113, 0), new PDGParticle("K0S",
		// 310, 10, 0.49761424, 0));
		// particleMap.put(new GiBUUParticle(113, 0), new PDGParticle("K0S",
		// 310, 10, 0.49761424, 0));

		particleMap.put(new GiBUUParticle(1, 1), new PDGParticle("p", 2212, 14, 0.938272046, 1));
		particleMap.put(new GiBUUParticle(-1, -1), new PDGParticle("p-", -2212, 15, 0.938272046, -1));
		// particleMap.put(new GiBUUParticle(1, -1), new PDGParticle("p-",
		// -2212, 15, 0.938272046, -1));// this
		// should
		// not
		// exist

		particleMap.put(new GiBUUParticle(1, 0), new PDGParticle("n", 2112, 13, 0.939565379, 0));
		particleMap.put(new GiBUUParticle(-1, 0), new PDGParticle("nbar", -2112, 13, 0.939565379, 0));
		particleMap.put(new GiBUUParticle(32, 0), new PDGParticle("Lambda0", 3122, 100, 1.115683, 0));
		particleMap.put(new GiBUUParticle(-32, 0), new PDGParticle("antiLambda0", -3122, 100, 1.115683, 0));

		particleMap.put(new GiBUUParticle(33, 0), new PDGParticle("Sigma0", 3212, 1.189, 0));
		particleMap.put(new GiBUUParticle(-33, 0), new PDGParticle("antiSigma0", -3212, 1.189, 0));

		particleMap.put(new GiBUUParticle(33, 1), new PDGParticle("Sigma+", 3222, 1.189, 1));
		particleMap.put(new GiBUUParticle(-33, -1), new PDGParticle("antiSigma+", -3222, 1.189, -1));

		particleMap.put(new GiBUUParticle(33, -1), new PDGParticle("Sigma-", 3112, 1.189, -1));
		particleMap.put(new GiBUUParticle(-33, 1), new PDGParticle("antiSigma-", -3112, 1.189, 1));

		particleMap.put(new GiBUUParticle(53, -1), new PDGParticle("Chi-", 3312, 1.315, -1));
		particleMap.put(new GiBUUParticle(-53, 1), new PDGParticle("antiChi-", -3312, 1.315, 1));
		particleMap.put(new GiBUUParticle(53, 0), new PDGParticle("Chi0", 3322, 1.315, 0));

		return particleMap;
	}

	static public PDGParticle getParticleById(int pid, int charge) {
		GiBUUParticle obj = new GiBUUParticle(pid, charge);
		if (gibuuDatabase.containsKey(obj) == true) {
			return gibuuDatabase.get(obj);
		}
		System.err.println("gibuuDatabase::Error -> there is no particle with pid " + pid + " or charge " + charge);
		return null;
	}
}
