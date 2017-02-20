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

import java.io.Serializable;

public class LundHeader implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numParticles;
	private int numTargetNuc;
	private int numTargetProt;
	private double targetPol;
	private double beamPol;
	private double x;
	private double y;
	private double W;
	private double q2;
	private double nu;

	public LundHeader(int numParticles, int numTargetNuc, int numTargetProt, double targetPol, double beamPol, double x,
			double y, double W, double q2, double nu) {
		this.numParticles = numParticles;
		this.numTargetNuc = numTargetNuc;
		this.numTargetProt = numTargetProt;
		this.targetPol = targetPol;
		this.beamPol = beamPol;
		this.x = x;
		this.y = y;
		this.W = W;
		this.q2 = q2;
		this.nu = nu;
	}

	public int getNumParticles() {
		return numParticles;
	}

	public void setNumParticles(int numParticles) {
		this.numParticles = numParticles;
	}

	public int getNumTargetNuc() {
		return numTargetNuc;
	}

	public void setNumTargetNuc(int numTargetNuc) {
		this.numTargetNuc = numTargetNuc;
	}

	public int getNumTargetProt() {
		return numTargetProt;
	}

	public void setNumTargetProt(int numTargetProt) {
		this.numTargetProt = numTargetProt;
	}

	public double getTargetPol() {
		return targetPol;
	}

	public void setTargetPol(double targetPol) {
		this.targetPol = targetPol;
	}

	public double getBeamPol() {
		return beamPol;
	}

	public void setBeamPol(double beamPol) {
		this.beamPol = beamPol;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getW() {
		return W;
	}

	public void setW(double w) {
		W = w;
	}

	public double getQ2() {
		return q2;
	}

	public void setQ2(double q2) {
		this.q2 = q2;
	}

	public double getNu() {
		return nu;
	}

	public void setNu(double nu) {
		this.nu = nu;
	}

	@Override
	public String toString() {
		return numParticles + " " + numTargetNuc + " " + numTargetProt + " " + targetPol + " " + beamPol + " " + x + " "
				+ y + " " + W + " " + q2 + " " + nu;
	}

}
